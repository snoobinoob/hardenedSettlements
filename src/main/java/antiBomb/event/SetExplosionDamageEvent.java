package antiBomb.event;

import antiBomb.extraSettlement.ExtraSettlementLevelData;
import necesse.engine.network.PacketReader;
import necesse.engine.network.PacketWriter;
import necesse.inventory.container.events.ContainerEvent;
import necesse.level.maps.levelData.settlementData.SettlementLevelData;

public class SetExplosionDamageEvent extends ContainerEvent {
    public final boolean doExplosionDamage;

    public SetExplosionDamageEvent(SettlementLevelData data) {
        doExplosionDamage = ((ExtraSettlementLevelData) data).doExplosionDamage;
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
