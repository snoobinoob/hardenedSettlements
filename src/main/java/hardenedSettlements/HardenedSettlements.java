package hardenedSettlements;

import hardenedSettlements.event.SetExplosionDamageEvent;
import hardenedSettlements.leveldata.HardenedSettlementsLevelData;
import necesse.engine.Settings;
import necesse.engine.modLoader.ModLoader;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.registries.LevelDataRegistry;
import necesse.inventory.container.events.ContainerEventRegistry;

import java.util.HashSet;
import java.util.Set;

@ModEntry
public class HardenedSettlements {
    private static final Set<String> conflictingModIDs = new HashSet<>();

    static {
        conflictingModIDs.add("settlementexpansion");
        conflictingModIDs.add("bettersettlementpvp");
    }

    public static boolean isActive;

    public void init() {
        isActive = ModLoader.getEnabledMods().stream().noneMatch(mod -> conflictingModIDs.contains(mod.id));
        if (!isActive) {
            return;
        }

        LevelDataRegistry.registerLevelData("hardenedsettlements", HardenedSettlementsLevelData.class);

        ContainerEventRegistry.registerUpdate(SetExplosionDamageEvent.class);
    }

    public void postInit() {
        Settings.UI.loadTextures();
    }
}
