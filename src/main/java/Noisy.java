import java.util.Scanner;

public class Noisy {


    public static void main(String[] args) {
        Task[] taskList = new Task[100];
        int currentPointer = 0;
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
            Task task = new Task(input);
            String goodbyeMessage = "____________________________________________________________\n"
                    + " Bye human :(\n"
                    + "____________________________________________________________";
            if (input.equals("bye")) {
                System.out.println(goodbyeMessage);
                scanner.close();
                break;
            }
            if (input.equals("list")) {
                String echoMessage = "____________________________________________________________\n";
                for (int i = 0; i < currentPointer; i++) {
                    int taskIndex = i + 1;
                    echoMessage += taskIndex + ". " + taskList[i] + "\n";
                }
                System.out.println(echoMessage + "____________________________________________________________");
                continue;
            }
            if (input.startsWith("mark ")) {
                String[] string = input.split(" ");
                Integer index = Integer.parseInt(string[1]);
                taskList[index - 1].markDone();
                String markString = "____________________________________________________________\n" +
                                    "Nice! I've marked this task as done:\n" +
                                    taskList[index - 1] + "\n" +
                                    "____________________________________________________________\n";
                System.out.println(markString);
                continue;
            }
            taskList[currentPointer] = task;
            currentPointer++;
        }
    }
}
