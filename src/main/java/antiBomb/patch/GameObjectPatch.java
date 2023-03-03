package antiBomb.patch;

import antiBomb.extraSettlement.ExtraSettlementLevelData;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.network.server.ServerClient;
import necesse.level.gameObject.GameObject;
import necesse.level.maps.Level;
import net.bytebuddy.asm.Advice;

public class GameObjectPatch {
    @ModMethodPatch(target = GameObject.class, name = "doExplosionDamage", arguments = {Level.class,
            int.class, int.class, int.class, int.class, ServerClient.class})
    public static class DoExplosionDamagePatch {
        @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
        public static boolean onExit(@Advice.AllArguments Object[] args) {
            Level level = (Level) args[0];
            int x = (int) args[1];
            int y = (int) args[2];
            ExtraSettlementLevelData data = ExtraSettlementLevelData.getData(level);
            if (level.settlementLayer.isActive() && !data.doExplosionDamage
                    && data.getDefendZone().containsTile(x, y)) {
                return true;
            }
            return false;
        }
    }
}
