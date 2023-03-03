package hardenedSettlements.extraSettlement;

import necesse.engine.network.Packet;
import necesse.engine.network.PacketWriter;
import necesse.engine.network.server.ServerClient;
import necesse.entity.objectEntity.SettlementFlagObjectEntity;
import necesse.inventory.container.settlement.events.SettlementBasicsEvent;
import necesse.level.maps.Level;

public class ExtraSettlementFlagObjectEntity extends SettlementFlagObjectEntity {
    public ExtraSettlementFlagObjectEntity(Level level, int x, int y) {
        super(level, x, y);
    }

    public void setExplosionDamage(boolean doExplosionDamage) {
        ExtraSettlementLevelData data = getLevelData();
        data.doExplosionDamage = doExplosionDamage;
    }

    public boolean getDoExplosionDamage() {
        ExtraSettlementLevelData data = ExtraSettlementLevelData.getData(getLevel());
        return data == null || data.doExplosionDamage;
    }

    @Override
    public ExtraSettlementLevelData getLevelData() {
        return ExtraSettlementLevelData.getDataCreateIfNoneExist(getLevel());
    }

    @Override
    public Packet getContainerContentPacket(ServerClient client) {
        Packet content = new Packet();
        ExtraSettlementLevelData levelData = getLevelData();
        PacketWriter writer = new PacketWriter(content);
        (new SettlementBasicsEvent(levelData)).write(writer);
        writer.putNextBoolean(getDoExplosionDamage());
        return content;
    }
}
