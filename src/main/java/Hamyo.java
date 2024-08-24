import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Collection;

public class Hamyo {

    private static boolean active = true;
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        greet();

        while (active) {
            try {
                String str = scanner.nextLine();
                if (str.startsWith("todo")) {
                    add(TaskType.TODO, str.substring(4));
                } else if (str.startsWith("deadline")) {
                    add(TaskType.DEADLINE, str.substring(8));
                } else if (str.startsWith("event")) {
                    add(TaskType.EVENT, str.substring(5));
                } else if (str.equals("list")) {
                    listTasks();
                } else if (str.startsWith("mark")) {
                    mark(str.substring(4));
                } else if (str.startsWith("unmark")) {
                    unmark(str.substring(6));
                } else if (str.startsWith("delete")) {
                    delete(str.substring(6));
                } else if (str.startsWith("bye")) {
                    terminate();
                } else {
                    throw new HamyoException("Invalid command.");
                }
            } catch (HamyoException e) {
                System.out.println(e.toString());
                printLine();
            }
        }
    }

    public static void printLine() {
        System.out.println("________________________________________________________________________________");
    }

    private static void printLogo() {
        System.out.println(
            "$$   $$   $$$$    $$$$ $$$$   $$   $$  $$$$$$\n" +
            "$$   $$  $$  $$  $$  $$$  $$  $$   $$  $$  $$\n" +
            "$$$$$$$  $$$$$$  $$  $$$  $$  $$$$$$$  $$  $$\n" +
            "$$   $$  $$  $$  $$  $$$  $$       $$  $$  $$\n" +
            "$$   $$  $$  $$  $$  $$$  $$  $$$$$$   $$$$$$");
    }

    public static void greet() {
        printLine();
        printLogo();
        System.out.println("\nAnnyeonghaseyo! Hamyo here!\nHow may I assist you today?");
        printLine();
    }

    public static void terminate() {
        active = false;
        System.out.println("Annyeong! Till we meet again. <3");
        printLine();
    }

    public static void add (TaskType taskType, String task) throws HamyoException {
        if (taskType.equals(TaskType.TODO)) {
            if (task.length() <= 1) {
                throw new HamyoException("Usage: todo [task description]");
            }
            tasks.add(new ToDo(new String[]{task.substring(1)}));
        } else if (taskType.equals(TaskType.DEADLINE)) {
            if (task.length() <= 1) {
                throw new HamyoException("Usage: deadline [task description] /by [deadline]");
            }
            String[] split = task.substring(1).split(" /by ");
            if (split.length != 2) {
                throw new HamyoException("Usage: deadline [task description] /by [deadline]");
            }
            tasks.add(new Deadline(split));
        } else if (taskType.equals(TaskType.EVENT)) {
            if (task.length() <= 1) {
                throw new HamyoException("Usage: event [task description] /from [start timestamp] /to [end timestamp]");
            }
            String[] split = task.substring(1).split(" /from | /to ");
            if (split.length != 3) {
                throw new HamyoException("Usage: event [task description] /from [start timestamp] /to [end timestamp]");
            }
            tasks.add(new Event(split));
        }
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1).toString());
        System.out.printf("There are %d tasks in the list now.\n", tasks.size());
        printLine();
    }

    public static void listTasks() {
        System.out.println("These are your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        printLine();
    }

    public static void mark(String str) throws HamyoException {
        try {
            if (str.length() <= 1) {
                throw new HamyoException("Usage: unmark [index]");
            }
            int index = Integer.parseInt(str.substring(1)) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new HamyoException("Usage: mark [index]");
            }
            tasks.get(index).mark();
        } catch (NumberFormatException e) {
            throw new HamyoException("Usage: mark [index]");
        }
    }

    public static void unmark(String str) throws HamyoException {
        try {
            if (str.length() <= 1) {
                throw new HamyoException("Usage: unmark [index]");
            }
            int index = Integer.parseInt(str.substring(1)) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new HamyoException("Usage: unmark [index]");
            }
            tasks.get(index).unmark();
        } catch (NumberFormatException e) {
            throw new HamyoException("Usage: unmark [index]");
        }
    }

    public static void delete(String str) throws HamyoException {
        try {
            if (str.length() <= 1) {
                throw new HamyoException("Usage: delete [index]");
            }
            int index = Integer.parseInt(str.substring(1)) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new HamyoException("Usage: delete [index]");
            }
            System.out.println("Noted. I've removed this task:");
            System.out.println(tasks.get(index).toString());
            tasks.remove(index);
            System.out.printf("There are %d tasks in the list now.\n", tasks.size());
            printLine();
        } catch (NumberFormatException e) {
            throw new HamyoException("Usage: delete [index]");
        }
    }
}
