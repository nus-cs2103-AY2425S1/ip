import java.io.IOException;

import chatbot.bot.Bword;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/** Main class to enable the GUI */
public class Main extends Application {
    private static final String FILELOCATION = "./data/bword.txt";
    private Bword bword = new Bword(FILELOCATION);


    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBword(bword); // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}


/*
     * Creates a dialog box containing user input, and appends it to
     * the dialog container. Clears the user input after processing.

private void handleUserInput() {
    String userText = userInput.getText();
    String dukeText = bword.getResponse(userInput.getText());
    dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(userText, userImage),
            DialogBox.getDukeDialog(dukeText, dukeImage)
    );
    userInput.clear();
}

import chatbot.bot.Bword;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {
    private static final String FILELOCATION = "./data/bword.txt";

    private Bword bword;

    public Main(String filePath) {
        this.bword = new Bword(filePath);
    }

    public Main() {
        this.bword = new Bword(FILELOCATION);
    }


    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our scene
        stage.show(); // Render the stage.
    }
}
*/
