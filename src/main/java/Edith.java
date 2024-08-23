import java.util.ArrayList;
import java.util.Scanner;

public class Edith {
    private static final String prompt = "Your next instruction: ";

    private final String lineBreak = "\n_______________________________________________________________________\n";
    private final String greeting = "Hello! I am EDITH, your personal chatbot companion:)"
            + "\nHow may I assist you?";
    private final String indentation = "    ";
    private final String exit = "It has been my pleasure helping you. Hope to see you again soon!";

    private ArrayList<String> listOfTasks = new ArrayList<>();

    public Edith() {
    }

    public void greet() {
        System.out.println(lineBreak + greeting + lineBreak);
    }

    public void exit() {
        System.out.println(indentation + exit + lineBreak);
    }

    public void echo(String message) {
        System.out.println(indentation + message + lineBreak);
    }

    public void add(String task) {
        listOfTasks.add(task);
        System.out.println(indentation + "added: " + task + lineBreak);
    }

    public void list() {
        String emptyListMessage = "Great news, you have no outstanding task! Have a break!";
        String filledListMessage = "Here are the tasks in your list:";

        if (listOfTasks.isEmpty()) {
            System.out.println(indentation + emptyListMessage + lineBreak);
        } else {
            System.out.println(indentation + filledListMessage);
            for (int i = 0; i < listOfTasks.size(); i++) {
                System.out.println(indentation + (i+1) + ") " + listOfTasks.get(i));
            }
            System.out.println(lineBreak);
        }
    }

    public static void main(String[] args) {
        Edith edith = new Edith();
        edith.greet();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            System.out.println(prompt);
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                edith.exit();
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                edith.list();
            } else {
                edith.add(userInput);
            }
        }

        scanner.close();
    }
}