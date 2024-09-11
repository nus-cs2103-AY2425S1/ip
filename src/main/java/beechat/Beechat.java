package beechat;

import java.lang.String;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Beechat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        System.out.println("Hello! I'm Beechat!\nWhat can I do for you?\n");
        ArrayList<Task> tasks = new ArrayList<>(FileHandler.loadTasks());
        String msg = sc.nextLine();
        Task task;
        int j;
        while (!msg.equals("bye")) {
            String start = (msg.split(" "))[0];
            if (start.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(String.format("%d. %s", i + 1, tasks.get(i)));
                }
                msg = sc.nextLine();
                continue;
            }
            if (start.equals("delete")) {
                try {
                    j = Integer.parseInt(msg.split(" ")[1]) - 1;
                    Task removedTask = tasks.remove(j);
                    System.out.println("OK, I've removed this task:\n" + removedTask);
                    FileHandler.saveTasks(tasks);
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
                    tasks.get(j).mark();
                    System.out.println("Nice! I've marked this task as done:\n" + tasks.get(j));
                    FileHandler.saveTasks(tasks);
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
                    tasks.get(j).unmark();
                    System.out.println("OK, I've marked this task as not done yet:\n" + tasks.get(j));
                    FileHandler.saveTasks(tasks);
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
                    tasks.add(task);
                    System.out.println("added: " + description);
                    FileHandler.saveTasks(tasks);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please enter the todo task in this form: \n" +
                            "todo [task name]");
                }
            }
            if (start.equals("deadline")) {
                try {
                    String description = ((msg.split("deadline "))[1]).split("/by ")[0];
                    LocalDateTime by = LocalDateTime.parse((msg.split("/by "))[1], inputFormatter);
                    task = new DeadlineTask(description, by);
                    tasks.add(task);
                    System.out.println("added: " + description);
                    FileHandler.saveTasks(tasks);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please enter the deadline task in this form: \n" +
                            "deadline [task name] /by [deadline]");
                } catch (DateTimeParseException e) {
                    System.out.println("Please enter the deadline in this form: \n" +
                            "MMM dd yyyy HHmm");
                }
            }
            if (start.equals("event")) {
                try {
                    String description = ((msg.split("event "))[1]).split("/from ")[0];
                    LocalDateTime from = LocalDateTime.parse((msg.split("/from "))[1].split(" /to ")[0], inputFormatter);
                    LocalDateTime to = LocalDateTime.parse((msg.split("/to "))[1], inputFormatter);
                    task = new EventTask(description, from, to);
                    tasks.add(task);
                    System.out.println("added: " + description);
                    FileHandler.saveTasks(tasks);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please enter the event task in this form: \n" +
                            "event [task name] /from [date and starttime] /by [date and endtime]");
                } catch (DateTimeParseException e) {
                    System.out.println("Please enter the from and to in this form: \n" +
                            "MMM dd yyyy HHmm");
                }
            }
            msg = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
