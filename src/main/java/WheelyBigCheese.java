import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class WheelyBigCheese {
    private static final String greeting = "Hello! I'm Wheely Big Cheese\nWhat can I do for you?";
    private static final String ending = "Schwooo Weeeeee!!! Shutting down.....";

    /**
     * Method to format the output of the bot
     * @param s String to say
     */
    private static void say(String s){
        System.out.println("____________________________________________________________");
        System.out.println(s);
        System.out.println("____________________________________________________________");
    }
    /**
     * Overloaded method to say a list
     * @param ls ArrayList
     */
    private static void say(ArrayList<?> ls) {
        for (int i = 0; i < ls.size(); i++) {
            String output = String.valueOf(i+1) + ". " + ls.get(i).toString();
            System.out.println(output);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Scanner to get user input
        String input = "";
        ArrayList<String> list = new ArrayList<>();
        boolean exitChat = false;

        WheelyBigCheese.say(greeting);

        do {
            input = sc.nextLine();
            switch (input) {
                case "bye":
                    exitChat = true;
                    break;
                case "list":
                    WheelyBigCheese.say(list);
                    break;
                default:
                    list.add(input);
                    WheelyBigCheese.say("added: " + input);
            }
        } while (!exitChat);

        WheelyBigCheese.say(ending);
    }
}
