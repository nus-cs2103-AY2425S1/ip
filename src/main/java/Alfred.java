import java.io.IOException;
import java.util.Scanner;

public class Alfred {
    private TaskList tasks;
    private Storage storage;

    public Alfred(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            Ui.showLoadingError(e);
            this.tasks = new TaskList();
        } catch (AlfredException e) {
            Ui.showCorruptedSaveError(e);
            storage.clearStorage();
            tasks = new TaskList();
        }
    }

    public void run() {
        // Greet user
        Ui.greet();

        // Create a Scanner Object
        Scanner in = new Scanner(System.in);

        String input = in.nextLine();
        while (!input.equals("bye")) {
            String command = Parser.getCommand(input);

            switch (command) {
            case "list":
                Ui.showList(tasks.getTasks());
                break;
            case "mark":
            case "unmark":
                tasks.updateTaskStatus(input, command.equals("mark"));
                break;
            case "delete":
                tasks.deleteTask(input);
                break;
            default:
                if (Task.isCreateTaskCommand(input)) {
                    try {
                        Task task = Task.initialise(input);
                        tasks.addTask(task);
                        Ui.showAddedTaskMessage(task, tasks.getTasksCount());
                    } catch (AlfredException e) {
                        Ui.showAlfredError(e);
                    } catch (Exception e) {
                        Ui.showUnexpectedError(e);
                    }
                } else {
                    Ui.showInvalidCommand();
                }
                break;
            }
            input = in.nextLine();
        }
        // save tasks
        storage.saveTasks(tasks.getTasks());

        Ui.farewell();
    }

    public static void main(String[] args) {
        new Alfred("./data/Alfred.txt").run();
    }
}
