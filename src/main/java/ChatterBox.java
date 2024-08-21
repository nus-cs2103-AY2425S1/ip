import java.util.Scanner;
public class ChatterBox {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        //Greeting the user
        System.out.println("\t\t" + "_".repeat(50));
        System.out.println("\t\t" + "Hello! I'm ChatterBox");
        System.out.println("\t\t" + "What can I do for you?");
        System.out.println("\t\t" + "_".repeat(50));

        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("\t\t" + "_".repeat(50));
                System.out.println("\t\t" + "Bye. Hope to see you again soon!");
                System.out.println("\t\t" + "_".repeat(50));
                break;
            } else {
                System.out.println("\t\t" + "_".repeat(50));
                System.out.println("\t\t" + input);
                System.out.println("\t\t" + "_".repeat(50));
            }
        }
    }
}
