import java.util.Scanner;
import java.util.ArrayList;

public class Prince {

    private static String LINE = "--------------------------------------";
    private static boolean TOGGLER = true;

    public static void main(String[] args) {

        // initialise scanner for user input
        Scanner sc = new Scanner(System.in);

        // initialise arr for input storage
        ArrayList<String> arr = new ArrayList<String>();

        printline();
        System.out.println("Hello! I'm Prince");
        System.out.println("What can I do for you?");
        printline();

        while (TOGGLER) {
            // get input from the user
            String input = sc.nextLine();
            printline();

            if (!input.equals("bye") && !input.equals("list")) {
                System.out.println("added: " + input);
                printline();

                // add the input to the arraylist
                arr.add(input);
            } else if (input.equals("list")) {

                // print the list of inputs
                int length = arr.size();
                for (int i = 0; i < length; i++) {
                    String text = arr.get(i);
                    int num = i + 1;
                    System.out.println(num + ". " + text);
                }
                printline();

            } else {
                TOGGLER = false;
            }
        }

        // close and cleanup scanner
        sc.close();
        System.out.println("Bye. Hope to see you again soon!");

    }

    private static void printline() {
        System.out.println(LINE);
    }
}
