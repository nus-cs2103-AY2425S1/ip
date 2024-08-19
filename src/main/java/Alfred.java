import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Alfred {
    private static List<String> lis;

    public static void main(String[] args) {
        // Create a Scanner Object
        Scanner in = new Scanner(System.in);

        // List to store text entered by used
        lis = new ArrayList<String>();

        // Greet user
        greet();

        // Echo user input
        String input = in.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printList();
            } else {
                addToList(input);
            }
            input = in.nextLine();
        }
        farewell();
    }

    public static void greet() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Good day Sir. I am Alfred, your English butler.");
        System.out.println("    Might I offer you some tea, or perhaps something stronger to suit the occasion?");
        System.out.println("    ____________________________________________________________");
    }

    public static void farewell() {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Farewell Sir. Should you need anything, you know where to find me.");
        System.out.println("    ____________________________________________________________");
    }

    public static void addToList(String input) {
        lis.add(input);
        System.out.println("    ____________________________________________________________");
        System.out.println("    added: " + input);
        System.out.println("    ____________________________________________________________");
    }

    public static void printList() {
        System.out.println("    ____________________________________________________________");
        int counter = 1;
        for (String s : lis) {
            System.out.println("    " + counter + ". " + s);
            counter++;
        }
        System.out.println("    ____________________________________________________________");
    }
}
