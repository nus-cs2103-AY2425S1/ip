import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sora {
    public static void main(String[] args) {
        final String horizontalLine = "\t------------------------------";
        String greeting = "\tHello! I'm Sora!\n\tWhat can I do for you?\n" + horizontalLine;
        System.out.println(horizontalLine + "\n" + greeting);

        Scanner commandScanner = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<>();
        while (true) {
            String command = commandScanner.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                break;
            } else if (command.equalsIgnoreCase("list")) {
                displayList(taskList);
            } else {
                taskList.add(command);
                System.out.println("\t added: " + command + "\n" + horizontalLine);
            }
        }

        String exit = "\tBye. Hope to see you again soon!";
        System.out.println(exit + "\n" + horizontalLine);
    }

    public static void displayList(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + list.get(i));
        }
    }
}
