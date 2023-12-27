package hardenedSettlements.extraSettlement;

import necesse.engine.save.LoadData;
import necesse.engine.save.SaveData;
import necesse.level.maps.Level;
import necesse.level.maps.levelData.LevelData;
import necesse.level.maps.levelData.settlementData.SettlementLevelData;

public class ExtraSettlementLevelData extends SettlementLevelData {
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

    public static ExtraSettlementLevelData getDataCreateIfNoneExist(Level level) {
        if (!level.isServer()) {
            throw new IllegalArgumentException("Level must be server level");
        } else {
            ExtraSettlementLevelData settlementData = getData(level);
            if (settlementData == null) {
                settlementData = new ExtraSettlementLevelData();
                level.addLevelData("settlement", settlementData);
            }

            return settlementData;
        }
    }

    public static ExtraSettlementLevelData getData(Level level) {
        LevelData data = level.getLevelData("settlement");
        return data instanceof ExtraSettlementLevelData ? (ExtraSettlementLevelData) data : null;
    }
}
