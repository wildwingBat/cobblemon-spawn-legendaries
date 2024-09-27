# Legendary Spawning Mod

**Spawn Legendaries** is a Minecraft mod designed as an add-on for the "Cobblemon" mod. It adds functionality to spawn legendary Pokémon, Ultra Beasts, and Paradox Pokémon in the game based on various conditions such as weather, time of day, and specific locations like caves, ocean, sky, etc. This was originally made to be used with the "Terralith" biome mod, but it also supports pure vanilla biomes. This mod functions very similarly to how Pixelmon handles spawning their legendaries, but has more features. Works in both single player and on multiplayer servers.

This uses Pokémon Go odds, where every legendary has a 1/20 chance of being shiny. When weather boosted, shiny odds become 1/10. More information about weather boosts below.

THIS MOD IS UNMAINTAINED. I MADE THIS FOR MY BROTHER, AND DO NOT PLAN ON ACTIVELY UPDATING IT. FEEL FREE TO FORK AND MAKE CHANGES!

Note: Cobblemon does not have a model for all legendaries yet! My brother used a datapack that contained all models; you will also need a similar datapack to see the legendaries in-game! I cannot provide that datapack to you.

## Features

- **Legendary Pokémon Spawning**: Adds spawn conditions for legendary Pokémon.
- **Ultra Beasts**: Includes spawn conditions for Ultra Beasts and Paradox Pokémon.
- **Paradox Pokémon**: No spawn conditions currently. Easy to add; I have them commented out.
- **Dynamic Conditions**: Spawning based on weather, time of day, and specific locations.
- **Multi-Dimensional**: Spawns different legendaries in the overworld, end, and nether.
- **Weather (Shiny) Boosts**: Boosts the shiny odds of the legendary depending on the weather. More information about this below.

## Installation

1. **Requirements**:
   - Minecraft Fabric version 1.20.1
   - Cobblemon version 1.4.1+

2. **Steps**:
   - Download and install Minecraft Fabric version 1.20.1.
   - Install Cobblemon version 1.4.1+.
   - Download the "Spawn Legendaries" mod from the GitHub repository.
   - Place the downloaded mod file into the `mods` folder of your Minecraft directory.

## Usage

- **Spawning Conditions**:
  - **Weather**: Pokémon spawn based on weather conditions (Sunny, Rainy, Thunderstorm). 
    - **Weather Boosts**: Has functionality to provide a shiny boost depending on the weather. For example Raikou, Zapdos, Thundurus, Zekrom (and a few others) have a shiny boost during thunderstorms, Moltres has a shiny boost during sunny weather, and suicune has a shiny boost during the rain. These were just basic examples, see `SpawnConditions.java` for the full list.  
  - **Time of Day**: Different Pokémon spawn at different times of the day (Dawn, Day, Exactly Noon, Afternoon, Dusk, Night, Exactly Midnight). 
  - **Locations**: Specific Pokémon spawn in caves, water, or specific biomes. Has support for minY and maxY spawn locations. For example, Rayquaza can spawn at minY 140 (very tall mountains) up to the maxY of 320 (the skybox). 

- **Commands**:
  - **`/checkspawn legendary`**: Checks the chances of all possible legendaries around every player on the server. Also provides the time until the next spawn, and the current time of the world (DAY, AFTERNOON, DUSK, NIGHT, etc).
  - **`/checkspawn here`**: Checks the chances of all possible legendaries around the user of the command. Also provides the time until the next spawn, and the current time of the world (DAY, AFTERNOON, DUSK, NIGHT, etc).
  - **`/legendaryspawn here`**: OP required. Silently triggers a legendary spawn event on the user of the command.
  - **`/legendaryspawn random`**: OP required. Triggers a legendary spawn event on a random player in the server. Makes an announcement. 

## Contributing

This mod is unmaintained and was created as a personal project. However, contributions are welcome. This mod may have bugs. Feel free to fork the repository and submit pull requests.

## Credits

- **Author**: wildwingBat
- **Last Updated**: May 1st, 2024
- **Special Thanks**: To my brother, for whom this mod was created.

## Contact

For any questions or issues, please open an issue on the GitHub repository.
