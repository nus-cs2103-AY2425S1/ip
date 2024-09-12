package tars;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindowTest extends ApplicationTest {

    private MainWindow controller;

    @BeforeAll
    public static void setUpClass() {
        if (Boolean.getBoolean("testfx.headless")) {
            System.setProperty("prism.order", "sw");
            System.setProperty("java.awt.headless", "true");
            System.setProperty("testfx.robot", "glass");
            System.setProperty("glass.platform", "Monocle");
            System.setProperty("monocle.platform", "Headless");
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        stage.setScene(new Scene(loader.load()));
        stage.show();
        controller = loader.getController();
    }

    @BeforeEach
    public void setup() {
        assertNotNull(controller, "MainWindow controller should be initialized");
    }

    @Test
    public void testWelcomeMessage() {
        FxAssert.verifyThat("#dialogContainer .label",
                LabeledMatchers.hasText("Hello! I'm TARS. What can I do for you today?"));
    }
}
