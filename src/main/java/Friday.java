import java.util.Objects;
import java.util.Scanner;

public class Friday {
    public static void main(String[] args) {
        String logo = "___________        .__     .___                \n"
                + "\\_   _____/_______ |__|  __| _/_____    ___.__.\n"
                + " |    __)  \\_  __ \\|  | / __ | \\__  \\  <   |  |\n"
                + " |     \\    |  | \\/|  |/ /_/ |  / __ \\_ \\___  |\n"
                + " \\___  /    |__|   |__|\\____ | (____  / / ____|\n"
                + "     \\/                     \\/      \\/  \\/     \n";

        System.out.println("Loading........\n" + logo);
        printLine();

        TaskList master = new TaskList(100);

        System.out.println("""
                Hello! I'm Friday!
                What would you like to do?
                """);

        boolean bye = false;
        while(!bye) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Your input > ");
            String input = getInput(sc);
            printLine();
            switch (input) {
                case "Bye":
                case "bye":
                    bye = true;
                    break;
                case "Quit":
                case "quit":
                case "Exit":
                case "exit":
                    System.out.println("Friday > Type \"bye\" or \"Bye\" to exit");
                    printLine();
                    break;
                case "List":
                case "list":
                    System.out.println("Friday > Here's everything!\n" + master.toString());
                    break;
                default:
                    master.addTask(new Task(input));
                    printLine();
                    break;
            }
        }
        terminate();
    }

    public static void printLine() {
        System.out.println("----------------------------------------------");
    }

    public static void terminate() {
        System.out.println("Friday > Bye! See you soon!");
        printLine();
    }

    public static String getInput(Scanner sc) {
        return sc.nextLine();
    }
}