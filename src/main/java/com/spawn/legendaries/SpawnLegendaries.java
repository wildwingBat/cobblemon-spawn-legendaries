package com.spawn.legendaries;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.text.Text;
import net.minecraft.text.MutableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.block.BlockState;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpawnLegendaries implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("spawn-legendaries");
    public static final Random RANDOM = new Random();
	public static final int SPAWN_RADIUS = 24;
	public static final int SPAWN_CHANCE = 35; 		   // 35%
	public static final int SHINY_CHANCE = 5; 		   // 5%
	public static final int BOOSTED_SHINY_CHANCE = 10; // 10%

	public static final double MIN_SPAWN_TIMER_MINUTES = 12.5;	// 12.5 minutes
	public static final double MAX_SPAWN_TIMER_MINUTES = 29.16;	// 29.16 minutes
	public static int MIN_SPAWN_TIMER_TICKS = (int) (MIN_SPAWN_TIMER_MINUTES * 60 * 20);
	public static int MAX_SPAWN_TIMER_TICKS = (int) (MAX_SPAWN_TIMER_MINUTES * 60 * 20);
	public static int SPAWN_TIMER = RANDOM.nextInt(MAX_SPAWN_TIMER_TICKS - MIN_SPAWN_TIMER_TICKS) + MIN_SPAWN_TIMER_TICKS;

	public boolean spawnConditionsInitialized = false;
    public int tickCount = 0;
	public boolean shinyWeatherBoost = false;

	public BlockPos spawnedOnBlock = null;
	public boolean spawnedAsShiny = false;

	private Commands commandsClass = new Commands(this);
	private SpawnConditions spawnConditionsClass = new SpawnConditions(this);

	@Override
	public void onInitialize() {
		LOGGER.info("Loading legendaries...");
		registerTickEvent();
		ServerLifecycleEvents.SERVER_STARTED.register(commandsClass::checkspawnsCommand);
		ServerLifecycleEvents.SERVER_STARTED.register(commandsClass::forcespawnCommand);
	}

	public void registerTickEvent() {
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			if (!spawnConditionsInitialized) {
				World overworld = server.getOverworld();
				World nether = server.getWorld(World.NETHER);
				World end = server.getWorld(World.END);
				
				spawnConditionsClass.initializeSpawnConditions(overworld, nether, end); // Pass the dimensions
				spawnConditionsInitialized = true; // Set the flag to true so it only runs once
			}
			tickCount++;
			if (tickCount >= SPAWN_TIMER) {
				tickCount = 0;
				if (RANDOM.nextInt(100) <= SPAWN_CHANCE)
					attemptLegendarySpawn(server);
				SPAWN_TIMER = RANDOM.nextInt(MAX_SPAWN_TIMER_TICKS - MIN_SPAWN_TIMER_TICKS) + MIN_SPAWN_TIMER_TICKS;
			}
		});
	}

	public void attemptLegendarySpawn(MinecraftServer server) {
		List<ServerPlayerEntity> players = server.getPlayerManager().getPlayerList();
		List<String> possibleSpawns;
		String selectedLegendary = "";
		if (!players.isEmpty()) {
			ServerPlayerEntity randomPlayer = getRandomPlayer(players);
			BlockPos playerPos = randomPlayer.getBlockPos();
			World world = randomPlayer.getEntityWorld();

			Biome playerBiome = world.getBiome(playerPos).value();

			// Get the biome name
			String biomeString = getBiomeName(world, playerBiome);

			possibleSpawns = findValidLegendary(randomPlayer);
			if (!possibleSpawns.isEmpty()) {
				// Randomly select one of the valid legendary names
				selectedLegendary = possibleSpawns.get(RANDOM.nextInt(possibleSpawns.size()));
				LOGGER.info("Found condition for: {}", selectedLegendary);
			} else {
				LOGGER.info("No valid legendary found for spawn condition.");
				selectedLegendary = "";
			}
			if (!(selectedLegendary == "")) { // Found a legendary
				LOGGER.info("Attempting to spawn {}...", selectedLegendary);
				// Check if player is within Y range
				boolean spawned = spawnOnSafeBlock(world, playerPos, selectedLegendary, randomPlayer);
				if(spawned == true) {
					int x = spawnedOnBlock.getX(); MutableText xPos = Text.literal("X: " + x).formatted(Formatting.AQUA);
					int y = spawnedOnBlock.getY(); MutableText yPos = Text.literal(" Y: " + y).formatted(Formatting.AQUA);
					int z = spawnedOnBlock.getZ(); MutableText zPos = Text.literal(" Z: " + z).formatted(Formatting.AQUA);
					MutableText biomeName = Text.literal(biomeString).formatted(Formatting.AQUA);

					MutableText newLine = Text.literal("\n");
					MutableText resetFormatting = Text.literal("\n\n").formatted(Formatting.RESET);
					MutableText announceSpawn = Text.literal("\n").append(Text.literal("LEGENDARY SPAWN").formatted(Formatting.GOLD,Formatting.BOLD,Formatting.UNDERLINE)).append(resetFormatting);
					MutableText legendaryText = Text.literal(selectedLegendary).formatted(Formatting.YELLOW);
					MutableText spawnLocation = Text.literal("\nSpawned at: ").formatted(Formatting.BLUE).append(xPos).append(yPos).append(zPos);
					MutableText spawnBiome = Text.literal("\nSpawned in: ").formatted(Formatting.BLUE).append(biomeName);

					if(spawnedAsShiny){
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
					spawnedAsShiny = false;
					spawnedOnBlock = null;		
				}
			}
		}
	}

	public boolean spawnOnSafeBlock(World world, BlockPos playerPos, String pokemonToSpawn, ServerPlayerEntity player) {
		pokemonToSpawn = pokemonToSpawn.toLowerCase();
		SpawnConditions.SpawnCondition spawnCondition = spawnConditionsClass.legendarySpawnConditions.get(pokemonToSpawn);
		int searchRange = SPAWN_RADIUS;
	
		// Iterate over the blocks in the X, Y, and Z search range
		for (int dx = -searchRange; dx <= searchRange; dx++) {
			for (int dy = -searchRange; dy <= searchRange; dy++) {
				for (int dz = -searchRange; dz <= searchRange; dz++) {
					// Calculate the X, Y, and Z position relative to the player
					int x = playerPos.getX() + dx;
					int y = playerPos.getY() + dy;
					int z = playerPos.getZ() + dz;
	
					// Start checking from the spawn condition minY to maxY
					for (int currentY = y; currentY <= spawnCondition.maxY; currentY++) {
						BlockPos currentPos = new BlockPos(x, currentY, z);
						// Check if there is solid ground below within minY to maxY range
						if (hasSolidGroundBelow(world, currentPos, spawnCondition.minY, spawnCondition.maxY, spawnCondition.inWater)) {
							// If there is solid ground below, check if the spawn position is air
							if (world.isAir(currentPos)) {
								// Optionally, check if there is enough space above for the Pokémon
								boolean hasSpaceAbove = true;
								if (spawnCondition.inCave) { // Only check for space above if inCave is true
									for (int i = 1; i <= 5; i++) {
										if (!world.isAir(currentPos.up(i))) {
											hasSpaceAbove = false;
											break;
										}
									}
								} else { // Check for skybox if inCave is false
									boolean canSeeSky = true;
									for (int i = currentY; i <= 320; i++) { // Check up to y=320
										if (!world.isAir(currentPos.up(i))) { //If not air above
											canSeeSky = false;
											break;
										}
									}
									if (!canSeeSky) {
										hasSpaceAbove = false;
									}
								}
								if (hasSpaceAbove) {
									// Adjust the spawning position to be one block above the solid ground
									BlockPos spawnPos = currentPos.up();
	
									// Ensure the spawning position is within the minY to maxY range
									if (spawnPos.getY() <= spawnCondition.maxY) {
										spawnedOnBlock = spawnPos;
										String spawnCommand = String.format("pokespawnat %d %d %d %s level=70", spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), pokemonToSpawn);
										String shinySpawnCommand = String.format("pokespawnat %d %d %d %s shiny level=70", spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), pokemonToSpawn);
										if (shinyWeatherBoost) {
											if (RANDOM.nextInt(100) <= BOOSTED_SHINY_CHANCE) { // 1/10 chance to be shiny (BOOSTED)
												runCommand(player, shinySpawnCommand);
												spawnedAsShiny = true;
											}
										} else if (RANDOM.nextInt(100) <= SHINY_CHANCE) { // 1/20 chance to be shiny (DEFAULT)
											runCommand(player, shinySpawnCommand);
											spawnedAsShiny = true;
										} else {
											runCommand(player, spawnCommand);
										}
	
										shinyWeatherBoost = false;
										return true; // Stop searching for a safe block once a Pokémon is spawned
									}
									return false;
								}
							}
						}
					}
				}
			}
		}
		LOGGER.info("Could not find a valid block. Not spawning {}.", pokemonToSpawn);
		return false;
	}	
	
	public List<String> findValidLegendary(ServerPlayerEntity player) {
		// Get the player's world and position
		World world = player.getEntityWorld();
		BlockPos pos = player.getBlockPos();
		
		// Get the biome where the player is located
		//Biome biome = world.getBiome(pos).value();
		Optional<RegistryKey<Biome>> optionalBiomeKey = world.getBiome(player.getBlockPos()).getKey();
		RegistryKey<Biome> biomeKey = null;
		if (optionalBiomeKey.isPresent()) {
			biomeKey = optionalBiomeKey.get();
		}
		
		SpawnConditions.TimeOfDay currentTimeOfDay = spawnConditionsClass.getTimeOfDay(world);
		SpawnConditions.Weather currentWeather = spawnConditionsClass.getWeather(world);
		
		List<String> possibleSpawns = new ArrayList<>();

		int playerYLevel = pos.getY();
		// Iterate through the map of legendary spawn conditions
		for (Map.Entry<String, SpawnConditions.SpawnCondition> entry : spawnConditionsClass.legendarySpawnConditions.entrySet()) {
			String legendaryName = entry.getKey();
			SpawnConditions.SpawnCondition spawnCondition = entry.getValue();
			
			// Check if the current environment matches the spawn condition for the legendary
			if (((spawnCondition.biomes != null && spawnCondition.biomes.contains(biomeKey)) || 
				 (spawnCondition.biome != null && spawnCondition.biome.equals(biomeKey))) &&
				 (spawnCondition.weather == null || spawnCondition.weather == currentWeather) &&
				 (spawnCondition.timeOfDay == null || spawnCondition.timeOfDay == currentTimeOfDay) &&
				 (playerYLevel >= spawnCondition.minY && playerYLevel <= spawnCondition.maxY)
				 ) {
				
				if(spawnCondition.weatherBoost != null && spawnCondition.weatherBoost == currentWeather)
					shinyWeatherBoost = true;
				
				possibleSpawns.add(legendaryName);
			}
		}

		return possibleSpawns;
	}
	
	// Method to check if there is solid ground below the specified position within the minY to maxY range
	public boolean hasSolidGroundBelow(World world, BlockPos pos, int minY, int maxY, boolean inWater) {
		for (int i = pos.getY() - 1; i >= minY; i--) {
			if (i <= maxY) { // Ensure the solid ground is within minY to maxY range
				BlockPos checkPos = new BlockPos(pos.getX(), i, pos.getZ());
				BlockState blockState = world.getBlockState(checkPos);
				if  (blockState.isSolidBlock(world, checkPos) || 
					(/*inWater && */!blockState.isSolidBlock(world, checkPos) && !world.isAir(checkPos))) {
					return true;
				}
			}
			else
				break; // Break loop if minY is exceeded
		}
		return false;
	}

	public static Registry<Biome> getBiomeRegistry(World world) {
		return world.getRegistryManager().get(RegistryKeys.BIOME);
	}

	public static Identifier getIdentifierForBiome(World world, Biome biome) {
		return getBiomeRegistry(world).getId(biome);
	}

	public static String getBiomeName(World world, Biome biome) {
		Identifier biomeId = getIdentifierForBiome(world, biome);
		return biomeId.getPath();
	}	

    public ServerPlayerEntity getRandomPlayer(List<ServerPlayerEntity> players) {
        return players.get(RANDOM.nextInt(players.size()));
    }

	public boolean isPlayerInEnd(World world) {
		return world.getRegistryKey().getValue().equals(new Identifier("minecraft:the_end"));
	}
	
	public boolean isPlayerInNether(World world) {
		return world.getRegistryKey().getValue().equals(new Identifier("minecraft:the_nether"));
	}

	public void runCommand(ServerPlayerEntity player, String command) {
		MinecraftServer server = player.getServer();
		//ServerCommandSource source = server.getCommandSource();
		//server.getCommandManager().executeWithPrefix(source, command);
		if (server != null) {
			server.getCommandManager().executeWithPrefix(player.getCommandSource().withLevel(4), command);
		}
	}

}