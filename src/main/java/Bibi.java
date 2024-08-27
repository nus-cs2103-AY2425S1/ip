import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Bibi {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Bibi(String filePath) {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(filePath);

        // Init
        ui.printWelcomeMessage();
        storage.initializeDataDirectory();
        try {
            storage.restoreTasks(tasks);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        ui.printHorizontalLine();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        String cmd;
        int index;

        while (scanner.hasNext()) {
            // Preconfigured commands
            switch (cmd = scanner.next()) {
            case "bye":
                // Exit
                ui.printExitMessage();

                try {
                    storage.writeToFile(tasks);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    return;
                }

                return;
            case "list":
                ui.printListMessage(tasks);
                break;
            case "mark":
                ui.printHorizontalLine();
                if (!(cmd = scanner.nextLine().trim()).matches("\\d+")) {
                    ui.printInvalidSyntaxMessage("Please use \"mark <int>\"");
                } else if ((index = Integer.parseInt(cmd)) - 1 >= tasks.getTaskCount() || index <= 0) {
                    System.out.println("Invalid task index");
                } else {
                    Task t = tasks.getTask(index - 1);
                    t.markAsDone();
                    ui.printTaskMarkedMessage(t);
                }
                ui.printHorizontalLine();

                try {
                    storage.writeToFile(tasks);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                break;
            case "unmark":
                ui.printHorizontalLine();
                if (!(cmd = scanner.nextLine().trim()).matches("\\d+")) {
                    ui.printInvalidSyntaxMessage("Please use \"unmark <int>\"");
                } else if ((index = Integer.parseInt(cmd)) - 1 >= tasks.getTaskCount() || index <= 0) {
                    System.out.println("Invalid task index");
                } else {
                    Task t = tasks.getTask(index - 1);
                    t.markAsNotDone();
                    ui.printTaskUnmarkedMessage(t);
                }
                ui.printHorizontalLine();

                try {
                    storage.writeToFile(tasks);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                break;
            case "todo":
                ui.printHorizontalLine();
                if (!(cmd = scanner.nextLine()).matches(".+")) {
                    ui.printInvalidSyntaxMessage("Please use \"todo <description>\"");
                } else {
                    ToDo td = new ToDo(cmd.stripIndent());
                    tasks.addToTaskList(td);

                    ui.printTaskAddedMessage(td, tasks.getTaskCount());
                }
                ui.printHorizontalLine();

                try {
                    storage.writeToFile(tasks);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                break;
            case "deadline":
                ui.printHorizontalLine();
                if (!(cmd = scanner.nextLine()).matches(".+ /by .+")) {
                    ui.printInvalidSyntaxMessage("Please use \"deadline <description> /by <deadline>\"");
                } else {
                    String[] input = cmd.split(" /by ");
                    Deadline dl = new Deadline(input[0].stripIndent(), input[1]);
                    tasks.addToTaskList(dl);

                    ui.printTaskAddedMessage(dl, tasks.getTaskCount());
                }
                ui.printHorizontalLine();

                try {
                    storage.writeToFile(tasks);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                break;
            case "event":
                ui.printHorizontalLine();
                if (!(cmd = scanner.nextLine()).matches(".+ /from .+ /to .+")) {
                    ui.printInvalidSyntaxMessage("Please use \"event <description> /from <time> /to <time>\"");
                } else {
                    String[] input = cmd.split(" /from ");
                    String[] interval = input[1].split(" /to ");
                    Event e = new Event(input[0].stripIndent(), interval[0], interval[1]);
                    tasks.addToTaskList(e);

                    ui.printTaskAddedMessage(e, tasks.getTaskCount());
                }
                ui.printHorizontalLine();

                try {
                    storage.writeToFile(tasks);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                break;
            case "remove":
                ui.printHorizontalLine();
                if (!scanner.hasNextInt()) {
                    ui.printInvalidSyntaxMessage("Please use \"remove <index>\"");
                } else if ((index = scanner.nextInt()) > tasks.getTaskCount() || index <= 0) {
                    System.out.println("Invalid task index");
                } else {
                    ui.printTaskRemovedMessage(tasks.removeFromTaskList(index), tasks.getTaskCount());
                }
                ui.printHorizontalLine();

                try {
                    storage.writeToFile(tasks);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                break;
            default:
                ui.printUnknownCommandMessage(cmd);
            }
        }
    }
    public static void main(String[] args) {
        new Bibi("data/list.txt").run();
    }
}
