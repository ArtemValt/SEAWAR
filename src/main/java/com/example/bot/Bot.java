package com.example.bot;

import lombok.Getter;
import lombok.Setter;

public class Bot {
    @Getter
    @Setter
    String message;
    String name = "Bot";


    public Bot(String message) {
        this.message = message;
    }

    public String say(String str) {
        return str;
    }
}
