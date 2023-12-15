package upd.dev.handler;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Execute {
    void exe(Update update);
}
