import java.util.ArrayList;
import java.util.Scanner;

public class LeBron {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList list = new ArrayList();

        String name = "LeBron";
        System.out.println("------------------------");
        System.out.println(String.format("Hello! I'm %s", name));
        System.out.println("What can I do for you?");
        System.out.println("------------------------");

        while (true) {
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(String.format("%s: Bye! I'm leaving now.", name));
                System.out.println("------------------------");
                break;
            } else if (input.equals("list")) {
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(String.format("%d. %s", i, list.get(i - 1)));
                }
                System.out.println("------------------------");
            } else {
                list.add(input);
                System.out.println(String.format("added: %s", input));
                System.out.println("------------------------");
            }
        }

    }
}
