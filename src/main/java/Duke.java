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
                for (int i = 0; i < this.taskStore.size(); ++i) {
                    System.out.printf("%d. %s\n", i+1, taskStore.get(i));
                }
            } else if (input.contains("unmark")) {
                char num = input.charAt(input.length() - 1);
                if (!Character.isDigit(num)) System.out.println("Invalid task number provided!");
                else {
                    int id = num - '0';
                    if (id > this.taskStore.size()) System.out.println("Invalid task number provided!");
                    else {
                        Task task = taskStore.get(id - 1);
                        task.markAsNotDone();
                    }
                }
            } else if (input.contains("mark")) {
                char num = input.charAt(input.length() - 1);
                if (!Character.isDigit(num)) System.out.println("Invalid task number provided!");
                else {
                    int id = num - '0';
                    if (id > this.taskStore.size()) System.out.println("Invalid task number provided!");
                    else {
                        Task task = taskStore.get(id - 1);
                        task.markAsDone();
                    }
                }
            } else {
                Task task = new Task(input);
                taskStore.add(task);
                System.out.printf("Added %s\n", input);
            }
        }
    }
}
