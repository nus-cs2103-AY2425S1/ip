import java.util.Scanner;
import java.util.ArrayList;

public class Fred {
    static String line = "____________________________________________________________";
    static String name = "Fred";
    static ArrayList<Task> taskList = new ArrayList<>();
    static int taskCount = 0;
    public static void main(String[] args) {
        greet();
        getInput();
    }

    private static void greet() {
        String greeting = "Hello! I'm " + name + "\n" +
                "What can I do for you?";
        System.out.println(line);
        System.out.println(greeting);
        System.out.println(line);
    }

    private static void sayFarewell() {
        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(line);
        System.out.println(farewell);
        System.out.println(line);
    }

    private static void exit(){
        System.exit(0);
    }

    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
        }
        sayFarewell();
        exit();
    }

    private static void getInput() {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            try {
                determineAction(input);
            } catch (Exception e) {
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println(line);
            }
        }
    }

    private static void determineAction(String input) throws FredException {
        String[] inputParts = input.split(" ", 2);
        if (inputParts.length == 1) {
            if (inputParts[0].isEmpty()) {
                throw new EmptyInputException();
            } else if (inputParts[0].equals("bye")) {
                sayFarewell();
                exit();
            } else if (inputParts[0].equals("list")) {
                printTaskList();
            } else if (inputParts[0].equals("todo") || inputParts[0].equals("deadline") || inputParts[0].equals("event")) {
                throw new EmptyTaskDescriptionException();
            } else {
                throw new UnknownCommandException();
            }
        } else if (inputParts.length == 2) {
            if (inputParts[0].equals("mark")) {
                markTaskAsDone(inputParts[1]);
            } else if (inputParts[0].equals("unmark")) {
                markTaskAsNotDone(inputParts[1]);
            } else if (inputParts[0].equals("todo") || inputParts[0].equals("deadline") || inputParts[0].equals("event")) {
                addToTaskList(inputParts[0], inputParts[1]);
            } else {
                throw new UnknownCommandException();
            }
        }
    }

    private static void addToTaskList(String taskType, String taskDetails) throws FredException {
        Task task;
        String[] taskDetailsArr = taskDetails.split(" /", 3);
        String description = taskDetailsArr[0];
        if (description.isEmpty()) {
            throw new EmptyTaskDescriptionException();
        }
        if (taskType.equals("todo")) {
            task = new ToDo(description);
        } else if (taskType.equals("deadline")) {
            String by = taskDetailsArr[1].substring(3);
            task = new Deadline(description, by);
        } else {
            String from = taskDetailsArr[1].substring(5);
            String to = taskDetailsArr[2].substring(3);
            task = new Event(description, from, to);
        }
        taskList.add(task);
        System.out.println(line);
        System.out.println(String.format("Got it. I've added this task:\n" +
                "   %s\n" +
                "Now you have %d tasks in the list.", task, taskList.size()));
        System.out.println(line);
    }

    private static void printTaskList() {
        int index = 1;
        System.out.println(line);
        for (Task task : taskList) {
            System.out.println(String.format("%s.%s", index++, task));
        }
        System.out.println(line);
    }

    private static void markTaskAsDone(String taskNumberString) throws InvalidTaskNumberException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(taskNumberString) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
        taskList.get(taskNumber).markAsDone();
        System.out.println(line);
        System.out.println(String.format("Nice! I've marked this task as done:\n" +
                "   %s", taskList.get(taskNumber)));
        System.out.println(line);
    }

    private static void markTaskAsNotDone(String taskNumberString) throws InvalidTaskNumberException {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(taskNumberString) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
        taskList.get(taskNumber).markAsNotDone();
        System.out.println(line);
        System.out.println(String.format("OK, I've marked this task as not done yet:\n" +
                "   %s", taskList.get(taskNumber)));
        System.out.println(line);
    }
}
