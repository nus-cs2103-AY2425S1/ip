import java.util.ArrayList;
import java.util.Scanner;

public class Schedulo {
    public static void main(String[] args) {
        String horizontalLine = "---------------";
        Scanner sc = new Scanner(System.in);
        ArrayList<String> Tasks = new ArrayList<>();
        System.out.println(horizontalLine);
        System.out.println("Hello I am Schedulo!");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);
        while (true) {
            String command = sc.nextLine();
            System.out.println(horizontalLine);
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(horizontalLine);
                break;
            }
            if (command.equals("list")) {
                for (int i = 0; i < Tasks.size(); i++) {
                    System.out.println((i+1) + ". " + Tasks.get(i));
                }
            } else {
                Tasks.add(command);
                System.out.println("added: " + command);
            }
            System.out.println(horizontalLine);
        }

        sc.close();
    }
}
