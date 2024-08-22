import java.util.ArrayList;
import java.util.Scanner;

public class Topaz {

    private static ArrayList<Task> todoList = new ArrayList<>(100);

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
            } else if (prompt.startsWith("mark")) {
                int index = Integer.parseInt(prompt.substring(5));
                markTodo(index);
            } else if (prompt.startsWith("unmark")) {
                int index = Integer.parseInt(prompt.substring(7));
                unmarkTodo(index);
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
        todoList.add(new Task(input));
        System.out.println("____________________________________________________________");
        System.out.println(" Recorded! Hard work pays off~");
        System.out.println("____________________________________________________________");
    }

    private static void listTodos() {
        System.out.println("____________________________________________________________");
        System.out.println(" Who works for money these days? Money is a means, not an end. Work should make you happy... That's the most fundamental principle.");
        System.out.println(" Don't forget your work~");
        for (int i = 0; i < todoList.size(); i++) {
            System.out.println((i + 1) + ". " + todoList.get(i).getStatus());
        }
        System.out.println("____________________________________________________________");
    }

    private static void markTodo(int index) {
        Task todo = todoList.get(index - 1);
        todo = todo.setDone();
        todoList.set(index - 1, todo);
        System.out.println("____________________________________________________________");
        System.out.println(" Nice! I've marked this task as done: ");
        System.out.println("    " + todo.getStatus());
        System.out.println("____________________________________________________________");
    }

    private static void unmarkTodo(int index) {
        Task todo = todoList.get(index - 1);
        todo = todo.setUndo();
        todoList.set(index - 1, todo);
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("    " + todo.getStatus());
        System.out.println("____________________________________________________________");
    }
}
