import java.io.*;
import java.util.*;
import task.*;

public class Alice {
    private static final String NAME = "Alice";
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String DATA_DIRECTORY = "./data";
    private static final String TASKS_FILE = "tasks.jsonl";

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
        this.tasks = new ArrayList<>();
        loadTasks();
    }

    private void say(String message) {
        System.out.println(String.format("> %s", message));
    }

    private void greet() {
        System.out.println(HORIZONTAL_LINE);
        say(String.format("Hello! I'm %s.", NAME));
        say("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    private void bye() {
        System.out.println(HORIZONTAL_LINE);
        say("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    private void echo(String line) {
        // echo user inputs
        System.out.println(HORIZONTAL_LINE);
        say(String.format("%s", line));
        System.out.println(HORIZONTAL_LINE);
    }

    private void warn(String message) {
        System.out.println(HORIZONTAL_LINE);
        say(String.format("Oops! %s", message));
        System.out.println(HORIZONTAL_LINE);
    }

    private void addTask(Task task) {
        tasks.add(task);
        System.out.println(HORIZONTAL_LINE);
        say("Got it. I've added this task:");
        System.out.println(String.format("\t%s", task));
        System.out.println(HORIZONTAL_LINE);
        saveTasks();
    }

    private void listTasks() {
        System.out.println(HORIZONTAL_LINE);
        if (tasks.isEmpty()) {
            say("You have no tasks.");
        } else {
            say("These are your tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(String.format("\t%d. %s", i + 1, tasks.get(i)));
            }
        }
        System.out.println(HORIZONTAL_LINE);
    }

    private void markTask(String line) {
        String[] tokens = line.split(" ", 2);
        if (tokens.length != 2) {
            warn("Missing task number. Usage: mark <task number>");
            return;
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException e) {
            warn("Invalid task number. Usage: mark <task number>");
            return;
        }

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            warn("Task number out of bounds. Usage: mark <task number>");
            return;
        }
        int index = taskNumber - 1;
        tasks.get(index).setCompletion(true);
        System.out.println(HORIZONTAL_LINE);
        say("Nice! I've marked this task as done:");
        System.out.println(String.format("\t%s", tasks.get(index)));
        System.out.println(HORIZONTAL_LINE);
        saveTasks();
    }

    private void unmarkTask(String line) {
        String[] tokens = line.split(" ", 2);
        if (tokens.length != 2) {
            warn("Missing task number. Usage: unmark <task number>");
            return;
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException e) {
            warn("Invalid task number. Usage: unmark <task number>");
            return;
        }

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            warn("Task number out of bounds. Usage: unmark <task number>");
            return;
        }

        int index = taskNumber - 1;
        tasks.get(index).setCompletion(false);            
        System.out.println(HORIZONTAL_LINE); 
        say("OK, I've marked this task as not done yet:");
        System.out.println(String.format("\t%s", tasks.get(index)));
        System.out.println(HORIZONTAL_LINE);
        saveTasks();
    }

    private void deleteTask(String line) {
        String[] tokens = line.split(" ", 2);
        if (tokens.length != 2) {
            warn("Missing task number. Usage: delete <task number>");
            return;
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException e) {
            warn("Invalid task number. Usage: delete <task number>");
            return;
        }

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            warn("Task number out of bounds. Usage: delete <task number>");
            return;
        }

        int index = taskNumber - 1;
        Task removedTask = tasks.remove(index);
        System.out.println(HORIZONTAL_LINE);
        say("Noted. I've removed this task:");
        System.out.println(String.format("\t%s", removedTask));
        System.out.println(HORIZONTAL_LINE);
        saveTasks();
    }

    private void loadTasks() {
        File dataDirectory = new File(DATA_DIRECTORY);
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        File tasksFile = new File(String.format("%s/%s", DATA_DIRECTORY, TASKS_FILE));
        try {
            FileReader input = new FileReader(tasksFile.getAbsoluteFile());
            BufferedReader reader = new BufferedReader(input);
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                Task task = Task.fromJsonString(line);
                tasks.add(task);
            }
            reader.close();
            input.close();
        } catch (IOException | InvalidTaskException e){
            warn("Unable to load tasks.");
        }
    }

    private void saveTasks() {
        File dataDirectory = new File(DATA_DIRECTORY);
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }

        File tasksFile = new File(String.format("%s/%s", DATA_DIRECTORY, TASKS_FILE));
        try {
            // overwrite file
            FileWriter output = new FileWriter(tasksFile.getAbsoluteFile(), false);
            BufferedWriter writer = new BufferedWriter(output);
            for (Task task : tasks) {
                writer.write(task.toJsonString());
                writer.write("\n");
            }
            writer.close();
            output.close();
        } catch (IOException e){
            warn("Unable to save tasks.");
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
                    warn(String.format("%s Usage: Alice <description>", exception));
                }
                continue;
            }

            if (input.startsWith(Commands.DEADLINE.name())) {
                try {
                    Task deadline = new Deadline(line);
                    addTask(deadline);
                } catch (InvalidTaskException exception) {
                    warn(String.format("%s Usage: deadline <description> /by <deadline>", exception));
                }
                continue;
            }

            if (input.startsWith(Commands.EVENT.name())) {
                try {
                    Task event = new Event(line);
                    addTask(event);
                } catch (InvalidTaskException exception) {
                    warn(String.format("%s Usage: event <description> /from <start> /to <end>", exception));
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
