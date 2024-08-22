import java.util.Scanner;

public class Duck {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Task[] cmds = new Task[100];
        int cmdNum = 0;

        System.out.println("Hello! I'm DUCK\n What can I do for you?");
        while (true) {
            String userCmd = scan.nextLine();
            if (userCmd.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userCmd.toLowerCase().equals("list")) {
                if (cmdNum==0) {
                    continue;
                }
                for (int n = 1; n<=cmdNum; n++) {
                    System.out.println(n + ". " + cmds[n - 1]);
                }
            } else if ((userCmd.length() > 3) && (userCmd.substring(0,4).toLowerCase().equals("mark"))) {
                int num = Integer.valueOf(userCmd.substring(5));
                if (num < cmdNum + 1) {
                    cmds[num-1].mark();
                }
                System.out.println("Nice! I've marked this task as done:\n "+ cmds[num-1]);
            } else if ((userCmd.length() > 5) && (userCmd.substring(0,6).toLowerCase().equals("unmark"))) {
                int num = Integer.valueOf(userCmd.substring(7));
                if (num < cmdNum + 1) {
                    cmds[num-1].unmark();
                }
                System.out.println("OK, I've marked this task as not done yet:\n "+ cmds[num-1]);
            } else {
                System.out.println("added: " + userCmd);
                cmds[cmdNum] = new Task(userCmd);
                cmdNum++;
            }
        }
        scan.close();
    }
}


