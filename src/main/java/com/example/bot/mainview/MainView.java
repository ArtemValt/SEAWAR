package com.example.bot.mainview;

import com.example.bot.battleLogic.battleship.Battle;
import com.github.rjeschke.txtmark.Processor;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;

import java.text.SimpleDateFormat;
import java.util.Date;

@Route("/seaGame")
public class MainView extends VerticalLayout {
    int ready = ;
    int countPlay = 0;
    Battle battle = new Battle();
    private final Storage storage;
    private Registration registration;

    private Grid<Storage.ChatMan> grid;
    private VerticalLayout chat;
    private VerticalLayout login;
    private String user = "";

    public MainView(Storage storage) {
        this.storage = storage;
        buildLogin();
        buildChat();
    }


    private void buildLogin() {
        login = new VerticalLayout() {{
            TextField field = new TextField();
            field.setPlaceholder("Напиши ок,если готов");
            add(
                    new H3("Начало игры\n" +
                            "В игре принимают участие два игрока. Они берут по одному игровому набору, располагаются так, чтобы не видеть игровое поле противника, и готовятся к игре.\n" +
                            "\n" +
                            "Игровые наборы располагаются так, как указано на рисунке (смотри в полной версии правил), а на игровых полях располагаются корабли.\n" +
                            "\n" +
                            "Внимание! Корабли не должны соприкасаться, то есть расстояние между ними должно быть не менее одной клетки.\n" +
                            "\n" +
                            "Внимание! Переставлять свои корабли после начала игры категорически запрещено.\n" +
                            "\n" +
                            "Ход игры\n" +
                            "После того как корабли расставлены, можно начинать игру.\n" +
                            "\n" +
                            "Существуют два основных варианта игры:\n" +
                            "\n" +
                            "Стрельбу игроки ведут строго по очереди.\n" +
                            "Стрельбу игроки ведут \"до первого промаха\", то есть если игрок попал в корабль противника, он производит следующий выстрел, и только после его промаха ход переходит к другому игроку.\n" +
                            "На игровом поле размещают свои корабли и отражают результаты выстрелов противника. Экран служит, чтобы отражать результаты собственных выстрелов по противнику. На поле и на экране промах обозначается белой фишкой, а попадание — красной. Каждый выстрел имеет свои координаты. Стреляющий должен называть координаты громко и четко. Например: «Выстрел по А4!»\n" +
                            "\n" +
                            "Игрок, по кораблям которого ведется огонь, отмечает выстрел на своем игровом поле:\n" +
                            "\n" +
                            "в случае промаха - белой\n" +
                            "в случае попадания - красной\n" +
                            "Затем он четко объявляет результат выстрела:\n" +
                            "\n" +
                            "вариант 1. \"Промах!\" - игрок не попал в корабль\n" +
                            "вариант 2. \"Ранил!\" - игрок попал в корабль, но не уничтожил.\n" +
                            "вариант 3. \"Потопил!\" - корабль уничтожен.\n" +
                            "Узнав результат своего выстрела, игрок отмечает его белой или красной фишкой на экране. Чтобы уничтожить корабль, требуется определенное количество попаданий. Оно соответствует числе отверстий под красные фишки на его палубе.\n" +
                            "\n" +
                            "Победитель тот, кто первым уничтожит все корабли противника"),
                    field,
                    new Button("Start Game") {{
                        addClickListener(click -> {
                            login.setVisible(false);
                            chat.setVisible(true);

                            user = field.getValue();
                            storage.addRecordJoined(user);

                        });
                        addClickShortcut(Key.ENTER);
                    }}
            );
        }};
        add(login);
    }

    private void buildChat() {
        chat = new VerticalLayout();
        add(chat);
        chat.setVisible(false);

        grid = new Grid<>();
        grid.setItems(storage.getMessages());

        grid.addColumn(new ComponentRenderer<>(message -> new Html(renderRow(message))))
                .setAutoWidth(true);
        grid.addColumn(new ComponentRenderer<>(message -> new Html(botSay(message))))
                .setAutoWidth(true);

        TextField field = new TextField();

        chat.add(

                new H3("Sea Battle"),
                grid,
                new HorizontalLayout() {{
                    add(
                            field,
                            new Button("➡") {{
                                addClickListener(click -> {
                                    storage.addRecord(user, field.getValue());
                                    field.clear();
                                });
                                addClickShortcut(Key.ENTER);
                            }}
                    );
                }}
        );
    }

    public void onMessage(Storage.ChatEvent event) {
        if (getUI().isPresent()) {
            UI ui = getUI().get();
            ui.getSession().lock();
            ui.beforeClientResponse(grid, ctx -> grid.scrollToEnd());
            ui.access(() -> grid.getDataProvider().refreshAll());
            ui.getSession().unlock();
        }
    }

    private String renderRow(Storage.ChatMan message) {
        if (message.getName().isEmpty())
            return Processor.process(String.format("_User **%s** подключился к просмотру игры_", message.getMessage()));
        else
            return Processor.process(String.format(message.getMessage(), ""));
    }

    private String botSay(Storage.ChatMan message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String date = simpleDateFormat.format(new Date());
        try {
            if (message.getName().isEmpty()) {
                countPlay++;
                if (countPlay == 1)
                    return Processor.process(String.format("_Oжидание второго игрока !_", ""));
                else if (countPlay == 2)
                    return Processor.process(String.format("_Ссылка для первого и  второго игрока **%s** !_",
                            "http://localhost:8080/locOneField \n " +
                                    "http://localhost:8080/locSecField"));
            } else if (message.getMessage().equals("готово")) {
                ready++;
                if (ready == 2)
                    return Processor.process(String.format("_Ссылка для первого и второго игрока **%s** !_ \n", "http://localhost:8080/shoo1 \n http://localhost:8080/shoo2"));
                return Processor.process(String.format("Bot say : оджидание второго игрока ", date));
            } else if (battle.whoWin(storage.getUserStorage().get(1)) || battle.whoWin(storage.getUserStorage().get(2)))
                return Processor.process(String.format(" _У нас есть победитель !_ ",
                        "http://localhost:8080/showdetails сылка для просмотра результатов"));
            return Processor.process(String.format(date, " "));
        } catch (NullPointerException e) {
            return Processor.process(String.format(date, " "));
        }
    }


    @Override
    protected void onAttach(AttachEvent attachEvent) {
        registration = storage.attachListener(this::onMessage);
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        registration.remove();
    }
}