import java.util.Scanner;

public class Opus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" Hello! I'm Opus");
        System.out.println(" What can I do for you?");

        Task[] tasks = new Task[101];
        int taskCount = 0;

        while(true) {
            String s = scanner.nextLine();
            String[] words = s.split(" ");
            if (s.equals("bye")) {
                break;
            } else if (s.equals("list")) {
                for (int i = 1; i <= taskCount; i++) {
                    System.out.println(i + ". " + tasks[i]);
                }
            } 
            else if (words[0].equals("mark")) {
                int i = Integer.parseInt(words[1]);
                tasks[i].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks[i]);
            } 
            else {
                tasks[++taskCount] = new Task(s);
                System.out.println("added: " + s);
            }
        }
        System.out.println(" Bye. Hope to see you again soon!");
    }
}
