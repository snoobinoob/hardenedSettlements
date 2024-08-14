package hardenedSettlements.action;

import hardenedSettlements.event.SetExplosionDamageEvent;
import hardenedSettlements.leveldata.HardenedSettlementsLevelData;
import necesse.inventory.container.customAction.BooleanCustomAction;
import necesse.inventory.container.settlement.SettlementContainer;
import necesse.level.maps.Level;

public class SetExplosionDamageAction extends BooleanCustomAction {
    private final SettlementContainer container;

    public SetExplosionDamageAction(SettlementContainer container) {
        this.container = container;
    }

    @Override
    protected void run(boolean doExplosionDamage) {
        if (!container.client.isServer()) {
            return;
        }
        Level level = container.getLevelData().getLevel();
        HardenedSettlementsLevelData levelData = HardenedSettlementsLevelData.getDataCreateIfNoneExist(level);
        levelData.doExplosionDamage = doExplosionDamage;
        new SetExplosionDamageEvent(doExplosionDamage).applyAndSendToClientsAt(level);
    }
}
