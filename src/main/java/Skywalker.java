

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Skywalker {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Skywalker");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);



        while (true) {
            String printable = scanner.nextLine();
            System.out.println("____________________________________________________________");
            try {

                if (Objects.equals(printable, "bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");

                    break;
                } else if (Objects.equals(printable, "list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + ". " + tasks.get(i).toString());
                    }
                    System.out.println("____________________________________________________________");
                } else if (printable.startsWith("mark ")) {
                    int index = Integer.parseInt(printable.split(" ")[1]) - 1;
                    if(index<0 || index >= tasks.size()) {
                        throw new IndexOutOfBoundsException("Task number is out of range!");
                    }
                    tasks.get(index).markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(index).toString());
                    System.out.println("____________________________________________________________");

                } else if (printable.startsWith("unmark ")) {
                    int index = Integer.parseInt(printable.split(" ")[1]) - 1;
                    if(index<0 || index >= tasks.size()) {
                        throw new IndexOutOfBoundsException("Task number is out of range!");
                    }
                    tasks.get(index).unmarkDone();
                    System.out.println(tasks.get(index).toString());
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("____________________________________________________________");
                } else if (printable.startsWith("todo ")) {
                    //exceptions handling to check if the command is correct
                    if (printable.substring(5).isEmpty()) {
                        throw new EmptyDescriptionException("the todo task description cannot be empty!!!!");
                    }
                    //System.out.println("____________________________________________________________");
                    Task task = new Todo(printable.substring(5));
                    tasks.add(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else if (printable.startsWith("deadline ")) {
                    // System.out.println("____________________________________________________________");

                    String[] information = printable.substring(9).split("/by ");
                    String description = information[0];
                    String by = information[1];
                    //empty exception catching
                    if (information.length<2 || description.isEmpty() || by.isEmpty()) {
                        throw new EmptyDescriptionException("the deadline task description/date cannot be empty!!!!");
                    }
                    System.out.println("Got it. I've added this task:");
                    Deadline task = new Deadline(description, by);
                    tasks.add(task);
                    System.out.println(task.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (printable.startsWith("event ")) {
                    //  System.out.println("____________________________________________________________");
                    String[] information = printable.substring(6).split("/from |/to ");
                    String description = information[0];
                    String from = information[1];
                    String to = information[2];
                    if (information.length<3 || from.isEmpty() || to.isEmpty()) {
                        throw new EmptyDescriptionException("the deadline task description/ from date/ to date cannot be empty!!!!");
                    }
                    System.out.println("Got it. I've added this task:");
                    Event task = new Event(description, from, to);
                    tasks.add(task);
                    System.out.println(task.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (printable.startsWith("delete ")) {

                    int index = Integer.parseInt(printable.substring(7))-1;
                    if(index<0 || index >= tasks.size()) {
                        throw new IndexOutOfBoundsException("Task number is out of range!");
                    }
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(tasks.get(index).toString());
                    tasks.remove(index);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }

                else {
                    throw new UnknownCommandException("the command is not correct :( check again!");
                }
            } catch (EmptyDescriptionException | IndexOutOfBoundsException| UnknownCommandException e) {
                System.out.println("OOPS!" + e.getMessage());
            } catch (Exception e) { // safety net catch error
                System.out.println("OOPS! Something is wrong: " + e.getMessage());
            }
        }
    }
}

