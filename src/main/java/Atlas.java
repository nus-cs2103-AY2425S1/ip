import java.util.Scanner;  // Import the Scanner class
import java.util.ArrayList;


public class Atlas {
    private static final String BOT_INDENT = "\t";
    private static final String SEP = BOT_INDENT + "___________________________________________";
    private static final String EXIT = "bye";
    private static final Scanner scanner = new Scanner(System.in);  // Create a Scanner object

    private static final ArrayList<Task> tasks = new ArrayList<>(); 

    public static void main(String[] args) {
        introduction();
        String input = null;
        do {
            parse(input);
            input = listen();
        } while (!input.equals(EXIT));
        exit();
        scanner.close();
    }

    private static void parse(String input) {
        if (input == null) {
            return;
        }
        String[] text = input.split(" ");
        if (text.length == 0) {
            return;
        }
        String command = text[0];

        boolean status = false;
        switch (command) {
            case "bye": // do nothing: handled in main loop
                break;
            case "list":
                listShow();
                break;
            case "mark":
                status = true;
            case "unmark":
                int index = Integer.parseInt(text[1]) - 1;
                taskMark(index, status);
                taskMarkLog(index, status);
                break;
            default:  // add the item
                addTask(input);
                addTaskLog(input);
        }
    }

    private static void addTask(String item) {
        tasks.add(new Task(item));
    }

    private static void addTaskLog(String item) {
        botMessage("added: " + item);
        separate();
    }

    private static void taskMark(int index, boolean status) {
        tasks.get(index).setDone(status);
    }

    private static void taskMarkLog(int index, boolean status) {
        String message = status ? "Nice! I've marked this task as done:"
            : "OK! I've marked this task as not done yet:";

        botMessage(message);
        botMessage("  " + tasks.get(index));
        separate();
    }

    private static void listShow() {
        botMessage("Here are the items in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            botMessage((i + 1) + "." + tasks.get(i));
        }
        separate();
    }

    private static void introduction() {
        botMessage("Hello! I'm Atlas.");
        botMessage("How can I help you today?");
        separate();
    }

    private static void exit() {

        botMessage("Goodbye! Have a great day ahead!");
        separate();
    }

    private static String listen() {
        System.out.println("");
        String text = scanner.nextLine();  // Read user input
        separate();
        return text;
    }

    private static void echo(String input) {
        if (input == null) {
            return;
        }
        botMessage(input);
        separate();
    }

    /* --------- HELPER FUNCTIONS --------- */

    private static void separate() {
        System.out.println(SEP);
    }

    private static void botMessage(String message) {
        System.out.println(BOT_INDENT + message);
    }
}
