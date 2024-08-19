import java.util.Scanner;

public class LeBron {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        String name = "LeBron";
        System.out.println("------------------------");
        System.out.println(String.format("Hello! I'm %s", name));
        System.out.println("What can I do for you?");
        System.out.println("------------------------");

        while (true) {
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(String.format("%s: Bye! I'm leaving now.", name));
                System.out.println("------------------------");
                break;
            } else {
                System.out.println(String.format("%s: %s", name, input));
                System.out.println("------------------------");
            }
        }

    }
}
