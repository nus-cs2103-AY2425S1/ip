import java.util.Scanner;

public class Hana {
    public static void main(String[] args) {
        String name = "Hana";
        String line = "___________________________________________";
        Scanner scanner = new Scanner(System.in);
        String input;

        //greet
        System.out.println(line);
        System.out.println(" Hello! I'm " + name);
        System.out.println(" What can I do for you?");
        System.out.println(line);

        //echo
        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            System.out.println(line);
            System.out.println(" " + input);
            System.out.println(line);
        }

        //exit
        System.out.println(line);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
