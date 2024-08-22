import java.util.Scanner;

public class Bopes {
    public static void main(String[] args) {
        String intro = "Bopes is a personal assistant that helps you manage your tasks.";
        System.out.println(intro);

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.println("What can I do for you?");
            input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            }

            System.out.println(input);
        }

        scanner.close();
    }
}
