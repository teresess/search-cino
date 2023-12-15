package upd.dev.actions.impl.actionbuilder;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import upd.dev.load.Loader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SendBuilder {
    Loader bot;
    String text;
    String chat_id;
    List<List<InlineKeyboardButton>> underMessage = new ArrayList<>();
    ReplyKeyboardMarkup underKeyboard;

    public SendBuilder(Loader bot, String text, String chat_id) {
        this.bot = bot;
        this.text = text;
        this.chat_id = chat_id;
    }
    public SendBuilder addRow(InlineKeyboardButton... btns) {

        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>(Arrays.asList(btns));
        underMessage.add(inlineKeyboardButtons);

        return this;
    }
    public SendBuilder addRow(KeyboardButton... btns) {
        KeyboardRow row = new KeyboardRow();
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        row.addAll(Arrays.asList(btns));

        if (underKeyboard != null) {
            keyboardRows = underKeyboard.getKeyboard();
        }
        keyboardRows.add(row);

        underKeyboard = new ReplyKeyboardMarkup();
        underKeyboard.setKeyboard(keyboardRows);

        return this;
    }

    public void exe() {
        SendMessage sendMessage = new SendMessage();

        sendMessage.setChatId(chat_id);
        sendMessage.setText(text);
        sendMessage.enableHtml(true);

        if (underMessage != null) {
            sendMessage.setReplyMarkup(new InlineKeyboardMarkup(underMessage));
        }
        if (underKeyboard != null) {
            underKeyboard.setOneTimeKeyboard(false);
            sendMessage.setReplyMarkup(underKeyboard);
        }

        try {
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}