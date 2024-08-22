import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

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

        System.out.println(logo);
        System.out.println("Hello! My name is Victor!");
        System.out.println("What can I do for you?");
        System.out.println("============================================================");
        String userInput = inp.nextLine();
        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.println("============================================================");
            if (userInput.trim().isEmpty()) {
                // User input is empty - ask again for input
                System.out.println("  ~  What can I do for you?");
            } else if (userInput.toLowerCase().contains("list")) {
                if (inputs.isEmpty()) {
                    System.out.println("  ~  Nothing has been input so far - nothing to list! What can I do for you?");
                } else {
                    System.out.println("  ~  Sure! Here are all of your tasks:");
                    for (int i = 0; i < inputs.size(); i++) {
                        System.out.println("  ~  " + (i + 1) + ". " + inputs.get(i));
                    }
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
                taskName = taskName.trim();
                if (taskName.isEmpty()) {
                    System.out.println("  ~  Please give a name for the To Do.");
                } else {
                    ToDo task = new ToDo(taskName);
                    inputs.add(task);
                    System.out.println("  ~  Cool! I added this To Do:");
                    System.out.println("  ~    " + task);
                    System.out.println("  ~  You now have " + inputs.size() + ((inputs.size() == 1) ? " task" : " tasks") + " in your list.");
                }
            } else if (userInput.trim().toLowerCase().startsWith("deadline")) {
                String[] parsed = userInput.trim().split(" ");
                String taskName = "";
                String deadline = "";

                boolean deadlineFlag = false;

                for (int i = 1; i < parsed.length; i++) {
                    if (parsed[i].startsWith("/")) {
                        deadlineFlag = true;
                        continue;
                    }
                    if (!deadlineFlag) {
                        taskName += " " + parsed[i];
                    } else {
                        deadline += " " + parsed[i];
                    }
                }
                taskName = taskName.trim();
                deadline = deadline.trim();
                if (taskName.isEmpty()) {
                    System.out.println("  ~  Please give a name for the Deadline.");
                } else if (deadline.isEmpty()) {
                    System.out.println("  ~  Please give a deadline for the Deadline.");
                } else {
                    Deadline task = new Deadline(taskName, deadline);
                    inputs.add(task);
                    System.out.println("  ~  Cool! I added this Deadline:");
                    System.out.println("  ~    " + task);
                    System.out.println("  ~  You now have " + inputs.size() + ((inputs.size() == 1) ? " task" : " tasks") + " in your list.");
                }
            } else if (userInput.trim().toLowerCase().startsWith("event")) {
                String[] parsed = userInput.trim().split(" ");
                String taskName = "";
                String start = "";
                String end = "";

                boolean startFlag = false;
                boolean endFlag = false;

                for (int i = 1; i < parsed.length; i++) {
                    if (parsed[i].startsWith("/")) {
                        if (startFlag) {
                            endFlag = true;
                        } else {
                            startFlag = true;
                        }
                        continue;
                    }
                    if (!startFlag) {
                        taskName += " " + parsed[i];
                    } else if (!endFlag) {
                        start += " " + parsed[i];
                    } else {
                        end += "" + parsed[i];
                    }
                }
                taskName = taskName.trim();
                start = start.trim();
                end = end.trim();
                if (taskName.isEmpty()) {
                    System.out.println("  ~  Please give a name for the Event.");
                } else if (start.isEmpty()) {
                    System.out.println("  ~  Please give a start time for the Event.");
                } else if (end.isEmpty()) {
                    System.out.println("  ~  Please give an end time for the Event.");
                } else {
                    Event task = new Event(taskName, start, end);
                    inputs.add(task);
                    System.out.println("  ~  Cool! I added this Event:");
                    System.out.println("  ~    " + task);
                    System.out.println("  ~  You now have " + inputs.size() + ((inputs.size() == 1) ? " task" : " tasks") + " in your list.");
                }
            } else {
                System.out.println("  ~  Please specify either a To Do, a Deadline or an Event!");
            }
            System.out.println("============================================================");
            userInput = inp.nextLine();
        }
        System.out.println("============================================================");
        System.out.println("Goodbye! Hope to see you again soon!");
        System.out.println("============================================================");
    }
}
