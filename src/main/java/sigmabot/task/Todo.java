package sigmabot.task;

import java.util.Scanner;

public class Todo extends Task {

    public Todo(String name, String description) {
        super(name, description);
    }

    public static Todo createTodo(Scanner sc) {
        System.out.println("Enter name: ");
        String name = sc.nextLine().trim();
        System.out.println("Enter description: ");
        String description = sc.nextLine().trim();
        return new Todo(name, description);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
