import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Spike {

    public static void helloMessage() {
        System.out.println("_________________________________________________________");
        System.out.println("Hello! I'm Spike\nWhat can I do for you?");
        System.out.println("_________________________________________________________");
        return;
    }

    public static void byeMessage() {
        System.out.println("     _________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("     _________________________________________________________");
        return;
    }

    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> toDoList = new ArrayList<>(100);
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equals("bye")) {
                byeMessage();
                break;
            } else if (input.equals("list")) {
                System.out.println("     _________________________________________________________");
                int counter = 0;
                for (String item : toDoList) {
                    counter++;
                    System.out.println("     " + counter + ". " + item);
                }
                System.out.println("     _________________________________________________________");
            } else {
                // addTask();
                toDoList.add(input);
                System.out.println("     _________________________________________________________");
                System.out.println("     " + "added: " + input);
                System.out.println("     _________________________________________________________");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        helloMessage();
        echo();
    }
}
