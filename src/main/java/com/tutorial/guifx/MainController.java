package com.tutorial.guifx;

import com.tutorial.guifx.dto.TodoDto;
import com.tutorial.guifx.services.ApplicationLogo;
import com.tutorial.guifx.views.TodoCellController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
@Slf4j
public class MainController implements Initializable {
    private static final int TOLERANCE_THRESHOLD = 0xCF;
    @FXML
    public VBox todoList;

    @FXML
    public ImageView mainLogo;

//    @FXML
//    private ListView<TodoDto> todoList;

    public void loadList() {
        List<TodoDto> data = new ArrayList<>();
        data.add(new TodoDto(1L, "hello", "this is description"));
        data.add(new TodoDto(2L, "hello", "this is another description"));
        data.forEach(eachItem -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("todoCell.fxml"));
            try {
                VBox box = loader.load();
                TodoCellController cell = loader.getController();
                cell.setData(eachItem);
                todoList.getChildren().add(box);
            } catch (IOException e) {
                log.error("Exception occured", e);
            }
        });
    }

    private Image makeTransparent(Image inputImage) {
        int W = (int) inputImage.getWidth();
        int H = (int) inputImage.getHeight();
        WritableImage outputImage = new WritableImage(W, H);
        PixelReader reader = inputImage.getPixelReader();
        PixelWriter writer = outputImage.getPixelWriter();
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                int argb = reader.getArgb(x, y);

                int r = (argb >> 16) & 0xFF;
                int g = (argb >> 8) & 0xFF;
                int b = argb & 0xFF;

                if (r >= TOLERANCE_THRESHOLD
                        && g >= TOLERANCE_THRESHOLD
                        && b >= TOLERANCE_THRESHOLD) {
                    argb &= 0x00FFFFFF;
                }

                writer.setArgb(x, y, argb);
            }
        }

        return outputImage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
//            todoList.setCellFactory(new TodoCellFactory());
//            todoList.getItems().add(new TodoDto(1L,"hello","this is description"));
//            Image logoPng = new Image(getClass().getResource("logo.png").openStream());

            mainLogo.setImage(ApplicationLogo.getLogo());


            loadList();
        } catch (Exception e) {
            log.error("Failed to initialize screen", e);
        }
    }
}