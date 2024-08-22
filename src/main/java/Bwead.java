import java.util.Scanner;

public class Bwead {

    public static String name = "Bwead";
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello from " + name + "!");
        //System.out.println("Bye. Hope to see you again soon!");

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(input);
            }

        }
    }
}
