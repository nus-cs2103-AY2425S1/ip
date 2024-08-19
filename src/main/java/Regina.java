import java.util.ArrayList;
import java.util.Scanner;

public class Regina {
    final static String NAME = "Regina";
    final static String INDENT = "    ";
    final static String LINE = INDENT + "-------------------------------";
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
        Task task = new Task(input);
        listOfTasks.add(task);
        System.out.println(LINE + "\n" + INDENT + "added: "
                + input + "\n" + LINE); // show that input was added
    }

    public void list() {
        int length = listOfTasks.size();
        StringBuilder inputList = new StringBuilder();
        for (int i = 0; i < length; i++) {
            inputList.append(INDENT)
                    .append(i + 1)
                    .append(".")
                    .append(listOfTasks.get(i).toString()) // get task
                    .append("\n");
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
