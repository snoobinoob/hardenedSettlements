package hardenedSettlements.patch;

import hardenedSettlements.extraSettlement.ExtraSettlementFlagObjectEntity;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.entity.objectEntity.ObjectEntity;
import necesse.level.gameObject.SettlementFlagObject;
import necesse.level.maps.Level;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bytecode.assign.Assigner.Typing;

public class SettlementFlagObjectPatch {
    @ModMethodPatch(target = SettlementFlagObject.class, name = "getNewObjectEntity",
            arguments = {Level.class, int.class, int.class})
    public static class GetNewObjectEntityPatch {
        @Advice.OnMethodExit()
        public static void onEnter(@Advice.AllArguments() Object[] args,
                @Advice.Return(readOnly = false, typing = Typing.DYNAMIC) ObjectEntity oe) {
            oe = new ExtraSettlementFlagObjectEntity((Level) args[0], (int) args[1], (int) args[2]);
        }
    }
}
