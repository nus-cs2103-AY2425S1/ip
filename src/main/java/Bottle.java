import java.util.Scanner;
public class Bottle {
    final static String lineBreak = "\n____________________________________________________________\n";

    public static void printwithBreak(String str) {
        System.out.println(lineBreak + str + lineBreak);
    }
    public static void main(String[] args) {
        String[] taskList = new String[100];
        int taskCount = 0;
        String welcomeMsg =
                " Hello! I'm Bottle\n" +
                " What can I do for you?";

        String byeMsg =" Bye. Hope to see you again soon!";
        printwithBreak(welcomeMsg);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                printwithBreak(byeMsg);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(lineBreak);
                for (int i = 0; i < taskCount; i++) {
                    int idx = i + 1;
                    System.out.println(idx + ". " + taskList[i]);
                }
                System.out.println(lineBreak);
            } else {
                taskList[taskCount] = input;
                taskCount++;
                printwithBreak("added " + input);
            }
        }
    }
}
