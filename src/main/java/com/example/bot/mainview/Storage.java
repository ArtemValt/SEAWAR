package com.example.bot.mainview;

import com.example.bot.model.BattleUser;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventBus;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.shared.Registration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class Storage {
    public int count = 0;
    @Getter
    private Queue<ChatMan> messages = new ConcurrentLinkedQueue<>();
    private ComponentEventBus eventBus = new ComponentEventBus(new Div());
    @Getter
    private HashMap<Integer, BattleUser> userStorage = new HashMap<>();

    @Getter
    @AllArgsConstructor
    public static class ChatMan {
        private String name;
        private String message;
    }


    public static class ChatEvent extends ComponentEvent<Div> {
        public ChatEvent() {
            super(new Div(), false);
        }
    }

    public void addRecord(String user, String message) {
        messages.add(new ChatMan(user, message));
        eventBus.fireEvent(new ChatEvent());
    }

    public void addRecordJoined(String user) {
        count++;
        userStorage.put(count, new BattleUser(String.valueOf(count), user));
        messages.add(new ChatMan("", user));
        eventBus.fireEvent(new ChatEvent());
    }

    public Registration attachListener(ComponentEventListener<ChatEvent> messageListener) {
        return eventBus.addListener(ChatEvent.class, messageListener);
    }

    public int size() {
        return messages.size();
    }
}