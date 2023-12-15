package upd.dev.load.cmds;

import org.telegram.telegrambots.meta.api.objects.Update;
import upd.dev.actions.Actions;
import upd.dev.handler.Execute;
import upd.dev.load.Loader;

public class Start implements Execute {
    Loader bot;
    public Start(Loader bot) {
        this.bot = bot;
    }
    @Override
    public void exe(Update update) {
        Actions.createSend(bot, "<strong>Отправьте код нужного вам фильма:</strong>", String.valueOf(update.getMessage().getChatId())).exe();
    }
}