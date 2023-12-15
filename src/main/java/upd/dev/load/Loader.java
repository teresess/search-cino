package upd.dev.load;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import upd.dev.dbmanager.DBManagerBuilder;
import upd.dev.handler.ButtonHandler;
import upd.dev.handler.CommandHandler;
import upd.dev.LoadEnv;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Loader extends TelegramLongPollingBot {
    CommandHandler commandHandler;
    ButtonHandler buttonHandler;
    LoadEnv loadEnv;


    @SuppressWarnings("deprecation")
    public Loader() {
        loadEnv = new LoadEnv();
        System.out.println("Dotenv: is load");

        commandHandler = new CommandHandler(this);
        buttonHandler = new ButtonHandler(this);
        System.out.println("Loader: all handlers is loaded");

//        loadDatabase();
    }
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            commandHandler.onCommandEvent(update);
        } else if (update.hasCallbackQuery()) {
            buttonHandler.onButtonEvent(update);
        }
    }

    public LoadEnv loadEnv() {
        return loadEnv;
    }
    public void loadDatabase() throws SQLException {
        DBManagerBuilder dbManagerBuilder = new DBManagerBuilder().setPath("./cino.db");
        PreparedStatement st = dbManagerBuilder.createStatement("CREATE DATABASE IF NOT EXISTS cino");

        if (st == null) {
            System.out.println("Database: created");
        } else {
            System.out.println("Database: connected");
        }
        st.execute();
    }
    @Override
    public String getBotUsername() {
        return loadEnv.getBotUserName();
    }
    @SuppressWarnings("deprecation")
    @Override
    public String getBotToken() {
        return loadEnv.getBotToken();
    }
    public void onRegister() {
        System.out.println("Bot: is load");
    }
}
