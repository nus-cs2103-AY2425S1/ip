import java.util.Scanner;

public class Pikappi {
    static Scanner reader = new Scanner(System.in);
    static String command;
    static Task[] tasks = new Task[100];
    static int numTasks = 0;

    public static void greet() {
        System.out.println("Pika! I'm Pikappi!\nWhat can I do for you?\n");
    }

    public static void goodbye() {
        System.out.println("Pi-kapi! See you again~\n");
    }

    public static void addTask(String task) {
        tasks[numTasks] = new Task(task);
        System.out.println("Pi-ka-pipi! I've added this task:\n " + tasks[numTasks] +
                "\nNow you have " + (numTasks + 1) + " tasks in the list.");
        numTasks++;
    }

    public static void addTodoTask(String task) {
        tasks[numTasks] = new TodoTask(task);
        System.out.println("Pi-ka-pipi! I've added this task:\n " + tasks[numTasks] +
                "\nNow you have " + (numTasks + 1) + " tasks in the list.");
        numTasks++;
    }

    public static void addDeadlineTask(String command) {
        String[] substrings = command.split("/by");
        String task = substrings[0].substring(9);
        String by = "";
        try {
            by = substrings[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            by = "";
        }
        tasks[numTasks] = new DeadlineTask(task, by);
        System.out.println("Pi-ka-pipi! I've added this task:\n " + tasks[numTasks] +
                "\nNow you have " + (numTasks + 1) + " tasks in the list.");
        numTasks++;
    }

    public static void addEventTask(String command) {
        String[] substrings = command.split("/from");
        String task = substrings[0].substring(6);
        String from = "";
        String to = "";
        try {
            String[] fromTo = substrings[1].split("/to");
            from = fromTo[0];
            to = fromTo[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            from = "";
            to = "";
        }
        tasks[numTasks] = new EventTask(task, from, to);
        System.out.println("Pi-ka-pipi! I've added this task:\n " + tasks[numTasks] +
                "\nNow you have " + (numTasks + 1) + " tasks in the list.");
        numTasks++;
    }
    
    public static void listTasks() {
        if (numTasks == 0) {
            System.out.println("Pika-ka! You have no tasks!");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numTasks; i++) {
            if (tasks[i] != null) {
                System.out.println((i + 1) + "." + tasks[i]);
            } else {
                break;
            }
        }
    }

    public static void markTask(int taskNumber) {
        tasks[taskNumber - 1].markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + tasks[taskNumber - 1]);
    }

    public static void unmarkTask(int taskNumber) {
        tasks[taskNumber - 1].unmarkAsDone();
        System.out.println("Ok, I've unmarked this task as not done yet:\n" + tasks[taskNumber - 1]);
    }

    public static void main(String[] args) {
        greet();

        while (true) {
            command = reader.nextLine();
            System.out.println("____________________________________________________________");
            if (command.equals("bye")) {
                goodbye();
                return;
            } else if (command.equals("list")) {
                listTasks();
            } else if (command.startsWith("mark")) {
               markTask(Integer.parseInt(command.split(" ")[1]));
            } else if (command.startsWith("unmark")) {
                unmarkTask(Integer.parseInt(command.split(" ")[1]));
            } else if (command.startsWith("todo")) {
                addTodoTask(command.substring(5));
            } else if (command.startsWith("deadline")) {
                addDeadlineTask(command);
            } else if (command.startsWith("event")) {
                addEventTask(command);
            } else {
                addTask(command);
            }
            System.out.println("____________________________________________________________");
        }
    }
}
