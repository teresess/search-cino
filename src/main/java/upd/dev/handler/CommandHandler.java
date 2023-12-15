package upd.dev.handler;

import org.telegram.telegrambots.meta.api.objects.Update;
import upd.dev.LoadEnv;
import upd.dev.actions.Actions;
import upd.dev.dbmanager.DBManager;
import upd.dev.dbmanager.Types;
import upd.dev.dbmanager.parametrs.Option;
import upd.dev.dbmanager.parametrs.Where;
import upd.dev.load.cmds.AddFilm;
import upd.dev.load.cmds.Start;
import upd.dev.handler.events.CommandEvent;
import upd.dev.load.Loader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandHandler implements CommandEvent {
    Map<String, Execute> cmds = new HashMap<>();
    int counter = 0;
    Loader bot;
    public CommandHandler(Loader bot) {
        this.bot = bot;
        // start
        regCmd("/start", new Start(bot));

        // admin
        regCmd("addFilm", new AddFilm(bot));

        // manage

        // message

        System.out.printf("CommandHandler: load %s commands\n", counter);
    }
    @Override
    public void onCommandEvent(Update update) {
        String text = update.getMessage().getText(), chatId = String.valueOf(update.getMessage().getChatId());

        if (cmds.containsKey(text)) {
            cmds.get(text).exe(update);
        } else if (text.startsWith("af:")) {
            cmds.get("addFilm").exe(update);
        } else {
            try {
                int code = Integer.parseInt(text);
                List<List<String>> list = DBManager.createConnection().setPath("./cino.db").setTable("cino").setType(Types.SELECT)
                                .setOption(
                                        Option.addMore("film")
                                )
                        .setWhere(
                                Where.add("code", text)
                        ).exeQue();

                String mess;
                if (list.isEmpty()) {
                    mess = "По коду <strong>%s</strong>, ничего небыло найдено".formatted(code);
                } else {
                    mess = "По коду <strong>%s</strong>, был найден фильм: <strong>%s</strong>".formatted(code, list.get(0).get(0));
                }
                Actions.createSend(bot, mess, chatId).exe();
            } catch (NumberFormatException e) {
                Actions.createSend(bot, "<strong>%s</strong>, не код.".formatted(text), chatId).exe();
            }
        }
    }
    void regCmd(String name, Execute event) {
        cmds.put(name, event);
        counter++;
    }
}