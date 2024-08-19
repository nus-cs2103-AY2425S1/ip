import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Reo {
    public static void main(String[] args) {
        enum Command {
            BYE,
            LIST,
            UNKNOWN
        }
        // Create a new Scanner object
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------\n" +
                "Hello! I'm Reo.\nWhat can I do for you?\n"
                + "----------------------");

        String currInput;
        currInput = "placeholder";

        ArrayList<Task> tasks = new ArrayList<>();
        while (!currInput.toUpperCase().equals(Command.BYE)) {
            String toPrint = "";
            toPrint = "";
            currInput = scanner.nextLine().trim();

            Command command;

            try {
                command = Command.valueOf(currInput.toUpperCase());
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
                case UNKNOWN:
                    tasks.add(new Todo(currInput));
                    toPrint += "----------------------\n";
                    toPrint += "added: " + currInput + "\n";
                    toPrint += "----------------------";
                    break;
                case BYE:
                    break;

            }


            System.out.println(toPrint);
        }

        System.out.println("Bye!");
        scanner.close();
    }
}
