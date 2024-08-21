import java.util.Scanner;
public class Hue {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String name = "Hue";

        System.out.println("____________________________________________________________" );
        System.out.println("Hello! I'm [" + name + "]");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________" );

        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            input = scanner.nextLine();
            System.out.println("____________________________________________________________");
            System.out.println(input);
            System.out.println("____________________________________________________________");
        } while (!input.equalsIgnoreCase("bye"));

            System.out.println("Bye. Hope to see you again soon!");

    }

}
