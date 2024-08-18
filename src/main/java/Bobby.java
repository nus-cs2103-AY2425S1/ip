import java.util.ArrayList;
import java.util.Scanner;

public class Bobby {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();

        System.out.println("Hello, I'm Bobby");
        System.out.println("What can I do for you?");
        while (true) {
            String s = input.nextLine();
            if (s.equals("bye")) {
                break;
            } else if (s.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + ". " + tasks.get(i));
                }
            } else {
                tasks.add(s);
                String text = String.format("Added: %s", s);
                System.out.println(text);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
