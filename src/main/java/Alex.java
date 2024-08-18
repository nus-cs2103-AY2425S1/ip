import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Alex {

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String line = "____________________________________________________________";
        ArrayList<Task> list = new ArrayList<>();
        String greeting =
                """
                        ____________________________________________________________
                         Hello! I'm Alex
                         What can I do for you?
                        ____________________________________________________________""";

        System.out.println(greeting);

        while(true) {
            String response = myObj.nextLine();
            if (response.equals("bye")) {
                break;
            } else if (response.equals("list")) {
                System.out.println(line + "\nHere are the tasks in your list: ");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(list.get(i - 1));
                }
                System.out.println(line);
           } else {
                String[] arrOfStr = response.split(" ");

                if ((arrOfStr[0].equals("mark") || arrOfStr[0].equals("unmark")
                ) && arrOfStr.length == 2) {
                    try {
                        int taskNumber = Integer.valueOf(arrOfStr[1]);

                        Task task = list.get(taskNumber - 1);
                        if (arrOfStr[0].equals("mark")) {
                            task.markAsDone();
                            System.out.println(line + "\nNice! I've marked this task as done: \n" + task.stringForMark() + "\n" + line);
                        } else {
                            task.markAsUndone();
                            System.out.println(line + "\nOK, I've marked this task as not done yet: \n" + task.stringForMark() + "\n" + line);
                        }
                    } catch (NumberFormatException e) {
                        list.add(new Task(list.size() + 1, response, false));
                        System.out.println(line + "\nadded: " + response + "\n" + line);
                    }
                } else {
                    list.add(new Task(list.size() + 1, response, false));
                    System.out.println(line + "\nadded: " + response + "\n" + line);
                }
            }
        }

        String farewell =
                """
                        ____________________________________________________________
                        Bye. Hope to see you again soon!
                        ____________________________________________________________""";

        System.out.println(farewell);
    }
}
