import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gallium {
    public static void main(String[] args) {
        String helloMessage = "Hello! I am Gallium. \n    What can I do for you?";
        String listMessage = "Here are the tasks in your list:";
        String byeMessage = "Bye. Hope to see you again soon!";
        String lines = "____________________________________________________________";
        Task[] taskList = new Task[100];
        Scanner userInput = new Scanner(System.in);
        System.out.println("    " + lines + "\n    " + helloMessage + "\n    " + lines);
        String Message = userInput.nextLine();
        String bye = "bye";
        String list = "list";
        String mark = "mark";
        String unmark = "unmark";

        while (!Message.equals(bye)) {
            try {
                if (Message.equals(list)) {
                    System.out.println("    " + lines);
                    System.out.println("    " + listMessage);
                    for (int i = 1; i < Task.count; i++) {
                        Task task = taskList[i - 1];
                        System.out.println("\n    " + i + ". " + task.toString());
                    }
                    System.out.println("    " + lines + "\n    ");
                    Message = userInput.nextLine();
                } else if (Message.matches(mark + " \\d+") || Message.matches(unmark + " \\d+")) {
                    boolean isMark = Message.startsWith(mark);
                    Pattern pattern = Pattern.compile((isMark ? mark : unmark) + " (\\d+)");
                    Matcher matcher = pattern.matcher(Message);
                    if (matcher.matches()) {
                        int index = Integer.parseInt(matcher.group(1));
                        Task task = taskList[index - 1];
                        task.setIsDone(isMark);
                        System.out.println("    " + lines);
                        System.out.println("    " + (isMark ? "Nice! I've marked this task as done: "
                                : "OK, I've marked this task as not done yet: ") + "\n" + "    "
                                + task.toString());
                        System.out.println("    " + lines + "\n    ");
                        Message = userInput.nextLine();
                    }
                } else if (Message.equals("todo") || Message.equals("todo ") || Message.equals("deadline")
                        || Message.equals("deadline ") || Message.equals("event") || Message.equals("event ")) {
                    throw new GalliumException("OOPS!!! The description of a " + Message + " cannot be empty.");
                } else if (Message.startsWith("todo ")) {
                    Todo todo = new Todo(Message);
                    System.out.println("    " + lines + "\n    " + "Got it. I've added this task: \n" + "    "
                            + todo.toString()
                            + "\n    Now you have " + Task.count + " tasks in the list.\n" + "    " + lines + "\n");
                    taskList[Task.count - 1] = todo;
                    Task.count++;
                } else if (Message.startsWith("deadline ")) {
                    Deadline deadline = new Deadline(Message);
                    System.out
                            .println("    " + lines + "\n    " + "Got it. I've added this task: \n"
                                    + "    " + deadline.toString()
                                    + "\n    Now you have " + Task.count + " tasks in the list.\n" + "    " + lines
                                    + "\n");
                    taskList[Task.count - 1] = deadline;
                    Task.count++;
                } else if (Message.startsWith("event ")) {
                    Event event = new Event(Message);
                    System.out.println("    " + lines + "\n    " + "Got it. I've added this task: \n" + "    "
                            + event.toString()
                            + "\n    Now you have " + Task.count + " tasks in the list.\n" + "    " + lines + "\n");
                    taskList[Task.count - 1] = event;
                    Task.count++;
                } else {
                    throw new GalliumException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                Message = userInput.nextLine();
            } catch (GalliumException e) {
                System.out.println("    " + lines + "\n    " + e.getMessage() + "\n    " + lines + "\n");
                Message = userInput.nextLine();
            }
        }
        System.out.println("    " + lines + "\n    " + byeMessage + "\n    " + lines + "\n");
        userInput.close();
    }
}
