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
            String input = scanner.nextLine();
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
                int index = (input.charAt(7) - '0' - 1);
                ls.get(index).markAsNotDone();
                String output = "Unmarked that as done for you. \n" +
                        ls.get(index).toString();
                printText(output);
                continue;
            }

            // mark task as done
            if (input.contains("mark")) {
                if (ls.isEmpty()) {
                    printText("No tasks in list!");
                    continue;
                }
                int index = (input.charAt(5) - '0' - 1);
                ls.get(index).markAsDone();
                String output = "Marked that as done for you. \n" +
                        ls.get(index).toString();
                printText(output);
                continue;
            }

            // create todo task
            if (input.contains("todo")) {
                String desc = input.substring(5);
                Todo task = new Todo(desc);
                ls.add(task);
                String output = "Added Todo task:\n" + task.toString();
                output += "\nYou currently have " + ls.size() + " task/s in your list";
                printText(output);
                continue;
            }

            // create deadline task
            if (input.contains("deadline")) {
                String str = input.substring(9);
                String desc = str.split(" /by ")[0];
                String deadline = str.split(" /by ")[1];
                Deadline task = new Deadline(desc, deadline);
                ls.add(task);
                String output = "Added Deadline task:\n" + task.toString();
                output += "\nYou currently have " + ls.size() + " task/s in your list";
                printText(output);
                continue;
            }

            // create event task
            if (input.contains("event")) {
                String str = input.substring(6);
                String desc = str.split(" /from ")[0];
                String duration = str.split(" /from ")[1];
                String from = duration.split(" /to ")[0];
                String to = duration.split(" /to ")[1];

                Event task = new Event(desc, from, to);
                ls.add(task);
                String output = "Added Event task:\n" + task.toString();
                output += "\nYou currently have " + ls.size() + " task/s in your list";
                printText(output);
                continue;
            }

        }

        printText("Bye. See you later!");
    }


}
