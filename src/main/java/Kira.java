import java.util.Scanner;

public class Kira {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List list = new List();
        String line = "____________________________________________________________\n";

        System.out.println(line +
                " Hello! I'm Kira\n" +
                " What can I do for you?\n" +
                line);

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(line +
                        "Bye. Hope to see you again soon!\n" +
                        line);
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                System.out.println(list.displayList());
                continue;
            }

            String[] strings = userInput.split(" ");

            if (strings[0].equals("mark")) {
                int index = Integer.parseInt(strings[1]) - 1;
                Task task = list.getTask(index);
                task.markAsDone();
            } else if (strings[0].equals("unmark")) {
                int index = Integer.parseInt(strings[1]) - 1;
                Task task = list.getTask(index);
                task.markAsUndone();
            } else {    // add task
                Task task = new Task(userInput);
                list.addTaskToList(task);
            }
        }
    }
}
