package de.oul.gamejam.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import de.oul.gamejam.JamGame;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.ChoiceDialog;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Launches the desktop (LWJGL3) application.
 */
public class Lwjgl3Launcher {
    private static int width;
    private static int height;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JFXPanel();
            Platform.runLater(() -> {
                List<String> choices = new ArrayList<>();
                choices.add("1600 x 900");
                choices.add("1400 x 900");
                choices.add("1366 x 768");
                choices.add("1280 x 800");

                ChoiceDialog<String> dialog = new ChoiceDialog<>("1600 x 900", choices);
                dialog.setTitle("Ying and Jam");
                dialog.setHeaderText("Welcome");
                dialog.setContentText("Choose your Resolution:");

                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()) {
                    switch (result.get()) {
                        case "1600 x 900":
                            width = 1600;
                            height = 900;
                            break;
                        case "1400 x 900":
                            width = 1400;
                            height = 900;
                            break;
                        case "1366 x 768":
                            width = 1366;
                            height = 768;
                            break;
                        case "1280 x 800":
                            width = 1280;
                            height = 800;
                            break;
                    }
                    createApplication();
                }
            });
        });
        //createApplication();
    }

    private static Lwjgl3Application createApplication() {
        return new Lwjgl3Application(new JamGame(), getDefaultConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration getDefaultConfiguration() {
        Lwjgl3ApplicationConfiguration configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setTitle("OULGameJam");
        configuration.setWindowedMode(width, height);
        configuration.setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png");
        return configuration;
    }

}
