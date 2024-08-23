import java.util.Scanner;

public class Noisy {


    public static void main(String[] args) {
        String[] taskList = new String[100];
        int currentPointer = 0;
        String echoMessage = "____________________________________________________________\n";
        String welcomeMessage = "____________________________________________________________\n"
                + " Hello! I'm Noisy\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n"
                + " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________";
        System.out.println(welcomeMessage);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String goodbyeMessage = "____________________________________________________________\n"
                    + " Bye human :(\n"
                    + "____________________________________________________________";
            if (input.equals("bye")) {
                System.out.println(goodbyeMessage);
                scanner.close();
                break;
            }
            if (input.equals("list")) {
                System.out.println(echoMessage + "____________________________________________________________");
                continue;
            }
            taskList[currentPointer] = input;
            currentPointer++;
            echoMessage += currentPointer + ". " + input + "\n";
        }
    }
}
