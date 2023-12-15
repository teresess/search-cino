package upd.dev.actions.impl.btnbuilder;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;

public class UnderKeyboardBuilder {
    String label;

    public UnderKeyboardBuilder(String label) {
        this.label = label;
    }
    public KeyboardButton build() {
        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText(label);

        return keyboardButton;
    }
}
