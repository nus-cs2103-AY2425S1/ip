import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bob {

    private static void dialogue(String input) {
        System.out.println("___________________________________\n");
        System.out.println(input);
        System.out.println("___________________________________\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List messages = new ArrayList<>();

        dialogue("Hello! I'm Bob\n"
                + "What can I do for you?");

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                dialogue("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                String out = "";
                for (int i = 0; i < messages.size(); i++) {
                    out += (i + 1) + ". " + messages.get(i) + "\n";
                }
                dialogue(out);
            } else {
                dialogue("Added:" + userInput);
                messages.add(userInput);
            }
        }

        scanner.close();
    }
}
