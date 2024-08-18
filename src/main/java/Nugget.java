import java.util.ArrayList;
import java.util.Scanner;

public class Nugget {
    public static void main(String[] args) {
        ArrayList<String> messages = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("________________________________________");
        System.out.println("Hello! I'm Nugget");
        System.out.println("What can I do for you?");
        System.out.println("________________________________________");
        while (true) {
            String text = scanner.nextLine();
            if (text.equals("bye")) {
                System.out.println("________________________________________");
                break;
            } else if (text.equals("list")) {
                Integer len = messages.size();
                for (int i = 0; i < len; i++) {
                    String formattedMessage = String.format("%d. %s", i + 1, messages.get(i));
                    System.out.println(formattedMessage);
                }
                System.out.println("________________________________________");
            } else {
                System.out.println("________________________________________");
                System.out.println("added: " + text);
                System.out.println("________________________________________");
                messages.add(text);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________________");
    }
}
