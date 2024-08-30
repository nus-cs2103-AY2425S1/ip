package duke.javafx;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow(stage);
        mainWindow.setDuke(duke);

        String rootCss = this.getClass().getResource("/css/root.css").toExternalForm();
        String scrollPaneCss = this.getClass().getResource("/css/scrollpane.css").toExternalForm();
        String vboxCss = this.getClass().getResource("/css/vbox.css").toExternalForm();
        String textFieldCss = this.getClass().getResource("/css/textfield.css").toExternalForm();
        String buttonCss = this.getClass().getResource("/css/button.css").toExternalForm();

        mainWindow.getScene().getStylesheets().addAll(rootCss, scrollPaneCss, vboxCss, textFieldCss, buttonCss);
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
