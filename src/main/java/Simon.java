import java.util.Scanner;
import java.util.ArrayList;
public class Simon {
    final String HOR_LINE = "\t____________________________________________________________\n";
    final String WLC_MSG = " Hello! I'm Simon \n" +
            " What can I do for you?\n";
    final String EXT_MSG = " Bye. Hope to see you again soon!";
    ArrayList<Task> taskList = new ArrayList<Task>(); // Create an ArrayList object

    int taskCount = 0;
    public void run() {
        System.out.print(WLC_MSG);
        Scanner sc = new Scanner(System.in);
        String input;
        while (!(input = sc.nextLine().trim()).equals("bye")) {
            try {
                if (input.isEmpty()) {
                    continue;
                } else if (input.equals("list")) {
                    printAllTasks();
                    continue;
                } else if (input.startsWith("mark")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index < 0 || index >= taskCount) {
                        throw new IndexOutOfBoundsException("Task number does not exist.");
                    }
                    taskList.get(index).markAsDone();
                    String prMsg = printMessage("\tNice! I've marked this task as done:\n" + "\t" + taskList.get(index).toString());
                    System.out.print(prMsg);
                    continue;
                } else if (input.startsWith("unmark")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index < 0 || index >= taskCount) {
                        throw new IndexOutOfBoundsException("Task number does not exist.");
                    }
                    taskList.get(index).markAsNotDone();
                    String prMsg = printMessage("\tOK, I've marked this task as not done yet:\n" + "\t" + taskList.get(index).toString());
                    System.out.print(prMsg);
                    continue;
                } else if (input.startsWith("deadline")) {
                    String[] info = input.substring(9).split("/by");
                    if (info.length < 2 || info[0].trim().isEmpty() || info[1].trim().isEmpty()) {
                        throw new IllegalArgumentException("The deadline command is missing description or date.");
                    }
                    Deadline deadline = new Deadline(info[0].trim(), taskCount, info[1].trim());
                    taskList.add(deadline);
                    taskCount++;
                    String prMsg = printMessage("Got it. I've added this task:\n" + "\t" + deadline.toString()) + "\tNow you have " + taskCount + " tasks in the list.";
                    System.out.println(prMsg);
                    continue;
                } else if (input.startsWith("event")) {
                    String[] info = input.substring(5).split("/from");
                    if (info.length < 2 || info[0].trim().isEmpty()) {
                        throw new IllegalArgumentException("The event command is missing description or date/time.");
                    }
                    String[] deadlines = info[1].split("/to");
                    if (deadlines.length < 2 || deadlines[0].trim().isEmpty() || deadlines[1].trim().isEmpty()) {
                        throw new IllegalArgumentException("The event command is missing the start or end time.");
                    }
                    String from = deadlines[0].trim();
                    String to = deadlines[1].trim();
                    String name = info[0].trim();
                    Events event = new Events(name, taskCount, from, to);
                    taskList.add(event);
                    taskCount++;
                    String prMsg = printMessage("Got it. I've added this task:\n" + "\t" + event.toString()) + "\tNow you have " + taskCount + " tasks in the list.";
                    System.out.println(prMsg);
                    continue;
                } else if (input.startsWith("todo")) {
                    String info = input.substring(4).trim();
                    if (info.isEmpty()) {
                        throw new IllegalArgumentException("The description of a todo cannot be empty.");
                    }
                    ToDo todo = new ToDo(info, taskCount);
                    taskList.add(todo);
                    taskCount++;
                    String prMsg = printMessage("Got it. I've added this task:\n" + "\t" + todo.toString()) + "\tNow you have " + taskCount + " tasks in the list.";
                    System.out.println(prMsg);
                    continue;
                }


                else {
                    throw new UnsupportedOperationException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (IndexOutOfBoundsException e) {
                String errorMsg = printMessage("OOPS!!! " + e.getMessage());
                System.out.print(errorMsg);
            } catch (IllegalArgumentException e) {
                String errorMsg = printMessage("OOPS!!! " + e.getMessage());
                System.out.print(errorMsg);
            } catch (UnsupportedOperationException e) {
                String errorMsg = printMessage("OOPS!!! " + e.getMessage());
                System.out.print(errorMsg);
            } catch (Exception e) {
                String errorMsg = printMessage("OOPS!!! An unexpected error occurred: " + e.getMessage());
                System.out.print(errorMsg);
            }
        }
        System.out.print(printMessage(EXT_MSG));
    }
    public static void main(String[] args) {
        Simon simon = new Simon();
        simon.run();
    }

    private String printMessage(String msg) {
        return HOR_LINE + "\t" + msg + "\n" + HOR_LINE;
    }
    private void printAllTasks() {
        System.out.print(HOR_LINE);
        for (int i = 0; i < taskCount; i++) {
            Task task = taskList.get(i);
            System.out.println("\t" + (i + 1) + ". " + task.toString());
        }
        System.out.print(HOR_LINE);
    }
}
