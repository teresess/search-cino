package upd.dev.load.cmds;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import upd.dev.LoadEnv;
import upd.dev.Methods;
import upd.dev.actions.Actions;
import upd.dev.dbmanager.DBManager;
import upd.dev.dbmanager.DBManagerBuilder;
import upd.dev.dbmanager.Types;
import upd.dev.dbmanager.parametrs.Option;
import upd.dev.handler.Execute;
import upd.dev.load.Loader;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AddFilm implements Execute {
    Loader loader;

    public AddFilm(Loader loader) {
        this.loader = loader;
    }

    @Override
    public void exe(Update update) {
        List<String> admins = loader.loadEnv().getAdminsId();
        String userId = String.valueOf(update.getMessage().getFrom().getId());

        if (!admins.contains(userId)) return;

        String text = update.getMessage().getText(), filmName = text.split(":")[1], chatId = String.valueOf(update.getMessage().getChatId());

        String code = generate(filmName, update.getMessage().getFrom().getUserName());
        Actions.createSend(loader, "Вы добавили фильм <strong>%s</strong>, его код <strong>%s</strong>".formatted(filmName, code), chatId).exe();
    }
    public String generate(String filmName, String userName) {
        String code = Methods.randomRange(1000, 9999);

        try {
            DBManagerBuilder dbManagerBuilder = new DBManagerBuilder().setPath("./cino.db");

            PreparedStatement preparedStatement = dbManagerBuilder.createStatement("INSERT INTO cino (code, film, username, date) VALUES ('%s', '%s', '%s', '%s')"
                    .formatted(code, filmName, userName, System.currentTimeMillis() / 1000));

            preparedStatement.execute();

            preparedStatement.close();
            dbManagerBuilder.connection.close();
        } catch (SQLException e) {
            return generate(filmName, userName);
        }
        return code;
    }
}
