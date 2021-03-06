package lycanite.lycanitesmobs.forestmobs;

import lycanite.lycanitesmobs.OldConfig;

public class SubConfig extends OldConfig {
	
	// ==================================================
	//               Load Config Settings
	// ==================================================
	@Override
	public void loadSettings() {
		super.loadSettings();
		
		// ========== Feature Control ==========
		loadSetting(this.featureStrings, "Feature Control", "BiomeTypes", "Group Biome Types", "FOREST");
		loadSetting(this.featureStrings, "Feature Control", "Dimensions", "Group Dimensions", "0, 7");

		// ========== Special Feature Control ==========
		// None
		
		// ========== Mob Control ==========
		loadMobSettings("Ent", 8, 10, 1, 3, "MONSTER");
		loadMobSettings("Trent", 1, 2, 1, 1, "MONSTER");
		loadMobSettings("Shambler", 4, 6, 1, 2, "MONSTER");
	}
}