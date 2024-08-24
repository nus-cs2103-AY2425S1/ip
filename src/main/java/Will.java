import Task.Task;
import Task.TaskList;
import Utilities.Parser;
import Utilities.Storage;
import Utilities.Ui;

import java.util.ArrayList;
import java.util.Scanner;


public class Will {

    /**
     * Task management chatbot.
     */
    public static void main(String[] args) {

        ArrayList<Task> tasks = new ArrayList<>();
        Storage storage = new Storage();
        storage.load(tasks);

        String logo = "WILL";
        Ui ui = new Ui();
        ui.greetMsg(logo);

        Scanner scanner = new Scanner(System.in);

        TaskList taskList = new TaskList();
        Parser dateTimeParser = new Parser();
        Parser parser = new Parser();

        while (true) {
            String userInput = scanner.nextLine();
            if (parser.parseCommand(tasks, userInput, taskList, ui, dateTimeParser)){
                break;
            }
        }
    }
}
