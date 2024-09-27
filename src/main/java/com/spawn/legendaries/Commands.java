package com.spawn.legendaries;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.text.Text;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;

import com.mojang.brigadier.CommandDispatcher;
import com.spawn.legendaries.SpawnConditions.TimeOfDay;

import static net.minecraft.server.command.CommandManager.*;

public class Commands {

    private final SpawnLegendaries spawnLegendaries;

    public Commands(SpawnLegendaries spawnLegendaries){
        this.spawnLegendaries = spawnLegendaries;
    }

    public void checkspawnsCommand(MinecraftServer server) {
        CommandDispatcher<ServerCommandSource> dispatcher = server.getCommandManager().getDispatcher();

        dispatcher.register(literal("checkspawn").requires(source -> source.hasPermissionLevel(0))
        .then(literal("legendary")
            .executes(context -> { // This is the new default behavior
                ServerCommandSource source = context.getSource();
                List<ServerPlayerEntity> onlinePlayers = server.getPlayerManager().getPlayerList();	
                
                if (!onlinePlayers.isEmpty()) {
                    Map<String, Integer> legendaryCounts = new HashMap<>();
                    int totalLegendaries = 0;

                    // Iterate through all online players
                    for (ServerPlayerEntity onlinePlayer : onlinePlayers) {
                        List<String> allPossibleLegendaries = spawnLegendaries.findValidLegendary(onlinePlayer);

                        // Increment count for each possible legendary
                        for (String legendary : allPossibleLegendaries) {
                            legendaryCounts.put(legendary, legendaryCounts.getOrDefault(legendary, 0) + 1);
                            totalLegendaries++;
                        }
                    }
                    MutableText prefix = Text.literal("Possible Legendaries: \n").formatted(Formatting.BLUE);
                    MutableText message = prefix;
                    if (!legendaryCounts.isEmpty()) {
                        // Calculate percentages
                        MutableText lP = Text.literal(" (").formatted(Formatting.BLUE);
                        MutableText p = Text.literal("%").formatted(Formatting.AQUA);
                        MutableText rP = Text.literal(")").formatted(Formatting.BLUE);
                        MutableText cm = Text.literal(", ").formatted(Formatting.WHITE);
                        Iterator<Map.Entry<String, Integer>> iterator = legendaryCounts.entrySet().iterator();
                        while(iterator.hasNext()) {
                            Map.Entry<String, Integer> entry = iterator.next();
                            String legendary = entry.getKey();
                            int count = entry.getValue();
                            double percentage = (double) count / totalLegendaries * 100;
                    
                            // Create a MutableText object for the percentage and format it
                            MutableText percentageText = Text.literal(String.format("%.2f", percentage)).formatted(Formatting.AQUA);
                    
                            // Append the formatted text to the message
                            message = message.append(Text.literal(legendary).formatted(Formatting.YELLOW))
                                            .append(lP)
                                            .append(percentageText)
                                            .append(p)
                                            .append(rP);
                    
                            // If there are more entries, append the comma separator
                            if (iterator.hasNext()) {
                                message = message.append(cm);
                            }
                        }
                    } else {
                        message = message.append(Text.literal("None").formatted(Formatting.RED));
                    }                                  
                    message = timeToSpawn().append(spawnChance()).append(currentTime(server)).append(message);
                    source.sendMessage(message);
                } else {
                    source.sendMessage(Text.literal("Not enough players online to spawn a legendary").formatted(Formatting.RED));
                }

                return 1; // Command was executed successfully
            })
            .then(literal("here")
                .executes(context -> {
                    ServerCommandSource source = context.getSource();
                    ServerPlayerEntity player = source.getPlayer();
                    if (player != null) {
                        List<String> allPossibleLegendaries = spawnLegendaries.findValidLegendary(player);
                        String result = String.join(", ", allPossibleLegendaries);

                        MutableText prefix = Text.literal("Possible Legendaries: ").formatted(Formatting.BLUE);
                        MutableText message = timeToSpawn().append(spawnChance());
                        if(allPossibleLegendaries.isEmpty())
                            message = prefix.append(Text.literal("None").formatted(Formatting.RED));
                        else
                            message = prefix.append(Text.literal(result).formatted(Formatting.YELLOW));

                        message = timeToSpawn().append(spawnChance()).append(currentTime(server)).append(message);
                        player.sendMessage(message, false);
                    } else {
                        source.sendError(Text.literal("Only players can execute this command."));
                    }
                    return 1; // Command was executed successfully
                    })
                )
            )
        );
    }


	public void forcespawnCommand(MinecraftServer server) {
		CommandDispatcher<ServerCommandSource> dispatcher = server.getCommandManager().getDispatcher();
	
		dispatcher.register(literal("legendaryspawn").requires(source -> source.hasPermissionLevel(2))
				.then(literal("here")
						.executes(context -> {
							ServerCommandSource source = context.getSource();
							ServerPlayerEntity player = source.getPlayer();
	
							if (player != null) {
								World world = player.getEntityWorld();
								BlockPos pos = player.getBlockPos();
	
								// Find valid legendary spawns at the player's location
								List<String> possibleSpawns = spawnLegendaries.findValidLegendary(player);
	
								// If there are valid spawns, randomly select one and spawn it
								if (!possibleSpawns.isEmpty()) {
									String selectedLegendary = possibleSpawns.get(SpawnLegendaries.RANDOM.nextInt(possibleSpawns.size()));
									SpawnLegendaries.LOGGER.info("Spawning {} at player's location...", selectedLegendary);
									boolean spawned = spawnLegendaries.spawnOnSafeBlock(world, pos, selectedLegendary, player);
                                    MutableText message = Text.literal("");
                                    if(spawned == true){
                                        MutableText legendaryText = Text.literal(selectedLegendary).formatted(Formatting.YELLOW);
                                        message = Text.literal("Successfully spawned ").formatted(Formatting.BLUE).append(legendaryText);
                                    }
                                    else {
                                        MutableText legendaryText = Text.literal(selectedLegendary).formatted(Formatting.YELLOW);
                                        message = Text.literal("Did not find a safe block to spawn ").formatted(Formatting.RED).append(legendaryText);
                                    }
                                    player.sendMessage(message, false);
                                    
								} else {
									MutableText message = Text.literal("No possible legendaries found!").formatted(Formatting.RED);
									player.sendMessage(message, false);
								}
							} else {
								source.sendError(Text.literal("Only players can execute this command."));
							}
	
							return 1; // Command was executed successfully
						})
				)
				.then(literal("random")
						.executes(context -> {
							ServerCommandSource source = context.getSource();
							List<ServerPlayerEntity> onlinePlayers = server.getPlayerManager().getPlayerList();	
							if (!onlinePlayers.isEmpty()) {
								ServerPlayerEntity randomPlayer = onlinePlayers.get(SpawnLegendaries.RANDOM.nextInt(onlinePlayers.size()));
								World world = randomPlayer.getEntityWorld();
								BlockPos pos = randomPlayer.getBlockPos();
	
								// Find valid legendary spawns at the randomly selected player's location
								List<String> possibleSpawns = spawnLegendaries.findValidLegendary(randomPlayer);
                                
								// If there are valid spawns, randomly select one and spawn it
								if (!possibleSpawns.isEmpty()) {
									String selectedLegendary = possibleSpawns.get(SpawnLegendaries.RANDOM.nextInt(possibleSpawns.size()));
									SpawnLegendaries.LOGGER.info("Manually spawning {} at a random player's location...", selectedLegendary);
									boolean spawned = spawnLegendaries.spawnOnSafeBlock(world, pos, selectedLegendary, randomPlayer);
                                    if(spawned == true) {
                                        Biome playerBiome = world.getBiome(pos).value();
                                        String biomeString = SpawnLegendaries.getBiomeName(world, playerBiome);
                                        int x = spawnLegendaries.spawnedOnBlock.getX(); MutableText xPos = Text.literal("X: " + x).formatted(Formatting.AQUA);
                                        int y = spawnLegendaries.spawnedOnBlock.getY(); MutableText yPos = Text.literal(" Y: " + y).formatted(Formatting.AQUA);
                                        int z = spawnLegendaries.spawnedOnBlock.getZ(); MutableText zPos = Text.literal(" Z: " + z).formatted(Formatting.AQUA);
                                        MutableText biomeName = Text.literal(biomeString).formatted(Formatting.AQUA);
                    
                                        MutableText newLine = Text.literal("\n");
                                        MutableText resetFormatting = Text.literal("\n\n").formatted(Formatting.RESET);
                                        MutableText announceSpawn = Text.literal("\n").append(Text.literal("LEGENDARY SPAWN").formatted(Formatting.GOLD,Formatting.BOLD,Formatting.UNDERLINE)).append(resetFormatting);
                                        MutableText legendaryText = Text.literal(selectedLegendary).formatted(Formatting.YELLOW);
                                        MutableText spawnLocation = Text.literal("\nSpawned at: ").formatted(Formatting.BLUE).append(xPos).append(yPos).append(zPos);
                                        MutableText spawnBiome = Text.literal("\nSpawned in: ").formatted(Formatting.BLUE).append(biomeName);
                    
                                        if(spawnLegendaries.spawnedAsShiny){
                                            MutableText shinyWord = Text.literal("SHINY!").formatted(Formatting.LIGHT_PURPLE).formatted(Formatting.BOLD);
                                            MutableText shinySpawnMessage = legendaryText.append(Text.literal(" has spawned and is ").formatted(Formatting.BLUE)).append(shinyWord);
                                            
                                            MutableText fullMessage = announceSpawn.append(shinySpawnMessage).append(spawnLocation).append(spawnBiome).append(newLine);
                    
                                            //Send to all players
                                            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                                                player.sendMessage(fullMessage, false);
                                                player.sendMessage(shinySpawnMessage, true);
                                            }
                                        }
                                        else {
                                            MutableText normalSpawnMessage = legendaryText.append(Text.literal(" has spawned!").formatted(Formatting.BLUE));
                    
                                            MutableText fullMessage = announceSpawn.append(normalSpawnMessage).append(spawnLocation).append(spawnBiome).append(newLine);
                                            
                                            // Send to all players
                                            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                                                player.sendMessage(fullMessage, false);
                                                player.sendMessage(normalSpawnMessage, true);
                                            }
                                            
                                        }
                                        spawnLegendaries.spawnedAsShiny = false;
                                        spawnLegendaries.spawnedOnBlock = null;		
                                    }
								} else {
									source.sendError(Text.literal("No possible legendaries found for random spawn!"));
								}
							} else {
								source.sendError(Text.literal("No online players available for random spawn!"));
							}
	
							return 1; // Command was executed successfully
						})
				)
		);
	}

    public MutableText timeToSpawn(){
        int ticksLeft = SpawnLegendaries.SPAWN_TIMER - spawnLegendaries.tickCount; // Total ticks until next spawn
        int minutesLeft = ticksLeft / (20 * 60); // 20 ticks/second * 60 seconds/minute
        int secondsLeft = (ticksLeft / 20) % 60; // Remaining seconds after minutes
        
        MutableText prefix = Text.literal("Time until next spawn attempt: ").formatted(Formatting.BLUE);
        MutableText message = prefix.append(Text.literal("" + minutesLeft + "min " + secondsLeft + "sec\n").formatted(Formatting.GREEN));
        return message;
    }

    public MutableText spawnChance(){
        MutableText prefix = Text.literal("Spawn chance: ").formatted(Formatting.BLUE);
        MutableText message = prefix.append(Text.literal(SpawnLegendaries.SPAWN_CHANCE + "%\n").formatted(Formatting.GOLD));
        return message;
    }

    public MutableText currentTime(MinecraftServer server) {
        ServerWorld world = server.getWorld(World.OVERWORLD); 
        long time = world.getTimeOfDay() % 24000; // This gives a value from 0 to 23999

        MutableText prefix = Text.literal("Current time: ").formatted(Formatting.BLUE);
        MutableText timeText = Text.literal("");

        if(time == 6000) timeText = Text.literal(TimeOfDay.NOON.name() + "\n").formatted(Formatting.GOLD);
        else if(time >= 0 && time < 9000) timeText = Text.literal(TimeOfDay.DAY.name() + "\n").formatted(Formatting.GOLD);
        else if(time >= 9000 && time < 12000) timeText = Text.literal(TimeOfDay.AFTERNOON.name() + "\n").formatted(Formatting.GOLD);
        else if(time >= 12000 && time < 13000) timeText = Text.literal(TimeOfDay.DUSK.name() + "\n").formatted(Formatting.GOLD);
        else if(time == 18000) timeText = Text.literal(TimeOfDay.MIDNIGHT.name() + "\n").formatted(Formatting.GOLD);
        else if(time >= 13000 && time < 23000) timeText = Text.literal(TimeOfDay.NIGHT.name() + "\n").formatted(Formatting.GOLD);
        else {
            timeText = Text.literal(TimeOfDay.DAWN.name() + "\n").formatted(Formatting.GOLD);
        }
        timeText = prefix.append(timeText);

        return timeText;
    }

}
