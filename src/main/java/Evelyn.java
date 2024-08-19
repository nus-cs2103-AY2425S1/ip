import java.util.Objects;
import java.util.Scanner;
public class Evelyn {
    private static String chatbotName = "Evelyn";
    private static String horizontalLine = "-----------------------------------------";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = null;
        System.out.println(horizontalLine);
        System.out.println("Hi! I am Evelyn");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        while (true) {
            text = scanner.nextLine();

            if ((Objects.equals(text, "bye")) || (Objects.equals(text, "BYE")) || (Objects.equals(text, "Bye"))) {
                System.out.println(horizontalLine);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(horizontalLine);
                break;
            } else {
                System.out.println(horizontalLine);
                System.out.println(text);
                System.out.println(horizontalLine);
            }
        }
    }
}
