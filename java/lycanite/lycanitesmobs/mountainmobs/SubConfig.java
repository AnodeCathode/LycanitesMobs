package lycanite.lycanitesmobs.mountainmobs;

import lycanite.lycanitesmobs.OldConfig;

public class SubConfig extends OldConfig {
	
	// ==================================================
	//               Load Config Settings
	// ==================================================
	@Override
	public void loadSettings() {
		super.loadSettings();
		
		// ========== Feature Control ==========
		loadSetting(this.featureStrings, "Feature Control", "BiomeTypes", "Group Biome Types", "MOUNTAIN");
		loadSetting(this.featureStrings, "Feature Control", "Dimensions", "Group Dimensions", "0");
		
		// ========== Special Feature Control ==========
		loadSetting(this.featureBools, "Feature Control", "TrollGriefing", "Troll Griefing", true);
		
		// ========== Mob Control ==========
		loadMobSettings("Jabberwock", 8, 10, 1, 2, "MONSTER");
		loadMobSettings("Troll", 4, 5, 1, 2, "MONSTER");
		loadMobSettings("Yale", 14, 5, 2, 3, "CREATURE");
	}
}