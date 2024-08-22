import java.util.Scanner;

public class Angel {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = "____________________________________________________________\n";

        System.out.println(logo + " Hello! I'm Angel\n What can I do for you?\n" + logo);

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                System.out.println(logo + " Bye. Hope to see you again soon!\n" + logo);
                break;
            } else {
                System.out.println(logo + " " + userInput + "\n" + logo);
            }
        }

        scanner.close();
    }
}
