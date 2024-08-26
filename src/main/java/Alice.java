import java.io.*;
import java.util.*;
import task.*;

public class Alice {
    private static final String NAME = "Alice";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String DATA_DIRECTORY_PATH = "./data";
    private static final String TASKS_FILE_NAME = "tasks.jsonl";

    private Ui ui;
    private Storage storage;
    private final List<Task> tasks;

    private enum Commands {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT
    }

    public Alice() {
        this.ui = new Ui();
        this.storage = new Storage(DATA_DIRECTORY_PATH, TASKS_FILE_NAME);
        this.tasks = loadTasks();
    }

    private void greet() {
        ui.say(String.format("Hello! I'm %s. What can I do for you?", NAME));
    }

    private void bye() {
        ui.say("Bye. Hope to see you again soon!");
    }

    private void echo(String line) {
        // echo user inputs
        ui.say(String.format("%s", line));
    }

    private void addTask(Task task) {
        tasks.add(task);
        String[] lines = new String[]{
            "Got it. I've added this task:",
            task.toString()
        };
        ui.say(lines);
        saveTasks();
    }

    private void listTasks() {
        if (tasks.isEmpty()) {
            ui.say("You have no tasks.");
        } else {
            String[] lines = new String[tasks.size() + 1];
            lines[0] = "These are your tasks:";
            for (int i = 0; i < tasks.size(); i++) {
                lines[i + 1] = String.format("%d. %s", i + 1, tasks.get(i));
            }
            ui.say(lines);
        }
    }

    private void markTask(String line) {
        String[] tokens = line.split(" ", 2);
        if (tokens.length != 2) {
            ui.warn("Missing task number. Usage: mark <task number>");
            return;
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException exception) {
            ui.warn("Invalid task number. Usage: mark <task number>");
            return;
        }

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            ui.warn("Task number out of bounds. Usage: mark <task number>");
            return;
        }

        int index = taskNumber - 1;
        tasks.get(index).setCompletion(true);
        String[] lines = new String[]{
            "Nice! I've marked this task as done:",
            tasks.get(index).toString()
        };
        ui.say(lines);
        saveTasks();
    }

    private void unmarkTask(String line) {
        String[] tokens = line.split(" ", 2);
        if (tokens.length != 2) {
            ui.warn("Missing task number. Usage: unmark <task number>");
            return;
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException exception) {
            ui.warn("Invalid task number. Usage: unmark <task number>");
            return;
        }

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            ui.warn("Task number out of bounds. Usage: unmark <task number>");
            return;
        }

        int index = taskNumber - 1;
        tasks.get(index).setCompletion(true);
        String[] lines = new String[]{
            "OK, I've marked this task as not done yet:",
            tasks.get(index).toString()
        };
        ui.say(lines);
        saveTasks();
    }

    private void deleteTask(String line) {
        String[] tokens = line.split(" ", 2);
        if (tokens.length != 2) {
            ui.warn("Missing task number. Usage: delete <task number>");
            return;
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException exception) {
            ui.warn("Invalid task number. Usage: delete <task number>");
            return;
        }

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            ui.warn("Task number out of bounds. Usage: delete <task number>");
            return;
        }

        int index = taskNumber - 1;
        Task removedTask = tasks.remove(index);
        String[] lines = new String[]{
            "Noted. I've removed this task:",
            removedTask.toString()
        };
        ui.say(lines);
        saveTasks();
    }

    private List<Task> loadTasks() {
        try {
            return storage.loadTasks();
        } catch (IOException | InvalidTaskException exception){
            ui.warn(String.format("%s Unable to load tasks.", exception));
        }
        return new ArrayList<>();
    }

    private void saveTasks() {
        try {
            storage.saveTasks(tasks);
        } catch (IOException exception){
            ui.warn(String.format("%s Unable to save tasks.", exception));
        }
    }

    private void listen() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String input = line.trim().toUpperCase();
            if (input.equals(Commands.BYE.name())) {
                break;
            }

            if (input.equals(Commands.LIST.name())) {
                listTasks();
                continue;
            }

            if (input.startsWith(Commands.MARK.name())) {
                markTask(line);
                continue;
            }

            if (input.startsWith(Commands.UNMARK.name())) {
                unmarkTask(line);
                continue;
            }

            if (input.startsWith(Commands.DELETE.name())) {
                deleteTask(line);
                continue;
            }

            if (input.startsWith(Commands.TODO.name())) {
                try {
                    Task toDo = new ToDo(line);
                    addTask(toDo);
                } catch (InvalidTaskException exception) {
                    ui.warn(String.format("%s Usage: Alice <description>", exception));
                }
                continue;
            }

            if (input.startsWith(Commands.DEADLINE.name())) {
                try {
                    Task deadline = new Deadline(line);
                    addTask(deadline);
                } catch (InvalidTaskException exception) {
                    ui.warn(String.format("%s Usage: deadline <description> /by <deadline>", exception));
                }
                continue;
            }

            if (input.startsWith(Commands.EVENT.name())) {
                try {
                    Task event = new Event(line);
                    addTask(event);
                } catch (InvalidTaskException exception) {
                    ui.warn(String.format("%s Usage: event <description> /from <start> /to <end>", exception));
                }
                continue;
            }

            // default
            echo(line);
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Alice alice = new Alice();

        alice.greet();
        alice.listen();
        alice.bye();
    }
}
