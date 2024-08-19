import java.lang.invoke.StringConcatFactory;
import java.util.Scanner;
public class Dook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        String separator = "____________________________________________________________";
        String greeting = "Hello! I'm Dook\nWhat can I do for you?\n" + separator;
        String exit = "Bye. Hope to see you again soon!\n" + separator;

        System.out.println(separator);
        System.out.println(greeting);

        String response;
        int count = 0;

        while (true) {
            response = scanner.nextLine();
            if (response.equals("bye")) {
                System.out.println(separator);
                System.out.println(exit);
                break;
            } else if (response.equals("list")) {
                System.out.println(separator);
                for (int i = 1; i <= count; i++) {
                    System.out.println(i + ". " + tasks[i - 1]);
                }
                System.out.println(separator);
            } else if (response.matches("^mark \\d+$")) {

                int number = Integer.parseInt(response.split(" ")[1]) - 1;
                tasks[number].markAsDone();
                System.out.println(separator);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[number]);
                System.out.println(separator);

            } else if (response.matches("^unmark \\d+$")) {

                int number = Integer.parseInt(response.split(" ")[1]) - 1;
                tasks[number].unmark();
                System.out.println(separator);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[number]);
                System.out.println(separator);

            } else if (response.startsWith("todo")) {
                String description = response.substring(5).trim();
                Task todo = new Todo(description);
                tasks[count] = todo;
                count++;
                System.out.println(separator);
                System.out.println("Got it. I've added this task:");
                System.out.println(todo);
                System.out.println("Now you have " + count + " tasks in the list");
                System.out.println(separator);

            } else if (response.startsWith("deadline")) {
                String[] deadlineInput = response.substring(9).split(" /by ", 2);
                if (deadlineInput.length == 2) {
                    String description = deadlineInput[0].trim();
                    String by = deadlineInput[1].trim();
                    Task deadline = new Deadline(description, by);
                    tasks[count] = deadline;
                    count++;
                    System.out.println(separator);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadline);
                    System.out.println("Now you have " + count + " tasks in the list");
                    System.out.println(separator);
                }


            } else if (response.startsWith("event")) {
                String[] eventInput = response.substring(6).split(" /from ", 2);
                if (eventInput.length == 2) {
                    String description = eventInput[0].trim();
                    String[] timings = eventInput[1].split(" /to ", 2);
                    if (timings.length == 2) {
                        String start = timings[0].trim();
                        String end = timings[1].trim();
                        Task event = new Event(description, start, end);
                        tasks[count] = event;
                        count++;
                        System.out.println(separator);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(event);
                        System.out.println("Now you have " + count + " tasks in the list");
                        System.out.println(separator);
                    }
                }

            } else {
                Task task = new Task(response);
                tasks[count] = task;
                count++;
                System.out.println(separator);
                System.out.println("added: " + response);
                System.out.println(separator);
            }
        }
    }
}
