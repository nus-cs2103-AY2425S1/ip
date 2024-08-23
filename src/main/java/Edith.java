import java.util.ArrayList;
import java.util.Scanner;

public class Edith {
    private static final String prompt = "Your next instruction: ";

    private final String lineBreak = "\n_______________________________________________________________________\n";
    private final String greeting = "Hello! I am EDITH, your personal chatbot companion:)"
            + "\nHow may I assist you?";
    private final String indentation = "    ";
    private final String exit = "It has been my pleasure helping you. Hope to see you again soon!";

    private ArrayList<Task> listOfTasks = new ArrayList<>();

    public Edith() {
    }

    public void greet() {
        System.out.println(lineBreak + greeting + lineBreak);
    }

    public void exit() {
        System.out.println(indentation + exit + lineBreak);
    }

    public void add(String string) {
        String message1 = "Got it. I've added this task:";
        Task task = new Task(string);

        listOfTasks.add(task);
        int numOfTasks = listOfTasks.size();
        String message2 = "There are now " + numOfTasks + " tasks in your list.";

        System.out.println(indentation + message1);
        System.out.println(indentation + task);
        System.out.println(indentation + message2 + lineBreak);
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

    public void mark(int index) {
        int num = index + 1;
        String message = "Alright, great job! I've marked task " + num + " as done:";
        listOfTasks.get(index).markTaskDone();

        System.out.println(indentation + message);
        System.out.println(indentation + listOfTasks.get(index) + lineBreak);
    }

    public void unmark(int index) {
        int num = index + 1;
        String message = "Sure, I've marked task " + num + " as not done yet:";
        listOfTasks.get(index).unmarkTaskDone();

        System.out.println(indentation + message);
        System.out.println(indentation + listOfTasks.get(index) + lineBreak);
    }

    public void invalidTaskException(int index) {
        System.out.println(indentation + "Task " + index + " does not exist. Please enter a valid task number." + lineBreak);
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
            } else if (userInput.startsWith("mark ")) {
                int index = Integer.parseInt(userInput.substring(5).trim()) - 1;
                try {
                    edith.mark(index);
                } catch (NumberFormatException e) {
                    edith.invalidTaskException(index);
                }
            } else if (userInput.startsWith("unmark ")) {
                int index = Integer.parseInt(userInput.substring(7).trim()) - 1;
                try {
                    edith.unmark(index);
                } catch (NumberFormatException e) {
                    edith.invalidTaskException(index);
                }
            } else {
                edith.add(userInput);
            }
        }

        scanner.close();
    }
}