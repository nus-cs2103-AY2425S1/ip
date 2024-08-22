import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class Weeny {
    public static void main(String[] args) {
        boolean farewell = false;
        List<Task> data = new ArrayList<>();
        Scanner user_input = new Scanner(System.in);

        line();
        System.out.println("Hello! I'm Weeny\nWhat can I do for you?");
        line();

        while (!farewell) {
            String input = user_input.nextLine();
            String[] inputParts = input.split(" ", 2);
            String command = inputParts[0];

            if (command.equals("list")) {
                line();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < data.size(); i++) {
                    System.out.println((i + 1) + ". " + data.get(i).toString());
                }
                line();
            } else if (command.equals("bye")) {
                farewell = true;
                line();
                System.out.println("Bye. Hope to see you again soon!");
                line();
            } else if (command.equals("mark")) {
                if (inputParts.length >= 2) {
                    int val = Integer.parseInt(inputParts[1]) - 1;
                    if (val >= 0 && val < data.size()) {
                        data.get(val).mark();
                        line();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(data.get(val).toString());
                        line();
                    }
                }
            } else if (command.equals("unmark")) {
                if (inputParts.length >= 2) {
                    int val = Integer.parseInt(inputParts[1]) - 1;
                    if (val >= 0 && val < data.size()) {
                        data.get(val).unmark();
                        line();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(data.get(val).toString());
                        line();
                    }
                }
            } else {
                Task newTask = new Task(input);
                data.add(newTask);
                line();
                System.out.println("Added: " + newTask.toString());
                line();
            }
        }

        user_input.close();
    }

    public static void line() {
        System.out.println("____________________________________________________________");
    }
}