import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Reo {
    public static void main(String[] args) {
        enum Command {
            BYE,
            LIST,
            TODO,
            EVENT,
            DEADLINE,
            MARK,
            UNMARK,
            DELETE,
            UNKNOWN,
        }
        // Create a new Scanner object
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------\n" +
                "Hello! I'm Reo.\nWhat can I do for you?\n"
                + "----------------------");
        ArrayList<Task> tasks = FileOperations.readFile();

        String currInput;
        currInput = "placeholder";

        // ArrayList<Task> tasks = data;
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
                    String[] parts1 = currInput.split(" ", 2);
                    Todo toPush = new Todo(parts1[1], false);
                    tasks.add(toPush);
                    FileOperations.writeFile(toPush);
                    toPrint += "----------------------\n";
                    try {
                        toPrint += "I've added this todo:\n " + toPush.toString() + "\n";
                        toPrint += "Now, you have " + tasks.size() + " task(s) in your list.\n";
                    } catch (ArrayIndexOutOfBoundsException e) {
                        toPrint += "Please enter a valid task name.\n";
                    }
                    toPrint += "----------------------";
                    break;
                case MARK:
                    try {
                        tasks.get(Integer.valueOf(words[1]) - 1).mark();
                        FileOperations.editLine(Integer.valueOf(words[1]), "mark");
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
                        FileOperations.editLine(Integer.valueOf(words[1]), "unmark");
                        toPrint += "----------------------\n";
                        toPrint += "Get better, I've marked this item as not done:\n";
                        toPrint += tasks.get(Integer.valueOf(words[1]) - 1).toString() + "\n";
                        toPrint += "----------------------";
                    } catch (ArrayIndexOutOfBoundsException e) {
                        toPrint = "----------------------\nPlease enter a valid task number.\n----------------------";
                    }
                    break;
                case DEADLINE:
                    try {
                        String[] parts = currInput.split("/by", 2);
                        String[] firstPart = parts[0].split(" ", 2);

                        String name = firstPart[1];
                        String deadline = parts[1].trim();

                        Deadline toPush1 = new Deadline(name, false, deadline);
                        tasks.add(toPush1);
                        FileOperations.writeFile(toPush1);

                        toPrint += "----------------------\n";
                        toPrint += "I've added this deadline:\n";
                        toPrint += toPush1.toString() + "\n";
                        toPrint += "Now, you have " + tasks.size() + " task(s) in your list.\n";
                        toPrint += "----------------------";
                    } catch (ArrayIndexOutOfBoundsException e) {
                        toPrint = "----------------------\nPlease enter a valid task name and deadline.\n----------------------";
                    }
                    break;
                case EVENT:
                    try {
                        String[] parts = currInput.split("/from", 2);
                        String[] firstPart = parts[0].split(" ", 2);
                        String[] secondPart = parts[1].split("/to", 2);

                        String name = firstPart[1];
                        String from = secondPart[0].trim();
                        String to = secondPart[1].trim();

                        Event toPush2 = new Event(name, false, to, from);
                        tasks.add(toPush2);
                        FileOperations.writeFile(toPush2);

                        toPrint += "----------------------\n";
                        toPrint += "I've added this event:\n";
                        toPrint += toPush2.toString() + "\n";
                        toPrint += "Now, you have " + tasks.size() + " task(s) in your list.\n";
                        toPrint += "----------------------";
                    } catch (ArrayIndexOutOfBoundsException e) {
                        toPrint = "----------------------\nPlease enter a valid task name and to & from dates.\n----------------------";
                    }
                    break;
                case DELETE:
                    try {
                        int index = Integer.valueOf(words[1]) - 1;
                        Task toRemove = tasks.get(index);
                        tasks.remove(index);
                        FileOperations.removeLine(index + 1);
                        toPrint += "----------------------\n";
                        toPrint += "I've deleted this task:\n";
                        toPrint += toRemove.toString() + "\n";
                        toPrint += "Now, you have " + tasks.size() + " task(s) in your list.\n";
                        toPrint += "----------------------";
                    } catch (ArrayIndexOutOfBoundsException e) {
                        toPrint = "----------------------\nPlease enter a valid task number.----------------------";
                    }
                case BYE:
                    break;
                case UNKNOWN:
                    toPrint = "----------------------\nERROR: Please enter a valid command.\n----------------------";

            }


            System.out.println(toPrint);
        }

        System.out.println("Bye!");
        scanner.close();
    }
}
