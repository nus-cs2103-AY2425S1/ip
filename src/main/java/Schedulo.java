import java.util.Scanner;

public class Schedulo {
    public static void main(String[] args) {
        String horizontalLine = "---------------";
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        System.out.println(horizontalLine);
        System.out.println("Hello I am Schedulo!");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);
        while (true) {
            String command = sc.nextLine();
            System.out.println(horizontalLine);
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(horizontalLine);
                break;
            }
            if (command.equals("list")) {
                System.out.println(taskList);
            } else {
                String[] splitwords = command.split(" ");
                String newcommand = splitwords[0];
                if (newcommand.equals("mark")) {
                    int index = Integer.valueOf(splitwords[1]);
                    System.out.println(taskList.markTask(index));
                } else if (newcommand.equals("unmark")) {
                    int index = Integer.valueOf(splitwords[1]);
                    System.out.println(taskList.unmarkTask(index));
                } else {
                    System.out.println(taskList.add(command));
                }
            }
            System.out.println(horizontalLine);
        }

        sc.close();
    }
}
