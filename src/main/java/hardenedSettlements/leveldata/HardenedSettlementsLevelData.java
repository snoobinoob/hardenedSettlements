package hardenedSettlements.leveldata;

import necesse.engine.save.LoadData;
import necesse.engine.save.SaveData;
import necesse.level.maps.Level;
import necesse.level.maps.levelData.LevelData;

public class HardenedSettlementsLevelData extends LevelData {
    public boolean doExplosionDamage;

    @Override
    public void addSaveData(SaveData save) {
        super.addSaveData(save);
        save.addBoolean("explosiondamage", doExplosionDamage);
    }

    @Override
    public void applyLoadData(LoadData save) {
        super.applyLoadData(save);
        doExplosionDamage = save.getBoolean("explosiondamage", false);
    }

    public static HardenedSettlementsLevelData getDataCreateIfNoneExist(Level level) {
        if (!level.isServer()) {
            throw new IllegalArgumentException("Level must be server level");
        }

        HardenedSettlementsLevelData levelData = getData(level);
        if (levelData == null) {
            levelData = new HardenedSettlementsLevelData();
            level.addLevelData("hardenedsettlements", levelData);
        }

        return levelData;
    }

    public static HardenedSettlementsLevelData getData(Level level) {
        LevelData data = level.getLevelData("hardenedsettlements");
        return data instanceof HardenedSettlementsLevelData ? (HardenedSettlementsLevelData) data : null;
    }
}
