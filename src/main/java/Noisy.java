import java.util.Scanner;
import java.util.ArrayList;

public class Noisy {


    public static void main(String[] args) {
        Storage storage = new Storage();
        TaskList taskList = new TaskList(storage.loadTasks());
        int currentPointer = taskList.getListSize();
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
                    echoMessage += taskIndex + ". " + taskList.getTask(i) + "\n";
                }
                System.out.println(echoMessage + "____________________________________________________________");
                continue;
            }
            if (input.startsWith("mark ")) {
                String[] string = input.split(" ");
                Integer index = Integer.parseInt(string[1]);
                taskList.markDoneFromList(index - 1);
                String markString = "____________________________________________________________\n" +
                        "Nice! I've marked this task as done:\n" +
                        taskList.getTask(index - 1) + "\n" +
                        "____________________________________________________________\n";
                System.out.println(markString);
                continue;
            }

            task = null;
            try {
                switch (input.split(" ")[0]) {
                    case "todo":
                        if (input.split(" ", 2).length < 2) {
                            throw new NoisyException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        task = new Todo(input.split(" ", 2)[1], false);
                        break;
                    case "Deadline":
                        String[] string = input.split(" ", 3);
                        task = new Deadline(string[1], false, string[2]);
                        break;
                    case "Event":
                        String[] eventString = input.split(" ", 4);
                        task = new Event(eventString[1], false, eventString[2], eventString[3]);
                        break;
                    case "delete":
                        String[] deleteString = input.split(" ");
                        Integer index = Integer.parseInt(deleteString[1]);
                        Task deletedTask = taskList.getTask(index - 1);
                        taskList.deleteFromList(index - 1);
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


            taskList.addToList(task);
            storage.saveTasks(taskList.getTasks());
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

