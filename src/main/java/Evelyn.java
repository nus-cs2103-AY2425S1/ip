import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
public class Evelyn {
    private static String chatbotName = "Evelyn";
    private static String horizontalLine = "-----------------------------------------";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList lst = new ArrayList(100);
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
            } else if (Objects.equals(text, "list")) {
                System.out.println(horizontalLine);
                for (int i = 0; i < lst.size(); i++) {
                    System.out.println((i + 1) + ". " + lst.get(i));
                }
                System.out.println(horizontalLine);
            } else {
                lst.add(new Task(text));
                System.out.println(horizontalLine);
                System.out.println("added: " + text);
                System.out.println(horizontalLine);
            }
        }
    }
}
