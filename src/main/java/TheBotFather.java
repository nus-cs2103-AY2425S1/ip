import java.util.Objects;
import java.util.Scanner;

public class TheBotFather {

    public static void main(String[] args) {

        printLine();
        printGreeting();
        printLine();

        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            printLine();
            if (Objects.equals(input, "bye")) break;
            else print(input);
            printLine();
        }

        printGoodBye();
        printLine();



    }

    public static void print(String str) {
        System.out.println("    " + str);
    }

    public static void printGreeting() {
        System.out.println("    Hello! I'm The BotFather");
        System.out.println("    What can I do for you?");
    }

    public static void printGoodBye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }
}
