import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sora {
    public static void main(String[] args) {
        final String horizontalLine = "\t------------------------------";
        String greeting = "\tHello! I'm Sora!\n\tWhat can I do for you?\n" + horizontalLine;
        System.out.println(horizontalLine + "\n" + greeting);

        Scanner commandScanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();
        while (true) {
            String[] command = commandScanner.nextLine().trim().split(" ", 2);
            if (command[0].equalsIgnoreCase("bye") && command.length == 1) {
                break;
            } else if (command[0].equalsIgnoreCase("list") && command.length == 1) {
                displayList(taskList);
            } else if (command[0].equalsIgnoreCase("mark") && command.length == 2) {
                taskList.get(Integer.parseInt(command[1]) - 1).setDone(true);
                System.out.println("\tNice! Sora has marked this task as done:");
                System.out.println("\t" + taskList.get(Integer.parseInt(command[1]) - 1));
            } else if (command[0].equalsIgnoreCase("unmark")) {
                taskList.get(Integer.parseInt(command[1]) - 1).setDone(false);
                System.out.println("\tOk, Sora has unmarked this task as not done:");
                System.out.println("\t" + taskList.get(Integer.parseInt(command[1]) - 1));
            } else {
                String description = command[0] + (command.length == 2 ? " " + command[1] : "");
                taskList.add(new Task(description));
                System.out.println("\t added: " + description);
            }
            System.out.println(horizontalLine);
        }

        String exit = "\tBye. Hope to see you again soon!";
        System.out.println(exit + "\n" + horizontalLine);
    }

    public static void displayList(List<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + list.get(i));
        }
    }
}
