import java.util.Scanner;
import java.util.ArrayList;

public class Twilight {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! I am Twilight your personal assistant\nWhat can I do for you?" );
        ArrayList<Task> tasks =  new ArrayList<Task>();
        String command = input.nextLine();
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("" + (i + 1) + ". " + tasks.get(i).toString());
                }
            } else if (command.substring(0,4).equals("mark")) {
                int task_num = Integer.valueOf(command.substring(5)) - 1;
                tasks.get(task_num).done();
                System.out.println("Excellent I have marked it: " + tasks.get(task_num).toString());
            } else if (command.substring(0,6).equals("unmark")) {
                int task_num = Integer.valueOf(command.substring(7)) - 1;
                tasks.get(task_num).unDone();
                System.out.println("Fine I have unmarked it: " + tasks.get(task_num).toString());
            } else {
                if (command.substring(0, 4).equals("todo")) {
                    tasks.add(new Todo(command.substring(5)));
                } else if (command.substring(0, 5).equals("event")) {
                    String[] split = command.split(" /from | /to ");
                    tasks.add(new Event(split[0].substring(6), split[1], split[2]));
                } else {
                    String[] split = command.split(" /by ");
                    tasks.add(new Deadline(split[0].substring(9), split[1]));
                }
                System.out.println("added: " + tasks.get(tasks.size() - 1).toString());
                System.out.println("There are " + tasks.size() + " tasks in the list");
            }
            command = input.nextLine();
        }
        System.out.println("See you");
    }
}
