import java.util.Scanner;

public class Duck {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] cmds = new String[100];
        int cmdNum = 0;

        System.out.println("Hello! I'm DUCK\n What can I do for you?");
        while (true) {
            String userCmd = scan.nextLine();
            if (userCmd.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (userCmd.toLowerCase().equals("list")) {
                for (int n = 1; n<=cmdNum; n++) {
                    System.out.println(n + ". " + cmds[n-1]);
                }
                cmds[cmdNum] = userCmd;
                cmdNum++;
                continue;
            }
            System.out.println("added: " + userCmd);
            cmds[cmdNum] = userCmd;
            cmdNum++;
        }
        scan.close();
    }
}
