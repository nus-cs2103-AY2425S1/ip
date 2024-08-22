import java.util.Scanner;

public class Moody {
    public static void main(String[] args) {
        String spacer = "____________________________________________________________\n";
        System.out.println(spacer
                + "Hello! I'm Moody!\nWhat can I do for you?\n"
                + spacer);

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(spacer
                        + "Bye. Hope to see you again soon!\n"
                        + spacer);
                break;
            } else {
                System.out.println(spacer + userInput + "\n" + spacer);
            }
        }
    }
}
