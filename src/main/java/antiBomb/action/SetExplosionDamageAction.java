package antiBomb.action;

import antiBomb.event.SetExplosionDamageEvent;
import antiBomb.extraSettlement.ExtraSettlementContainer;
import antiBomb.extraSettlement.ExtraSettlementLevelData;
import necesse.engine.network.Packet;
import necesse.engine.network.PacketReader;
import necesse.engine.network.PacketWriter;
import necesse.engine.network.server.ServerClient;
import necesse.inventory.container.settlement.actions.SettlementAccessRequiredContainerCustomAction;
import necesse.level.maps.levelData.settlementData.SettlementLevelData;

public class SetExplosionDamageAction extends SettlementAccessRequiredContainerCustomAction {
    public SetExplosionDamageAction(ExtraSettlementContainer container) {
        super(container);
    }

    public void runAndSend(boolean doExplosionDamage) {
        Packet content = new Packet();
        PacketWriter writer = new PacketWriter(content);
        writer.putNextBoolean(doExplosionDamage);
        runAndSendAction(content);
    }

    @Override
    public void executePacket(PacketReader reader, SettlementLevelData data, ServerClient client) {
        ExtraSettlementLevelData extraData = (ExtraSettlementLevelData) data;
        extraData.doExplosionDamage = reader.getNextBoolean();
        extraData.sendEvent(SetExplosionDamageEvent.class);
    }
}
