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
