import java.util.Scanner;

public class Kotori {
    public static void main(String[] args) {
        printGreeting();
        Scanner s = new Scanner(System.in);
        while (s.hasNext()){
            String input = s.next();
            if (input.equals("bye")) {
                printExit();
                break;
            } else {
                printMessage(input);
            }
        }

        s.close();

    }

    public static void printLine() {
        System.out.println("    ___________________________________________");
    }

    public static void printMessage(String input) {
        printLine();
        System.out.println("    " + input);
        printLine();
    }

    public static void printGreeting() {
        printMessage("Hello! I'm Kotori.\n    What can I do for you?");
    }

    public static void printExit() {
        printMessage("Bye! Hope to see you again soon.");
    }
}
