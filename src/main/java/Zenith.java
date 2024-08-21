import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Zenith {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>();

        System.out.println(Message.getGreeting());
        User user = new User();

        while (true) {

            String input = scanner.nextLine();
            String[] inputSplit = input.split(" ", 2);
            switch (inputSplit[0]) {
                case "todo":
                    Task todo = new Todo(inputSplit[1].trim());
                    System.out.println("Got it. I've added this task:");
                    System.out.println(todo.toString());
                    user.addList(todo);
                    System.out.println("Now you have " + User.getCount() + " tasks in the list.\n");
                    break;
                case "deadline":
                    String[] deadlineSplit = inputSplit[1].split("/by");
                    Task deadline = new Deadline(deadlineSplit[0].trim(),deadlineSplit[1].trim());
                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadline.toString());
                    user.addList(deadline);
                    System.out.println("Now you have " + User.getCount() + " tasks in the list.\n");
                    break;
                case "event":
                    String[] eventSplit = inputSplit[1].split("/from | /to");
                    Task event = new Event(eventSplit[0].trim(),eventSplit[1].trim(),eventSplit[2].trim());
                    System.out.println("Got it. I've added this task:");
                    System.out.println(event.toString());
                    user.addList(event);
                    System.out.println("Now you have " + User.getCount() + " tasks in the list.\n");
                    break;
                case "mark":
                    System.out.println("Nice! I've marked this task as done:");
                    list = user.getList();
                    Task markTask = list.get(parseInt(inputSplit[1]) - 1);
                    markTask.setStatus(true);
                    System.out.println(markTask.toString() + "\n");
                    break;
                case "unmark":
                    System.out.println("OK, I've marked this task as not done yet:");
                    list = user.getList();
                    Task unmarkTask = list.get(parseInt(inputSplit[1]) - 1);
                    unmarkTask.setStatus(false);
                    System.out.println(unmarkTask.toString() + "\n");
                    break;
                case "list":
                    list = user.getList();
                    int listCount = 1;
                    for (Task item: list) {
                        System.out.println(listCount + ". " + item.toString());
                        listCount++;
                    }
                    System.out.println();
                    break;
                case "bye":
                    System.out.println(Message.getExit());
                    break;
            }

            if (input.equals("bye")) {
                break;
            }

        }
    }
}
