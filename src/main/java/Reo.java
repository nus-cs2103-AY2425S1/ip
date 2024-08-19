import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Reo {
    public static void main(String[] args) {
        enum Command {
            BYE,
            LIST,
            TODO,
            MARK,
            UNMARK,
            UNKNOWN,
        }
        // Create a new Scanner object
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------\n" +
                "Hello! I'm Reo.\nWhat can I do for you?\n"
                + "----------------------");

        String currInput;
        currInput = "placeholder";

        ArrayList<Task> tasks = new ArrayList<>();
        while (!currInput.toLowerCase().equals("bye")) {
            String toPrint = "";
            toPrint = "";
            currInput = scanner.nextLine().trim();

            Command command;
            String[] words = currInput.split("\\s+");

            try {
                command = Command.valueOf(words[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                command = Command.UNKNOWN;
            }

            switch (command) {
                case LIST:
                    toPrint += "----------------------\n";
                    for (int i = 0; i < tasks.size(); i++) {
                        int listIndex = i + 1;
                        toPrint += listIndex + ". " + tasks.get(i).toString() + "\n";
                    }
                    toPrint += "----------------------";
                    break;
                case TODO:
                    tasks.add(new Todo(words[1]));
                    toPrint += "----------------------\n";
                    try {
                        toPrint += "added: " + words[1] + "\n";
                    } catch (ArrayIndexOutOfBoundsException e) {
                        toPrint += "Please enter a valid task name.\n";
                    }
                    toPrint += "----------------------";
                    break;
                case MARK:
                    try {
                        tasks.get(Integer.valueOf(words[1]) - 1).mark();
                        toPrint += "----------------------\n";
                        toPrint += "Good job! I've marked this item as done:\n";
                        toPrint += tasks.get(Integer.valueOf(words[1]) - 1).toString() + "\n";
                        toPrint += "----------------------";
                    } catch (ArrayIndexOutOfBoundsException e) {
                        toPrint = "----------------------\nPlease enter a valid task number.----------------------";
                    }
                    break;
                case UNMARK:
                    try {
                        tasks.get(Integer.valueOf(words[1]) - 1).unmark();
                        toPrint += "----------------------\n";
                        toPrint += "Get better, I've marked this item as not done:\n";
                        toPrint += tasks.get(Integer.valueOf(words[1]) - 1).toString() + "\n";
                        toPrint += "----------------------";
                    } catch (ArrayIndexOutOfBoundsException e) {
                        toPrint = "----------------------\nPlease enter a valid task number.----------------------";
                    }
                    break;
                case BYE:
                    break;
                case UNKNOWN:
                    break;

            }


            System.out.println(toPrint);
        }

        System.out.println("Bye!");
        scanner.close();
    }
}
