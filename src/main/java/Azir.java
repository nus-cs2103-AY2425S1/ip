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
                taskList.add(new Task(input));
                System.out.printf("added: %s\n", input);
            }
            System.out.println("----------------------------------");
        }
        System.out.println("----------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("----------------------------------");
    }
}
