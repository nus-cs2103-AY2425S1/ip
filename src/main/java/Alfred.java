import java.util.Scanner;

public class Alfred {
    public static void main(String[] args) {
        // Create a Scanner Object
        Scanner in = new Scanner(System.in);

        // Greet user
        greet();

        // Echo user input
        String input = in.nextLine();
        while (!input.equals("bye")) {
            echo(input);
            input = in.nextLine();
        }
        farewell();
    }

    public static void greet() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Good day Sir. I trust you are well.");
        System.out.println("    Might I offer you some tea, or perhaps something stronger to suit the occasion?");
        System.out.println("    ____________________________________________________________");
    }

    public static void farewell() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Farewell Sir. Should you need anything, you know where to find me");
        System.out.println("    ____________________________________________________________");
    }

    public static void echo(String input) {
        System.out.println("    ____________________________________________________________");
        System.out.println("    " + input);
        System.out.println("    ____________________________________________________________");
    }
}
