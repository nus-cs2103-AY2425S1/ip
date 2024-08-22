import java.util.Scanner;

public class Duck {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);*/
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello! I'm DUCK\n What can I do for you?");
        while (true) {
            String userCmd = scan.nextLine();
            if (userCmd.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(userCmd);
        }
        scan.close();
    }
}
