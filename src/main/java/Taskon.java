import java.util.ArrayList;
import java.util.Scanner;

public class Taskon {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();
        greet();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String toEcho = scanner.nextLine();
            if (toEcho.equals("bye")) {
                exit();
                break;
            } else if (toEcho.equals("list")) {
                listItems(tasks);
            } else {
                tasks.add(toEcho);
                System.out.println("Added: " + toEcho + "\n");
            }
        }
    }

    public static void greet() {
        String greeting = "Hello! I'm Taskon\nWhat can I do for you?\n";
        System.out.println(greeting);
    }

    public static void exit() {
        String exiting = "Bye. Hope to see you again soon!\n";
        System.out.println(exiting);
    }

    public static void listItems(ArrayList<String> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i+1 + ". " + tasks.get(i));
        }
    }
}
