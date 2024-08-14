package hardenedSettlements.action;

import hardenedSettlements.event.SetExplosionDamageEvent;
import hardenedSettlements.leveldata.HardenedSettlementsLevelData;
import necesse.engine.network.Packet;
import necesse.engine.network.PacketReader;
import necesse.engine.network.server.ServerClient;
import necesse.inventory.container.settlement.SettlementContainer;
import necesse.inventory.container.settlement.actions.SettlementAccessRequiredContainerCustomAction;
import necesse.level.maps.levelData.settlementData.SettlementLevelData;

public class RequestExplosionDamageAction extends SettlementAccessRequiredContainerCustomAction {
    public RequestExplosionDamageAction(SettlementContainer container) {
        super(container);
    }

    public void runAndSend() {
        this.runAndSendAction(new Packet());
    }

    @Override
    public void executePacket(PacketReader reader, SettlementLevelData settlementData, ServerClient client) {
        HardenedSettlementsLevelData data = HardenedSettlementsLevelData.getDataCreateIfNoneExist(client.getLevel());
        new SetExplosionDamageEvent(data.doExplosionDamage).applyAndSendToClient(client);
    }
}
