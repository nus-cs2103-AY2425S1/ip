import java.util.ArrayList;
import java.util.Scanner;

public class Thanos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

        System.out.println("Hello! I'm Thanos!\nWhat can I do for you?");

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d. %s\n", i + 1, tasks.get(i));
                }
            } else {
                tasks.add(userInput);
                System.out.println("added: " + userInput);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
