import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
public class Julie {
    private static final String NAME = "Julie";
    private static boolean run = true;
    private static final List<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        CommonFunctions.wrappedLinePrint("Hi!! My name is: " + NAME + "!\nHow may I help?");
        Scanner sc = new Scanner(System.in);
        while (run) {
            String input = sc.nextLine();
            if (input.startsWith("mark")) {
                String[] tokens = input.split(" ");
                int x = Integer.parseInt(tokens[1]) - 1;
                Task t = taskList.get(x);
                t.mark();
                CommonFunctions.wrappedLinePrint(String.format("Ooh, this task is done!\n%s",t));
            } else if (input.startsWith("unmark")) {
                String[] tokens = input.split(" ");
                int x = Integer.parseInt(tokens[1]) - 1;
                Task t = taskList.get(x);
                t.unmark();
                CommonFunctions.wrappedLinePrint(String.format("Oop, this task is not yet done\n%s",t));
            } else if (input.equals("bye")) {
                run = false;
            } else if (input.equals("list")) {
                CommonFunctions.printList(taskList);
            } else if (input.startsWith("todo")) {
                Task t = new ToDo(input.substring(5));
                taskList.add(t);
                CommonFunctions.addedPrompt(t, taskList);
            } else if (input.startsWith("deadline")) {
                String[] tokens = input.split(" /by ");
                Task t = new Deadline(tokens[0].substring(9), tokens[1]);
                taskList.add(t);
                CommonFunctions.addedPrompt(t, taskList);
            } else if (input.startsWith("event")) {
                String[] tokens = input.split(" /from | /to ");
                Task t = new Event(tokens[0].substring(6), tokens[1], tokens[2]);
                taskList.add(t);
                CommonFunctions.addedPrompt(t, taskList);
            } else {
                // For now do nothing
            }
        }
        CommonFunctions.wrappedLinePrint("Bye!! See you soon!!");
    }
}
