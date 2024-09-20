package kafka;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class KafkaLauncher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
