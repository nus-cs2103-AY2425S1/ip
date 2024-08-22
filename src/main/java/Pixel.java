import java.sql.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Pixel {
    public static String LINE = "\t" + "------------------------------------";
    public static ArrayList<String> tasks = new ArrayList<>();
    public static void greeting() {
        System.out.println(LINE);
        System.out.println("\t" + "Hello! I'm Pixel!");
        System.out.println("\t" + "What can I do for you?");
        System.out.println(LINE);
    }

    public static void exit() {
        System.out.println(LINE);
        System.out.println("\t" + "Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void response() {
        while (true) {
            Scanner scanner = new Scanner(System.in); // Creating a scanner object
            String command = scanner.nextLine();
            switch (command) {
            case "list":
                System.out.println(LINE);
                for (int index = 1; index <= tasks.size(); index++) {
                    System.out.println("\t" + index + ". " + tasks.get(index-1));
                }
                System.out.println(LINE);
                break;
            case "bye":
                exit();
                break;
            default:
                tasks.add(command);
                System.out.println(LINE);
                System.out.println("\t" + "added: " + command);
                System.out.println(LINE);
                break;
            }
            if (command.equals("bye")) {
                break;
            }
        }
    }
    public static void main(String[] args) {
        greeting();
        response();
    }
}
