import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Astor {
    public static void main(String[] args) {
        System.out.println("Hello, I'm Astor! \n" +
                "What can I do for you?\n" +
                "--------------------------------------");

        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<>();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                int index = 1;
                for (Task t : list) {
                    System.out.println(index + ". " + t.toString());
                    index++;
                }
                System.out.println("--------------------------------------");
            } else if (input.startsWith("mark")) {
                int index = -1;
                try {
                    String formattedString = input.substring(4).trim();
                    index = Integer.parseInt(formattedString);
                } catch (NumberFormatException e) {
                    System.out.println("Please indicated clearly which task to mark!\n" +
                            "--------------------------------------");
                    continue;
                }
                if (index >= 1 && index <= list.size()) {
                    Task task = list.get(index - 1);
                    if (task.isDone()) {
                        System.out.println("This task is already done:\n" +
                                task + "\n--------------------------------------");
                    } else {
                        task.markDone();
                        System.out.println("Nice! I've marked this task as done:\n" +
                                task + "\n--------------------------------------");
                    }
                } else {
                    System.out.println("Please enter a valid task number\n" +
                            "--------------------------------------");
                }
            } else if (input.startsWith("unmark")) {
                int index = -1;
                try {
                    String formattedString = input.substring(6).trim();
                    index = Integer.parseInt(formattedString);
                } catch (NumberFormatException e) {
                    System.out.println("Please indicated clearly which task to unmark!\n" +
                            "--------------------------------------");
                    continue;
                }
                if (index >= 1 && index <= list.size()) {
                    Task task = list.get(index - 1);
                    if (task.isDone()) {
                        task.markUndone();
                        System.out.println("OK, I've marked this task as not done yet:\n" +
                                task + "\n--------------------------------------");
                    } else {
                        System.out.println("This task is already marked as uncompleted:\n" +
                                task + "\n--------------------------------------");
                    }
                } else {
                    System.out.println("Please enter a valid task number\n" +
                            "--------------------------------------");
                }
            } else if (input.isEmpty()) {
                System.out.println("Please type something!\n" +
                        "--------------------------------------");
            }else {
                Task task = new Task(input);
                list.add(task);
                System.out.println("added: " + input + "\n--------------------------------------");
            }
        }
    }
}
