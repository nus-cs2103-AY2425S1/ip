package reo.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import reo.Reo;
import reo.contacts.Contact;
import reo.contacts.ContactList;
import reo.storage.ContactStorage;
import reo.storage.TaskStorage;
import reo.tasks.Task;
import reo.tasks.TaskList;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A GUI for Duke using FXML.
 */
public class Ui extends Application {

    private Reo reo = new Reo();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            TaskStorage taskStorage = new TaskStorage("./data/reo.txt");
            TaskList tasklist;
            try {
                tasklist = new TaskList(taskStorage.readFile());
            } catch (Exception e) {
                tasklist = new TaskList(new ArrayList<Task>());
            }

            ContactStorage contactStorage = new ContactStorage("./data/contacts.txt");
            ContactList contactList;

            try {
                contactList = new ContactList(contactStorage.readFile());
            } catch (Exception e) {
                contactList = new ContactList(new ArrayList<Contact>());
            }

            fxmlLoader.<MainWindow>getController().setProperties(tasklist, taskStorage, contactList, contactStorage);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
