import java.util.ArrayList;
import java.util.Scanner;

public class Oliver {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Oliver.");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> tasks = new ArrayList<>();
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("\t" + (i+1) + ". " + tasks.get(i));
                }
            } else {
                tasks.add(input);
                System.out.println("\tadded: " + input);
            }
        }
        sc.close();
    }
}
