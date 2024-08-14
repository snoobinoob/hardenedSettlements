package hardenedSettlements.patch;

import hardenedSettlements.HardenedSettlements;
import hardenedSettlements.form.ExplosionToggleButton;
import necesse.engine.localization.message.LocalMessage;
import necesse.engine.modLoader.annotations.ModMethodPatch;
import necesse.gfx.forms.Form;
import necesse.gfx.forms.components.FormComponent;
import necesse.gfx.forms.components.FormLabel;
import necesse.gfx.forms.components.localComponents.FormLocalLabel;
import necesse.gfx.forms.presets.containerComponent.settlement.SettlementSettingsForm;
import necesse.gfx.gameFont.FontOptions;
import necesse.inventory.container.settlement.SettlementContainer;
import necesse.inventory.container.settlement.events.SettlementBasicsEvent;
import net.bytebuddy.asm.Advice;

public class SettlementSettingsFormPatch {
    public static ExplosionToggleButton button;
    public static boolean dataLoaded = false;

    @ModMethodPatch(target = SettlementSettingsForm.class, name = "init", arguments = {})
    public static class InitPatch {
        @Advice.OnMethodExit
        public static void onExit(@Advice.FieldValue("container") SettlementContainer container) {
            dataLoaded = false;
        }
    }

    @ModMethodPatch(target = SettlementSettingsForm.class, name = "update", arguments = {SettlementBasicsEvent.class})
    public static class UpdatePatch {
        @Advice.OnMethodExit
        public static void onExit(
                @Advice.This SettlementSettingsForm<?> thiz,
                @Advice.FieldValue("settings") Form settings
        ) {
            if (!HardenedSettlements.isActive) {
                return;
            }

            for (FormComponent comp : settings.getComponents()) {
                if (comp.getBoundingBox().y == 165) {
                    ((FormLocalLabel) comp).setPosition(5, 205);
                }
            }
            FormLocalLabel damageLabel = settings.addComponent(
                    new FormLocalLabel(new LocalMessage("ui", "defendzoneexplosiondamage"),
                            new FontOptions(16), FormLabel.ALIGN_LEFT, 40, 165));
            int x = (int) damageLabel.getBoundingBox().getMaxX();
            boolean prevSetting = button != null && button.isToggled();
            button = settings.addComponent(new ExplosionToggleButton(thiz.container, x + 5, 161));
            button.setToggled(prevSetting);
            if (!dataLoaded) {
                SettlementContainerPatch.requestExplosionDamageAction.runAndSend();
            }

            settings.setHeight(settings.getHeight() + 40);
        }
    }
}
