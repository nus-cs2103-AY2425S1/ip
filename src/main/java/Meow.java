import java.util.Scanner;

public class Meow {
    private static String name = "Meow";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("_____________________________________________________________\n" +
                " Hello! I'm " + name + "\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        while (true) {
            String input = scanner.nextLine();
            System.out.println("____________________________________________________________");
            System.out.println(input);
            System.out.println("____________________________________________________________");

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
        }
    }
}