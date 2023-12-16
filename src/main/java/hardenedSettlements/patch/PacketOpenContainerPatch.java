package hardenedSettlements.patch;

import hardenedSettlements.HardenedSettlements;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.engine.network.Packet;
import necesse.engine.network.packet.PacketOpenContainer;
import necesse.engine.registries.ContainerRegistry;
import necesse.entity.objectEntity.ObjectEntity;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bytecode.assign.Assigner.Typing;

public class PacketOpenContainerPatch {
    @ModMethodPatch(target = PacketOpenContainer.class, name = "ObjectEntity",
            arguments = {int.class, ObjectEntity.class, Packet.class, Object.class})
    public static class ObjectEntityPatch {
        @Advice.OnMethodEnter
        public static void onEnter(@Advice.Argument(value = 0, readOnly = false,
                typing = Typing.DYNAMIC) int containerID) {
            if (HardenedSettlements.isActive && containerID == ContainerRegistry.SETTLEMENT_CONTAINER) {
                containerID = HardenedSettlements.EXTRA_SETTLEMENT_CONTAINER;
            }
        }
    }

}
