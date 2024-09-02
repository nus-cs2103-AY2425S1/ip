import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
public class Bottle {
    final static String lineBreak = "\n____________________________________________________________\n";
    public static void printWithBreak(String str) {
        System.out.println(lineBreak + str + lineBreak);
    }
    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();
        ArrayList<Task> taskList = taskManager.loadTasks();
        String welcomeMsg =
                " Hello! I'm Bottle\n" +
                " What can I do for you?";

        String byeMsg =" Bye. Hope to see you again soon!";
        printWithBreak(welcomeMsg);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("bye")) {
                    printWithBreak(byeMsg);
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    printTaskList(taskList);
                } else if (input.startsWith("mark ")) {
                    markTask(taskList, input.substring(5));
                } else if (input.startsWith("unmark ")) {
                    unmarkTask(taskList, input.substring(7));
                } else if (input.startsWith("todo ")) {
                    addTodoTask(taskList, input.substring(5));
                } else if (input.startsWith("deadline ")) {
                     addDeadlineTask(taskList, input.substring(9));
                } else if (input.startsWith("event ")) {
                    addEventTask(taskList, input.substring(6));
                } else {
                    throw new RuntimeException("OOPS!!! Something went wrong.");
                }
            } catch (RuntimeException e) {
                printWithBreak(e.getMessage());
            } finally {
                taskManager.saveTasks(taskList);
            }
        }
    }

    private static void printTaskList(ArrayList<Task> taskList) {
        System.out.println(lineBreak);
        for (int i = 0; i < taskList.size(); i++) {
            int idx = i + 1;
            System.out.println(idx + ". " + taskList.get(i));
        }
        System.out.println(lineBreak);
    }

    private static void markTask(ArrayList<Task> taskList, String taskIndexStr) {
        int taskIndex = Integer.parseInt(taskIndexStr) - 1;
        if (taskIndex >= 0 && taskIndex < taskList.size()) {
            Task task = taskList.get(taskIndex);
            task.mark();
            printWithBreak("Nice! I've marked this task as done:\n" + task);
        } else {
            throw new IndexOutOfBoundsException("OOPS!!! Task number is out of bounds.");
        }
    }

    private static void unmarkTask(ArrayList<Task> taskList, String taskIndexStr) {
        int taskIndex = Integer.parseInt(taskIndexStr) - 1;
        if (taskIndex >= 0 && taskIndex < taskList.size()) {
            Task task = taskList.get(taskIndex);
            task.unMark();
            printWithBreak("OK, I've marked this task as not done yet:\n" + task);
        } else {
            throw new IndexOutOfBoundsException("OOPS!!! Task number is out of bounds.");
        }
    }

    private static void addTodoTask(ArrayList<Task> taskList, String description) {
        if (description.isEmpty()) {
            throw new IllegalArgumentException("OOPS!!! The description of a todo cannot be empty.");
        }
        taskList.add(new Todo(description));
        printTaskAddedMessage(taskList);
    }

    private static void printTaskAddedMessage(ArrayList<Task> taskList) {
        printWithBreak("Got it. I've added this task:\n   " + taskList.get(taskList.size() - 1) +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    private static void addDeadlineTask(ArrayList<Task> taskList, String input) {
        String[] parts = input.split(" /by ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("OOPS!!! The deadline format is incorrect. Use: deadline <description> /by <dd/MM/yyyy HHmm>");
        }
        String description = parts[0];
        LocalDateTime byTime = parseDateTime(parts[1]);
        taskList.add(new Deadline(description, byTime));
        printTaskAddedMessage(taskList);
    }

    private static void addEventTask(ArrayList<Task> taskList, String input) {
        String[] parts = input.split(" /from | /to ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("OOPS!!! The event format is incorrect. Use: event <description> /from <dd/MM/yyyy HHmm> /to <dd/MM/yyyy HHmm>");
        }
        String description = parts[0];
        LocalDateTime from = parseDateTime(parts[1]);
        LocalDateTime to = parseDateTime(parts[2]);
        taskList.add(new Event(description, from, to));
        printTaskAddedMessage(taskList);
    }

    private static LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("OOPS!!! The date format is incorrect. Please use: dd/MM/yyyy HHmm");
        }
    }

}
