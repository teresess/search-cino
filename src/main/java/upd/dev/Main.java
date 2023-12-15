package upd.dev;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import upd.dev.load.Loader;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.telegram.telegrambots.meta.TelegramBotsApi;


public class Main {
    static Loader loader;

    public static void main(String[] args) throws TelegramApiException {

        TelegramBotsApi tba = new TelegramBotsApi(DefaultBotSession.class);
        System.out.println("main: loading...");
        loader = new Loader();
        tba.registerBot(loader);
    }
    public static Loader getBot() {
        return loader;
    }
}