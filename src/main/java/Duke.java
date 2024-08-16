import java.util.*;
public class Duke {
    private List<Task> taskStore = new ArrayList<Task>();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Duke duke = new Duke();
        duke.handleUserInput();
    }

    private void handleUserInput() {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String input = scan.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                scan.close();
                break;
            } else if (input.equals("list")) {
                if (taskStore.isEmpty()) {
                    System.out.println("List is currently empty.");
                    continue;
                }
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < this.taskStore.size(); ++i) {
                    System.out.printf("%d. %s\n", i+1, taskStore.get(i));
                }
            } else if (input.contains("unmark")) {
                char num = input.charAt(input.length() - 1);
                if (!Character.isDigit(num)) System.err.println("Invalid task number provided!");
                else {
                    int id = num - '0';
                    if (id > this.taskStore.size()) System.err.println("Invalid task number provided!");
                    else {
                        Task task = taskStore.get(id - 1);
                        task.markAsNotDone();
                    }
                }
            } else if (input.contains("mark")) {
                char num = input.charAt(input.length() - 1);
                if (!Character.isDigit(num)) System.err.println("Invalid task number provided!");
                else {
                    int id = num - '0';
                    if (id > this.taskStore.size()) System.err.println("Invalid task number provided!");
                    else {
                        Task task = taskStore.get(id - 1);
                        task.markAsDone();
                    }
                }
            } else if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
                System.out.println("Got it. I've added this task:");
                Task task = createTask(input);
                taskStore.add(task);
                System.out.println(task);
                System.out.printf("Now you have %d tasks in the list.\n", this.taskStore.size());
            } else {
                System.err.println("Invalid command provided!");
            }
        }
    }

    private Task createTask(String input) {
        if (input.contains("todo")) {
            return new Todo(input.substring(5));
        } else if (input.contains("deadline")) {
            int byDate = input.indexOf("/by");
            return new Deadline(input.substring(9, byDate - 1), input.substring(byDate+4));
        } else {
            int fromDate = input.indexOf("/from");
            int toDate = input.indexOf("/to");
            return new Event(input.substring(6, fromDate - 1), input.substring(fromDate + 6, toDate - 1), input.substring(toDate + 4));
        }
    }
}
