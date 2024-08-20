import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Talkie {

    protected static List<Task> taskList = new ArrayList<>();
    protected static Scanner scanner = new Scanner(System.in);

    protected static String welcomeMessage = """
                -----------------------------------------------\s
                Hello! I'm Talkie, your friendly ChatBot.\s
                What can I do for you?\s
                -----------------------------------------------\s
                """;

    protected static String byeMessage = """ 
                -----------------------------------------------\s
                Bye. Hope to see you again soon!\s
                -----------------------------------------------\s
                """;

    // Creates Deadline Task
    public static void createDeadline(String input) {
        String[] parts = input.split(" ", 2); // Split into type and the rest of the input
        String details = parts[1]; // rest of the input (eg. from, to details)
        String[] deadlineParts = details.split("/by ");
        String description = deadlineParts[0].trim();
        String by = deadlineParts[1].trim();

        Task newDeadline = new Deadline(description, by);
        taskList.add(newDeadline);
        String message = Talkie.addMessage(newDeadline);
        System.out.println(message);
    }

    // Creates Event Task
    public static void createEvent(String input) {
        String[] parts = input.split(" ", 2); // Split into type and the rest of the input
        String details = parts[1]; // rest of the input (eg. from, to details)
        String[] eventParts = details.split("/from | /to ");
        String description = eventParts[0].trim();
        String from = eventParts[1].trim();
        String to = eventParts[2].trim();

        Task newEvent = new Event(description, from, to);
        taskList.add(newEvent);
        String message = Talkie.addMessage(newEvent);
        System.out.println(message);
    }

    // Creates ToDo Task
    public static void createToDo(String input) {
        String[] parts = input.split(" ", 2); // Split into type and the rest of the input
        String details = parts[1]; // rest of the input (eg. from, to details)
        Task newToDo = new ToDo(details.trim());
        taskList.add(newToDo);
        String message = Talkie.addMessage(newToDo);
        System.out.println(message);
    }

    // The message displayed whenever a task is created
    public static String addMessage(Task t) {
        String taskWord = (taskList.size() > 1) ? "tasks" : "task";

        return "-----------------------------------------------\n"
                + "Got it. I've added this task:\n"
                + "  " + t + "\n"
                + "Now you have " + taskList.size() + " " + taskWord + " in the list.\n"
                + "-----------------------------------------------\n";
    }

    // Display the list of tasks
    public static void listTasks() {
        String listMessage = "";
        for (int i=0; i<taskList.size(); i++) {
            Task currTask = taskList.get(i);
            String description = (i + 1) + ". " + currTask + "\n";
            listMessage += description;
        }

        String finalListMessage = "-----------------------------------------------\n"
                + "Here are the tasks in your list:\n"
                +  listMessage
                + "-----------------------------------------------\n";
        System.out.println(finalListMessage);
    }

    // Marks a task
    public static void markTask(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        Task task = taskList.get(index);
        task.markAsDone();
        String doneMessage = "______________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + " " + task + "\n"
                + "______________________________________________\n";
        System.out.println(doneMessage);
    }

    // Unmarks a Task
    public static void unmarkTask(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        Task task = taskList.get(index);
        task.markAsNotDone();
        String undoneMessage = "______________________________________________\n"
                + "OK, I've marked this task as not done yet:\n"
                + " " + task + "\n"
                + "______________________________________________\n";
        System.out.println(undoneMessage);

    }

    // Main program
    public static void runTalkie() {
        System.out.println(Talkie.welcomeMessage);

        boolean isFinished = false;
        while (!isFinished) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println(byeMessage);
                isFinished = true;
            }

            if (input.equalsIgnoreCase("list")) {
                Talkie.listTasks();
            }

            if (input.startsWith("mark")) {
                Talkie.markTask(input);
            }

            if (input.startsWith("unmark")) {
                Talkie.unmarkTask(input);
            }

            if (input.startsWith("todo")) {
                Talkie.createToDo(input);
            }

            if (input.startsWith("deadline")) {
                Talkie.createDeadline(input);
            }

            if (input.startsWith("event")) {
                Talkie.createEvent(input);
            }
        }
    }

    public static void main(String[] args) {
        // Start of Talkie
        Talkie.runTalkie();
    }
}
