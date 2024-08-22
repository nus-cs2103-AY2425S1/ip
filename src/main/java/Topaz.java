import java.util.ArrayList;
import java.util.Scanner;

public class Topaz {

    private static ArrayList<String> todoList = new ArrayList<>(100);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        greetUser();

        while (true) {
            String prompt = scanner.nextLine();
            if (prompt.contains("bye")) {
                goodbyeUser();
                break;
            } else if (prompt.equals("list")) {
                listTodos();
            } else {
                addTodos(prompt);
            }
        }

        scanner.close();
    }

    private static void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Topaz — I work in the IPC's Strategic Investment Department. Long time no see! How have you been? Clocking in a lot of overtime?");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private static void goodbyeUser() {
        System.out.println(" Ugh, why do they have to call me for such a trivial matter... Sorry, something just came up at work, I've got to get going!");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    private static void echo(String input) {
        System.out.println("____________________________________________________________");
        System.out.println(input);
        System.out.println("____________________________________________________________");
    }

    private static void addTodos(String input) {
        todoList.add(input);
        System.out.println("____________________________________________________________");
        System.out.println(" Recorded! Hard work pays off~");
        System.out.println("____________________________________________________________");
    }

    private static void listTodos() {
        System.out.println("____________________________________________________________");
        System.out.println(" Who works for money these days? Money is a means, not an end. Work should make you happy... That's the most fundamental principle.");
        System.out.println(" Don't forget your work~");
        for (int i = 0; i < todoList.size(); i++) {
            System.out.println((i + 1) + ". " + todoList.get(i));
        }
        System.out.println("____________________________________________________________");
    }
}
