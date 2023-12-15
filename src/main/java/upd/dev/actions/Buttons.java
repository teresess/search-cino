package upd.dev.actions;

import upd.dev.actions.impl.btnbuilder.UnderKeyboardBuilder;
import upd.dev.actions.impl.btnbuilder.UnderMessageBuilder;


public class Buttons {
    public static UnderMessageBuilder createUnderMessage(String label, String callback_date) {
        return new UnderMessageBuilder(label, callback_date);
    }
    public static UnderKeyboardBuilder createUnderKeyboard(String label) {
        return new UnderKeyboardBuilder(label);
    }
}
