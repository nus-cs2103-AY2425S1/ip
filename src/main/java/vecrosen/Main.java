package vecrosen;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javafx.application.Application;
import javafx.application.Platform;
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
            //ui.speak("Savefile is corrupted!");
            taskList = new TaskList();
        }
        //ui.speak("Hello, I'm Vecrosen.");
        //ui.speak("What can I do for you?");
    }

    /**
     * Determines the response to the given user input.
     * @param input Input provided by the user.
     */
    public static void respond(String input) {
        ArrayList<Object> parseArgs = new ArrayList<Object>();
        Parser.ActionType actionType = Parser.parse(input, parseArgs);
        performAction(actionType, parseArgs);
        taskList.save(data);
    }

    private static void performAction(Parser.ActionType actionType, ArrayList<Object> parseArgs) {
        switch (actionType) {
        case BYE:
            exitProgram();
            return;
        case MARK:
            setCompletion((Integer) parseArgs.get(0), true);
            break;
        case UNMARK:
            setCompletion((Integer) parseArgs.get(0), false);
            break;
        case LIST:
            taskList.printList(ui);
            break;
        case TODO:
            // Fallthrough
        case DEADLINE:
            // Fallthrough
        case EVENT:
            addTask(actionType, parseArgs);
            break;
        case DELETE:
            deleteTask((Integer) parseArgs.get(0));
            break;
        case FIND:
            handleFind((String) parseArgs.get(0));
            break;
        case FORMATTING:
            ui.alertInvalidFormat((String) parseArgs.get(0));
            break;
        case UNDEFINED:
            handleUndefined();
            break;
        default:
            System.err.println("Action type not recognized!");
        }
    } // I don't see a non-redundant way to shorten this any further.

    private static void setCompletion(Integer itemNo, boolean isDone) {
        if (itemNo > taskList.getListSize()) {
            ui.speak("Invalid task number!");
            return;
        }
        String target = taskList.setDone(itemNo, isDone);
        String newStateString = "incomplete";
        if (isDone) {
            newStateString = "complete";
        }
        ui.speak("Task marked as " + newStateString + ": " + target);
    }

    private static void addTask(Parser.ActionType taskType, ArrayList<Object> args) {
        String desc = (String) args.get(0);
        Task task;
        String typeString;
        switch (taskType) {
        case TODO:
            task = new Task(desc);
            typeString = "Todo";
            break;
        case DEADLINE:
            task = new Deadline(desc, (String) args.get(1));
            typeString = "Deadline";
            break;
        case EVENT:
            task = new Event(desc, (String) args.get(1), (String) args.get(2));
            typeString = "Event";
            break;
        default:
            throw new IllegalArgumentException("taskType does not correspond to the action of adding a task");
        }
        taskList.addTask(task);
        ui.speak(typeString + " added: " + desc);
    }

    private static void deleteTask(Integer itemNo) {
        if (itemNo >= taskList.getListSize()) {
            ui.speak("Invalid task number!");
            return;
        }
        String target = taskList.deleteTask(itemNo);
        ui.speak("Removing task: " + target);
        ui.speak("You now have " + taskList.getListSize() + " tasks left in record.");
    }

    private static void handleFind(String term) {
        ArrayList<Integer> indices = new ArrayList<Integer>();
        ArrayList<Task> res = taskList.find(term, indices);
        ui.printList(res, indices, "Here are the matching tasks:", "No matches!");
    }

    private static void handleUndefined() {
        ui.speak("Sorry, I don't understand.");
        ui.speak("Commands: todo deadline event mark unmark delete list bye");
    }

    private static void exitProgram() {
        ui.speak("Bye. Hope to see you again soon!");
        Platform.exit();
    }
}
