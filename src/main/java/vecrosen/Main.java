package vecrosen;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The main logic of the application. Essentially a memo app.
 */
public class Main extends Application {
    private static Ui ui;
    private static TaskList taskList;
    private static File data;

    @Override
    public void start(Stage stage) {
        data = new File("data/vecrosen.txt");
        File datafolder = new File("data");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            ui = fxmlLoader.<Ui>getController();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!datafolder.exists()) {
            datafolder.mkdir();
        }
        try {
            taskList = new TaskList(data);
        } catch (IndexOutOfBoundsException | NoSuchElementException e) {
            ui.speak("Savefile is corrupted!");
            taskList = new TaskList();
        }
        ui.speak("Hello, I'm Vecrosen.");
        ui.speak("What can I do for you?");
    }

    /**
     * Determines the response to the given user input.
     * @param input Input provided by the user.
     */
    public static void respond(String input) {
        int itemNo;
        String desc;
        ArrayList<Object> parseArgs = new ArrayList<Object>();
        Parser.ActionType actionType = Parser.parse(input, parseArgs);
        switch (actionType) {
        case BYE:
            ui.speak("Bye. Hope to see you again soon!");
            return;
        case MARK:
            itemNo = (Integer) parseArgs.get(0);
            try {
                String target = taskList.setDone(itemNo, true);
                ui.speak("Task marked as complete: " + target);
            } catch (IndexOutOfBoundsException e) {
                ui.speak("Invalid task number!");
            }
            break;
        case UNMARK:
            itemNo = (Integer) parseArgs.get(0);
            try {
                String target = taskList.setDone(itemNo, false);
                ui.speak("Task marked as incomplete: " + target);
            } catch (IndexOutOfBoundsException e) {
                ui.speak("Invalid task number!");
            }
            break;
        case list:
            taskList.printList(ui);
            break;
        case TODO:
            desc = (String) parseArgs.get(0);
            taskList.addTask(new Task(desc));
            ui.speak("Todo added: " + desc);
            break;
        case DEADLINE:
            desc = (String) parseArgs.get(0);
            taskList.addTask(new Deadline(desc, (String) parseArgs.get(1)));
            ui.speak("Deadline added: " + desc);
            break;
        case EVENT:
            desc = (String) parseArgs.get(0);
            taskList.addTask(new Event(desc, (String) parseArgs.get(1), (String) parseArgs.get(2)));
            ui.speak("Event added: " + desc);
            break;
        case DELETE:
            itemNo = (Integer) parseArgs.get(0);
            String target = taskList.deleteTask(itemNo);
            ui.speak("Removing task: " + target);
            ui.speak("You now have " + taskList.getListSize() + " tasks left in record.");
            break;
        case FIND:
            ArrayList<Integer> indices = new ArrayList<Integer>();
            ArrayList<Task> res = taskList.find((String) parseArgs.get(0), indices);
            ui.printList(res, indices, "Here are the matching tasks:", "No matches!");
            break;
        case FORMATTING:
            ui.alertInvalidFormat((String) parseArgs.get(0));
            break;
        case UNDEFINED:
            ui.speak("Sorry, I don't understand.");
            ui.speak("Commands: todo deadline event mark unmark delete list bye");
            break;
        default:
            System.err.println("Action type not recognized!");
        }
        taskList.save(data);
    } // TODO shorten
}
