import java.util.Scanner;

public class Beechat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Beechat!\nWhat can I do for you?\n");
        String msg = sc.nextLine();
        while (!msg.equals("bye")) {
            System.out.println(msg);
            msg = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
