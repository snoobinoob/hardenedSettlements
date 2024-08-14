package hardenedSettlements.form;

import hardenedSettlements.event.SetExplosionDamageEvent;
import hardenedSettlements.patch.SettlementContainerPatch;
import necesse.engine.GlobalData;
import necesse.engine.Settings;
import necesse.engine.localization.message.LocalMessage;
import necesse.engine.state.MainGame;
import necesse.gfx.forms.components.FormContentIconToggleButton;
import necesse.gfx.forms.components.FormInputSize;
import necesse.gfx.ui.ButtonColor;
import necesse.inventory.container.settlement.SettlementContainer;

public class ExplosionToggleButton extends FormContentIconToggleButton {
    private static final LocalMessage onMsg = new LocalMessage("ui", "defendzoneexplosionson");
    private static final LocalMessage offMsg = new LocalMessage("ui", "defendzoneexplosionsoff");

    public ExplosionToggleButton(SettlementContainer container, int x, int y) {
        super(x, y, FormInputSize.SIZE_24, ButtonColor.BASE, Settings.UI.button_checked_20, Settings.UI.button_escaped_20);
        setActive(container.getLevelLayer().doIHaveOwnerAccess(((MainGame) GlobalData.getCurrentState()).getClient()));
        onToggled(e -> {
            SettlementContainerPatch.setExplosionDamageAction.runAndSend(isToggled());
        });
        container.onEvent(SetExplosionDamageEvent.class, e -> {
            update(e.doExplosionDamage);
        });
    }

    private void update(boolean doExplosionDamage) {
        setToggled(doExplosionDamage);
        setTooltips(doExplosionDamage ? onMsg : offMsg);
    }
}
