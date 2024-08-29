import java.lang.String;
import java.util.ArrayList;
import java.util.Scanner;

public class Beechat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Beechat!\nWhat can I do for you?\n");
        ArrayList<Task> list = new ArrayList<>();
        String msg = sc.nextLine();
        Task task;
        int j;
        while (!msg.equals("bye")) {
            if (msg.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(String.format("%d. %s", i + 1, list.get(i)));
                }
                msg = sc.nextLine();
                continue;
            }
            task = new Task(msg);
            if ((msg.split(" "))[0].equals("mark")) {
                j = Integer.valueOf((msg.split(" "))[1]) - 1;
                list.get(j).mark();
                System.out.println("Nice! I've marked this task as done:\n" + list.get(j));
                msg = sc.nextLine();
                continue;
            }
            if ((msg.split(" "))[0].equals("unmark")) {
                j = Integer.valueOf((msg.split(" "))[1]) - 1;
                list.get(j).unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" + list.get(j));
                msg = sc.nextLine();
                continue;
            }
            list.add(task);
            System.out.println("added: " + msg);
            msg = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
