package beechat;

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
            String start = (msg.split(" "))[0];
            if (start.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(String.format("%d. %s", i + 1, list.get(i)));
                }
                msg = sc.nextLine();
                continue;
            }
            if (start.equals("delete")) {
                try {
                    j = Integer.parseInt(msg.split(" ")[1]) - 1;
                    list.remove(j);
                    System.out.println("OK, I've removed this task:\n" + list.get(j));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please delete in this form: \n" +
                            "delete [integer]");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please enter a valid index" );
                }
                msg = sc.nextLine();
                continue;
            }
            if (start.equals("mark")) {
                try {
                    j = Integer.parseInt(msg.split(" ")[1]) - 1;
                    list.get(j).mark();
                    System.out.println("Nice! I've marked this task as done:\n" + list.get(j));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please mark in this form: \n" +
                            "mark [index]");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please enter a valid index" );
                }
                msg = sc.nextLine();
                continue;
            }
            if (start.equals("unmark")) {
                try {
                    j = Integer.parseInt(msg.split(" ")[1]) - 1;
                    list.get(j).unmark();
                    System.out.println("OK, I've marked this task as not done yet:\n" + list.get(j));
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please unmark in this form: \n" +
                            "unmark [integer]");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please enter a valid index" );
                }
                msg = sc.nextLine();
                continue;
            }
            if (start.equals("todo")) {
                try {
                    String description = (msg.split("todo "))[1];
                    task = new TodoTask(description);
                    list.add(task);
                    System.out.println("added: " + description);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please enter the todo task in this form: \n" +
                            "todo [task name]");
                }
            }
            if (start.equals("deadline")) {
                try {
                    String description = ((msg.split("deadline "))[1]).split("/by ")[0];
                    String by = (msg.split("/by "))[1];
                    task = new DeadlineTask(description, by);
                    list.add(task);
                    System.out.println("added: " + description);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please enter the deadline task in this form: \n" +
                            "deadline [task name] /by [deadline]");
                }
            }
            if (start.equals("event")) {
                try {
                    String description = ((msg.split("event "))[1]).split("/from ")[0];
                    String from = (msg.split("/from "))[1].split("/to ")[0];
                    String to = (msg.split("/to "))[1];
                    task = new EventTask(description, from, to);
                    list.add(task);
                    System.out.println("added: " + description);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please enter the event task in this form: \n" +
                            "event [task name] /from [date and starttime] /by [endtime]");
                }
            }
            msg = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
