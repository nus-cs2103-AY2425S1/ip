import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class Killua {
    private static final String LINE = "______________________________________________________________________________";

    public enum Command {
        BYE("bye"), LIST("list"), MARK("mark"), UNMARK("unmark"), DELETE("delete"), TODO("todo"), DEADLINE("deadline"), EVENT("event");
        private final String command;

        Command(String command) {
            this.command = command;
        }

        public static Command fromString(String command) throws KilluaException {
            for (Command cmd : Command.values()) {
                if (cmd.command.equalsIgnoreCase(command)) {
                    return cmd;
                }
            }
            throw new KilluaException("Invalid input: " + command);
        }
    }

    private static void showUserPage() {
        printLine();
        System.out.println("Welcome to Killua Task Manager!");
        System.out.println("Here are some commands you can use:");
        System.out.println("  bye - Exit the application");
        System.out.println("  list - List all tasks");
        System.out.println("  mark <task number> - Mark a task as done");
        System.out.println("  unmark <task number> - Mark a task as not done yet");
        System.out.println("  delete <task number> - Delete a task");
        System.out.println("  todo <description> - Add a new todo task");
        System.out.println("  deadline <description> /by <date> - Add a new deadline task");
        System.out.println("  event <description> /from <start time> /to <end time> - Add a new event task");
        printLine();
    }

    private static void printLine() {
        System.out.println(LINE);
    }

    private static void add(ArrayList<Task> tasks, Task task){
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() +  " tasks in the list.");
        printLine();
    }

    private static void delete(ArrayList<Task> tasks, int taskNumber) {
        Task task = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        printLine();
        System.out.println("OK, I've deleted this task:");
        System.out.println("  " + task);
        printLine();
    }

    private static void bye(){
        printLine();
        System.out.println("Bye!");
        printLine();
    }

    private static void list(ArrayList<Task> tasks){
        printLine();
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.printf("%d.%s%n", i + 1, task);
            }
        }
        printLine();
    }

    private static void markTaskDone(ArrayList<Task> tasks, int taskNumber) {
        tasks.get(taskNumber).markAsDone();
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(taskNumber));
        printLine();
    }

    private static void unmarkTask(ArrayList<Task> tasks, int taskNumber) {
        tasks.get(taskNumber).unmark();
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + tasks.get(taskNumber));
        printLine();
    }

    public static void main(String[] args) {
        showUserPage();

        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (flag) {
            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ", 2);
            String commandStr = parts[0];
            String argument = (parts.length > 1) ? parts[1] : "";

            try {
                Command command = Command.fromString(commandStr);
                switch (command) {
                    case BYE -> {
                        bye();
                        flag = false;
                    }
                    case LIST -> list(tasks);
                    case MARK, UNMARK, DELETE -> {
                        try {
                            int taskNumber = Integer.parseInt(argument);
                            if (command == Command.MARK) {
                                markTaskDone(tasks, taskNumber - 1);
                            } else if (command == Command.UNMARK) {
                                unmarkTask(tasks, taskNumber - 1);
                            } else {
                                delete(tasks, taskNumber - 1);
                            }
                        } catch (NumberFormatException e) {
                            throw new KilluaException("Please provide a valid number! e.g., " + command + " 1");
                        } catch (IndexOutOfBoundsException e) {
                            throw new KilluaException("Task not found: Task " + argument);
                        }
                    }
                    case TODO -> {
                        if (Objects.equals(argument, "")) {
                            throw new KilluaException("Todo description cannot be empty!");
                        }
                        Task todo = new Todo(argument);
                        tasks.add(todo);
                        add(tasks, todo);
                    }
                    case DEADLINE -> {
                        if (Objects.equals(argument, "")) {
                            throw new KilluaException("Deadline description cannot be empty!");
                        }
                        try {
                            String[] strs = argument.split("/", 2);
                            String by = strs[1].substring(3).strip();
                            Task deadline = new Deadline(strs[0].strip(), by);
                            tasks.add(deadline);
                            add(tasks, deadline);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new KilluaException("Please use the correct format for deadlines: deadline <description> /by <date>");
                        }
                    }
                    case EVENT -> {
                        if (Objects.equals(argument, "")) {
                            throw new KilluaException("Event description cannot be empty!");
                        }
                        try {
                            String[] strs = argument.split("/", 3);
                            String from = strs[1].substring(5).strip();
                            String to = strs[2].substring(3).strip();
                            Task event = new Event(strs[0].strip(), from, to);
                            tasks.add(event);
                            add(tasks, event);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new KilluaException("Please use the correct format for events: event <description> /from <start time> /to <end time>");
                        }
                    }
                }
            } catch (KilluaException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
            }
        }

        scanner.close();
    }
}
