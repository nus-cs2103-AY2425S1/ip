import java.lang.String;
import java.util.ArrayList;
import java.util.Scanner;

public class Beechat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Beechat!\nWhat can I do for you?\n");
        ArrayList<String> list = new ArrayList<>();
        String msg = sc.nextLine();
        while (!msg.equals("bye")) {
            if (msg.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(String.format("%d. %s", i + 1, list.get(i)));
                }
                msg = sc.nextLine();
                continue;
            }
            list.add(msg);
            System.out.println("added: " + msg);
            msg = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
