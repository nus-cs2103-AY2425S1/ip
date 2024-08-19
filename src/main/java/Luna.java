import java.util.Scanner;

public class Luna {
    public static void main(String[] args) {
        String greetings = "Hello! I'm Luna\n" +
                "What can I do for you?";
        System.out.println(greetings);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                String exit = "Bye! Hope to see you again soon!";
                System.out.println(exit);
                break;
            }

            System.out.println(input);
        }

        scanner.close();

    }
}
