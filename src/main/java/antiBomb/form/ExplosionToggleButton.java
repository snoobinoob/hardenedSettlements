package antiBomb.form;

import antiBomb.event.SetExplosionDamageEvent;
import antiBomb.extraSettlement.ExtraSettlementContainer;
import necesse.engine.Settings;
import necesse.engine.localization.message.LocalMessage;
import necesse.gfx.forms.components.FormContentIconToggleButton;
import necesse.gfx.forms.components.FormInputSize;
import necesse.gfx.ui.ButtonColor;

public class ExplosionToggleButton extends FormContentIconToggleButton {
    private static final LocalMessage onMsg = new LocalMessage("ui", "defendzoneexplosionson");
    private static final LocalMessage offMsg = new LocalMessage("ui", "defendzoneexplosionsoff");

    public ExplosionToggleButton(ExtraSettlementContainer container, int x, int y) {
        super(x, y, FormInputSize.SIZE_24, ButtonColor.BASE, Settings.UI.button_checked_20,
                Settings.UI.button_escaped_20);
        update(container.doExplosionDamage);
        onToggled(e -> container.setExplosionDamage.runAndSend(isToggled()));
        container.onEvent(SetExplosionDamageEvent.class, e -> update(e.doExplosionDamage));
    }

    private void update(boolean doExplosionDamage) {
        setToggled(doExplosionDamage);
        setTooltips(doExplosionDamage ? onMsg : offMsg);
    }
}
