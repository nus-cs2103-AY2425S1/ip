import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Joe {
    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static final String CHATBOT_NAME = "Joe";
    public static final String ADD_TASK_MESSAGE = "Got it. I've added this task:\n";
    public static final String TASK_COUNT_MESSAGE = "Now you have %d tasks in the list.\n";
    public static final String FILE_NAME = "./data/store.txt";

    public static String input = "";
    public static ArrayList<Task> store = new ArrayList<>();

    public static void greet() {
        System.out.printf("%s\nHello! I'm %s\nWhat can I do for you?\n%s\n", HORIZONTAL_LINE, CHATBOT_NAME, HORIZONTAL_LINE);
    }

    public static void farewell() {
        System.out.printf("Bye. Hope to see you again soon!\n%s", HORIZONTAL_LINE);
    }

    public static void printHelp() {
        System.out.println("Here are the commands you can use:");
        System.out.println("list - List all tasks");
        System.out.println("todo <task> - Add a todo task");
        System.out.println("deadline <task> /by <date> - Add a deadline task with date in yyyy-mm-dd format");
        System.out.println("event <task> /from <date> /to <date> - Add an event task with dates in yyyy-mm-dd format");
        System.out.println("mark <index> - Mark a task as done");
        System.out.println("unmark <index> - Mark a task as not done yet");
        System.out.println("delete <index> - Delete a task");
        System.out.println("help - Show this help message");
        System.out.println("bye - Exit the program");
    }

    public static void handleList(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
                System.out.println(i + 1 + "." + list.get(i));
        }
    }

    public static void handleDone(ArrayList<Task> list, int index) {
        list.get(index).toggleDone();
        System.out.printf("Nice! I've marked this task as done:\n%s\n", list.get(index));
    }

    public static void handleUndone(ArrayList<Task> list, int index) {
        list.get(index).toggleDone();
        System.out.printf("Nice! I've marked this task as not done yet:\n%s\n", list.get(index));
    }

    public static void handleDelete(ArrayList<Task> list, int index) {
        System.out.printf("Noted. I've removed this task:\n%s\n", list.get(index));
        list.remove(index);
        System.out.printf(TASK_COUNT_MESSAGE, list.size());
    }

    public static void handleTodo(String input) {
        String task = input.substring(5);
        if (task.equals("")) {
            System.out.println("Don't expect me to remember nothing!");
            return;
        }
        store.add(new TaskTodo(task));
        System.out.printf("%s%s\n", ADD_TASK_MESSAGE, store.getLast());
        System.out.printf(TASK_COUNT_MESSAGE, store.size());
    }

    public static void handleDeadline(String input) {
        int byIndex = input.indexOf("/by ");
        if (byIndex == -1) {
            System.out.println("BY WHEN??!!");
            return;
        }
        String task = input.substring(9, byIndex - 1);
        if (task.equals("")) {
            System.out.println("Don't expect me to remember nothing!");
            return;
        }
        String by = input.substring(byIndex + 4);
        store.add(new TaskDeadline(task, by));
        System.out.printf("%s%s\n", ADD_TASK_MESSAGE, store.getLast());
        System.out.printf(TASK_COUNT_MESSAGE, store.size());
    }

    public static void handleEvent(String input) {
        int fromIndex = input.indexOf("/from ");
        int toIndex = input.indexOf("/to ");
        if (fromIndex == -1 || toIndex == -1) {
            System.out.println("Give me a valid from and to!");
            return;
        }
        String task = input.substring(6, fromIndex - 1);
        if (task.equals("")) {
            System.out.println("Don't expect me to remember nothing!");
            return;
        }
        String from = input.substring(fromIndex + 6, toIndex - 1);
        String to = input.substring(toIndex + 4);
        store.add(new TaskEvent(task, from, to));
        System.out.printf("%s%s\n", ADD_TASK_MESSAGE, store.getLast());
        System.out.printf(TASK_COUNT_MESSAGE, store.size());
    }

    public static void saveTasks() {
        try {
            System.out.println("Saving tasks...");
            File file = new File(FILE_NAME);
            file.getParentFile().mkdirs();
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            for (Task task : store) {
                writer.write(task.toSaveString() + "\n");
            }
            writer.close();
            System.out.println("Tasks saved!");
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("An error occurred, tasks not saved.");
        }
    }

    public static void loadTasks() {
        try {
            System.out.println("Loading tasks...");
            File file = new File(FILE_NAME);
            file.getParentFile().mkdirs();
            file.createNewFile();
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] parts = data.split("\\|");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String task = parts[2];
                if (type.equals("T")) {
                    store.add(new TaskTodo(task));
                } else if (type.equals("D")) {
                    String by = parts[3];
                    store.add(new TaskDeadline(task, by));
                } else if (type.equals("E")) {
                    String from = parts[3];
                    String to = parts[4];
                    store.add(new TaskEvent(task, from, to));
                }
                if (isDone) {
                    store.get(store.size() - 1).toggleDone();
                }
            }
            reader.close();
            System.out.println("Tasks loaded!");
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("An error occurred, tasks not loaded.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        loadTasks();
        printHelp();
        greet();
        while (!input.equals("bye")) {
            input = scanner.nextLine();
            System.out.println(HORIZONTAL_LINE);
            if (input.equals("bye")) {
                break;
            }
            else if (input.contains("|")) {
                System.out.println("| is a special character and cannot be used.");
            }
            else if (input.equals("list")) {
                handleList(store);
            }
            else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                handleDone(store, index);
            }
            else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                handleUndone(store, index);
            }
            else if (input.startsWith("todo")) {
                handleTodo(input);
            }
            else if (input.startsWith("deadline")) {
                handleDeadline(input);
            }
            else if (input.startsWith("event")) {
                handleEvent(input);
            }
            else if (input.startsWith("delete")) {
                handleDelete(store, Integer.parseInt(input.split(" ")[1]) - 1);
            }
            else if (input.equals("help")) {
                printHelp();
            }
            else {
                System.out.println("Give me a valid command!");
            }
            System.out.println(HORIZONTAL_LINE);
        }
        saveTasks();
        farewell();
        scanner.close();
    }
}
