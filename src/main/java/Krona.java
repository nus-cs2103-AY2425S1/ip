import java.util.Scanner;
public class Krona {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] tasks = new String[100];
        int allTasks = 0;

        System.out.println("Hello! I'm Krona\n" +
                "What can i Do for you?");

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < allTasks; i++ ) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else {
                tasks[allTasks] = input;
                allTasks++;
                System.out.println("added: " + input);
            }
        }
    }
}
