import java.util.Scanner;

public class Ollie {
    // Private Types
    private static final String NAME = "Ollie";
    private static final String DIVIDER = "____________________________________________________________";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Skeletal Beginning
        System.out.println(Ollie.DIVIDER);

        // Greet
        String greet = String.format("Hello! I'm %s!\nWhat can I do for you?\n%s", Ollie.NAME, Ollie.DIVIDER);
        System.out.println(greet);

        // Echo
        Object input = scanner.nextLine();
        while(!input.equals("bye")) {
            String echo = String.format("%s\n%s",input.toString(), Ollie.DIVIDER);
            System.out.println(echo);
            input = scanner.nextLine();
        }

        // Exit
        String exit = String.format("Bye. Hope to see you again soon!\n%s",Ollie.DIVIDER);
        System.out.println(exit);
    }

}
