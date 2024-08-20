import java.util.ArrayList;
import java.util.Scanner;

public class Serenity {
    public static void main(String[] args) {

        ArrayList<Task> list = new ArrayList<>();
        String horizontalLine = "__________________________________________";

        System.out.println(horizontalLine);
        System.out.println("Hi, I'm Serenity!\n" + "What can I do for you?");
        System.out.println(horizontalLine);

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            if (input.equalsIgnoreCase("list")) {
                System.out.println(horizontalLine);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + 1 + ". " + list.get(i));
                }
            } else if (input.startsWith("mark")) {
                int index = Integer.parseInt(input.substring(5)) - 1;
                Task t = list.get(index);
                t.markAsDone();
                System.out.println(horizontalLine);
                System.out.println("Nice! I've marked this task as done:\n" + t);
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                Task t = list.get(index);
                t.markAsNotDone();
                System.out.println(horizontalLine);
                System.out.println("OK, I've marked this task as not done yet:\n" + t);
            } else {
                Task t = new Task(input);
                list.add(t);
                System.out.println(horizontalLine);
                System.out.println("added: " + input);
            }
            System.out.println(horizontalLine);
            input = sc.nextLine();
        }
        sc.close();

        System.out.println(horizontalLine);
        System.out.println("Goodbye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
