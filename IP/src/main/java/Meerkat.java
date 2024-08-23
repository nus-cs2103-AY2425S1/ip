import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class

public class Meerkat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        String lines = "____________________________________________________________";
        String greeting = """
                Hello! I'm a meerkat from singapore
                What can I do for you?
                """;
        String bye = """
                Goodnight, sleep tight, Hope I don't ever see you again!
                """;
        System.out.println(lines + "\n" + greeting + lines);
        List<Task> listOfTasks = new ArrayList<>();

        while (true) {
            String taskName = sc.nextLine();
            // splits string based on space
            String[] strArray = taskName.split(" ");
            // create new task with name
            Task thisTask = new Task(taskName);

            // to end program
            if (taskName.equalsIgnoreCase("bye")) {
                System.out.println(lines + "\n" + bye + lines);
                break;

            // to display list of items
            } else if (taskName.equalsIgnoreCase("list")) {
                System.out.println(lines);
                for (int i = 1; i < listOfTasks.size() + 1; i++) {
                    System.out.println(i + "." + listOfTasks.get(i-1).toString());
                }
                System.out.println(lines);

            // mark item as done
            } else if (strArray.length == 2 && strArray[0].equals("mark")) {
                try {
                    int num = Integer.parseInt(strArray[1]);
                    if (num > 0 && num <= listOfTasks.size()) {
                        listOfTasks.get(num - 1).markAsCompleted();
                        System.out.println(lines + "\nNice! I've marked this task as done:\n" + listOfTasks.get(num-1) + "\n" + lines);
                    // task number is not within range
                    } else {
                        System.out.println(lines + "\nThis task does not exist! Unable to unmark.\n" + lines);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(lines + "\nThis task does not exist! Unable to mark.\n" + lines);
                }
            // mark item as not done
            } else if (strArray.length == 2 && strArray[0].equals("unmark")) {
                try {
                    int num = Integer.parseInt(strArray[1]);
                    if (num > 0 && num <= listOfTasks.size()) {
                        listOfTasks.get(num - 1).markAsIncomplete();
                        System.out.println(lines + "\nOK, I've marked this task as not done yet:\n" + listOfTasks.get(num-1) + "\n" + lines);
                    // task number is not within range
                    } else {
                        System.out.println(lines + "\nThis task does not exist! Unable to unmark.\n" + lines);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(lines + "\nThis task does not exist! Unable to unmark.\n" + lines);
                }
            }
            else {
                listOfTasks.add(thisTask);
                System.out.println(lines + "\nadded: " + taskName + "\n" + lines);
            }

        }
        System.exit(0);
    }
}
