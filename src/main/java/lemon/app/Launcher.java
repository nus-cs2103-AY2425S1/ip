package lemon.app;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.util.Duration;
import lemon.Lemon;
import lemon.Response;
import lemon.gui.LemonFx;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    // Set this to use Command line instead
    public final static boolean IS_GUI = true;
    public static Response out = new Response();
    public static void main(String[] args) {
        if (IS_GUI) {
            Application.launch(LemonFx.class, args);
        } else {
            Lemon lemon = new Lemon();
            lemon.executeLemon();
        }
    }

    public static void exit() {
        out.disableWindow();
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished( event -> Platform.exit() );
        delay.play();
    }
}
