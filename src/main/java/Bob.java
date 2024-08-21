import java.util.Scanner;
public class Bob {
    public static void main(String[] args) {
        String logo = "Bob";
        System.out.println("Hello! I'm " + logo);
        System.out.println("What can I do for you?");
        Task[] list = new Task[100];
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.equals("bye")) {
            if(input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < count; i++) {
                    System.out.println((i + 1) + "." + list[i]);
                }
                input = scanner.nextLine();
                continue;
            }

            if(input.contains("unmark")) {
                String[] parts = input.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                list[index].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:\n" + list[index]);
                input = scanner.nextLine();
                continue;
            }
            if(input.contains("mark") && !input.contains("unmark")) {
                String[] parts = input.split(" ");
                int index = Integer.parseInt(parts[1]) - 1;
                list[index].markAsDone();
                System.out.println("Nice! I've marked this task as done:\n" + list[index]);
                input = scanner.nextLine();
                continue;
            }
            Task t = null;

            if(input.startsWith("todo")) {
                String[] parts = input.split(" ");
                StringBuilder description = new StringBuilder();
                for (int i = 1; i < parts.length; i++) {
                    description.append(parts[i]).append(" ");
                }
                t = new Todo(description.toString());
            } else if (input.startsWith("deadline")) {
                String[] parts = input.split(" ");
                t = new Deadline(parts[1], parts[2]);
            } else if (input.startsWith("event")) {
                String[] parts = input.split(" ");
                t = new Event(parts[1], parts[2], parts[3]);
            }
            System.out.println("Got it. I've added this task:\n" + t);
            System.out.println("Now you have " + (count + 1) + (count == 0 ? " task in the list." : " tasks in the list."));
            list[count] = t;
            count++;
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
