import java.util.Scanner;
import java.util.ArrayList;

public class Diego {

    public static void main(String[] args) {
        String hiMessage = """
            ____________________________________________________________
            Hello! I'm Diego
            What can I do for you?
            ____________________________________________________________
            """;

        String byeMessage = """
            ____________________________________________________________
            Bye. Hope to see you again soon!
            ____________________________________________________________
            """;

        System.out.println(hiMessage);

        ArrayList<Task> tasks = new ArrayList<>();
        int taskCount = 0;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list");
                    for (int i = 0; i < taskCount; i++) {
                        System.out.println(i + 1 + ". " + tasks.get(i));
                    }
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("mark")) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task currTask = tasks.get(index);
                    currTask.mark();
                    System.out.println(currTask);
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("unmark")) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Ok! I've marked this task as not done:");
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task currTask = tasks.get(index);
                    currTask.unMark();
                    System.out.println(currTask);
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("event")) {
                    System.out.println("____________________________________________________________");
                    String[] data = input.split(" /from | /to ");
                    if (data.length < 3) throw new NoDescriptionException();
                    tasks.add(new Event(data[0].substring(6), data[1], data[2]));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + tasks.get(taskCount));
                    taskCount++;
                    System.out.printf("Now you have %d tasks in the list%n", taskCount);
                    System.out.println("____________________________________________________________");

                } else if (input.startsWith("todo")) {
                    System.out.println("____________________________________________________________");
                    if (input.length() <= 5) throw new NoDescriptionException();
                    tasks.add(new Todo(input.substring(5)));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + tasks.get(taskCount));
                    taskCount++;
                    System.out.printf("Now you have %d tasks in the list%n", taskCount);
                    System.out.println("____________________________________________________________");

                } else if (input.startsWith("deadline")) {
                    System.out.println("____________________________________________________________");
                    String[] data = input.split(" /by");
                    if (data.length < 2) throw new NoDescriptionException();
                    tasks.add(new Deadline(data[0].substring(9), data[1]));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + tasks.get(taskCount));
                    taskCount++;
                    System.out.printf("Now you have %d tasks in the list%n", taskCount);
                    System.out.println("____________________________________________________________");

                } else if (input.startsWith("delete")) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Noted. I've removed this task:");
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    Task currTask = tasks.get(index);
                    System.out.println(" " + currTask);
                    taskCount--;
                    System.out.printf("Now you have %d tasks in the list%n", taskCount);
                    System.out.println("____________________________________________________________");

                }else {
                    throw new UnknownCommandException();
                }
            } catch (DiegoException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println(byeMessage);
    }
}
