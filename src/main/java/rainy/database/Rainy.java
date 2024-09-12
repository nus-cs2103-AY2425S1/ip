package rainy.database;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import rainy.gui.Main;
import rainy.rainyexceptions.InvalidIndexException;
import rainy.rainyexceptions.InvalidMarkAndUnmarkException;
import rainy.tasks.TaskTracker;

/**
 * Initializes the Rainy Chatbot.
 * The Rainy program is a greenfield project building a rudimentary chatbot.
 *
 * @author Tan Soo Yap
 * @since 2024-08-19
 */
public class Rainy {
    /**
     * Serves as the entry point for the Rainy chatbot.
     *
     * @param args  Command-line arguments passed to the program as an array of {@code String} objects.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    /**
     * Prints out an output based on user command.
     *
     * @param scanCommand  User command typed into the TextField.
     * @throws InvalidIndexException         Thrown by <code>TaskManager</code> object when user provides
     *                                       a non-existent task number.
     * @throws InvalidMarkAndUnmarkException Thrown by <code>Task</code> object when user wants to mark a
     *                                       marked tasked or unmark an unmarked task.
     * @throws IOException
     */
    public static void acceptInput(String scanCommand) throws InvalidIndexException, InvalidMarkAndUnmarkException,
            IOException {
        // Initialize UI, Storage, TaskTracker, Parser, and File objects
        UI ui = new UI();
        Storage storage = new Storage();
        TaskTracker tm;
        Parser ps = new Parser();
        File newFile = new File("src/main/java/rainy.txt");
        tm = storage.copyPreviousFiles(newFile);
        tm.receivedFirstInput();
        String messages = scanCommand;
        ps.firstInput(messages);
        String[] input = ps.getInput();
        String[] splitByTask = ps.getSplitByTask();
        String message = ps.getMessage();
        int count = ps.getCount();
        assert(count >= -1);
        Instructions instruction = ps.enumOperator(message);
        switch (instruction) {
        case LIST:
            System.out.println(tm.getList());
            break;

        case MARK:
            if (count != -1) {
                tm.markDone(count - 1);
            } else {
                ui.noCategoryDeclared();
            }
            if (tm.getCounter() > 0) {
                File f = new File("src/main/java/rainy.txt");
                storage.writeOverFile(f, tm);
            }
            break;

        case UNMARK:
            if (count != -1) {
                tm.unmarkDone(count - 1);
            } else {
                ui.noCategoryDeclared();
            }
            if (tm.getCounter() > 0) {
                File f = new File("src/main/java/rainy.txt");
                storage.writeOverFile(f, tm);
            }
            break;

        case DELETE:
            if (count != -1) {
                tm.delete(count - 1);
            } else {
                ui.noCategoryDeclared();
            }
            if (tm.getCounter() >= 0) {
                File f = new File("src/main/java/rainy.txt");
                storage.writeOverFile(f, tm);
            }
            break;

        case TODO:
            if (input.length == 1) {
                ui.noToDoDescription();
            } else {
                tm.updateListToDo(splitByTask[0].substring(5));
            }
            if (tm.getCounter() > 0) {
                File f = new File("src/main/java/rainy.txt");
                storage.writeOverFile(f, tm);
            }
            break;

        case DEADLINE:
            if (input.length == 1) {
                ui.noDeadlineDescription();
            } else if (splitByTask.length == 1) {
                ui.noEndDateDeadline();
            } else if (splitByTask.length < 4) {
                ui.invalidDateDeadline();
            } else {
                tm.updateListDeadline(splitByTask[0].substring(9), "" + splitByTask[3].substring(0, 4)
                    + "-" + splitByTask[2] + "-" + splitByTask[1].substring(3, 5)
                    + " " + splitByTask[3].substring(5, 9));
            }
            if (tm.getCounter() > 0) {
                File f = new File("src/main/java/rainy.txt");
                storage.writeOverFile(f, tm);
            }
            break;

        case EVENT:
            if (input.length == 1) {
                ui.noEventDescription();
            } else if (splitByTask.length < 5) {
                ui.invalidEventDate();
            } else {
                tm.updateListEvent(splitByTask[0].substring(6), splitByTask[3].substring(0, 4)
                    + "-" + splitByTask[2] + "-" + splitByTask[1].substring(3, 5), splitByTask[4]);
            }
            if (tm.getCounter() > 0) {
                File f = new File("src/main/java/rainy.txt");
                storage.writeOverFile(f, tm);
            }
            break;

        case SORT:
            tm.sortList();
            System.out.println(tm.getList());
            File f = new File("src/main/java/rainy.txt");
            storage.writeOverFile(f, tm);
            break;

        case FIND:
            tm.findTask(ps.findTask(messages));
            break;

        case BYE:
            f = new File("src/main/java/rainy.txt");
            storage.writeOverFile(f, tm);
            ui.goodbyeMessage();
            break;

        case INVALID:
            ui.noCategoryDeclared();
            break;

        default:
            ui.noCategoryDeclared();
        }
        System.out.print("^");
    }
}

