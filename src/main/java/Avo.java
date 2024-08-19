import java.util.Objects;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Avo {
    private static void greet() {
        String greetingMessage = "Hello, I am Avo.\nWhat can I do for you?";
        System.out.println(greetingMessage);
    }
    private static void endSession() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
    }
    public static void main(String[] args) {
        greet();
        List<String> inputs = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (Objects.equals(userInput, "exit")) {
                break;
            }
            if (Objects.equals(userInput, "list")) {
                for (int i = 0; i < inputs.size(); i++) {
                    System.out.println(Integer.toString(i + 1) + "." + inputs.get(i));
                }
            } else {
                inputs.add(userInput);
                System.out.println("added: " + userInput);
            }
        }
        endSession();
    }
}
