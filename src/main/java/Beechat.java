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
            if ((msg.split(" "))[0].equals("todo")) {
                String description = (msg.split("todo "))[1];
                task = new TodoTask(description);
                list.add(task);
                System.out.println("added: " + description);
            }
            if ((msg.split(" "))[0].equals("deadline")) {
                String description = ((msg.split("deadline "))[1]).split("/by ")[0];
                String by = (msg.split("/by "))[1];
                task = new DeadlineTask(description, by);
                list.add(task);
                System.out.println("added: " + description);
            }
            if ((msg.split(" "))[0].equals("event")) {
                String description = ((msg.split("event "))[1]).split("/from ")[0];
                String from = (msg.split("/from "))[1].split("/to ")[0];
                String to = (msg.split("/to "))[1];
                task = new EventTask(description, from, to);
                list.add(task);
                System.out.println("added: " + description);
            }
            msg = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
