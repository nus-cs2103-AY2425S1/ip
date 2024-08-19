import java.util.Scanner;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        Janet janet = new Janet();
        System.out.println(janet.greet());

        Scanner input = new Scanner(System.in);

        while (input.hasNext()) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                // say bye and exit the program
                String exitMessage = janet.exit();
                System.out.println(exitMessage);
                break;
            } else if (command.equals("list")) {
                // show all the tasks inside the list of task
                String currentListOfTasks = janet.showList();
                System.out.println(currentListOfTasks);
            } else {
                String[] commandDetails = command.split(" ");
                if (commandDetails[0].equals("mark")) {
                    // mark the task as done
                    String markSuccess = janet.markAsDone(Integer.parseInt(commandDetails[1]));
                    System.out.println(markSuccess);
                } else if (commandDetails[0].equals("unmark")) {
                    // unmark the task
                    String unmarkSuccess = janet.unmark(Integer.parseInt(commandDetails[1]));
                    System.out.println(unmarkSuccess);
                } else {
                    // add the new task into the list of tasks
                    Task task = new Task(command);
                    String addTaskSuccess = janet.addTaskToList(task);
                    System.out.println(addTaskSuccess);
                }
            }
        }
    }
}
