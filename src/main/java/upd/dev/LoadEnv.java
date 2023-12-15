package upd.dev;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.Arrays;
import java.util.List;

public class LoadEnv {
    Dotenv dotenv;
    public LoadEnv() {
        this.dotenv = Dotenv.load();
    }
    public String getBotToken() {
        return dotenv.get("BOT_TOKEN");
    }
    public String getBotUserName() {
        return dotenv.get("BOT_USERNAME");
    }
    public List<String> getAdminsId() {
        return Arrays.stream(dotenv.get("ADMINS").split(":")).toList();
    }
}
