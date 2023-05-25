package com.tutorial.guifx;

import com.tutorial.guifx.services.ApplicationLogo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.builder.SpringApplicationBuilder;
import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() throws Exception {
        applicationContext = new SpringApplicationBuilder(ProcessApplication.class).headless(false).run();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
        Image icon = ApplicationLogo.getLogo();
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        String mainCss = Objects.requireNonNull(Main.class.getResource("main.css")).toExternalForm();
        scene.getStylesheets().add(mainCss);
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("TodoApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

//    public static void main(String[] args) {
//        launch();
//    }
}