import ui.Ui;
import java.util.Scanner;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Meeks extends Application {
    private Mudkip meeks = new Mudkip();

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Meeks");
            FXMLLoader fxmlLoader = new FXMLLoader(Meeks.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMudkip(meeks);  // inject the Duke instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Echo echo = new Echo();
        Ui ui = new Ui();

        while (true) {
            String command = scanner.nextLine();
            echo.setWord(command);


            if (command.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
                continue;
            }
            System.out.print(echo.echoOut());

            if (command.equalsIgnoreCase("bye")) {
                break;
            }
        }
        scanner.close();
    }
}


