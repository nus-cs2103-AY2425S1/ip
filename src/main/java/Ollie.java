import java.util.Scanner;
import java.util.ArrayList;

public class Ollie {
    // Private Types
    private static final String NAME = "Ollie";
    private static final String DIVIDER = "____________________________________________________________";
    private static final ArrayList<String> userInputs = new ArrayList<String>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Greet
        Ollie.greet();

        String input = scanner.nextLine();
        while(!input.equals("bye")) {

            if(input.equals("list")) {
                // List
                Ollie.list();
            } else {
                // Remember
                Ollie.remember(input);

                // Echo
                Ollie.echo(input);

            }
            input = scanner.nextLine();
        }

        // Exit
        Ollie.exit();
    }

    // Private Methods
    private static void greet() {
        // Skeletal Beginning
        System.out.println(Ollie.DIVIDER);

        // Greet
        String greet = String.format("Hello! I'm %s!\nWhat can I do for you?\n%s", Ollie.NAME, Ollie.DIVIDER);
        System.out.println(greet);
    }

    private static void exit() {
        // Exit
        String exit = String.format("Bye. Hope to see you again soon!\n%s",Ollie.DIVIDER);
        System.out.println(exit);
    }

    private static void remember(String s) {
        userInputs.add(s);
    }
    private static void list() {
        for(int i = 0; i < userInputs.size(); i++) {
            String listItem = String.format("%d. %s", i + 1, userInputs.get(i));
            System.out.println(listItem);
        }
        System.out.println(Ollie.DIVIDER);
    }

    private static void echo(String s) {
        String echo = String.format("%s\n%s",s, Ollie.DIVIDER);
        System.out.println(echo);
    }


}
