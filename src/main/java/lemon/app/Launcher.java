package lemon.app;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.util.Duration;
import lemon.Lemon;
import lemon.Response;
import lemon.gui.LemonFx;

/**
 * Launcher class to workaround classpath issues.
 * Handle the ability to launch Lemon with GUI or command line
 * @author He Yiheng
 */
public class Launcher {
    public static final boolean IS_GUI = true;
    // Will be suppressing ConstantName to keep code similarity with System.out
    public static final Response out = new Response();
    public static void main(String[] args) {
        if (IS_GUI) {
            Application.launch(LemonFx.class, args);
        } else {
            Lemon lemon = new Lemon();
            lemon.executeLemon();
        }
    }

    /**
     * End launcher along with the JavaFx program with a 3-second delay
     */
    public static void exit() {
        out.disableWindow();
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }
}
