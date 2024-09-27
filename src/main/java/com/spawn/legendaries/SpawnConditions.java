package com.spawn.legendaries;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class SpawnConditions {

    //private final SpawnLegendaries sL;

    public Map<String, SpawnCondition> legendarySpawnConditions;

    public SpawnConditions(SpawnLegendaries spawnLegendaries){
        //this.sL = spawnLegendaries;
    }

    public enum TimeOfDay {
		DAWN,
		DAY,
		NOON,
		AFTERNOON,
		DUSK,
		NIGHT,
		MIDNIGHT,
		INVALID
	}
	
	public enum Weather {
		SUNNY,
		RAINY,
		THUNDERSTORM
	}

    public TimeOfDay getTimeOfDay(World world) {
		long time = world.getTimeOfDay() % 24000; // This gives a value from 0 to 23999
		if(time == 18000) return TimeOfDay.MIDNIGHT;
		if(time == 6000) return TimeOfDay.NOON;
		else if(time >= 0 && time < 9000) return TimeOfDay.DAY;
		else if(time >= 9000 && time < 12000) return TimeOfDay.AFTERNOON;
		else if(time >= 12000 && time < 13000) return TimeOfDay.DUSK;
		else if(time >= 13000 && time < 23000) return TimeOfDay.NIGHT;
		else if(time >= 23000 && time < 0) return TimeOfDay.DAWN;
		else{
			//SpawnLegendaries.LOGGER.info("No valid spawn condition found for any legendary");
			return TimeOfDay.DAWN;
		} 
	}
	
	public Weather getWeather(World world) {
		if (world.isThundering()) {
			return Weather.THUNDERSTORM;
		} else if (world.isRaining()) {
			return Weather.RAINY;
		} else {
			return Weather.SUNNY;
		}
	}

    // Define a class to hold spawn conditions
    public class SpawnCondition {
        public List<RegistryKey<Biome>> biomes = null;
        public RegistryKey<Biome> biome = null;
        public int minY = -127;
        public int maxY = -127;
        public TimeOfDay timeOfDay = null;
        public Weather weather = null; //mandatory for spawn; leave null if don't need
        public boolean inCave = false;
        public boolean inWater = false;
        public Weather weatherBoost = null;
    }

    public void initializeSpawnConditions(World overworld, World nether, World end) {
		// Create a map of legendary Pokémon to their spawn conditions
		legendarySpawnConditions = new HashMap<>();

		// LEGENDARY POKEMON
		SpawnCondition mewtwoCondition = new SpawnCondition();
		SpawnCondition lugiaCondition = new SpawnCondition();
		SpawnCondition hoohCondition = new SpawnCondition();
		SpawnCondition kyogreCondition = new SpawnCondition();
		SpawnCondition groudonCondition = new SpawnCondition();
		SpawnCondition rayquazaCondition = new SpawnCondition();
		SpawnCondition dialgaCondition = new SpawnCondition();
		SpawnCondition palkiaCondition = new SpawnCondition();
		SpawnCondition giratinaCondition = new SpawnCondition();
		SpawnCondition reshiramCondition = new SpawnCondition();
		SpawnCondition zekromCondition = new SpawnCondition();
		SpawnCondition kyuremCondition = new SpawnCondition();
		SpawnCondition xerneasCondition = new SpawnCondition();
		SpawnCondition yveltalCondition = new SpawnCondition();
		SpawnCondition zygardeCondition = new SpawnCondition();
		SpawnCondition cosmogCondition = new SpawnCondition();
		SpawnCondition necrozmaCondition = new SpawnCondition();
		SpawnCondition zacianCondition = new SpawnCondition();
		SpawnCondition zamazentaCondition = new SpawnCondition();
		SpawnCondition eternatusCondition = new SpawnCondition();
		SpawnCondition calyrexCondition = new SpawnCondition();
		SpawnCondition koraidonCondition = new SpawnCondition();
		SpawnCondition miraidonCondition = new SpawnCondition();

		// MYTHICAL POKEMON
		SpawnCondition mewCondition = new SpawnCondition();
		SpawnCondition celebiCondition = new SpawnCondition();
		SpawnCondition jirachiCondition = new SpawnCondition();
		SpawnCondition deoxysCondition = new SpawnCondition();
		SpawnCondition phioneCondition = new SpawnCondition();
		SpawnCondition manaphyCondition = new SpawnCondition();
		SpawnCondition darkraiCondition = new SpawnCondition();
		SpawnCondition shayminCondition = new SpawnCondition();
		SpawnCondition arceusCondition = new SpawnCondition();
		SpawnCondition victiniCondition = new SpawnCondition();
		SpawnCondition keldeoCondition = new SpawnCondition();
		SpawnCondition meloettaCondition = new SpawnCondition();
		SpawnCondition genesectCondition = new SpawnCondition();
		SpawnCondition diancieCondition = new SpawnCondition();
		SpawnCondition hoopaCondition = new SpawnCondition();
		SpawnCondition volcanionCondition = new SpawnCondition();
		SpawnCondition marshadowCondition = new SpawnCondition();
		SpawnCondition magearnaCondition = new SpawnCondition();
		SpawnCondition zeraoraCondition = new SpawnCondition();
		SpawnCondition meltanCondition = new SpawnCondition();
		SpawnCondition melmetalCondition = new SpawnCondition();
		SpawnCondition zarudeCondition = new SpawnCondition();

		// SUB-LEGENDARY POKEMON
		SpawnCondition articunoCondition = new SpawnCondition();
		SpawnCondition moltresCondition = new SpawnCondition();
		SpawnCondition zapdosCondition = new SpawnCondition();
		SpawnCondition raikouCondition = new SpawnCondition();
		SpawnCondition enteiCondition = new SpawnCondition();
		SpawnCondition suicuneCondition = new SpawnCondition();
		SpawnCondition regirockCondition = new SpawnCondition();
		SpawnCondition regiceCondition = new SpawnCondition();
		SpawnCondition registeelCondition = new SpawnCondition();
		SpawnCondition regigigasCondition = new SpawnCondition();
		SpawnCondition regidragoCondition = new SpawnCondition();
		SpawnCondition regielekiCondition = new SpawnCondition();
		SpawnCondition latiasCondition = new SpawnCondition();
		SpawnCondition latiosCondition = new SpawnCondition();
		SpawnCondition uxieCondition = new SpawnCondition();
		SpawnCondition mespritCondition = new SpawnCondition();
		SpawnCondition azelfCondition = new SpawnCondition();
		SpawnCondition heatranCondition = new SpawnCondition();
		SpawnCondition cresseliaCondition = new SpawnCondition();
		SpawnCondition cobalionCondition = new SpawnCondition();
		SpawnCondition terrakionCondition = new SpawnCondition();
		SpawnCondition virizionCondition = new SpawnCondition();
		SpawnCondition tornadusCondition = new SpawnCondition();
		SpawnCondition thundurusCondition = new SpawnCondition();
		SpawnCondition landorusCondition = new SpawnCondition();
		SpawnCondition typenullCondition = new SpawnCondition();
		SpawnCondition tapukokoCondition = new SpawnCondition();
		SpawnCondition tapuleleCondition = new SpawnCondition();
		SpawnCondition tapubuluCondition = new SpawnCondition();
		SpawnCondition tapufiniCondition = new SpawnCondition();
		SpawnCondition kubfuCondition = new SpawnCondition();
		SpawnCondition urshifuCondition = new SpawnCondition();
		SpawnCondition glastrierCondition = new SpawnCondition();
		SpawnCondition spectrierCondition = new SpawnCondition();
		SpawnCondition enamorusCondition = new SpawnCondition();
		SpawnCondition tingluCondition = new SpawnCondition();
		SpawnCondition chienpaoCondition = new SpawnCondition();
		SpawnCondition wochienCondition = new SpawnCondition();
		SpawnCondition chiyuCondition = new SpawnCondition();
		SpawnCondition ogerponCondition = new SpawnCondition();
		SpawnCondition munkidoriCondition = new SpawnCondition();

		// ULTRA BEASTS
		SpawnCondition buzzwoleCondition = new SpawnCondition();
		SpawnCondition pheromosaCondition = new SpawnCondition();
		SpawnCondition xurkitreeCondition = new SpawnCondition();
		SpawnCondition kartanaCondition = new SpawnCondition();
		SpawnCondition guzzlordCondition = new SpawnCondition();
		SpawnCondition poipoleCondition = new SpawnCondition();
		SpawnCondition naganadelCondition = new SpawnCondition();
		SpawnCondition stakatakaCondition = new SpawnCondition();
		SpawnCondition blacephalonCondition = new SpawnCondition();
		SpawnCondition celesteelaCondition = new SpawnCondition();

		/*// PARADOX POKEMON
		SpawnCondition greattuskCondition = new SpawnCondition();
		SpawnCondition screamtailCondition = new SpawnCondition();
		SpawnCondition brutebonnetCondition = new SpawnCondition();
		SpawnCondition fluttermaneCondition = new SpawnCondition();
		SpawnCondition slitherwingCondition = new SpawnCondition();
		SpawnCondition sandyshocksCondition = new SpawnCondition();
		SpawnCondition irontreadsCondition = new SpawnCondition();
		SpawnCondition ironbundleCondition = new SpawnCondition();
		SpawnCondition ironhandsCondition = new SpawnCondition();
		SpawnCondition ironmothCondition = new SpawnCondition();
		SpawnCondition ironthornsCondition = new SpawnCondition();
		SpawnCondition roaringmoonCondition = new SpawnCondition();
		SpawnCondition ironvaliantCondition = new SpawnCondition();
		SpawnCondition walkingwakeCondition = new SpawnCondition();
		SpawnCondition ironleavesCondition = new SpawnCondition();*/

		
		// +---------------------------------------+
		//                  MEWTWO                  
		// +---------------------------------------+
		// Caves during the afternoons

		Arrays.asList(
			BiomeKeys.LUSH_CAVES,
			BiomeKeys.DRIPSTONE_CAVES,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cave/andesite_caves")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cave/crystal_caves")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cave/deep_caves")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cave/desert_caves")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cave/frostfire_caves")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cave/fungal_caves")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cave/granite_caves")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cave/ice_caves")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cave/infested_caves")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cave/mantle_caves")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cave/thermal_caves")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cave/tuff_caves")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cave/underground_jungle"))
		);
		mewtwoCondition.maxY = 256; //Caves generate at any altitude up to Y-level 256
		mewtwoCondition.minY = -59; //and may span from the surface all the way to Y-level -59
		mewtwoCondition.timeOfDay = TimeOfDay.AFTERNOON;
		mewtwoCondition.weather = null;
		mewtwoCondition.inCave = true;
		legendarySpawnConditions.put("Mewtwo", mewtwoCondition);

		// +---------------------------------------+
		//                 LUGIA                                     
		// +---------------------------------------+
		lugiaCondition.biomes = Arrays.asList(
			BiomeKeys.OCEAN,
			BiomeKeys.DEEP_OCEAN,
			BiomeKeys.WARM_OCEAN,
			BiomeKeys.LUKEWARM_OCEAN,
			BiomeKeys.DEEP_LUKEWARM_OCEAN,
			BiomeKeys.COLD_OCEAN,
			BiomeKeys.DEEP_COLD_OCEAN,
			BiomeKeys.FROZEN_OCEAN,
			BiomeKeys.DEEP_FROZEN_OCEAN
		);
		lugiaCondition.maxY = 100;
		lugiaCondition.minY = 0; // sea level - 10 to allow for swimming
		lugiaCondition.timeOfDay = TimeOfDay.DAY;
		lugiaCondition.weather = Weather.RAINY;
		lugiaCondition.inCave = false;
		lugiaCondition.inWater = true;
		legendarySpawnConditions.put("Lugia", lugiaCondition);

		// +---------------------------------------+
		//                 HO-OH                                     
		// +---------------------------------------+
		hoohCondition.biomes = Arrays.asList(
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "skylands_autumn")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "skylands_summer"))
		);
		hoohCondition.maxY = 320; 
		hoohCondition.minY = 150;
		hoohCondition.timeOfDay = TimeOfDay.AFTERNOON;
		hoohCondition.weather = Weather.SUNNY;
		hoohCondition.weatherBoost = null;
		hoohCondition.inCave = false;
		hoohCondition.inWater = false;
		legendarySpawnConditions.put("HoOh", hoohCondition);


		// +---------------------------------------+
		//                 RAYQUAZA                
		// +---------------------------------------+
		// Peaks, Highlands, and Sky Islands during the day and sunny
		
		rayquazaCondition.biomes = Arrays.asList(
			BiomeKeys.FROZEN_PEAKS,
			BiomeKeys.JAGGED_PEAKS,
			BiomeKeys.STONY_PEAKS,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "emerald_peaks")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "volcanic_peaks"))
		);
		rayquazaCondition.maxY = 320;
		rayquazaCondition.minY = 140;
		rayquazaCondition.timeOfDay = TimeOfDay.AFTERNOON;
		rayquazaCondition.weather = Weather.SUNNY;
		rayquazaCondition.inCave = false;
		legendarySpawnConditions.put("Rayquaza", rayquazaCondition);

		// +---------------------------------------+
		//                  KYOGRE                 
		// +---------------------------------------+
		// Deep Oceans at night and during thunderstorms
		
		kyogreCondition.biomes = Arrays.asList(
			BiomeKeys.DEEP_OCEAN,
			BiomeKeys.DEEP_COLD_OCEAN,
			BiomeKeys.DEEP_LUKEWARM_OCEAN,
			BiomeKeys.DEEP_FROZEN_OCEAN
		);
		kyogreCondition.maxY = 80;
		kyogreCondition.minY = 0;  // sea level - 10 (to allow for swimming)
		kyogreCondition.timeOfDay = TimeOfDay.NIGHT;
		kyogreCondition.weather = Weather.RAINY;
		kyogreCondition.inCave = false;
		kyogreCondition.inWater = true;
		legendarySpawnConditions.put("Kyogre", kyogreCondition);

		// +---------------------------------------+
		//                 GROUDON                 
		// +---------------------------------------+
		// Deserts during the afternoons and sunny
		
		groudonCondition.biomes = Arrays.asList(
			BiomeKeys.DESERT,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "desert_canyon")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "desert_oasis")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "desert_spires")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "lush_desert")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "sandstone_valley"))
		);
		groudonCondition.maxY = 150;
		groudonCondition.minY = 0;
		groudonCondition.timeOfDay = TimeOfDay.AFTERNOON;
		groudonCondition.weather = Weather.SUNNY;
		groudonCondition.inCave = false;
		groudonCondition.inWater = false;
		legendarySpawnConditions.put("Groudon", groudonCondition);

		// +---------------------------------------+
		//                 DIALGA                                     
		// +---------------------------------------+
		dialgaCondition.biomes = Arrays.asList(
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "emerald_peaks"))
		);
		dialgaCondition.maxY = 255;
		dialgaCondition.minY = 65;
		dialgaCondition.timeOfDay = null;
		dialgaCondition.weather = null;
		dialgaCondition.inCave = false;
		dialgaCondition.inWater = false;
		legendarySpawnConditions.put("Dialga", dialgaCondition);

		// +---------------------------------------+
		//                 PALKIA                                     
		// +---------------------------------------+
		palkiaCondition.biomes = Arrays.asList(
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "moonlight_grove")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "moonlight_valley"))
		);
		palkiaCondition.maxY = 255;
		palkiaCondition.minY = 60;
		palkiaCondition.timeOfDay = null;
		palkiaCondition.weather = null;
		palkiaCondition.inCave = false;
		palkiaCondition.inWater = false;
		legendarySpawnConditions.put("Palkia", palkiaCondition);

		// +---------------------------------------+
		//                 GIRATINA                                 
		// +---------------------------------------+
		giratinaCondition.biomes = Arrays.asList(
			BiomeKeys.NETHER_WASTES,
			BiomeKeys.WARPED_FOREST,
			BiomeKeys.CRIMSON_FOREST,
			BiomeKeys.SOUL_SAND_VALLEY,
			BiomeKeys.BASALT_DELTAS
		);
		giratinaCondition.maxY = 255;
		giratinaCondition.minY = 60;
		giratinaCondition.timeOfDay = TimeOfDay.NIGHT;
		giratinaCondition.weather = null;
		giratinaCondition.inCave = false;
		giratinaCondition.inWater = false;
		legendarySpawnConditions.put("Giratina", giratinaCondition);

		// +---------------------------------------+
		//                 RESHIRAM                             
		// +---------------------------------------+
		reshiramCondition.biomes = Arrays.asList(
			BiomeKeys.OLD_GROWTH_PINE_TAIGA,
			BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA
		);
		reshiramCondition.maxY = 255;
		reshiramCondition.minY = 62;
		reshiramCondition.timeOfDay = TimeOfDay.DAY;
		reshiramCondition.weather = Weather.SUNNY;
		reshiramCondition.inCave = false;
		reshiramCondition.inWater = false;
		legendarySpawnConditions.put("Reshiram", reshiramCondition);

		// +---------------------------------------+
		//                 ZEKROM                                  
		// +---------------------------------------+
		zekromCondition.biomes = Arrays.asList(
			BiomeKeys.OLD_GROWTH_PINE_TAIGA,
			BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA
		);
		zekromCondition.maxY = 255;
		zekromCondition.minY = 62;
		zekromCondition.timeOfDay = TimeOfDay.NIGHT;
		zekromCondition.weather = null;
		zekromCondition.weatherBoost = Weather.THUNDERSTORM;
		zekromCondition.inCave = false;
		zekromCondition.inWater = false;
		legendarySpawnConditions.put("Zekrom", zekromCondition);

		// +---------------------------------------+
		//                 KYUREM                                  
		// +---------------------------------------+
		kyuremCondition.biomes = Arrays.asList(
			BiomeKeys.SNOWY_SLOPES,
			BiomeKeys.SNOWY_BEACH,
			BiomeKeys.ICE_SPIKES,
			BiomeKeys.SNOWY_PLAINS,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "frozen_cliffs")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "glacial_chasm")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "skylands_winter"))
		);
		kyuremCondition.maxY = 300;
		kyuremCondition.minY = 65;
		kyuremCondition.timeOfDay = TimeOfDay.NIGHT;
		kyuremCondition.weather = null;
		kyuremCondition.weatherBoost = Weather.RAINY;
		kyuremCondition.inCave = false;
		kyuremCondition.inWater = false;
		legendarySpawnConditions.put("Kyurem", kyuremCondition);

		// +---------------------------------------+
		//                 XERNEAS                                 
		// +---------------------------------------+
		xerneasCondition.biomes = Arrays.asList(
			BiomeKeys.DARK_FOREST,
			BiomeKeys.FLOWER_FOREST
		);
		xerneasCondition.maxY = 255;
		xerneasCondition.minY = 65;
		xerneasCondition.timeOfDay = TimeOfDay.DAY;
		xerneasCondition.weather = null;
		xerneasCondition.inCave = false;
		xerneasCondition.inWater = false;
		legendarySpawnConditions.put("Xerneas", xerneasCondition);

		// +---------------------------------------+
		//                 YVELTAL                                 
		// +---------------------------------------+
		yveltalCondition.biomes = Arrays.asList(
			BiomeKeys.TAIGA,
			BiomeKeys.DARK_FOREST
		);
		yveltalCondition.maxY = 256;
		yveltalCondition.minY = 65;
		yveltalCondition.timeOfDay = TimeOfDay.NIGHT;
		yveltalCondition.weather = null;
		yveltalCondition.inCave = false;
		yveltalCondition.inWater = false;
		legendarySpawnConditions.put("Yveltal", yveltalCondition);

		// +---------------------------------------+
		//                 ZYGARDE                                
		// +---------------------------------------+
		zygardeCondition.biomes = Arrays.asList(
			BiomeKeys.LUSH_CAVES
		);
		zygardeCondition.maxY = 120;
		zygardeCondition.minY = -60;
		zygardeCondition.timeOfDay = null;
		zygardeCondition.weather = null;
		zygardeCondition.inCave = true;
		zygardeCondition.inWater = false;
		legendarySpawnConditions.put("Zygarde", zygardeCondition);

		// +---------------------------------------+
		//                 COSMOG                                 
		// +---------------------------------------+
		cosmogCondition.biomes = Arrays.asList(
			BiomeKeys.FLOWER_FOREST,
			BiomeKeys.SUNFLOWER_PLAINS,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "sakura_valley")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "moonlight_valley")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "moonlight_grove")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cloud_forest"))
		);
		cosmogCondition.maxY = 255;
		cosmogCondition.minY = 65;
		cosmogCondition.timeOfDay = TimeOfDay.DAWN;
		cosmogCondition.weather = null;
		cosmogCondition.inCave = false;
		cosmogCondition.inWater = false;
		legendarySpawnConditions.put("Cosmog", cosmogCondition);

		// +---------------------------------------+
		//                 NECROZMA                            
		// +---------------------------------------+
		necrozmaCondition.biomes = Arrays.asList(
			BiomeKeys.DARK_FOREST
		);
		necrozmaCondition.maxY = 255;
		necrozmaCondition.minY = 62;
		necrozmaCondition.timeOfDay = TimeOfDay.NIGHT;
		necrozmaCondition.weather = null;
		necrozmaCondition.inCave = false;
		necrozmaCondition.inWater = false;
		legendarySpawnConditions.put("Necrozma", necrozmaCondition);

		// +---------------------------------------+
		//                 ZACIAN                                   
		// +---------------------------------------+
		zacianCondition.biomes = Arrays.asList(
			BiomeKeys.FOREST,
			BiomeKeys.BIRCH_FOREST,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "white_cliffs"))
		);
		zacianCondition.maxY = 255;
		zacianCondition.minY = 60;
		zacianCondition.timeOfDay = TimeOfDay.DAY;
		zacianCondition.weather = Weather.SUNNY;
		zacianCondition.inCave = false;
		zacianCondition.inWater = false;
		legendarySpawnConditions.put("Zacian", zacianCondition);

		// +---------------------------------------+
		//                 ZAMAZENTA                            
		// +---------------------------------------+
		zamazentaCondition.biomes = Arrays.asList(
			BiomeKeys.FOREST,
			BiomeKeys.BIRCH_FOREST,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "white_cliffs"))
		);
		zamazentaCondition.maxY = 255;
		zamazentaCondition.minY = 60;
		zamazentaCondition.timeOfDay = TimeOfDay.NIGHT;
		zamazentaCondition.weather = Weather.SUNNY;
		zamazentaCondition.inCave = false;
		zamazentaCondition.inWater = false;
		legendarySpawnConditions.put("Zamazenta", zamazentaCondition);

		// +---------------------------------------+
		//                 ETERNATUS                            
		// +---------------------------------------+
		eternatusCondition.biomes = Arrays.asList(
			BiomeKeys.SWAMP,
			BiomeKeys.MANGROVE_SWAMP,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "orchid_swamp"))
		);
		eternatusCondition.maxY = 255;
		eternatusCondition.minY = 62;
		eternatusCondition.timeOfDay = TimeOfDay.NIGHT;
		eternatusCondition.weather = Weather.RAINY;
		eternatusCondition.inCave = false;
		eternatusCondition.inWater = false;
		legendarySpawnConditions.put("Eternatus", eternatusCondition);

		// +---------------------------------------+
		//                 CALYREX                                 
		// +---------------------------------------+
		calyrexCondition.biomes = Arrays.asList(
			BiomeKeys.SNOWY_PLAINS,
			BiomeKeys.SNOWY_TAIGA,
			BiomeKeys.SNOWY_SLOPES,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "snowy_shield"))
		);
		calyrexCondition.maxY = 255;
		calyrexCondition.minY = 62;
		calyrexCondition.timeOfDay = null;
		calyrexCondition.weather = null;
		calyrexCondition.inCave = false;
		calyrexCondition.inWater = false;
		legendarySpawnConditions.put("Calyrex", calyrexCondition);

		// +---------------------------------------+
		//                 KORAIDON                               
		// +---------------------------------------+
		koraidonCondition.biomes = Arrays.asList(
			BiomeKeys.WINDSWEPT_HILLS,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "highlands")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "arid_highlands")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "alpine_highlands")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "forested_highlands")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "temperate_highlands"))
		);
		koraidonCondition.maxY = 255;
		koraidonCondition.minY = 60;
		koraidonCondition.timeOfDay = TimeOfDay.DAY;
		koraidonCondition.weather = Weather.SUNNY;
		koraidonCondition.inCave = false;
		koraidonCondition.inWater = false;
		legendarySpawnConditions.put("Koraidon", koraidonCondition);

		// +---------------------------------------+
		//                 MIRAIDON                               
		// +---------------------------------------+
		miraidonCondition.biomes = Arrays.asList(
			BiomeKeys.TAIGA,
			BiomeKeys.SNOWY_TAIGA,
			BiomeKeys.PLAINS,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "siberian_taiga"))
		);
		miraidonCondition.maxY = 255;
		miraidonCondition.minY = 60;
		miraidonCondition.timeOfDay = null;
		miraidonCondition.weather = Weather.SUNNY;
		miraidonCondition.inCave = false;
		miraidonCondition.inWater = false;
		legendarySpawnConditions.put("Miraidon", miraidonCondition);


		// MYTHICAL POKEMON


		// +---------------------------------------+
		//                 MEW                                        
		// +---------------------------------------+
		mewCondition.biomes = Arrays.asList(
			BiomeKeys.JUNGLE,
			BiomeKeys.SPARSE_JUNGLE,
			BiomeKeys.BAMBOO_JUNGLE,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "tropical_jungle")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "jungle_mountains")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "rocky_jungle")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "amethyst_rainforest"))
		);
		mewCondition.maxY = 255;
		mewCondition.minY = 60;
		mewCondition.timeOfDay = TimeOfDay.DAY;
		mewCondition.weather = Weather.SUNNY;
		mewCondition.inCave = false;
		mewCondition.inWater = false;
		legendarySpawnConditions.put("Mew", mewCondition);

		// +---------------------------------------+
		//                 CELEBI                                     
		// +---------------------------------------+
		celebiCondition.biomes = Arrays.asList(
			BiomeKeys.FLOWER_FOREST,
			BiomeKeys.MEADOW,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "blooming_plateau")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "blooming_valley")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "lavender_forest")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "lavender_valley")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "moonlight_grove")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "moonlight_valley")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "sakura_grove")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "sakura_valley"))
		);
		celebiCondition.maxY = 255;
		celebiCondition.minY = 30;
		celebiCondition.timeOfDay = TimeOfDay.DAWN;
		celebiCondition.weather = Weather.SUNNY;
		celebiCondition.inCave = false;
		celebiCondition.inWater = false;
		legendarySpawnConditions.put("Celebi", celebiCondition);

		// +---------------------------------------+
		//                 JIRACHI                                   
		// +---------------------------------------+
		jirachiCondition.biomes = Arrays.asList(
			BiomeKeys.WINDSWEPT_HILLS,
			BiomeKeys.WINDSWEPT_GRAVELLY_HILLS,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "granite_cliffs")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "granite_cliffs")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "white_cliffs"))


		);
		jirachiCondition.maxY = 255;
		jirachiCondition.minY = 0;
		jirachiCondition.timeOfDay = TimeOfDay.DUSK;
		jirachiCondition.weather = null;
		jirachiCondition.inCave = false;
		jirachiCondition.inWater = false;
		legendarySpawnConditions.put("Jirachi", jirachiCondition);

		// +---------------------------------------+
		//                 DEOXYS                                  
		// +---------------------------------------+
		deoxysCondition.biomes = Arrays.asList(
			BiomeKeys.FROZEN_PEAKS,
			BiomeKeys.JAGGED_PEAKS,
			BiomeKeys.STONY_PEAKS,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "emerald_peaks")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "volcanic_peaks"))
		);
		deoxysCondition.maxY = 320;
		deoxysCondition.minY = 140;
		deoxysCondition.timeOfDay = TimeOfDay.DUSK;
		deoxysCondition.weather = null;
		deoxysCondition.inCave = false;
		deoxysCondition.inWater = false;
		legendarySpawnConditions.put("Deoxys", deoxysCondition);

		// +---------------------------------------+
		//                 PHIONE                                   
		// +---------------------------------------+
		phioneCondition.biomes = Arrays.asList(
			BiomeKeys.WARM_OCEAN,
			BiomeKeys.LUKEWARM_OCEAN,
			BiomeKeys.DEEP_LUKEWARM_OCEAN
		);
		phioneCondition.maxY = 64;
		phioneCondition.minY = 0;
		phioneCondition.timeOfDay = TimeOfDay.NIGHT;
		phioneCondition.weather = null;
		phioneCondition.inCave = false;
		phioneCondition.inWater = true;
		phioneCondition.weatherBoost = Weather.RAINY;
		legendarySpawnConditions.put("Phione", phioneCondition);

		// +---------------------------------------+
		//                 MANAPHY                                
		// +---------------------------------------+
		manaphyCondition.biomes = Arrays.asList(
			BiomeKeys.OCEAN,
			BiomeKeys.DEEP_OCEAN,
			BiomeKeys.DEEP_COLD_OCEAN,
			BiomeKeys.COLD_OCEAN
		);
		manaphyCondition.maxY = 64;
		manaphyCondition.minY = 0;
		manaphyCondition.timeOfDay = TimeOfDay.AFTERNOON;
		manaphyCondition.weather = null;
		manaphyCondition.inCave = false;
		manaphyCondition.inWater = true;
		phioneCondition.weatherBoost = Weather.RAINY;
		legendarySpawnConditions.put("Manaphy", manaphyCondition);

		// +---------------------------------------+
		//                 DARKRAI                                 
		// +---------------------------------------+
		darkraiCondition.biomes = Arrays.asList(
			BiomeKeys.DARK_FOREST,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "moonlight_grove")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "moonlight_valley")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "mirage_isles"))
		);
		darkraiCondition.maxY = 255;
		darkraiCondition.minY = 62;
		darkraiCondition.timeOfDay = TimeOfDay.NIGHT;
		darkraiCondition.weather = null;
		darkraiCondition.inCave = false;
		darkraiCondition.inWater = false;
		legendarySpawnConditions.put("Darkrai", darkraiCondition);

		// +---------------------------------------+
		//                 SHAYMIN                                 
		// +---------------------------------------+
		shayminCondition.biomes = Arrays.asList(
			BiomeKeys.FLOWER_FOREST,
			BiomeKeys.MEADOW,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "blooming_plateau")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "blooming_valley")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "lavender_forest")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "lavender_valley")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "moonlight_grove")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "moonlight_valley")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "sakura_grove")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "sakura_valley"))
		);
		shayminCondition.maxY = 255;
		shayminCondition.minY = 30;
		shayminCondition.timeOfDay = TimeOfDay.DAY;
		shayminCondition.weather = Weather.SUNNY;
		shayminCondition.inCave = false;
		shayminCondition.inWater = false;
		legendarySpawnConditions.put("Shaymin", shayminCondition);

		// +---------------------------------------+
		//                 ARCEUS                                   
		// +---------------------------------------+
		arceusCondition.biomes = Arrays.asList(
			BiomeKeys.MEADOW,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cloud_forest")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "emerald_peaks"))
		);
		arceusCondition.maxY = 255;
		arceusCondition.minY = 62;
		arceusCondition.timeOfDay = TimeOfDay.DAWN;
		arceusCondition.weather = null;
		arceusCondition.inCave = false;
		arceusCondition.inWater = false;
		legendarySpawnConditions.put("Arceus", arceusCondition);

		// +---------------------------------------+
		//                 VICTINI                                  
		// +---------------------------------------+
		victiniCondition.biomes = Arrays.asList(
			BiomeKeys.SAVANNA,
			BiomeKeys.SAVANNA_PLATEAU,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "brushland")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "fractured_savanna")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "savanna_slopes"))
		);
		victiniCondition.maxY = 256;
		victiniCondition.minY = 62;
		victiniCondition.timeOfDay = TimeOfDay.DAY;
		victiniCondition.weather = Weather.SUNNY;
		victiniCondition.inCave = false;
		victiniCondition.inWater = false;
		legendarySpawnConditions.put("Victini", victiniCondition);

		// +---------------------------------------+
		//                 KELDEO                                   
		// +---------------------------------------+
		keldeoCondition.biomes = Arrays.asList(
			BiomeKeys.RIVER,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "warm_river"))
		);
		keldeoCondition.maxY = 256;
		keldeoCondition.minY = 0;
		keldeoCondition.timeOfDay = TimeOfDay.DAY;
		keldeoCondition.weather = null;
		keldeoCondition.inCave = false;
		keldeoCondition.inWater = false;
		legendarySpawnConditions.put("Keldeo", keldeoCondition);

		// +---------------------------------------+
		//                 MELOETTA                               
		// +---------------------------------------+
		meloettaCondition.biomes = Arrays.asList(
			BiomeKeys.FLOWER_FOREST,
			BiomeKeys.MEADOW,
			BiomeKeys.SUNFLOWER_PLAINS,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "blooming_plateau")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "blooming_valley"))
		);
		meloettaCondition.maxY = 255;
		meloettaCondition.minY = 0;
		meloettaCondition.timeOfDay = TimeOfDay.NIGHT;
		meloettaCondition.weather = Weather.SUNNY;
		meloettaCondition.inCave = false;
		meloettaCondition.inWater = false;
		legendarySpawnConditions.put("Meloetta", meloettaCondition);

		// +---------------------------------------+
		//                 GENESECT                               
		// +---------------------------------------+
		genesectCondition.biomes = Arrays.asList(
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "rocky_jungle")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "rocky_mountains"))
		);
		genesectCondition.maxY = 255;
		genesectCondition.minY = 62;
		genesectCondition.timeOfDay = TimeOfDay.NIGHT;
		genesectCondition.weather = null;
		genesectCondition.inCave = false;
		genesectCondition.inWater = false;
		legendarySpawnConditions.put("Genesect", genesectCondition);

		// +---------------------------------------+
		//                 DIANCIE                                 
		// +---------------------------------------+
		diancieCondition.biomes = Arrays.asList(
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "amethyst_canyon")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "amethyst_rainforest"))
		);
		diancieCondition.maxY = 255;
		diancieCondition.minY = 0;
		diancieCondition.timeOfDay = null;
		diancieCondition.weather = null;
		diancieCondition.inCave = false;
		diancieCondition.inWater = false;
		legendarySpawnConditions.put("Diancie", diancieCondition);

		// +---------------------------------------+
		//                 HOOPA                                     
		// +---------------------------------------+
		hoopaCondition.biomes = Arrays.asList(
			BiomeKeys.DESERT,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "desert_canyon")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "desert_oasis")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "desert_spires")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "lush_desert")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "sandstone_valley"))
		);
		hoopaCondition.maxY = 255;
		hoopaCondition.minY = 60;
		hoopaCondition.timeOfDay = TimeOfDay.AFTERNOON;
		hoopaCondition.weather = null;
		hoopaCondition.inCave = false;
		hoopaCondition.inWater = false;
		legendarySpawnConditions.put("Hoopa", hoopaCondition);

		// +---------------------------------------+
		//                 VOLCANION                             
		// +---------------------------------------+
		volcanionCondition.biomes = Arrays.asList(
			BiomeKeys.DESERT,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "desert_oasis")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "desert_spires")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "lush_desert")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "caldera"))
		);
		volcanionCondition.maxY = 127;
		volcanionCondition.minY = 0;
		volcanionCondition.timeOfDay = TimeOfDay.DAWN;
		volcanionCondition.weather = Weather.SUNNY;
		volcanionCondition.inCave = false;
		volcanionCondition.inWater = false;
		legendarySpawnConditions.put("Volcanion", volcanionCondition);

		// +---------------------------------------+
		//                 MARSHADOW                           
		// +---------------------------------------+
		marshadowCondition.biomes = Arrays.asList(
			BiomeKeys.DEEP_DARK,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "frostfire_caves"))
		);
		marshadowCondition.maxY = 50;
		marshadowCondition.minY = -60;
		marshadowCondition.timeOfDay = TimeOfDay.NIGHT;
		marshadowCondition.weather = null;
		marshadowCondition.inCave = true;
		marshadowCondition.inWater = false;
		legendarySpawnConditions.put("Marshadow", marshadowCondition);

		// +---------------------------------------+
		//                 MAGEARNA                              
		// +---------------------------------------+
		magearnaCondition.biomes = Arrays.asList(
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "blooming_valley")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "rocky_jungle")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "snowy_cherry_grove")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "sakura_valley"))
		);
		magearnaCondition.maxY = 255;
		magearnaCondition.minY = 64;
		magearnaCondition.timeOfDay = TimeOfDay.DAY;
		magearnaCondition.weather = null;
		magearnaCondition.inCave = false;
		magearnaCondition.inWater = false;
		legendarySpawnConditions.put("Magearna", magearnaCondition);

		// +---------------------------------------+
		//                 ZERAORA                                 
		// +---------------------------------------+
		zeraoraCondition.biomes = Arrays.asList(
			BiomeKeys.PLAINS,
			BiomeKeys.SAVANNA,
			BiomeKeys.SAVANNA_PLATEAU
		);
		zeraoraCondition.maxY = 255;
		zeraoraCondition.minY = 64;
		zeraoraCondition.timeOfDay = TimeOfDay.AFTERNOON;
		zeraoraCondition.weather = null;
		zeraoraCondition.inCave = false;
		zeraoraCondition.inWater = false;
		zeraoraCondition.weatherBoost = Weather.THUNDERSTORM;
		legendarySpawnConditions.put("Zeraora", zeraoraCondition);

		// +---------------------------------------+
		//                 MELTAN                                    
		// +---------------------------------------+
		meltanCondition.biomes = Arrays.asList(
			BiomeKeys.DRIPSTONE_CAVES,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cave/mantle_caves")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cave/thermal_caves")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cave/tuff_caves"))
		);
		meltanCondition.maxY = 50;
		meltanCondition.minY = -64;
		meltanCondition.timeOfDay = TimeOfDay.DAWN;
		meltanCondition.weather = null;
		meltanCondition.inCave = true;
		meltanCondition.inWater = false;
		legendarySpawnConditions.put("Meltan", meltanCondition);

		// +---------------------------------------+
		//                 MELMETAL                              
		// +---------------------------------------+
		melmetalCondition.biomes = Arrays.asList(
			BiomeKeys.DRIPSTONE_CAVES,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cave/mantle_caves")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cave/thermal_caves")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cave/tuff_caves"))
		);
		meltanCondition.maxY = 50;
		meltanCondition.minY = -64;
		melmetalCondition.timeOfDay = TimeOfDay.DUSK;
		meltanCondition.weather = null;
		melmetalCondition.inCave = true;
		melmetalCondition.inWater = false;
		legendarySpawnConditions.put("Melmetal", melmetalCondition);

		// +---------------------------------------+
		//                 ZARUDE                                   
		// +---------------------------------------+
		zarudeCondition.biomes = Arrays.asList(
			BiomeKeys.JUNGLE,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "jungle_mountains")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "rocky_jungle"))
		);
		zarudeCondition.maxY = 255;
		zarudeCondition.minY = 60;
		zarudeCondition.timeOfDay = TimeOfDay.NIGHT;
		zarudeCondition.weather = null;
		zarudeCondition.inCave = false;
		zarudeCondition.inWater = false;
		legendarySpawnConditions.put("Zarude", zarudeCondition);


		// Sub-Legendaries


		// +---------------------------------------+
		//                 ARTICUNO                                
		// +---------------------------------------+
		articunoCondition.biomes = Arrays.asList(
			BiomeKeys.ICE_SPIKES,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "frozen_cliffs")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "glacial_chasm")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "ice_marsh")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "wintry_forest"))
		);
		articunoCondition.maxY = 320;
		articunoCondition.minY = 100;
		articunoCondition.timeOfDay = TimeOfDay.NIGHT;
		articunoCondition.weather = null;
		articunoCondition.inCave = false;
		articunoCondition.inWater = false;
		articunoCondition.weatherBoost = Weather.RAINY;
		legendarySpawnConditions.put("Articuno", articunoCondition);

		// +---------------------------------------+
		// |               MOLTRES                 |
		// +---------------------------------------+
		moltresCondition.biomes = Arrays.asList(
			BiomeKeys.BADLANDS,
			BiomeKeys.ERODED_BADLANDS,
			BiomeKeys.WOODED_BADLANDS,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "bryce_canyon")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "painted_mountains")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "red_oasis")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "warped_mesa")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "white_mesa"))
		);
		moltresCondition.maxY = 320; //skybox
		moltresCondition.minY = 100; //minimum Y for moltres to spawn
		moltresCondition.timeOfDay = TimeOfDay.AFTERNOON;
		moltresCondition.weather = null;
		moltresCondition.inCave = false;
		moltresCondition.weatherBoost = Weather.SUNNY;
		legendarySpawnConditions.put("Moltres", moltresCondition);
	

		// +---------------------------------------+
		//                 ZAPDOS                                   
		// +---------------------------------------+
		zapdosCondition.biomes = Arrays.asList(
			BiomeKeys.SAVANNA_PLATEAU,
			BiomeKeys.WINDSWEPT_SAVANNA,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "savanna_badlands")) ,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "fractured_savanna")) ,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "red_oasis")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "savanna_slopes"))
		);
		zapdosCondition.maxY = 320;
		zapdosCondition.minY = 100;
		zapdosCondition.timeOfDay = TimeOfDay.DAY;
		zapdosCondition.weather = null;
		zapdosCondition.inCave = false;
		zapdosCondition.inWater = false;
		zapdosCondition.weatherBoost = Weather.THUNDERSTORM;
		legendarySpawnConditions.put("Zapdos", zapdosCondition);

		// +---------------------------------------+
		//                 RAIKOU
		// +---------------------------------------+
		raikouCondition.biomes = Arrays.asList(
			BiomeKeys.SAVANNA,
			BiomeKeys.SAVANNA_PLATEAU,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "savanna_badlands")) ,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "fractured_savanna")) ,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "red_oasis")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "savanna_slopes")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "arid_highlands")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "hot_shrubland")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "shrubland"))

		);
		raikouCondition.maxY = 255;
		raikouCondition.minY = 64;
		raikouCondition.timeOfDay = TimeOfDay.AFTERNOON;
		raikouCondition.weather = null;
		raikouCondition.inCave = false;
		raikouCondition.inWater = false;
		raikouCondition.weatherBoost = Weather.THUNDERSTORM;
		legendarySpawnConditions.put("Raikou", raikouCondition);

		// +---------------------------------------+
		//                 ENTEI
		// +---------------------------------------+
		enteiCondition.biomes = Arrays.asList(
			BiomeKeys.WINDSWEPT_GRAVELLY_HILLS,
			BiomeKeys.WINDSWEPT_HILLS,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "gravel_desert")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "mountain_steppe")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "steppe"))
		);
		enteiCondition.maxY = 255;
		enteiCondition.minY = 62;
		enteiCondition.timeOfDay = TimeOfDay.DAY;
		enteiCondition.weather = Weather.SUNNY;
		enteiCondition.inCave = false;
		enteiCondition.inWater = false;
		legendarySpawnConditions.put("Entei", enteiCondition);

		// +---------------------------------------+
		//                 SUICUNE                                 
		// +---------------------------------------+
		suicuneCondition.biomes = Arrays.asList(
			BiomeKeys.BEACH,
			BiomeKeys.SNOWY_BEACH,
			BiomeKeys.STONY_SHORE,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "ice_marsh")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "gravel_beach")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "caldera"))
		);
		suicuneCondition.maxY = 255;
		suicuneCondition.minY = 60;
		suicuneCondition.timeOfDay = TimeOfDay.NIGHT;
		suicuneCondition.weather = null;
		suicuneCondition.inCave = false;
		suicuneCondition.inWater = false;
		suicuneCondition.weatherBoost = Weather.RAINY;
		legendarySpawnConditions.put("Suicune", suicuneCondition);

		// +---------------------------------------+
		//                 REGIROCK                               
		// +---------------------------------------+
		regirockCondition.biomes = Arrays.asList(
			BiomeKeys.DESERT,
			BiomeKeys.BADLANDS,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "ancient_sands"))
		);
		regirockCondition.maxY = 50;
		regirockCondition.minY = -60;
		regirockCondition.timeOfDay = null;
		regirockCondition.weather = null;
		regirockCondition.inCave = true;
		regirockCondition.inWater = false;
		legendarySpawnConditions.put("Regirock", regirockCondition);

		// +---------------------------------------+
		//                 REGICE                                     
		// +---------------------------------------+
		regiceCondition.biomes = Arrays.asList(
			BiomeKeys.SNOWY_PLAINS,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "wintry_lowlands")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "glacial_chasm")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "ice_marsh")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "snowy_shield")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "frozen_cliffs")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "snowy_badlands")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "wintry_forest"))
		);
		regiceCondition.maxY = 50;
		regiceCondition.minY = -60;
		regiceCondition.timeOfDay = null;
		regiceCondition.weather = null;
		regiceCondition.inCave = true;
		regiceCondition.inWater = false;
		legendarySpawnConditions.put("Regice", regiceCondition);

		// +---------------------------------------+
		//                 REGISTEEL                               
		// +---------------------------------------+
		registeelCondition.biomes = Arrays.asList(
			BiomeKeys.PLAINS,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "emerald_peaks")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "granite_cliffs"))
		);
		registeelCondition.maxY = 50;
		registeelCondition.minY = -60;
		registeelCondition.timeOfDay = null;
		registeelCondition.weather = null;
		registeelCondition.inCave = true;
		registeelCondition.inWater = false;
		legendarySpawnConditions.put("Registeel", registeelCondition);

		// +---------------------------------------+
		//                 REGIDRAGO                               
		// +---------------------------------------+
		regidragoCondition.biomes = Arrays.asList(
			BiomeKeys.TAIGA,
			BiomeKeys.OLD_GROWTH_PINE_TAIGA,
			BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "basalt_cliffs")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "shield"))
		);
		regidragoCondition.maxY = 50;
		regidragoCondition.minY = -60;
		regidragoCondition.timeOfDay = null;
		regidragoCondition.weather = null;
		regidragoCondition.inCave = true;
		regidragoCondition.inWater = false;
		legendarySpawnConditions.put("Regidrago", registeelCondition);

		// +---------------------------------------+
		//                REGIELEKI                               
		// +---------------------------------------+
		regielekiCondition.biomes = Arrays.asList(
			BiomeKeys.SAVANNA,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "brushland")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "savanna_slopes")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "hot_shrubland"))
		);
		regielekiCondition.maxY = 50;
		regielekiCondition.minY = -60;
		regielekiCondition.timeOfDay = null;
		regielekiCondition.weather = null;
		regielekiCondition.inCave = true;
		regielekiCondition.inWater = false;
		legendarySpawnConditions.put("Regieleki", regielekiCondition);

		// +---------------------------------------+
		//                REGIGIGAS                               
		// +---------------------------------------+
		regigigasCondition.biomes = Arrays.asList(
			BiomeKeys.FOREST,
			BiomeKeys.FLOWER_FOREST,
			BiomeKeys.BIRCH_FOREST,
			BiomeKeys.WINDSWEPT_FOREST
		);
		regigigasCondition.maxY = 20;
		regigigasCondition.minY = -60;
		regigigasCondition.timeOfDay = null;
		regigigasCondition.weather = null;
		regigigasCondition.inCave = true;
		regigigasCondition.inWater = false;
		legendarySpawnConditions.put("Regigigas", regigigasCondition);

		// +---------------------------------------+
		//                 LATIAS                                     
		// +---------------------------------------+
		latiasCondition.biomes = Arrays.asList(
			BiomeKeys.WARM_OCEAN,
			BiomeKeys.LUKEWARM_OCEAN,
			BiomeKeys.DEEP_LUKEWARM_OCEAN
		);
		latiasCondition.maxY = 255;
		latiasCondition.minY = 0;
		latiasCondition.timeOfDay = TimeOfDay.DAY;
		latiasCondition.weather = null;
		latiasCondition.inCave = false;
		latiasCondition.inWater = true;
		legendarySpawnConditions.put("Latias", latiasCondition);

		// +---------------------------------------+
		//                 LATIOS                                     
		// +---------------------------------------+
		latiosCondition.biomes = Arrays.asList(
			BiomeKeys.WARM_OCEAN,
			BiomeKeys.LUKEWARM_OCEAN,
			BiomeKeys.DEEP_LUKEWARM_OCEAN
		);
		latiosCondition.maxY = 255;
		latiosCondition.minY = 0;
		latiosCondition.timeOfDay = TimeOfDay.AFTERNOON;
		latiosCondition.weather = null;
		latiosCondition.inCave = false;
		latiosCondition.inWater = true;
		legendarySpawnConditions.put("Latios", latiosCondition);

		// +---------------------------------------+
		//                 UXIE                                       
		// +---------------------------------------+
		uxieCondition.biomes = Arrays.asList(
			BiomeKeys.BIRCH_FOREST,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "white_cliffs"))
		);
		uxieCondition.maxY = 255;
		uxieCondition.minY = 60;
		uxieCondition.timeOfDay = TimeOfDay.AFTERNOON;
		uxieCondition.weather = null;
		uxieCondition.inCave = false;
		uxieCondition.inWater = false;
		legendarySpawnConditions.put("Uxie", uxieCondition);

		// +---------------------------------------+
		//                 MESPRIT                                     
		// +---------------------------------------+
		mespritCondition.biomes = Arrays.asList(
			BiomeKeys.DARK_FOREST
		);
		mespritCondition.maxY = 255;
		mespritCondition.minY = 60;
		mespritCondition.timeOfDay = TimeOfDay.NIGHT;
		mespritCondition.weather = null;
		mespritCondition.inCave = false;
		mespritCondition.inWater = false;
		legendarySpawnConditions.put("Mesprit", mespritCondition);

		// +---------------------------------------+
		//                 AZELF                                        
		// +---------------------------------------+
		azelfCondition.biomes = Arrays.asList(
			BiomeKeys.FOREST,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "forested_highlands"))
		);
		azelfCondition.maxY = 255;
		azelfCondition.minY = 0;
		azelfCondition.timeOfDay = TimeOfDay.DAY;
		azelfCondition.weather = null;
		azelfCondition.inCave = false;
		azelfCondition.inWater = false;
		legendarySpawnConditions.put("Azelf", azelfCondition);

		// +---------------------------------------+
		//                 HEATRAN                                    
		// +---------------------------------------+
		heatranCondition.biomes = Arrays.asList(
			BiomeKeys.NETHER_WASTES,
			BiomeKeys.WARPED_FOREST,
			BiomeKeys.CRIMSON_FOREST,
			BiomeKeys.SOUL_SAND_VALLEY,
			BiomeKeys.BASALT_DELTAS,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "volcanic_crater"))
		);
		heatranCondition.maxY = 200;
		heatranCondition.minY = 62;
		heatranCondition.timeOfDay = TimeOfDay.DAY;
		heatranCondition.weather = null;
		heatranCondition.inCave = false;
		heatranCondition.inWater = false;
		legendarySpawnConditions.put("Heatran", heatranCondition);

		// +---------------------------------------+
		//                 CRESSELIA                                   
		// +---------------------------------------+
		cresseliaCondition.biomes = Arrays.asList(
			BiomeKeys.FLOWER_FOREST,
			BiomeKeys.CHERRY_GROVE,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "sakura_grove")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "sakura_valley")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "sakura_grove"))
		);
		cresseliaCondition.maxY = 255;
		cresseliaCondition.minY = 64;
		cresseliaCondition.timeOfDay = TimeOfDay.NIGHT;
		cresseliaCondition.weather = Weather.SUNNY;
		cresseliaCondition.inCave = false;
		cresseliaCondition.inWater = false;
		legendarySpawnConditions.put("Cresselia", cresseliaCondition);

		// +---------------------------------------+
		//                 COBALION                                   
		// +---------------------------------------+
		cobalionCondition.biomes = Arrays.asList(
			BiomeKeys.TAIGA,
			BiomeKeys.OLD_GROWTH_PINE_TAIGA,
			BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA,
			BiomeKeys.BIRCH_FOREST,
			BiomeKeys.OLD_GROWTH_BIRCH_FOREST,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "forested_highlands")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "alpine_highlands")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "rocky_shrubland")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "bryce_canyon"))

		);
		cobalionCondition.maxY = 255;
		cobalionCondition.minY = 62;
		cobalionCondition.timeOfDay = TimeOfDay.NIGHT;
		cobalionCondition.weather = null;
		cobalionCondition.inCave = false;
		cobalionCondition.inWater = false;
		legendarySpawnConditions.put("Cobalion", cobalionCondition);

		// +---------------------------------------+
		//                 TERRAKION                                   
		// +---------------------------------------+
		terrakionCondition.biomes = Arrays.asList(
			BiomeKeys.TAIGA,
			BiomeKeys.OLD_GROWTH_PINE_TAIGA,
			BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA,
			BiomeKeys.BIRCH_FOREST,
			BiomeKeys.OLD_GROWTH_BIRCH_FOREST,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "forested_highlands")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "alpine_highlands")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "rocky_shrubland")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "bryce_canyon"))
		);
		terrakionCondition.maxY = 255;
		terrakionCondition.minY = -60;
		terrakionCondition.timeOfDay = TimeOfDay.AFTERNOON;
		terrakionCondition.weather = null;
		terrakionCondition.inCave = false;
		terrakionCondition.inWater = false;
		legendarySpawnConditions.put("Terrakion", terrakionCondition);

		// +---------------------------------------+
		//                 VIRIZION                                     
		// +---------------------------------------+
		virizionCondition.biomes = Arrays.asList(
			BiomeKeys.PLAINS,
			BiomeKeys.TAIGA,
			BiomeKeys.OLD_GROWTH_PINE_TAIGA,
			BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA,
			BiomeKeys.BIRCH_FOREST,
			BiomeKeys.OLD_GROWTH_BIRCH_FOREST,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "forested_highlands")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "alpine_highlands")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "rocky_shrubland")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "bryce_canyon"))
		);
		virizionCondition.maxY = 255;
		virizionCondition.minY = 62;
		virizionCondition.timeOfDay = TimeOfDay.DAY;
		virizionCondition.weather = null;
		virizionCondition.inCave = false;
		virizionCondition.inWater = false;
		legendarySpawnConditions.put("Virizion", virizionCondition);

		// +---------------------------------------+
		//                 TORNADUS                                    
		// +---------------------------------------+
		tornadusCondition.biomes = Arrays.asList(
			BiomeKeys.WINDSWEPT_HILLS,
			BiomeKeys.WINDSWEPT_GRAVELLY_HILLS,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "haze_mountain")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cloud_forest")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "mountain_steppe")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "steppe")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "highlands")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "skylands_winter"))
		);
		tornadusCondition.maxY = 320;
		tornadusCondition.minY = 128; //cloud level
		tornadusCondition.timeOfDay = null;
		tornadusCondition.weather = null;
		tornadusCondition.inCave = false;
		tornadusCondition.inWater = false;
		tornadusCondition.weatherBoost = Weather.THUNDERSTORM;
		legendarySpawnConditions.put("Tornadus", tornadusCondition);

		// +---------------------------------------+
		//                 THUNDURUS                                    
		// +---------------------------------------+
		thundurusCondition.biomes = Arrays.asList(
			BiomeKeys.WINDSWEPT_SAVANNA,
			BiomeKeys.WINDSWEPT_FOREST,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "hot_shrubland")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cloud_forest")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "mountain_steppe")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "steppe")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "highlands")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "skylands_summer"))
		);
		tornadusCondition.maxY = 320;
		tornadusCondition.minY = 128; //cloud level
		thundurusCondition.timeOfDay = null;
		thundurusCondition.weather = null;
		thundurusCondition.inCave = false;
		thundurusCondition.inWater = false;
		tornadusCondition.weatherBoost = Weather.THUNDERSTORM;
		legendarySpawnConditions.put("Thundurus", thundurusCondition);

		// +---------------------------------------+
		//                 LANDORUS                                      
		// +---------------------------------------+
		landorusCondition.biomes = Arrays.asList(
			BiomeKeys.BADLANDS,
			BiomeKeys.DESERT,
			BiomeKeys.WOODED_BADLANDS,
			BiomeKeys.ERODED_BADLANDS,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "ancient_sands")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "painted_mountains")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cloud_forest")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "skylands_autumn"))
		);
		tornadusCondition.maxY = 320;
		tornadusCondition.minY = 128; //cloud level
		landorusCondition.timeOfDay = null;
		landorusCondition.weather = null;
		landorusCondition.inCave = false;
		landorusCondition.inWater = false;
		tornadusCondition.weatherBoost = Weather.THUNDERSTORM;
		legendarySpawnConditions.put("Landorus", landorusCondition);

		// +---------------------------------------+
		//                 TYPE:NULL                                   
		// +---------------------------------------+
		typenullCondition.biomes = Arrays.asList(
			BiomeKeys.SPARSE_JUNGLE,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "jungle_mountains"))
		);
		typenullCondition.maxY = 255;
		typenullCondition.minY = 60;
		typenullCondition.timeOfDay = null;
		typenullCondition.weather = null;
		typenullCondition.inCave = false;
		typenullCondition.inWater = false;
		legendarySpawnConditions.put("Type:Null", typenullCondition);

		// +---------------------------------------+
		//                 TAPU KOKO                                      
		// +---------------------------------------+
		tapukokoCondition.biomes = Arrays.asList(
			BiomeKeys.SPARSE_JUNGLE,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "jungle_mountains")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "alpha_islands"))
		);
		tapukokoCondition.maxY = 255;
		tapukokoCondition.minY = 60;
		tapukokoCondition.timeOfDay = TimeOfDay.AFTERNOON;
		tapukokoCondition.weather = null;
		tapukokoCondition.inCave = false;
		tapukokoCondition.inWater = true;
		legendarySpawnConditions.put("Tapu Koko", tapukokoCondition);

		// +---------------------------------------+
		//                 TAPU LELE                                      
		// +---------------------------------------+
		tapuleleCondition.biomes = Arrays.asList(
			BiomeKeys.BAMBOO_JUNGLE,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "amethyst_rainforest")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "alpha_islands"))

		);
		tapuleleCondition.maxY = 255;
		tapuleleCondition.minY = 52; //sea level - 10 to allow for swimming
		tapuleleCondition.timeOfDay = TimeOfDay.NIGHT;
		tapuleleCondition.weather = null;
		tapuleleCondition.inCave = false;
		tapuleleCondition.inWater = false;
		legendarySpawnConditions.put("Tapu Lele", tapuleleCondition);

		// +---------------------------------------+
		//                 TAPU BULU                                      
		// +---------------------------------------+
		tapubuluCondition.biomes = Arrays.asList(
			BiomeKeys.JUNGLE,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "tropical_jungle")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "alpha_islands"))
		);
		tapubuluCondition.maxY = 255;
		tapubuluCondition.minY = 60;
		tapubuluCondition.timeOfDay = TimeOfDay.AFTERNOON;
		tapubuluCondition.weather = null;
		tapubuluCondition.inCave = false;
		tapubuluCondition.inWater = false;
		legendarySpawnConditions.put("Tapu Bulu", tapubuluCondition);

		// +---------------------------------------+
		//                 TAPU FINI                                      
		// +---------------------------------------+
		tapufiniCondition.biomes = Arrays.asList(
			BiomeKeys.BEACH,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "blooming_plateau")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "blooming_valley")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "alpha_islands"))
		);
		tapufiniCondition.maxY = 255;
		tapufiniCondition.minY = 60;
		tapufiniCondition.timeOfDay = TimeOfDay.DAY;
		tapufiniCondition.weather = null;
		tapufiniCondition.inCave = false;
		tapufiniCondition.inWater = true;
		legendarySpawnConditions.put("Tapu Fini", tapufiniCondition);

		// +---------------------------------------+
		//                 KUBFU                                      
		// +---------------------------------------+
		kubfuCondition.biomes = Arrays.asList(
			BiomeKeys.BAMBOO_JUNGLE
		);
		kubfuCondition.maxY = 255;
		kubfuCondition.minY = 60;
		kubfuCondition.timeOfDay = TimeOfDay.AFTERNOON;
		kubfuCondition.weather = null;
		kubfuCondition.inCave = false;
		kubfuCondition.inWater = false;
		legendarySpawnConditions.put("Kubfu", kubfuCondition);

		// +---------------------------------------+
		//                 URSHIFU                                      
		// +---------------------------------------+
		urshifuCondition.biomes = Arrays.asList(
			BiomeKeys.BAMBOO_JUNGLE
		);
		urshifuCondition.maxY = 255;
		urshifuCondition.minY = 60;
		urshifuCondition.timeOfDay = TimeOfDay.NIGHT;
		urshifuCondition.weather = Weather.THUNDERSTORM;
		urshifuCondition.inCave = false;
		urshifuCondition.inWater = false;
		legendarySpawnConditions.put("Urshifu", urshifuCondition);

		// +---------------------------------------+
		//                 GLASTRIER                                     
		// +---------------------------------------+
		glastrierCondition.biomes = Arrays.asList(
			BiomeKeys.SNOWY_PLAINS,
			BiomeKeys.SNOWY_SLOPES,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "wintry_lowlands"))
		);
		glastrierCondition.maxY = 255;
		glastrierCondition.minY = 60;
		glastrierCondition.timeOfDay = TimeOfDay.DAY;
		glastrierCondition.weather = null;
		glastrierCondition.inCave = false;
		glastrierCondition.inWater = false;
		legendarySpawnConditions.put("Glastrier", glastrierCondition);

		// +---------------------------------------+
		//                 SPECTRIER                                     
		// +---------------------------------------+
		spectrierCondition.biomes = Arrays.asList(
			BiomeKeys.SOUL_SAND_VALLEY,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "cave/frostfire_caves"))
		);
		spectrierCondition.maxY = 255;
		spectrierCondition.minY = 0;
		spectrierCondition.timeOfDay = TimeOfDay.NIGHT;
		spectrierCondition.weather = null;
		spectrierCondition.inCave = true;
		spectrierCondition.inWater = false;
		legendarySpawnConditions.put("Spectrier", spectrierCondition);

		// +---------------------------------------+
		//                 ENAMORUS                                     
		// +---------------------------------------+
		enamorusCondition.biomes = Arrays.asList(
			BiomeKeys.MUSHROOM_FIELDS,
			BiomeKeys.SUNFLOWER_PLAINS,
			BiomeKeys.CHERRY_GROVE,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "sakura_valley")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "sakura_grove")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "mirage_isles")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "skylands_spring"))
		);
		enamorusCondition.maxY = 255;
		enamorusCondition.minY = 0;
		enamorusCondition.timeOfDay = TimeOfDay.DAWN;
		enamorusCondition.weather = null;
		enamorusCondition.inCave = false;
		enamorusCondition.inWater = false;
		legendarySpawnConditions.put("Enamorus", enamorusCondition);

		// +---------------------------------------+
		//                 TINGLU                                     
		// +---------------------------------------+
		tingluCondition.biomes = Arrays.asList(
			BiomeKeys.TAIGA,
			BiomeKeys.OLD_GROWTH_PINE_TAIGA,
			BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "rocky_mountains")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "rocky_shrubland"))
		);
		tingluCondition.maxY = 255;
		tingluCondition.minY = 60;
		tingluCondition.timeOfDay = TimeOfDay.AFTERNOON;
		tingluCondition.weather = null;
		tingluCondition.inCave = false;
		tingluCondition.inWater = false;
		tingluCondition.weatherBoost = Weather.RAINY;
		legendarySpawnConditions.put("Tinglu", tingluCondition);

		// +---------------------------------------+
		//                 CHIENPAO                                     
		// +---------------------------------------+
		chienpaoCondition.biomes = Arrays.asList(
			BiomeKeys.SNOWY_SLOPES,
			BiomeKeys.FROZEN_PEAKS,
			BiomeKeys.JAGGED_PEAKS,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "alpine_grove")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "siberian_grove")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "siberian_taiga")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "wintry_forest")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "snowy_shield")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "snowy_maple_forest")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "frozen_cliffs"))
		);
		chienpaoCondition.maxY = 255;
		chienpaoCondition.minY = 60;
		chienpaoCondition.timeOfDay = TimeOfDay.NIGHT;
		chienpaoCondition.weather = null;
		chienpaoCondition.inCave = false;
		chienpaoCondition.inWater = false;
		chienpaoCondition.weatherBoost = Weather.RAINY;
		legendarySpawnConditions.put("Chienpao", chienpaoCondition);

		// +---------------------------------------+
		//                 WOCHIEN                                   
		// +---------------------------------------+
		wochienCondition.biomes = Arrays.asList(
			BiomeKeys.MANGROVE_SWAMP,
			BiomeKeys.SWAMP,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "orchid_swamp"))
		);
		wochienCondition.maxY = 255;
		wochienCondition.minY = 0;
		wochienCondition.timeOfDay = TimeOfDay.NIGHT;
		wochienCondition.weather = null;
		wochienCondition.inCave = false;
		wochienCondition.inWater = false;
		wochienCondition.weatherBoost = Weather.RAINY;
		legendarySpawnConditions.put("Wochien", wochienCondition);

		// +---------------------------------------+
		//                 CHIYU                                       
		// +---------------------------------------+
		chiyuCondition.biomes = Arrays.asList(
			BiomeKeys.NETHER_WASTES,
			BiomeKeys.BASALT_DELTAS,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "volcanic_crater")),
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "caldera"))
		);
		chiyuCondition.maxY = 255;
		chiyuCondition.minY = 0;
		chiyuCondition.timeOfDay = TimeOfDay.AFTERNOON;
		chiyuCondition.weather = null;
		chiyuCondition.inCave = false;
		chiyuCondition.inWater = false;
		chiyuCondition.weatherBoost = Weather.RAINY;
		legendarySpawnConditions.put("Chiyu", chiyuCondition);

		// +---------------------------------------+
		//                 OGERPON                                     
		// +---------------------------------------+
		ogerponCondition.biomes = Arrays.asList(
			BiomeKeys.SWAMP,
			BiomeKeys.MANGROVE_SWAMP,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "orchid_swamp"))
		);
		ogerponCondition.maxY = 255;
		ogerponCondition.minY = 60;
		ogerponCondition.timeOfDay = TimeOfDay.DAY;
		ogerponCondition.weather = null;
		ogerponCondition.inCave = false;
		ogerponCondition.inWater = false;
		legendarySpawnConditions.put("Ogerpon", ogerponCondition);

		// +---------------------------------------+
		//                 MUNKIDORI                                     
		// +---------------------------------------+
		munkidoriCondition.biomes = Arrays.asList(
			BiomeKeys.SWAMP,
			BiomeKeys.MANGROVE_SWAMP,
			RegistryKey.of(RegistryKeys.BIOME, new Identifier("terralith", "orchid_swamp"))
		);
		munkidoriCondition.maxY = 255;
		munkidoriCondition.minY = 60;
		munkidoriCondition.timeOfDay = TimeOfDay.AFTERNOON;
		munkidoriCondition.weather = null;
		munkidoriCondition.inCave = false;
		munkidoriCondition.inWater = false;
		legendarySpawnConditions.put("Munkidori", munkidoriCondition);


		// ULTRA BEASTS


		// +---------------------------------------+
		//                 BUZZWOLE                                     
		// +---------------------------------------+
		buzzwoleCondition.biomes = Arrays.asList(
			BiomeKeys.THE_END,
			BiomeKeys.END_HIGHLANDS,
			BiomeKeys.END_MIDLANDS,
			BiomeKeys.SMALL_END_ISLANDS,
			BiomeKeys.END_BARRENS
		);
		buzzwoleCondition.maxY = 255;
		buzzwoleCondition.minY = 62;
		buzzwoleCondition.timeOfDay = TimeOfDay.DAWN;
		buzzwoleCondition.weather = null;
		buzzwoleCondition.inCave = false;
		buzzwoleCondition.inWater = false;
		legendarySpawnConditions.put("Buzzwole", buzzwoleCondition);

		// +---------------------------------------+
		//                 PHEROMOSA                                     
		// +---------------------------------------+
		pheromosaCondition.biomes = Arrays.asList(
			BiomeKeys.THE_END,
			BiomeKeys.END_HIGHLANDS,
			BiomeKeys.END_MIDLANDS,
			BiomeKeys.SMALL_END_ISLANDS,
			BiomeKeys.END_BARRENS
		);
		pheromosaCondition.maxY = 255;
		pheromosaCondition.minY = 62;
		pheromosaCondition.timeOfDay = TimeOfDay.DAWN;
		pheromosaCondition.weather = null;
		pheromosaCondition.inCave = false;
		pheromosaCondition.inWater = false;
		legendarySpawnConditions.put("Pheromosa", pheromosaCondition);

		// +---------------------------------------+
		//                 XURKITREE                                     
		// +---------------------------------------+
		xurkitreeCondition.biomes = Arrays.asList(
			BiomeKeys.THE_END,
			BiomeKeys.END_HIGHLANDS,
			BiomeKeys.END_MIDLANDS,
			BiomeKeys.SMALL_END_ISLANDS,
			BiomeKeys.END_BARRENS
		);
		xurkitreeCondition.maxY = 255;
		xurkitreeCondition.minY = 62;
		xurkitreeCondition.timeOfDay = TimeOfDay.DAY;
		xurkitreeCondition.weather = null;
		xurkitreeCondition.inCave = true;
		xurkitreeCondition.inWater = false;
		legendarySpawnConditions.put("Xurkitree", xurkitreeCondition);

		// +---------------------------------------+
		//                 KARTANA                                     
		// +---------------------------------------+
		kartanaCondition.biomes = Arrays.asList(
			BiomeKeys.THE_END,
			BiomeKeys.END_HIGHLANDS,
			BiomeKeys.END_MIDLANDS,
			BiomeKeys.SMALL_END_ISLANDS,
			BiomeKeys.END_BARRENS
		);
		kartanaCondition.maxY = 255;
		kartanaCondition.minY = 62;
		kartanaCondition.timeOfDay = TimeOfDay.DAY;
		kartanaCondition.weather = null;
		kartanaCondition.inCave = false;
		kartanaCondition.inWater = false;
		legendarySpawnConditions.put("Kartana", kartanaCondition);

		// +---------------------------------------+
		//                 GUZZLORD                                     
		// +---------------------------------------+
		guzzlordCondition.biomes = Arrays.asList(
			BiomeKeys.THE_END,
			BiomeKeys.END_HIGHLANDS,
			BiomeKeys.END_MIDLANDS,
			BiomeKeys.SMALL_END_ISLANDS,
			BiomeKeys.END_BARRENS
		);
		guzzlordCondition.maxY = 255;
		guzzlordCondition.minY = 62;
		guzzlordCondition.timeOfDay = TimeOfDay.NIGHT;
		guzzlordCondition.weather = null;
		guzzlordCondition.inCave = false;
		guzzlordCondition.inWater = false;
		legendarySpawnConditions.put("Guzzlord", guzzlordCondition);

		// +---------------------------------------+
		//                 POIPOLE                                     
		// +---------------------------------------+
		poipoleCondition.biomes = Arrays.asList(
			BiomeKeys.THE_END,
			BiomeKeys.END_HIGHLANDS,
			BiomeKeys.END_MIDLANDS,
			BiomeKeys.SMALL_END_ISLANDS,
			BiomeKeys.END_BARRENS
		);
		poipoleCondition.maxY = 255;
		poipoleCondition.minY = 62;
		poipoleCondition.timeOfDay = TimeOfDay.DUSK;
		poipoleCondition.weather = null;
		poipoleCondition.inCave = false;
		poipoleCondition.inWater = false;
		legendarySpawnConditions.put("Poipole", poipoleCondition);

		// +---------------------------------------+
		//                 NAGANADEL                                     
		// +---------------------------------------+
		naganadelCondition.biomes = Arrays.asList(
			BiomeKeys.THE_END,
			BiomeKeys.END_HIGHLANDS,
			BiomeKeys.END_MIDLANDS,
			BiomeKeys.SMALL_END_ISLANDS,
			BiomeKeys.END_BARRENS
		);
		naganadelCondition.maxY = 255;
		naganadelCondition.minY = 62;
		naganadelCondition.timeOfDay = TimeOfDay.DUSK;
		naganadelCondition.weather = null;
		naganadelCondition.inCave = false;
		naganadelCondition.inWater = false;
		legendarySpawnConditions.put("Naganadel", naganadelCondition);

		// +---------------------------------------+
		//                 STAKATAKA                                     
		// +---------------------------------------+
		stakatakaCondition.biomes = Arrays.asList(
			BiomeKeys.THE_END,
			BiomeKeys.END_HIGHLANDS,
			BiomeKeys.END_MIDLANDS,
			BiomeKeys.SMALL_END_ISLANDS,
			BiomeKeys.END_BARRENS
		);
		stakatakaCondition.maxY = 255;
		stakatakaCondition.minY = 62;
		stakatakaCondition.timeOfDay = TimeOfDay.AFTERNOON;
		stakatakaCondition.weather = null;
		stakatakaCondition.inCave = false;
		stakatakaCondition.inWater = true;
		legendarySpawnConditions.put("Stakataka", stakatakaCondition);

		// +---------------------------------------+
		//                BLACEPHALON                                     
		// +---------------------------------------+
		blacephalonCondition.biomes = Arrays.asList(
			BiomeKeys.THE_END,
			BiomeKeys.END_HIGHLANDS,
			BiomeKeys.END_MIDLANDS,
			BiomeKeys.SMALL_END_ISLANDS,
			BiomeKeys.END_BARRENS
		);
		blacephalonCondition.maxY = 255;
		blacephalonCondition.minY = 62;
		blacephalonCondition.timeOfDay = TimeOfDay.NIGHT;
		blacephalonCondition.weather = null;
		blacephalonCondition.inCave = false;
		blacephalonCondition.inWater = false;
		legendarySpawnConditions.put("Blacephalon", blacephalonCondition);

		// +---------------------------------------+
		//               CELESTEELA                                     
		// +---------------------------------------+
		celesteelaCondition.biomes = Arrays.asList(
			BiomeKeys.THE_END,
			BiomeKeys.END_HIGHLANDS,
			BiomeKeys.END_MIDLANDS,
			BiomeKeys.SMALL_END_ISLANDS,
			BiomeKeys.END_BARRENS
		);
		celesteelaCondition.maxY = 255;
		celesteelaCondition.minY = 62;
		celesteelaCondition.timeOfDay = TimeOfDay.AFTERNOON;
		celesteelaCondition.weather = null;
		celesteelaCondition.inCave = false;
		celesteelaCondition.inWater = false;
		legendarySpawnConditions.put("Celesteela", celesteelaCondition);


		// Repeat for each legendary
	}
}
