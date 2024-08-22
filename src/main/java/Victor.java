import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Victor {

    public static void main(String[] args) {
        String logo = ",---.  ,---..-./`)     _______ ,---------.    ,-----.    .-------.\n"
+ "|   /  |   |\\ .-.')   /   __  \\\\          \\ .'  .-,  '.  |  _ _   \\    \n"
+ "|  |   |  .'/ `-' \\  | ,_/  \\__)`--.  ,---'/ ,-.|  \\ _ \\ | ( ' )  |\n"
+ "|  | _ |  |  `-'`\"`,-./  )         |   \\  ;  \\  '_ /  | :|(_ o _) /\n"
+ "|  _( )_  |  .---. \\  '_ '`)       :_ _:  |  _`,/ \\ _/  || (_,_).' __\n"
+ "\\ (_ o._) /  |   |  > (_)  )  __   (_I_)  : (  '\\_/ \\   ;|  |\\ \\  |  |\n"
+ " \\ (_,_) /   |   | (  .  .-'_/  ) (_(=)_)  \\ `\"/  \\  ) / |  | \\ `'   /\n"
+ "  \\     /    |   |  `-'`-'     /   (_I_)    '. \\_/``\".'  |  |  \\    /\n"
+ "   `---`     '---'    `._____.'    '---'      '-----'    ''-'   `'-'\n";
        Scanner inp = new Scanner(System.in);
        ArrayList<Task> inputs = new ArrayList<Task>();
        String[] exclamations = new String[]{"Wonderful", "Cool", "Magnificent", "Splendid", "Great", "Fantastic", "Marvellous"};
        Random Random = new Random();

        System.out.println(logo);
        System.out.println("Hello! My name is Victor!");
        System.out.println("What can I do for you?");
        System.out.println("============================================================");
        String userInput = inp.nextLine();

        // Continues asking for input until user input is "bye"
        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.println("============================================================");

            // handles case of empty user input
            if (userInput.trim().isEmpty()) {
                // User input is empty - ask again for input
                System.out.println("  ~  What can I do for you?");
            } else if (userInput.toLowerCase().startsWith("list")) {
                if (inputs.isEmpty()) {
                    // no tasks were added to the list yet
                    System.out.println("  ~  No tasks in the list, add some To Dos, Events, and Deadlines first!");
                } else {
                    System.out.println("  ~  Sure! Here are all of your tasks:");
                    for (int i = 0; i < inputs.size(); i++) {
                        System.out.println("  ~  " + (i + 1) + ". " + inputs.get(i));
                    }
                }
            } else if (userInput.trim().toLowerCase().startsWith("delete")) {
                try {
                    String[] parsed = userInput.trim().split(" ");
                    int num = Integer.parseInt(parsed[parsed.length - 1]) - 1;

                    // retrieve and remove deleted task to reference later
                    Task removed = inputs.get(num);
                    inputs.remove(num);
                    System.out.println("  ~  Deleting the task below now!");
                    System.out.println("  ~  " + removed);
                } catch (NumberFormatException e) {
                    System.out.println("  ~  Sorry, I don't think you entered a number for which task to mark as done!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("  ~  I don't think there's a task with that number!");
                }
            } else if (userInput.trim().toLowerCase().startsWith("mark")) {
                try {
                    String[] parsed = userInput.trim().split(" ");
                    int num = Integer.parseInt(parsed[parsed.length - 1]) - 1;
                    inputs.get(num).markDone();
                    System.out.println("  ~  You finished a task! Well done! I marked this task as done:");
                    System.out.println("  ~  " + inputs.get(num));
                } catch (NumberFormatException e) {
                    System.out.println("  ~  Sorry, I don't think you entered a number for which task to mark as done!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("  ~  I don't think there's a task with that number!");
                }
            } else if (userInput.trim().toLowerCase().startsWith("unmark")) {
                try {
                    String[] parsed = userInput.trim().split(" ");
                    int num = Integer.parseInt(parsed[parsed.length - 1]) - 1;
                    inputs.get(num).markUndone();
                    System.out.println("  ~  Oops, I guess you didn't finish the task! I marked this task as undone:");
                    System.out.println("  ~  " + inputs.get(num));
                } catch (NumberFormatException e) {
                    System.out.println("  ~  Sorry, I don't think you entered a number for which task to mark as not done!");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("  ~  I don't think there's a task with that number!");
                }
            } else if (userInput.trim().toLowerCase().startsWith("todo")) {
                String[] parsed = userInput.trim().split(" ");
                String taskName = "";
                for (int i = 1; i < parsed.length; i++) {
                    taskName += " " + parsed[i];
                }
                // trim so that blank space cannot be counted as name for task
                taskName = taskName.trim();
                if (taskName.isEmpty()) {
                    System.out.println("  ~  Please give a name for the To Do. The format should be \"todo (description)\"");
                } else {
                    ToDo task = new ToDo(taskName);
                    inputs.add(task);
                    System.out.println("  ~  " + exclamations[Random.nextInt(exclamations.length)] + "! I added this To Do:");
                    System.out.println("  ~    " + task);
                    System.out.println("  ~  You now have " + inputs.size() + ((inputs.size() == 1) ? " task" : " tasks") + " in your list.");
                }
            } else if (userInput.trim().toLowerCase().startsWith("deadline")) {
                String[] parsed = userInput.trim().split(" ");
                String taskName = "";
                String deadline = "";

                boolean isDeadline = false;

                for (int i = 1; i < parsed.length; i++) {
                    if (parsed[i].startsWith("/")) {
                        isDeadline = true;
                        continue;
                    }
                    if (!isDeadline) {
                        taskName += " " + parsed[i];
                    } else {
                        deadline += " " + parsed[i];
                    }
                }
                // trim so that blank space cannot be counted as name for task or deadlines
                taskName = taskName.trim();
                deadline = deadline.trim();
                if (taskName.isEmpty()) {
                    System.out.println("  ~  Please give a name for the Deadline. The format should be \"deadline (description) /by (deadline)\"");
                } else if (deadline.isEmpty()) {
                    System.out.println("  ~  Please give a deadline for the Deadline. The format should be \"deadline (description) /by (deadline)\"");
                } else {
                    Deadline task = new Deadline(taskName, deadline);
                    inputs.add(task);
                    System.out.println("  ~  " + exclamations[Random.nextInt(exclamations.length)] + "! I added this Deadline:");
                    System.out.println("  ~    " + task);
                    System.out.println("  ~  You now have " + inputs.size() + ((inputs.size() == 1) ? " task" : " tasks") + " in your list.");
                }
            } else if (userInput.trim().toLowerCase().startsWith("event")) {
                String[] parsed = userInput.trim().split(" ");
                String taskName = "";
                String start = "";
                String end = "";

                boolean isStart = false;
                boolean isEnd = false;

                for (int i = 1; i < parsed.length; i++) {
                    if (parsed[i].startsWith("/")) {
                        if (isStart) {
                            isEnd = true;
                        } else {
                            isStart = true;
                        }
                        continue;
                    }
                    if (!isStart) {
                        taskName += " " + parsed[i];
                    } else if (!isEnd) {
                        start += " " + parsed[i];
                    } else {
                        end += "" + parsed[i];
                    }
                }
                // trim so that blank space cannot be counted as name for task, start or end
                taskName = taskName.trim();
                start = start.trim();
                end = end.trim();
                if (taskName.isEmpty()) {
                    System.out.println("  ~  Please give a name for the Event. The format should be \"event (description) /from (start) /to (end)\"");
                } else if (start.isEmpty()) {
                    System.out.println("  ~  Please give a start time for the Event. The format should be \"event (description) /from (start) /to (end)\"");
                } else if (end.isEmpty()) {
                    System.out.println("  ~  Please give an end time for the Event. The format should be \"event (description) /from (start) /to (end)\"");
                } else {
                    Event task = new Event(taskName, start, end);
                    inputs.add(task);
                    System.out.println("  ~  " + exclamations[Random.nextInt(exclamations.length)] + "! I added this Event:");
                    System.out.println("  ~    " + task);
                    System.out.println("  ~  You now have " + inputs.size() + ((inputs.size() == 1) ? " task" : " tasks") + " in your list.");
                }
            } else {
                // user input does not match any specified command
                System.out.println("  ~  Sorry, that's not something I know how to do :( Please specify either a To Do, a Deadline or an Event!");
            }
            System.out.println("============================================================");
            userInput = inp.nextLine();
        }
        System.out.println("============================================================");
        System.out.println("Goodbye! Hope to see you again soon!");
        System.out.println("============================================================");
    }
}
