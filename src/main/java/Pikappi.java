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

    public static void addTodoTask(String command) throws PikappiException {
        String[] substrings = command.split(" ");
        if (substrings.length == 1) {
            throw new PikappiException("Pi-ka..?? What do you want todo..?");
        }
        String task = substrings[1];
        tasks[numTasks] = new TodoTask(task);
        System.out.println("Pi-ka-pipi! I've added this task:\n " + tasks[numTasks] +
                "\nNow you have " + (numTasks + 1) + " tasks in the list.");
        numTasks++;
    }

    public static void addDeadlineTask(String command) throws PikappiException {
        String[] substrings = command.split("/by");
        if (command.split(" ").length == 1) {
            throw new PikappiException("Pi-ka..?? What is the task..?");
        }
        String task = substrings[0].substring(9);
        if (task.isEmpty()) {
            throw new PikappiException("Pi-ka..?? What is the task..?");
        }
        String by = "";
        try {
            by = substrings[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new PikappiException("Pi-ka..?? When is the deadline..?");
        }
        tasks[numTasks] = new DeadlineTask(task, by);
        System.out.println("Pi-ka-pipi! I've added this task:\n " + tasks[numTasks] +
                "\nNow you have " + (numTasks + 1) + " tasks in the list.");
        numTasks++;
    }

    public static void addEventTask(String command) throws PikappiException {
        String[] substrings = command.split("/from");
        if (command.split(" ").length == 1) {
            throw new PikappiException("Pi-ka..?? What is the task..?");
        }
        String task = substrings[0].substring(6);
        if (task.isEmpty()) {
            throw new PikappiException("Pi-ka..?? What is the task..?");
        }
        String from = "";
        String to = "";
        try {
            String[] fromTo = substrings[1].split("/to");
            from = fromTo[0];
            to = fromTo[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new PikappiException("Pi-ka..?? When is the event starting and ending..?");
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
        System.out.println("Okie, I've unmarked this task as not done yet:\n" + tasks[taskNumber - 1]);
    }

    public static void main(String[] args) throws PikappiException {
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
                try {
                    addTodoTask(command);
                } catch (PikappiException e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.startsWith("deadline")) {
                try {
                    addDeadlineTask(command);
                } catch (PikappiException e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.startsWith("event")) {
                try {
                    addEventTask(command);
                } catch (PikappiException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    throw new PikappiException("Pi-ka..?? I don't understand..");
                } catch (PikappiException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("____________________________________________________________");
        }
    }
}
