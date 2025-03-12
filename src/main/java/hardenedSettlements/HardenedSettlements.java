package hardenedSettlements;

import hardenedSettlements.event.SetExplosionDamageEvent;
import hardenedSettlements.leveldata.HardenedSettlementsLevelData;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.registries.LevelDataRegistry;
import necesse.inventory.container.events.ContainerEventRegistry;

@ModEntry
public class HardenedSettlements {

    public void init() {
        LevelDataRegistry.registerLevelData("hardenedsettlements", HardenedSettlementsLevelData.class);

        ContainerEventRegistry.registerUpdate(SetExplosionDamageEvent.class);
    }
}
