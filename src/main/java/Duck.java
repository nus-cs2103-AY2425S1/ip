import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Scanner;

public class Duck {
    static ArrayList<Task> ls = new ArrayList<>();

    public static void main(String[] args) {
        String logo = """
                        ,---,                                  ,-. \s
                      .'  .' `\\                            ,--/ /| \s
                    ,---.'     \\          ,--,           ,--. :/ | \s
                    |   |  .`\\  |       ,'_ /|           :  : ' /  \s
                    :   : |  '  |  .--. |  | :    ,---.  |  '  /   \s
                    |   ' '  ;  :,'_ /| :  . |   /     \\ '  |  :   \s
                    '   | ;  .  ||  ' | |  . .  /    / ' |  |   \\  \s
                    |   | :  |  '|  | ' |  | | .    ' /  '  : |. \\ \s
                    '   : | /  ; :  | : ;  ; | '   ; :__ |  | ' \\ \\\s
                    |   | '` ,/  '  :  `--'   \\'   | '.'|'  : |--' \s
                    ;   :  .'    :  ,      .-./|   :    :;  |,'    \s
                    |   ,.'       `--`----'     \\   \\  / '--'      \s
                    '---'                        `----'            \s
                """;


        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duck");
        System.out.println("What can I do for you? \n");

        getInputTillBye();
        System.out.println("Bye. Hope to see you again soon!");

    }

    // obtain user input till he inputs bye, ignoring case
    public static void getInputTillBye() {
        Scanner in = new Scanner(System.in);
        String message = in.nextLine();
        while (!message.equalsIgnoreCase("bye")) {

            reactTo(message);
            in = new Scanner(System.in);
            message = in.nextLine();
        }
    }

    public static void reactTo(String message) {
        if (message.equalsIgnoreCase("list")) {
            int idx = 1;
            System.out.println("Here are the tasks in your list:");
            for (Task t : ls) {
                System.out.println(idx++ + "." + t.toString());
            }
        } else if (isUpdatingStatus(message)) {
            updateStatus(message);
        } else {
            ls.add(new Task(message));
            System.out.println("added: " + message);
        }
    }

    public static boolean isUpdatingStatus(String message) {
        String[] words = message.split(" ");
        return words.length == 2
                && (words[0].equals("mark") || words[0].equals("unmark"))
                && isInteger(words[1]);
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void updateStatus(String message) {
        String[] words = message.split(" ");
        try {
            if (words[0].equals("mark")) {
                ls.get(Integer.parseInt(words[1]) - 1).markAsDone();
            } else {
                ls.get(Integer.parseInt(words[1]) - 1).markAsIncomplete();
            }
        } catch (NumberFormatException e) {
            System.out.println("Oops! you have to indicate a valid task index!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oops! Index out of bound :( Input a valid task index.");
        }
    }


}
