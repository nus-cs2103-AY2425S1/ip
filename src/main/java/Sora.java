import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sora {
    public static void main(String[] args) {
        final String horizontalLine = "\t------------------------------";
        String greeting = "\tHello! I'm Sora!\n\tWhat can I do for you?\n" + horizontalLine;
        System.out.println(horizontalLine + "\n" + greeting);

        Scanner commandScanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        while (true) {
            try {
                String[] command = commandScanner.nextLine().trim().split(" ", 2);
                if (command[0].equalsIgnoreCase("bye") && command.length == 1) {
                    break;
                } else if (command[0].equalsIgnoreCase("list") && command.length == 1) {
                    taskList.displayList();
                } else if (command[0].equalsIgnoreCase("mark") && command.length == 2) {
                    taskList.get(Integer.parseInt(command[1]) - 1).setDone(true);
                    System.out.println("\tNice! Sora has marked this task as done:");
                    System.out.println("\t" + taskList.get(Integer.parseInt(command[1]) - 1));
                } else if (command[0].equalsIgnoreCase("unmark") && command.length == 2) {
                    taskList.get(Integer.parseInt(command[1]) - 1).setDone(false);
                    System.out.println("\tOk, Sora has unmarked this task as not done:");
                    System.out.println("\t" + taskList.get(Integer.parseInt(command[1]) - 1));
                } else if (command[0].equalsIgnoreCase("todo") && command.length == 2) {
                    taskList.add(new ToDo(command[1]));
                    System.out.println("\tGot it. Sora has added this task:");
                    System.out.println("\t" + taskList.get(taskList.size() - 1));
                    System.out.println("\tNow, you have " + taskList.size() + " tasks in your list");
                } else if (command[0].equalsIgnoreCase("deadline") && command.length == 2) {
                    String[] deadlineInformation = command[1].trim().split(" /by ", 2);
                    taskList.add(new Deadline(deadlineInformation[0], deadlineInformation[1]));
                    System.out.println("\tGot it. Sora has added this task:");
                    System.out.println("\t" + taskList.get(taskList.size() - 1));
                    System.out.println("\tNow, you have " + taskList.size() + " tasks in your list");
                } else if (command[0].equalsIgnoreCase("event") && command.length == 2) {
                    String eventFrom = command[1].trim().split(" /from ", 2)[1].split(" /to", 2)[0];
                    String eventTo = command[1].trim().split("/to ", 2)[1];
                    taskList.add(new Event(command[0], eventFrom, eventTo));
                    System.out.println("\tGot it. Sora has added this task:");
                    System.out.println("\t" + taskList.get(taskList.size() - 1));
                    System.out.println("\tNow, you have " + taskList.size() + " tasks in your list");
                } else if (command[0].equalsIgnoreCase("delete") && command.length == 2) {
                    Task deletedTask = taskList.remove(Integer.parseInt(command[1]) - 1);
                    System.out.println("\tNoted. Sora has removed this task:");
                    System.out.println("\t" + deletedTask);
                } else if (command.length == 0) {
                    throw new SoraException("Error: No Command Inputted.");
                } else {
                    throw new SoraException("Error: Command cannot be understood.");
                }
                System.out.println(horizontalLine);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error: Number Entered > No. of tasks in your list. Please Try Again.");
            } catch (SoraException e) {
                System.out.println(e.getMessage());
            }
        }

        String exit = "\tBye. Hope to see you again soon!";
        System.out.println(exit + "\n" + horizontalLine);
    }


}
