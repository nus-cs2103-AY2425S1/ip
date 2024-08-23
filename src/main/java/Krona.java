import java.util.Scanner;
public class Krona {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int allTasks = 0;

        System.out.println("Hello! I'm Krona\n" +
                "What can i Do for you?");

        while (true) {
            String input = sc.nextLine();
            String[] words = input.split(" ", 2);

            if (words[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (words[0].equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < allTasks; i++ ) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else if (words[0].equals("mark")) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                tasks[taskIndex].markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[taskIndex]);
            } else if (words[0].equals("unmark")) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                tasks[taskIndex].markNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks[taskIndex]);
            } else {
                tasks[allTasks] = new Task(input);
                allTasks++;
                System.out.println("added: " + input);
            }
        }
    }
}
