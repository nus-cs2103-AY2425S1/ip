import mollyexception.MollyException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.util.Scanner;

/**
 * Molly bot class containing static methods that help Molly interact with user.
 */
public class Molly {

    public static String[] helpCommands = {
            "todo (description) - creates a new todo", "deadline (description) /by (end time or date) - creates a deadline",
            "event (description) /from (start time or date) /to (end time or date) - creates an event",
            "list - shows you all your tasks",
            "mark (task number) / unmark (task number) - marks or unmarks your tasks as done",
            "delete (task number) - removes a task"
    };
    private Storage storage;


    public Molly(String filePath) {
        this.storage = new Storage(filePath);
    }


    /**
     * Processes user inputs and manipulates the task array accordingly.
     */
    public void assistUser() throws MollyException {
        //ArrayList<task.Task> botMemory = new ArrayList<>();
        Ui.greetUser();
        TaskList taskList = new TaskList(storage.loadTasks());
        Scanner botScanner = new Scanner(System.in);
        Parser MollyParser = new Parser(storage, taskList);
        String userInput = "";
        Ui.printLine();
        while (!userInput.toLowerCase().equals("bye")) {
            try {
                userInput = botScanner.nextLine();

                if (userInput.toLowerCase().equals("bye")) {
                    break;
                }
                MollyParser.handleUserInput(userInput);

            } catch (MollyException e) {
                System.out.println(e.getMessage());
                Ui.printLine();
            }

        }
        storage.saveTasks(taskList);
        Ui.sayBye();

    }


    public static void main(String[] args) throws MollyException {
        new Molly("./data/Molly.txt").assistUser();
    }
}
