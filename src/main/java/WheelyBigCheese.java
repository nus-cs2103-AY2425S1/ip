import java.util.Objects;
import java.util.Scanner;

public class WheelyBigCheese {
    private static final String greeting = "Hello! I'm Wheely Big Cheese\nWhat can I do for you?";
    private static final String ending = "Bye. Hope to see you again soon!";

    /**
     * Method to format the output of the bot
     * @param s string to say
     */
    private static void Say(String s){
        System.out.println("____________________________________________________________");
        System.out.println(s);
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Scanner to get user input
        String input = "";

        WheelyBigCheese.Say(greeting);
        while (true){
            input = sc.nextLine();
            if (input.equals("bye")) break;
            WheelyBigCheese.Say(input);
        }

        WheelyBigCheese.Say(ending);
    }
}
