import java.util.Scanner;
import java.util.ArrayList;

public class Hana {
    private static final int MAX_TASKS = 100;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static String line = "___________________________________________";
    private static String name = "Hana";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        //greet
        System.out.println(line);
        System.out.println(" Hello! I'm " + name);
        System.out.println(" What can I do for you?");
        System.out.println(line);

        while (true) {
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(line);
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println(line);
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    listTasks();
                } else if (input.startsWith("mark")) {
                    handleMark(input);
                } else if (input.startsWith("unmark")) {
                    handleUnmark(input);
                } else if (input.startsWith("todo")) {
                    handleTodo(input);
                } else if (input.startsWith("deadline")) {
                    handleDeadline(input);
                } else if (input.startsWith("event")) {
                    handleEvent(input);
                } else if (input.startsWith("delete")) {
                    handleDelete(input);
                } else {
                    throw new HanaException("I'm sorry, I don't recognize that command. Here are some examples of what you can do:\n"
                            + "1. List all tasks: list\n"
                            + "2. Mark a task as done: mark [task number]\n"
                            + "3. Unmark a task: unmark [task number]\n"
                            + "4. Add a todo: todo [description]\n"
                            + "5. Add a deadline: deadline [description] /by [due date]\n"
                            + "6. Add an event: event [description] /from [start time] /to [end time]\n"
                            + "7. Delete a task: delete [task number]");
                }
            } catch (HanaException e) {
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println(line);
            } catch (Exception e) {
                System.out.println(line);
                System.out.println("Oops! Something went wrong. Please try again.");
                System.out.println(line);
            }
        }
        scanner.close();
    }

    private static void handleMark(String input) throws HanaException {
        String[] parts = input.split(" ", 2);
        if (tasks.isEmpty()) {
            throw new HanaException("No tasks added yet. Add a task first!");
        }
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new HanaException("Task number cannot be empty.");
        }
        int taskNumber = Integer.parseInt(parts[1].trim());
        markTask(taskNumber, true);
    }

    private static void handleUnmark(String input) throws HanaException {
        String[] parts = input.split(" ", 2);
        if (tasks.isEmpty()) {
            throw new HanaException("No tasks added yet. Add a task first!");
        }
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new HanaException("Task number cannot be empty.");
        }
        int taskNumber = Integer.parseInt(parts[1].trim());
        markTask(taskNumber, false);
    }

    private static void handleTodo(String input) throws HanaException {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            throw new HanaException("ToDo task must have a description.");
        }
        addTask(new ToDo(description));
    }

    private static void handleDeadline(String input) throws HanaException {
        String[] parts = input.substring(8).split(" /by ");
        if (parts.length < 2) {
            throw new HanaException("Deadline task must have a description and a due date.");
        }
        addTask(new Deadline(parts[0].trim(), parts[1].trim()));
    }

    private static void handleEvent(String input) throws HanaException {
        String[] parts = input.substring(5).split(" /from | /to ");
        if (parts.length < 3) {
            throw new HanaException("Event task must have a description, start time, and end time.");
        }
        addTask(new Event(parts[0].trim(), parts[1].trim(), parts[2].trim()));
    }

    private static void handleDelete(String input) throws HanaException {
        String[] parts = input.split(" ", 2);
        if (tasks.isEmpty()) {
            throw new HanaException("No tasks added yet. Add a task first!");
        }
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new HanaException("Task number cannot be empty.");
        }
        int taskNumber = Integer.parseInt(parts[1].trim());
        deleteTask(taskNumber);
    }

    private static void addTask(Task task) throws HanaException {
        if (tasks.size() < MAX_TASKS) {
            tasks.add(task);
            System.out.println(line);
            System.out.println("Got it. I've added this task:");
            System.out.println("    " + task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(line);
        } else {
            throw new HanaException("Task list is full!");
        }
    }

    private static void listTasks() throws HanaException {
        if (tasks.isEmpty()) {
            throw new HanaException("No tasks added yet.");
        } else {
            System.out.println(line);
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
            System.out.println(line);
        }
    }

    private static void markTask(int taskNumber, boolean isDone) throws HanaException {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            task.setDone(isDone);
            System.out.println(line);
            if (isDone) {
                System.out.println("Nice! I've marked this task as done:");
            } else {
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println("  " + task);
            System.out.println(line);
        } else {
            throw new HanaException("Invalid task number! Task number must be between 1 and " + tasks.size() + ".");
        }
    }

    private static void deleteTask(int taskNumber) throws HanaException {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task removedTask = tasks.remove(taskNumber - 1);
            System.out.println(line);
            System.out.println("Noted. I've removed this task:");
            System.out.println("    " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(line);
        } else {
            throw new HanaException("Invalid task number! Task number must be between 1 and " + tasks.size() + ".");
        }
    }
}
