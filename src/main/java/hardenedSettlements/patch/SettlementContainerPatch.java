package hardenedSettlements.patch;

import hardenedSettlements.action.RequestExplosionDamageAction;
import hardenedSettlements.action.SetExplosionDamageAction;
import hardenedSettlements.event.SetExplosionDamageEvent;
import necesse.engine.modLoader.annotations.ModConstructorPatch;
import necesse.engine.network.NetworkClient;
import necesse.engine.network.Packet;
import necesse.entity.objectEntity.SettlementFlagObjectEntity;
import necesse.inventory.container.customAction.BooleanCustomAction;
import necesse.inventory.container.settlement.SettlementContainer;
import net.bytebuddy.asm.Advice;

import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

public class SettlementContainerPatch {
    public static BooleanCustomAction setExplosionDamageAction;
    public static RequestExplosionDamageAction requestExplosionDamageAction;

    public static final Predicate<SetExplosionDamageEvent> truePredicate = e -> true;
    public static final BooleanSupplier trueSupplier = () -> true;

    @ModConstructorPatch(target = SettlementContainer.class, arguments = {NetworkClient.class, int.class, SettlementFlagObjectEntity.class, Packet.class})
    public static class ConstructorPatch {

        @Advice.OnMethodExit
        static void onExit(@Advice.This SettlementContainer thiz) {
            setExplosionDamageAction = thiz.registerAction(new SetExplosionDamageAction(thiz));
            requestExplosionDamageAction = thiz.registerAction(new RequestExplosionDamageAction(thiz));
            thiz.subscribeEvent(SetExplosionDamageEvent.class, truePredicate, trueSupplier);
        }
    }
}
