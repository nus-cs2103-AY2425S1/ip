import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.java.Deadline;
import main.java.Event;
import main.java.Task;
import main.java.Todo;

import javax.sound.sampled.Line;

public class Karen {
    private static List<Task> tasks = new ArrayList<>();
    public static void main(String[] args) {
        final String LINE = "_______________________\n";
        String output = LINE +
                        "Hi! I'm Karen\n" +
                        "What can I do for you?\n" +
                        LINE;

        System.out.print(output);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String[] command = input.split(" ", 2); //[keyword, parameters;

            output = "";

            if (command[0].equals("bye")) {
                //End program
                System.out.print(
                        LINE +
                        "Bye! Hope to see you again!\n" +
                        LINE
                );
                break;

            } else if (command[0].equals("list")) {
                //List tasks
                output += LINE;
                if (tasks.isEmpty()) {
                    output += "No tasks yet!\n";
                } else {
                    for (int i = 0; i < tasks.size(); i++) {
                        Task t = tasks.get(i);
                        output += String.format("%d. %s\n", i+1, t);
                    }
                }
                output += LINE;

            } else if (command[0].equals("mark")) {
                //Mark as done
                try {
                    int n = Integer.parseInt(command[1]) - 1;
                    Task marked_task = tasks.get(n);
                    marked_task.mark();
                    output += LINE +
                            "Nice! I've marked this task as done:\n\t" +
                            marked_task + "\n" +
                            LINE;
                } catch (NumberFormatException NFE) {
                    output += LINE
                            + "Error! You must input a number after the 'mark' command\n"
                            + LINE;
                } catch (IndexOutOfBoundsException IOB) {
                    output += LINE
                            + "Invalid index! Use 'list' to see the tasks and their respective indices!\n"
                            + LINE;
                }

            } else if (command[0].equals("unmark")) {
                //Unmark as done
                try {
                    int n = Integer.parseInt(command[1]) - 1;
                    Task unmarked_task = tasks.get(n);
                    unmarked_task.unmark();
                    output += LINE +
                            "Ok! This task is now marked undone:\n\t" +
                            unmarked_task + "\n" +
                            LINE;
                } catch (NumberFormatException NFE) {
                    output += LINE
                            + "Error! You must input a number after the 'unmark' command\n"
                            + LINE;
                } catch (IndexOutOfBoundsException IOB) {
                    output += LINE
                            + "Invalid index! Use 'list' to see the tasks and their respective indices!\n"
                            + LINE;
                }

            } else {
                //add new Task
                Task task = null;
                if (command[0].equals("todo")) {
                    try {
                        task = new Todo(command[1]);
                    } catch (IndexOutOfBoundsException e) {
                        output += LINE
                                + "Please enter a name for your todo!\n"
                                + LINE;
                    }
                } else if(command[0].equals("deadline")) {
                    try {
                        String[] params = command[1].split("/by ", 2);
                        task = new Deadline(params[0], params[1]);
                    } catch (Exception e) {
                        output += LINE
                                + "Invalid input! Deadlines must follow this syntax: deadline <name> /by <due date>\n"
                                + LINE;
                    }
                } else if(command[0].equals("event")) {
                    try {
                        String[] params = command[1].split("/from ", 2);
                        String name = params[0];
                        String[] fromTo = params[1].split("/to ",2);
                        task = new Event(name, fromTo[0], fromTo[1]);
                    } catch (Exception e) {
                        output += LINE
                                + "Error! Events must follow this syntax: " +
                                "event <name> /from <start time> /to <end time>\n"
                                + LINE;
                    }
                } else {
                    output += LINE
                            + "Sorry! I don't understand :(\n"
                            + LINE;
                }

                if (task != null) {
                    tasks.add(task);
                    output += LINE +
                            "Got it! Added this task:\n\t" +
                            task + "\n" +
                            String.format("Now you have %d tasks in the list.\n", tasks.size()) +
                            LINE;
                }
            }

            System.out.print(output);
        }

    }
}
