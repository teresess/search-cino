package upd.dev.actions;

import upd.dev.actions.impl.actionbuilder.EditBuilder;
import upd.dev.actions.impl.actionbuilder.SendBuilder;
import upd.dev.load.Loader;

public class Actions {
    public static SendBuilder createSend(Loader bot, String text, String chat_id) {
        return new SendBuilder(bot, text, chat_id);
    }
    public static EditBuilder createEdit(Loader bot, String text, String chat_id, int message_id) {
        return new EditBuilder(bot, text, chat_id, message_id);
    }
}
