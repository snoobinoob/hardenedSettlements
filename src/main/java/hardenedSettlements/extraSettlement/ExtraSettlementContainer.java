package hardenedSettlements.extraSettlement;

import hardenedSettlements.action.SetExplosionDamageAction;
import hardenedSettlements.event.SetExplosionDamageEvent;
import necesse.engine.network.NetworkClient;
import necesse.engine.network.Packet;
import necesse.engine.network.PacketReader;
import necesse.entity.objectEntity.ObjectEntity;
import necesse.inventory.container.settlement.SettlementContainer;
import necesse.inventory.container.settlement.events.SettlementBasicsEvent;

public class ExtraSettlementContainer extends SettlementContainer {
    public SetExplosionDamageAction setExplosionDamage;

    public boolean doExplosionDamage;

    public ExtraSettlementContainer(NetworkClient client, int uniqueSeed, ObjectEntity oe,
            Packet content) {
        super(client, uniqueSeed, (ExtraSettlementFlagObjectEntity) oe, content);

        PacketReader reader = new PacketReader(content);
        new SettlementBasicsEvent(reader);
        doExplosionDamage = reader.getNextBoolean();
        setExplosionDamage = registerAction(new SetExplosionDamageAction(this));

        subscribeEvent(SetExplosionDamageEvent.class, e -> true, () -> true);
        onEvent(SetExplosionDamageEvent.class, e -> {
            if (client.isServerClient()) {
                ((ExtraSettlementFlagObjectEntity) oe).setExplosionDamage(e.doExplosionDamage);
            }
        });
    }

}
