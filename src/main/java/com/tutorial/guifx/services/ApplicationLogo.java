package com.tutorial.guifx.services;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApplicationLogo {
    private static final int TOLERANCE_THRESHOLD = 0xCF;
    private static Image logo;
    private static Image makeTransparent(Image inputImage) {
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

    public ApplicationLogo() {
        try {
            logo = makeTransparent(new Image(ApplicationLogo.class.getResource("/com/tutorial/guifx/logo.png").openStream()));
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static Image getLogo(){
        return logo;
    }
}
