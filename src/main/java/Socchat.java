import java.util.ArrayList;
import java.util.Scanner;

public class Socchat {

    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        greet();
        while (true) {
            String input = scanner.nextLine();
            System.out.print("> ");
            if (input.equals("bye")) {
                exit();
                break;
            } else if (input.equals("list")) {
                list(tasks);
            } else {
                tasks.add(input);
                System.out.print("added: ");
                System.out.println(input);
            }

        }
    }
    public static void greet() {
        System.out.println("Hello! I'm Socchat!");
        System.out.println("What can I do for you?");
    }
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!    ");
    }
    public static void list(ArrayList<String> tasks) {
        for(int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + ": ");
            System.out.println(tasks.get(i));
        }
    }
}
