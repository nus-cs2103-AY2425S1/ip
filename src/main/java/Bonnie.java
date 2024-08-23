import java.util.Scanner;
import java.util.ArrayList;

import src.main.java.Deadline;
import src.main.java.Event;
import src.main.java.Task;
import src.main.java.Todo;

public class Bonnie {

    // Variables that are
    private static String username;
    private static ArrayList<Task> tasklist = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello I'm Bonnie, what is your name?");
        Scanner scanner_obj = new Scanner(System.in);
        String my_username = scanner_obj.nextLine();
        System.out.println(String.format("Hey %s! Welcome to the Bonnie chat bot! Please input a command to continue!\n", my_username));
        System.out.println(
                "1. bye : leaves the conversation with Bonnie\n" +
                "2. mark/unmark {task number}: marks or unmarks that task as done\n" +
                "3. todo {task name}: Bonnie adds a todo task into your task list\n" +
                "4. deadline {task name} /by {deadline}: Bonnie adds a task with a deadline to your task list.\n" +
                "5. event {task name} /from {start} /to {end}: Bonnie adds an event with a start/end time to your task list.\n" +
                "Bonnie wants to remind you that you should substitute items with curly braces with the actual information.\n" +
                "Also, do remember to use the forward slashes! \"/from\" is valid but \"from\" is NOT valid!\n" +
                "Example: \"event clean floor /from 18th September 5pm /to 18th September 6pm\" is a valid command\n"
                );
        username = my_username;

        while (true) {
            Scanner repeated_scanner = new Scanner(System.in);
            String input = repeated_scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye " + username + "\n");
                return;
            } else if (input.equals("list")) {
                String list = "Your current tasks are\n";
                for (int i = 1; i <= tasklist.size(); i++) {
                    list += String.format("%d. %s\n", i, tasklist.get(i - 1));
                }
                System.out.println(list);
            } else if (check_mark_command(input)) {
                String[] arr = input.split(" ", 2);
                Integer taskNum = Integer.valueOf(arr[1]);
                if (arr[0].equals("mark")) {
                    // Mark task "taskNum" as done
                    // Note that the actual task is off by -1 in the list!
                    tasklist.get(taskNum - 1).markAsDone();
                } else if (arr[0].equals("unmark")) {
                    // Mark task "taskNum" as not done
                    tasklist.get(taskNum - 1).unmarkAsDone();
                }
            } else {
                // Want to parse and add task into task list
                // parseAndAddTask(input) has the added feature of returning the description of the task
                // without any by/from/to information
                String taskName = parseAndAddTask(input);
                System.out.println(String.format("Hey %s, I have added \"%s\" into your task list!\n", username, taskName));
                System.out.println(String.format("You now have %d tasks to complete!", tasklist.size()));
            }
        }
    }

    public static boolean check_mark_command(String targetString) {
        String[] arr = targetString.split(" ");
        if ((arr[0].equals("mark") || arr[0].equals("unmark")) && arr.length == 2) {
            try {
                Integer taskNum = Integer.valueOf(arr[1]);
                if (taskNum < tasklist.size() && taskNum >= 0) {
                    return true;
                } else {
                    System.out.println("You might have tried to mark or unmark a task that does not exist.\n" +
                                       "If so, please delete this wrongly added task using the delete command.\n");
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static String parseAndAddTask(String input) {
        // Want to split the string according to spaces 1st
        String[] split_string = input.split(" ", 2);
        if (split_string[0].equals("todo")) {
            String taskName = split_string[1];
            tasklist.add(new Todo(taskName));
            return taskName;
        } else if (split_string[0].equals("deadline")) {
            String[] components = split_string[1].split("/by", 2);
            tasklist.add(new Deadline(components[0], components[1]));
            return components[0];
        } else if (split_string[0].equals("event")) {
            // Idea is that original string is in the form {event_name} /from {start} /to {end}
            // Hence first split will get event name and the {start} /to {end}
            // Second split will split the {start} /to {end} to get the actual start and end
            String[] component1 = split_string[1].split("/from", 2);
            String[] component2 = component1[1].split("/to", 2);
            tasklist.add(new Event(component1[0], component2[0], component2[1]));
            return component1[0];
        } else {
            tasklist.add(new Task(input));
            return input;
        }
    }

}
