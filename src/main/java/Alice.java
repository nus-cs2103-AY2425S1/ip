import java.util.ArrayList;
import java.util.Scanner;

public class Alice {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("Hello! I am Alice! \nWhat can I do for you?");
        System.out.println("------------------------------------------");

        // get commands from the user while response is not "bye"
        String response = "";
        while (true) {
            Scanner scanner = new Scanner(System.in);
            response = scanner.nextLine();

            // if user says bye, exit the loop
            if (response.equals("bye")) {
                break;
            }
            // if user wants list, print out all the tasks added
            else if (response.equals("list")) {
                System.out.println("------------------------------------------");
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, tasks.get(i).toString());
                }
                System.out.println("------------------------------------------");
                continue;
            }

            // for marking tasks as done or undone: separate the string from the index
            String[] result = response.split(" ");
            switch (result[0]) {
                case "mark":
                    Task markTask = tasks.get(Integer.parseInt(result[1]) - 1);
                    markTask.markAsDone();
                    System.out.println("------------------------------------------");
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println(markTask);
                    System.out.println("------------------------------------------");
                    break;
                case "unmark":
                    Task unmarkTask = tasks.get(Integer.parseInt(result[1]) - 1);
                    unmarkTask.markAsUndone();
                    System.out.println("------------------------------------------");
                    System.out.println("Ok, I've marked this task as not done yet: ");
                    System.out.println(unmarkTask);
                    System.out.println("------------------------------------------");
                    break;
                default:
                    break;
            }
            if (result[0].equals("mark") || result[0].equals("unmark")) {
                continue;
            }
            
            // all other strings are tasks that should be added
            // determines type of task added
            String[] taskInfo = response.split(" ", 2);
            switch (taskInfo[0]) {
                case "todo":
                    // taskInfo[1] contains description
                    Todo todoTask = new Todo(taskInfo[1]);
                    tasks.add(todoTask);
                    break;
                case "deadline":
                    // taskInfo[1] contains description /by deadline
                    String[] deadlineInfo = taskInfo[1].split("/by");
                    // deadlineInfo[0] = description, deadlineInfo[1] = deadline
                    Deadline deadlineTask = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                    tasks.add(deadlineTask);
                    break;
                case "event":
                    // taskInfo[1] contains description /from from /to to
                    String[] eventInfo = taskInfo[1].split("/from");
                    String[] times = eventInfo[1].split("/to");
                    Event eventTask = new Event(eventInfo[0], times[0], times[1]);
                    tasks.add(eventTask);
                    break;
                default:
                    break;
            }
            System.out.println("------------------------------------------");
            System.out.println("Got it. I've added this task: ");
            System.out.println(tasks.get(tasks.size() - 1));
            System.out.printf("Now you have %d tasks in the list%n", tasks.size());
            System.out.println("------------------------------------------");
        }

        System.out.println("------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
    }
}
