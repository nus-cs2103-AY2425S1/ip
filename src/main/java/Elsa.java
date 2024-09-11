import java.util.ArrayList;
import java.util.List;

/**
 * Represents Elsa, a chatbot.
 * @author Aaron
 */
public class Elsa {
    private TaskList tasks;
    private final Ui ui;

    public Elsa() {
        ui = new Ui();

        try {
            Storage storage = new Storage(); // Initialisation of the storage may throw an ElsaException
            List<Task> savedTasks = storage.populateTaskList();
            tasks = new TaskList(savedTasks);
        } catch (ElsaException e) {
            ui.showError("Oops, looks like an error occurred when loading the tasks: " + e.getMessage());
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        ui.greetUser();

        boolean isGoodbye = false;
        while (!isGoodbye) {
            try {
                String originalCommand = ui.readCommand();
                Command command = Parser.parse(originalCommand);
                command.execute(tasks, ui);
                isGoodbye = command.isGoodbye();
            } catch (ElsaException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Elsa().run();
    }
}
