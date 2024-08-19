import java.util.Objects;
import java.util.Scanner;
public class Avo {
    public static void main(String[] args) {
        String greetingMessage = "Hello, I am Avo.\nWhat can I do for you?";
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(greetingMessage);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            System.out.println(userInput);
            if (Objects.equals(userInput, "exit")) {
                break;
            }
        }
        System.out.println(exitMessage);
    }
}
