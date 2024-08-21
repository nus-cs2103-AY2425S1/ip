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
            try {
                if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
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
                    if (input.startsWith("todo")) {
                        System.out.println("Got it. I've added this task:");
                        String description = input.substring(5);
                        Task currTask = new Todo(description);
                        taskList.add(currTask);
                        System.out.println(currTask);
                    } else if (input.startsWith("deadline")) {
                        System.out.println("Got it. I've added this task:");
                        int byIndex = input.indexOf("/by");
                        String description = input.substring(9, byIndex - 1);
                        String day = input.substring(byIndex + 4);
                        Task currTask = new Deadline(description, day);
                        taskList.add(currTask);
                        System.out.println(currTask);
                    } else if (input.startsWith("event")){
                        System.out.println("Got it. I've added this task:");
                        int fromIndex = input.indexOf("/from");
                        int toIndex = input.indexOf("/to");
                        String description = input.substring(6, fromIndex - 1);
                        String startDay = input.substring(fromIndex + 6, toIndex - 1);
                        String endDay = input.substring(toIndex + 4);
                        Task currTask = new Event(description, startDay, endDay);
                        taskList.add(currTask);
                        System.out.println(currTask);
                    } else {
                        throw new AzirException("Azir does not take in this input");
                    }
                    System.out.printf("Now you have %d %s in the list\n", taskList.size(), taskList.size() == 1 ? "task" : "tasks");
                }
                System.out.println("----------------------------------");
            } catch (AzirException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("----------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("----------------------------------");
    }
}
