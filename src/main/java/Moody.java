import java.util.Scanner;

public class Moody {
    public static void main(String[] args) {
        String spacer = "____________________________________________________________\n";
        System.out.println(spacer
                + "Hello! I'm Moody!\nWhat can I do for you?\n"
                + spacer);

        Scanner scanner = new Scanner(System.in);
        String userInput;
        String[] userList = new String[100];
        int taskCount = 0;

        while (true) {
            userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(spacer
                        + "Bye. Hope to see you again soon!\n"
                        + spacer);
                break;
            } else if (userInput.equals("list")){
                System.out.print(spacer);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + userList[i]);
                }
                System.out.println(spacer);
            } else {
                userList[taskCount] = userInput;
                taskCount++;
                System.out.println(spacer
                        + "added: "
                        + userInput
                        + "\n"
                        + spacer);
            }
        }
    }
}
