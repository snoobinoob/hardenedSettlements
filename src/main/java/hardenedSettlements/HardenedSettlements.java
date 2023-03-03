package hardenedSettlements;

import hardenedSettlements.event.SetExplosionDamageEvent;
import hardenedSettlements.extraSettlement.ExtraSettlementContainer;
import hardenedSettlements.extraSettlement.ExtraSettlementLevelData;
import necesse.engine.modLoader.annotations.ModEntry;
import necesse.engine.registries.ContainerRegistry;
import necesse.engine.registries.LevelDataRegistry;
import necesse.gfx.forms.presets.containerComponent.settlement.SettlementContainerForm;
import necesse.inventory.container.events.ContainerEventRegistry;

@ModEntry
public class HardenedSettlements {
    public static int EXTRA_SETTLEMENT_CONTAINER;

    public void init() {
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
