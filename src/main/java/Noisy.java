import java.util.Scanner;
import java.util.ArrayList;

public class Noisy {


    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<Task>();
        int currentPointer = 0;
        Task task = null;
        String welcomeMessage = "____________________________________________________________\n"
                + " Hello! I'm Noisy\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";
        System.out.println(welcomeMessage);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String goodbyeMessage = "____________________________________________________________\n"
                    + " Bye human :(\n"
                    + "____________________________________________________________";
            if (input.equals("bye")) {
                System.out.println(goodbyeMessage);
                scanner.close();
                break;
            }
            if (input.equals("list")) {
                String echoMessage = "____________________________________________________________\n" +
                        "Here are the tasks in your list\n";
                for (int i = 0; i < currentPointer; i++) {
                    int taskIndex = i + 1;
                    echoMessage += taskIndex + ". " + taskList.get(i) + "\n";
                }
                System.out.println(echoMessage + "____________________________________________________________");
                continue;
            }
            if (input.startsWith("mark ")) {
                String[] string = input.split(" ");
                Integer index = Integer.parseInt(string[1]);
                taskList.get(index - 1).markDone();
                String markString = "____________________________________________________________\n" +
                        "Nice! I've marked this task as done:\n" +
                        taskList.get(index - 1) + "\n" +
                        "____________________________________________________________\n";
                System.out.println(markString);
                continue;
            }

            task = new Task(input);
            try {
                switch (input.split(" ")[0]) {
                    case "todo":
                        if (input.split(" ", 2).length < 2) {
                            throw new NoisyException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        task = new Todo(input.split(" ", 2)[1]);
                        break;
                    case "Deadline":
                        String[] string = input.split(" ", 3);
                        task = new Deadline(string[1], string[2]);
                        break;
                    case "Event":
                        String[] eventString = input.split(" ", 4);
                        task = new Event(eventString[1], eventString[2], eventString[3]);
                        continue;
                    case "delete":
                        String[] deleteString = input.split(" ");
                        Integer index = Integer.parseInt(deleteString[1]);
                        Task deletedTask = taskList.get(index - 1);
                        taskList.remove(index - 1);
                        currentPointer--;
                        String deleteMessage = "____________________________________________________________\n" +
                                               " Noted. I've removed this task:\n" +
                                               deletedTask + "\n" +
                                               "Now you have " + currentPointer + " tasks in the list\n" +
                                               "____________________________________________________________\n";
                        System.out.println(deleteMessage);
                        continue;
                    default:
                        throw new NoisyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (NoisyException e) {
                System.out.println(e);
                continue;
            }


            taskList.add(task);
            currentPointer++;
            String taskAdd = "____________________________________________________________\n" +
                    " Got it. I've added this task:\n" +
                    task + "\n" +
                    "Now you have " + currentPointer + " tasks in the list.\n" +
                    "____________________________________________________________";
            System.out.println(taskAdd);
        }
    }
}

