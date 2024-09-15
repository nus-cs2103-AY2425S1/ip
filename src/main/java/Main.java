import gui.MainWindow;
import impl.chatbot.Danny;
import impl.storage.Storage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private final Danny danny;


    /**
     * Overview of the whole program.
     * Initialises Danny and Storage loader.
     */
    public Main() {
        danny = new Danny();
        Storage loader;
        try {
            loader = new Storage("src/main/java/data/tasks.txt", danny);
            System.out.println("Loading previous lists...");
            loader.loadTask();
            System.out.println("Load Completed. Welcome back :)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Save not found, starting off with new list.");
        }
//        danny.run();
//        try {
//            assert loader != null;
//            loader.saveTask();
//            System.out.println("Saving successful");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            System.out.println("Saving unsuccessful :(");
//        }
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setVars(danny);  // inject the Danny instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        try {
            Storage loader = new Storage("src/main/java/data/tasks.txt", danny);
            loader.saveTask();
            System.out.println("Saving successful");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Saving unsuccessful :(");
        }
    }


}
