import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Regina {
    final static String NAME = "Regina";
    final static String INDENT = "    ";
    final static String LINE = INDENT + "-------------------------------";
    private final ArrayList<String> listOfTasks;
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
        listOfTasks.add(input);
        System.out.println(LINE + "\n" + INDENT + "added: "
                + input + "\n" + LINE); // show that input was added
    }

    public String list() {
        int length = listOfTasks.size();
        StringBuilder inputList = new StringBuilder();
        for (int i = 1; i <= length; i++) {
            inputList.append(INDENT)
                    .append(i)
                    .append(". ")
                    .append(listOfTasks.get(i - 1))
                    .append("\n");
        }
        return inputList.toString();
    }

    public void exit() {
        System.out.println(LINE + "\n" + INDENT +
                "Bye. Hope to see you again soon!\n" + LINE);
        this.scanner.close();
    }

    public static void main(String[] args) {
        Regina regina = new Regina(); // create instance of Regina chatbot
        regina.greet(); // greet
        String userInput;

        while (true) {
            userInput = regina.readInput();   // Read user input
            if (userInput.equals("bye")) {
                break;
            }

            // Print out the list
            if (userInput.equals("list")) {
                String inputList = regina.list();
                System.out.println(LINE + "\n" + inputList + LINE);
            } else {
                // Add input to list
                regina.add(userInput);
            }
        }
        // Exit
        regina.exit();
    }
}
