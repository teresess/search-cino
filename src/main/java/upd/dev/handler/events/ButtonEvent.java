package upd.dev.handler.events;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface ButtonEvent {
    void onButtonEvent(Update update);
}
