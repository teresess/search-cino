package upd.dev.handler;

import org.telegram.telegrambots.meta.api.objects.Update;
import upd.dev.handler.events.ButtonEvent;
import upd.dev.load.Loader;

import java.util.HashMap;
import java.util.Map;

public class ButtonHandler implements ButtonEvent {
    Map<String, Execute> btns = new HashMap<>();
    int counter = 0;
    public ButtonHandler(Loader bot) {


        System.out.printf("ButtonHandler: load %s buttons\n", counter);
    }
    @Override
    public void onButtonEvent(Update update) {
        String btnId = update.getCallbackQuery().getData();

        if (btns.containsKey(btnId)) {
            btns.get(btnId).exe(update);
        }
        if (btnId.contains("pay") || btnId.contains("req")) {
            btns.get("pay").exe(update);
        }
        if (btnId.contains("add") || btnId.contains("plus") || btnId.contains("minus")) {
            btns.get("add").exe(update);
        }
        if (btnId.contains("user")) {
            btns.get("user").exe(update);
        }
    }
    void regBtn(String name, Execute event) {
        btns.put(name, event);
        counter++;
    }
}
