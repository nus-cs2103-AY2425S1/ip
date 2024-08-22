import java.util.Scanner;
import  java.util.ArrayList;

public class Rasputin {

    private static void printText(String text) {
        System.out.println(lineBreak);
        System.out.println(text);
        System.out.println(lineBreak + "\n");
    }

    private static String lineBreak = "____________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String name = "Rasputin";

        // initialize task list
        ArrayList<Task> ls = new ArrayList<>();

        // scanner to read user input
        Scanner scanner = new Scanner(System.in);

        // greeting
        printText("Hello, I'm " + name + "!\nWhat can I do for you?");

        while (true) {
            String input = scanner.nextLine().trim();
            // if user types "bye", break out of loop and bid farewell
            if (input.equals("bye")) {
                break;
            }

            // display current list of tasks
            if (input.equals("list")) {
                if (ls.isEmpty()) {
                    printText("No tasks in list!");
                    continue;
                }
                int index = 1;
                System.out.println(lineBreak);
                for (Task item: ls) {
                    System.out.println(index + "." + item.toString());
                    index++;
                }
                System.out.println(lineBreak + "\n");
                continue;
            }

            // unmark task
            if (input.contains("unmark")) {
                if (ls.isEmpty()) {
                    printText("No tasks in list!");
                    continue;
                }
                try {
                    int index = (input.charAt(7) - '0' - 1);
                    ls.get(index).markAsNotDone();
                    String output = "Task has been unmarked.\n" +
                            ls.get(index).toString();
                    printText(output);
                }
                catch (StringIndexOutOfBoundsException e) {
                    printText("ERROR! Task to be unmarked not specified.");
                }
                catch (IndexOutOfBoundsException e) {
                    printText("ERROR! Task not found.");
                }
                continue;
            }

            // mark task as done
            if (input.contains("mark")) {
                if (ls.isEmpty()) {
                    printText("No tasks in list!");
                    continue;
                }
                try {
                    int index = (input.charAt(5) - '0' - 1);
                    ls.get(index).markAsDone();
                    String output = "Marked that as done for you.\n" +
                            ls.get(index).toString();
                    printText(output);
                }
                catch (StringIndexOutOfBoundsException e) {
                    printText("ERROR! Task to be marked not specified.");
                }
                catch (IndexOutOfBoundsException e) {
                    printText("ERROR! Task not found.");
                }
                finally {
                    continue;
                }
            }

            // create todo task
            if (input.contains("todo")) {
                try {
                    String desc = input.substring(5);
                    Todo task = new Todo(desc);
                    ls.add(task);
                    String output = "Added Todo task:\n" + task.toString();
                    output += "\nYou currently have " + ls.size() + " task/s in your list.";
                    printText(output);
                }
                catch (StringIndexOutOfBoundsException e) {
                    printText("ERROR! The description of a todo cannot be empty.");
                }
                finally {
                    continue;
                }

            }

            // create deadline task
            if (input.contains("deadline")) {
                try {
                    String str = input.trim().substring(9);
                    String desc = str.split(" /by ")[0];
                    String deadline = str.split(" /by ")[1];
                    Deadline task = new Deadline(desc, deadline);
                    ls.add(task);
                    String output = "Added Deadline task:\n" + task.toString();
                    output += "\nYou currently have " + ls.size() + " task/s in your list.";
                    printText(output);
                    continue;
                }
                catch (StringIndexOutOfBoundsException e) {
                    printText("ERROR! The description of a deadline cannot be empty.");
                }

                catch (ArrayIndexOutOfBoundsException e) {
                    printText("ERROR! Deadline tasks require a deadline to be completed by.");
                }
                finally {
                    continue;
                }
            }

            // create event task
            if (input.contains("event")) {
                try {
                    String str = input.substring(6);
                    String desc = str.split(" /from ")[0];
                    String duration = str.split(" /from ")[1];
                    String from = duration.split(" /to ")[0];
                    String to = duration.split(" /to ")[1];

                    Event task = new Event(desc, from, to);
                    ls.add(task);
                    String output = "Added Event task:\n" + task.toString();
                    output += "\nYou currently have " + ls.size() + " task/s in your list.";
                    printText(output);
                }
                catch (StringIndexOutOfBoundsException e) {
                    printText("ERROR! The description of an event cannot be empty.");
                }

                catch (ArrayIndexOutOfBoundsException e) {
                    printText("ERROR! Event tasks require a duration for the event.");
                }
                finally {
                    continue;
                }
            }

            if (input.contains("delete")) {
                try {
                    int index = (input.charAt(7) - '0' - 1);
                    String output = "Done, removed that task for you.\n" +
                            ls.get(index).toString();
                    ls.remove(index);
                    printText(output);
                }
                catch (StringIndexOutOfBoundsException e) {
                    printText("ERROR! Task to be deleted not specified.");
                }
                catch (IndexOutOfBoundsException e) {
                    printText("ERROR! Task not found.");
                }
                finally {
                    continue;
                }
            }



            printText("I'm sorry, I don't understand that command.");

        }

        printText("Bye. See you later!");
    }


}
