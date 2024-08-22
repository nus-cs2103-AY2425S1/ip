import java.util.Scanner;

public class XBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[100];
        int taskCount = 0;

        System.out.println("Hello! I'm XBot\n" + "What can I do for you?");
        String input = scanner.nextLine();
        while(!input.equalsIgnoreCase("bye")) {
            String[] words = input.split("\\s+", 2);
            String command = words[0];
            String rest = words.length > 1 ? words[1] : "";
            if (input.equalsIgnoreCase("list")) {
                //display list
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < taskCount; i++) {
                    int index = i + 1;
                    System.out.println(index + ". " + list[i].toString());
                }
            } else if (!input.isEmpty()) {
                //store input in list
                list[taskCount] = new Task(input);
                System.out.println("added: " + input);
                taskCount++;
            } else {
                //do nothing when input is empty
            }
            input = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}
