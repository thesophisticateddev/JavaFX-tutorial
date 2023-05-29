package com.tutorial.guifx;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.tutorial.guifx")
public class ProcessApplication {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

}
