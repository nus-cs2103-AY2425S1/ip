import java.util.Scanner;

public class Astor {
    public static void main(String[] args) {
        System.out.println("Hello, I'm Astor! \n" +
                "What can I do for you?\n" +
                "--------------------------------------");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            }
            System.out.println(input + "\n--------------------------------------");
        }

    }
}
