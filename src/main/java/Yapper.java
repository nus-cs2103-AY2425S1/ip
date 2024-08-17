import java.util.Scanner;

public class Yapper {
    public static void main(String[] args) {

        String divider = "____________________________________________________________";
        String name = "Yapper";

        System.out.println(divider);
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(divider);

        Task[] taskList = new Task[100];
        int totalTasks = 0;

        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String[] split = input.split(" ");
            String command = split[0];

            if (command.equals("bye")) {
                System.out.println(divider);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(divider);
                break;
            } else if (command.equals("list")) {
                System.out.println(divider);
                for (int i = 1; i <= totalTasks; i++) {
                    System.out.println(i + ". " + taskList[i - 1]);
                }
                System.out.println(divider);
            } else if (command.equals("mark") | command.equals("unmark")) {
                int taskNumber = Integer.parseInt(split[1]);
                if (taskNumber < 1 || taskNumber > totalTasks) {
                    System.out.println(divider);
                    System.out.println("Oopsie!, Couldn't find that one! :)");
                    System.out.println(divider);
                    continue;
                }

                String message = "";
                if (command.equals("mark")) {
                    message = "Nice! I've marked this task as done:";
                    taskList[taskNumber - 1].mark();
                } else {
                    message = "OK, I've marked this task as not done yet:";
                    taskList[taskNumber - 1].unmark();
                }
                System.out.println(divider);
                System.out.println(message);
                System.out.println(" " + taskList[taskNumber - 1]);
                System.out.println(divider);
            } else {
                taskList[totalTasks++] = new Task(input);
                System.out.println(divider);
                System.out.println("added: " + input);
                System.out.println(divider);
            }
        }
    }
}
