import java.util.Scanner;

public class XBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] list = new String[100];
        int taskCount = 0;

        System.out.println("Hello! I'm XBot\n" + "What can I do for you?");
        String input = scanner.nextLine();
        while(!input.equalsIgnoreCase("bye")) {
            if (input.equalsIgnoreCase("list")) {
                //display list
                for (int i = 0; i < taskCount; i++) {
                    int index = i + 1;
                    System.out.println(index + ". " + list[i]);
                }
            } else if (!input.isEmpty()) {
                //store input in list
                list[taskCount] = input;
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
