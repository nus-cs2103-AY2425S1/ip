import java.util.Scanner;
import java.util.ArrayList;

public class Azir {
    public static void main(String[] args) {
        String input;
        ArrayList<String> taskList = new ArrayList<String>();
        System.out.println("----------------------------------");
        System.out.println("Hello! I'm Azir");
        System.out.println("What can I do for you?");
        System.out.println("----------------------------------");

        Scanner obj = new Scanner(System.in);

        while (!(input = obj.nextLine()).equals("bye")) {
            System.out.println("----------------------------------");
            if (input.equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.printf("%d. %s\n", i + 1, taskList.get(i));
                }
            } else {
                taskList.add(input);
                System.out.printf("added: %s\n", input);
            }
            System.out.println("----------------------------------");
        }
        System.out.println("----------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("----------------------------------");
    }
}
