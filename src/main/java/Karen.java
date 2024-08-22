import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import main.java.Task;

public class Karen {
    private static List<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        final String LINE = "_______________________\n";
        String output = LINE +
                        "Hi! I'm Karen\n" +
                        "What can I do for you?\n" +
                        LINE;

        System.out.print(output);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            output = "";

            if (input.equals("bye")) {
                //End program
                System.out.print(
                        LINE +
                        "Bye! Hope to see you again!\n" +
                        LINE
                );
                break;

            } else if (input.equals("list")) {
                //List tasks
                output += LINE;
                if (tasks.isEmpty()) {
                    output += "No tasks yet!\n";
                } else {
                    for (int i = 0; i < tasks.size(); i++) {
                        Task t = tasks.get(i);
                        output += String.format("%d. %s\n", i+1, t);
                    }
                }
                output += LINE;

            } else if (input.split(" ")[0].equals("mark")) {
                //Mark as done
                int n = Integer.parseInt(input.split(" ")[1]) - 1;
                Task marked_task = tasks.get(n);
                marked_task.mark();
                output += LINE +
                        "Nice! I've marked this task as done:\n\t" +
                        marked_task + "\n" +
                        LINE;

            } else if (input.split(" ")[0].equals("unmark")) {
                //Unmark as done
                int n = Integer.parseInt(input.split(" ")[1]) - 1;
                Task unmarked_task = tasks.get(n);
                unmarked_task.unmark();
                output += LINE +
                        "Ok! This task is now marked undone:\n\t" +
                        unmarked_task + "\n" +
                        LINE;

            } else {
                //Add new task
                Task newTask = new Task(input);
                tasks.add(newTask);
                output = LINE +
                        "Added: " + input + "\n" +
                        LINE;
            }

            System.out.print(output);
        }

    }
}
