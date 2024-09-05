import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Duke {
    private final static String FILE_NAME = "data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

//    private static void addItem(String inp) {
//        userInputs.add(new Task(inp));
//    }
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        // Tesla calls storage to pass the stored stuff to task list
        // UI greets and then prompts user for response and returns the user's response to the program. Parser works through it
        // and modifies task list and signals ui accordingly for displaying necessary response to user
        // finally, when the user says bye and the
        ui.greet();
        String userResponse = ui.out();
        while (!Objects.equals(userResponse, "bye")) {
            try {
                Parser ps = new Parser(userResponse); //ui.out() gives the data from the user to parser, which processes and stores the data
                ps.process(tasks, ui); //ps.process(tasks) modifies task list and ui accordingly
                userResponse = ui.out();
            } catch (EmptyTaskException e) {
                System.out.println("The description of the task must contain some substance; it cannot be void.");
            } catch (EmptyCommandException e) {
                System.out.println("An empty command has been received.");
            } catch (InvalidInstructionException e) {
                System.out.println("The instruction provided is deemed invalid.");
            } catch (DateTimeParseException e) {
                System.out.println("Ah, esteemed inquirer, the date format you have provided is not correct." +
                        " It must be expressed as \"yyyy-mm-dd\".");
            }
        }
        try {
            storage.writeToFile(FILE_NAME,tasks);
        } catch (IOException e) {
            System.out.println("The endeavor to create the storage file has encountered an impediment." +
                    " I implore you to attempt this task once more in due course. Till then: ");
        }
        System.out.println("Farewell! Until we meet again.\n");

    }

    public static void main(String[] args) {
        new Duke(FILE_NAME).run();
    }
}
