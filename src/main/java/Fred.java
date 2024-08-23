import java.util.Scanner;
import java.util.ArrayList;

public class Fred {
    static String line = "____________________________________________________________";
    static String name = "Fred";
    static ArrayList<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        greet();
        getInput();
    }

    public static void say(String message) {
        System.out.println(line);
        System.out.println(message);
        System.out.println(line);
    }

    private static void greet() {
        String message = "Hello! I'm " + name + "\n" +
                "What can I do for you?";
        say(message);
    }

    private static void sayFarewell() {
        String message = "Bye. Hope to see you again soon!";
        say(message);
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
            say(input);
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
                say(e.getMessage());
            }
        }
    }

    private static void determineAction(String input) throws FredException {
        input = input.strip();
        String[] inputParts = input.split(" ", 2);
        if (inputParts.length == 1) {
            if (inputParts[0].isEmpty()) {
                throw new EmptyInputException();
            } else if (inputParts[0].equals("bye")) {
                sayFarewell();
                exit();
            } else if (inputParts[0].equals("list")) {
                printTaskList();
            } else if (inputParts[0].equals("mark") || inputParts[0].equals("unmark")) {
                throw new InvalidTaskNumberException();
            } else if (inputParts[0].equals("todo") || inputParts[0].equals("deadline") || inputParts[0].equals("event")) {
                throw new EmptyTaskDescriptionException();
            } else {
                throw new UnknownCommandException();
            }
        } else if (inputParts.length == 2) {
            String message;
            inputParts[1] = inputParts[1].strip();
            if (inputParts[0].equals("mark") || inputParts[0].equals("unmark") || inputParts[0].equals("delete")) {
                int taskNumber;
                Task task;
                try {
                    taskNumber = Integer.parseInt(inputParts[1]) - 1;
                    task = taskList.get(taskNumber);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new InvalidTaskNumberException();
                }
                if (inputParts[0].equals("mark")) {
                    markTaskAsDone(task, taskNumber);
                    message = String.format("Nice! I've marked this task as done:\n" +
                            "   %s", taskList.get(taskNumber));
                } else if (inputParts[0].equals("unmark")) {
                    markTaskAsNotDone(task, taskNumber);
                    message = String.format("OK, I've marked this task as not done yet:\n" +
                            "   %s", taskList.get(taskNumber));
                } else {
                    deleteFromTaskList(task);
                    message = String.format("Noted. I've removed this task:\n" +
                            "   %s", task);
                }
                say(message);
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

    private static void markTaskAsDone(Task task, int taskNumber) {
        task.markAsDone();
    }

    private static void markTaskAsNotDone(Task task, int taskNumber) {
        task.markAsNotDone();
    }

    private static void deleteFromTaskList(Task task) {
        taskList.remove(task);
    }
}
