package hardenedSettlements;

import hardenedSettlements.event.SetExplosionDamageEvent;
import hardenedSettlements.extraSettlement.ExtraSettlementContainer;
import hardenedSettlements.extraSettlement.ExtraSettlementLevelData;
import necesse.engine.GameLog;
import necesse.engine.modLoader.ModLoader;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.registries.ContainerRegistry;
import necesse.engine.registries.LevelDataRegistry;
import necesse.gfx.forms.presets.containerComponent.settlement.SettlementContainerForm;
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

    public static int EXTRA_SETTLEMENT_CONTAINER;

    public static boolean isActive;

    public void init() {
        isActive = ModLoader.getEnabledMods().stream().noneMatch(mod -> mod.isEnabled() && conflictingModIDs.contains(mod.id));
        if (!isActive) {
            return;
        }

        LevelDataRegistry.registerLevelData("extrasettlement", ExtraSettlementLevelData.class);

        ContainerEventRegistry.registerUpdate(SetExplosionDamageEvent.class);

        EXTRA_SETTLEMENT_CONTAINER =
                ContainerRegistry.registerOEContainer(
                        (client, seed, oe, content) -> new SettlementContainerForm<>(client,
                                new ExtraSettlementContainer(client.getClient(), seed, oe,
                                        content)),
                        (client, seed, oe, content, serverObj) -> new ExtraSettlementContainer(
                                client, seed, oe, content));
    }
}
