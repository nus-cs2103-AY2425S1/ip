import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;

import java.sql.SQLOutput;
import java.util.Objects;
import java.util.Scanner;
public class Skywalker {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Skywalker");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        Task[] tasks = new Task[100];
        int count = 0;


        while (true) {
            Scanner scanner = new Scanner(System.in);
            String printable = scanner.nextLine();
            System.out.println("____________________________________________________________");

            if (Objects.equals(printable, "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");

                break;
            } else if (Objects.equals(printable, "list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.println(i + 1 + ". " + tasks[i].toString());
                }
                System.out.println("____________________________________________________________");
            } else if (printable.startsWith("mark ")) {
                System.out.println("Nice! I've marked this task as done:");
                int index = Integer.parseInt(printable.split(" ")[1]) - 1;
                tasks[index].markDone();
                System.out.println(tasks[index].toString());

            } else if (printable.startsWith("unmark ")) {
                System.out.println("OK, I've marked this task as not done yet:");
                int index = Integer.parseInt(printable.split(" ")[1]) - 1;
                tasks[index].unmarkDone();
                System.out.println(tasks[index].toString());
            } else if (printable.startsWith("todo ")){
                //System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                Task task = new Todo(printable.substring(5));
                tasks[count++] = task;
                System.out.println(task.toString());
                System.out.println("Now you have "+(count)+" tasks in the list.");
            } else if (printable.startsWith("deadline ")) {
               // System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                String[] information = printable.substring(9).split("/by ");
                String description = information[0];
                String by = information[1];
                Deadline task = new Deadline(description, by);
                tasks[count++] = task;
                System.out.println(task.toString());
                System.out.println("Now you have " + (count) + " tasks in the list.");
            } else if (printable.startsWith("event ")) {
              //  System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                String[] information = printable.substring(6).split("/from |/to ");
                String description = information[0];
                String from = information[1];
                String to = information[2];
                Event task = new Event(description, from, to);
                tasks[count++] = task;
                System.out.println(task.toString());
                System.out.println("Now you have " + (count) + " tasks in the list.");
            }

                //Task task = new Task(printable);
                //tasks[count++] = task;
                System.out.println("____________________________________________________________");

            }
        }
    }

