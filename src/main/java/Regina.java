import java.util.ArrayList;
import java.util.Scanner;

public class Regina {
    private final static String NAME = "Regina";
    private final static String INDENT = "    ";
    private final static String LINE = INDENT + "-------------------------------";
    private final ArrayList<Task> listOfTasks;
    private final Scanner scanner = new Scanner(System.in);

    public Regina() {
        listOfTasks = new ArrayList<>();
    }

    public void greet() {
        System.out.printf(LINE + "\n" + INDENT + "Hello! I'm %s \n" +
                INDENT + "What can I do for you?\n" + LINE + "\n", NAME);
    }

    public String readInput() {
        return this.scanner.nextLine();
    }

    public void add(String input) {
        // types of tasks
        final String TODO = "todo";
        final String DEADLINE = "deadline";
        final String EVENT = "event";
        String[] parts = input.split(" "); // Split input by spaces
        String taskType = parts[0];
        Task task = null;
        switch (taskType) {
            case TODO:
                String todoDescription = input.substring(5).trim();;
                task = new ToDosTask(todoDescription);
                break;
            case DEADLINE:
                String[] deadlineParts = input.substring(9).trim().split(" /by ");
                String deadlineDescription = deadlineParts[0];
                String deadline = deadlineParts[1];
                task = new DeadlinesTask(deadlineDescription, deadline);
                break;
            case EVENT:
                String[] eventParts = input.substring(6).trim().split(" /");
                String eventDescription = eventParts[0];
                String startTime = eventParts[1].substring(5).trim(); // take the substring after the word "from"
                String endTime = eventParts[2].substring(3).trim(); // take the substring after the word "to"
                task = new EventsTask(eventDescription, startTime, endTime);
                break;
            default:
                System.out.println(LINE + "\n" + INDENT + "Unknown task type. Use: todo, deadline, or event.\n" + LINE);
                return;
        }
        listOfTasks.add(task);
        int noOfTasks = listOfTasks.size();
        System.out.println(LINE + "\n" + INDENT + "Got it. I've added this task: \n  "
                + INDENT
                + task.toString()
                + String.format("%sNow you have %d task%s in the list.\n",
                    INDENT,
                    noOfTasks,
                    noOfTasks > 1 ? "s" : "") + LINE);
    }

    public void list() {
        int length = listOfTasks.size();
        StringBuilder inputList = new StringBuilder();
        for (int i = 0; i < length; i++) {
            inputList.append(INDENT)
                    .append(i + 1)
                    .append(".")
                    .append(listOfTasks.get(i).toString()); // get task
        }
        System.out.println(LINE + "\n" + inputList + LINE);
    }

    public void mark(int index) {
        Task task = listOfTasks.get(index);
        task.checkTask();
        System.out.printf("%sNice! I've marked this task as done:\n%s  %s\n",
                INDENT, INDENT, task.toString());
    }

    public void unmark(int index) {
        Task task = listOfTasks.get(index);
        task.uncheckTask();
        System.out.printf("%sOK, I've marked this task as not done yet:\n%s  %s\n",
                INDENT, INDENT, task.toString());
    }

    public void exit() {
        System.out.println(LINE + "\n" + INDENT +
                "Bye. Hope to see you again soon!\n" + LINE);
        this.scanner.close();
    }

    public static void main(String[] args) {
        final Regina REGINA = new Regina(); // create instance of Regina chatbot
        REGINA.greet(); // greet
        String userInput;

        while (true) {
            userInput = REGINA.readInput();   // Read user input
            if (userInput.equals("bye")) {
                break; // proceed to exit chatbot
            }
            if (userInput.equals("list")) {
                REGINA.list(); // Print out the list
            } else if (userInput.startsWith("mark")) {
                String[] parts = userInput.split(" "); // Split input by spaces
                if (parts.length == 2) { // Ensure there's an index
                    int index = Integer.parseInt(parts[1]) - 1; // Convert to zero-based index
                    REGINA.mark(index); // Unmark the task
                }
            } else if (userInput.startsWith("unmark")) {
                String[] parts = userInput.split(" "); // Split input by spaces
                if (parts.length == 2) { // Ensure there's an index
                    int index = Integer.parseInt(parts[1]) - 1; // Convert to zero-based index
                    REGINA.unmark(index); // Unmark the task
                }
            } else {
                REGINA.add(userInput); // Add input to list
            }
        }
        // Exit
        REGINA.exit();
    }
}
