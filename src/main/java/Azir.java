import java.util.ArrayList;
import java.util.Scanner;

public class Azir {
    public static void main(String[] args) {
        String input;
        ArrayList<Task> taskList = new ArrayList<Task>();
        System.out.println("----------------------------------");
        System.out.println("Hello! I'm Azir");
        System.out.println("What can I do for you?");
        System.out.println("----------------------------------");

        Scanner obj = new Scanner(System.in);

        while (!(input = obj.nextLine()).equals("bye")) {
            System.out.println("----------------------------------");
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.printf("%d. %s\n", i + 1, taskList.get(i));
                }
            } else if (input.startsWith("mark")) {
                String[] result = input.split(" ");
                Task chosenTask = taskList.get(Integer.valueOf(result[1]) - 1);
                chosenTask.setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(chosenTask);
            } else if (input.startsWith("unmark")) {
                String[] result = input.split(" ");
                Task chosenTask = taskList.get(Integer.valueOf(result[1]) - 1);
                chosenTask.setNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(chosenTask);
            }
            else {
                System.out.println("Got it. I've added this task:");
                if (input.startsWith("todo")) {
                    String description = input.substring(5);
                    taskList.add(new Todo(description));
                } else if (input.startsWith("deadline")) {
                    int byIndex = input.indexOf("/by");
                    String description = input.substring(9, byIndex - 1);
                    String day = input.substring(byIndex + 4);
                    taskList.add(new Deadline(description, day));
                } else {
                    int fromIndex = input.indexOf("/from");
                    int toIndex = input.indexOf("/to");
                    String description = input.substring(6, fromIndex - 1);
                    String startDay = input.substring(fromIndex + 6, toIndex - 1);
                    String endDay = input.substring(toIndex + 4);
                    taskList.add(new Event(description, startDay, endDay));
                }
                System.out.printf("Now you have %d tasks in the list\n", taskList.size());
            }
            System.out.println("----------------------------------");
        }
        System.out.println("----------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("----------------------------------");
    }
}
