package hardenedSettlements.event;

import necesse.engine.network.PacketReader;
import necesse.engine.network.PacketWriter;
import necesse.inventory.container.events.ContainerEvent;

public class SetExplosionDamageEvent extends ContainerEvent {
    public final boolean doExplosionDamage;

    public SetExplosionDamageEvent(boolean doExplosionDamage) {
        this.doExplosionDamage = doExplosionDamage;
    }

    public SetExplosionDamageEvent(PacketReader reader) {
        super(reader);
        doExplosionDamage = reader.getNextBoolean();
    }

    @Override
    public void write(PacketWriter writer) {
        writer.putNextBoolean(doExplosionDamage);
    }
}
