package hardenedSettlements.patch;

import hardenedSettlements.HardenedSettlements;
import hardenedSettlements.leveldata.HardenedSettlementsLevelData;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.network.server.ServerClient;
import necesse.level.gameObject.GameObject;
import necesse.level.maps.Level;
import necesse.level.maps.levelData.settlementData.SettlementLevelData;
import net.bytebuddy.asm.Advice;

public class GameObjectPatch {
    @ModMethodPatch(target = GameObject.class, name = "doExplosionDamage", arguments = {Level.class,
            int.class, int.class, int.class, int.class, ServerClient.class})
    public static class DoExplosionDamagePatch {
        @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
        public static boolean onExit(@Advice.AllArguments Object[] args) {
            if (!HardenedSettlements.isActive) {
                return false;
            }

            Level level = (Level) args[0];
            int x = (int) args[1];
            int y = (int) args[2];
            SettlementLevelData settlementData = SettlementLevelData.getSettlementData(level);
            if (level.settlementLayer.isActive() && settlementData.getDefendZone().containsTile(x, y)) {
                HardenedSettlementsLevelData data = HardenedSettlementsLevelData.getDataCreateIfNoneExist(level);
                return !data.doExplosionDamage;
            }
            return false;
        }
    }
}
