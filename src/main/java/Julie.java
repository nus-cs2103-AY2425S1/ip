import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
public class Julie {
    private static final String NAME = "Julie";
    private static boolean run = true;
    private static final List<Task> taskList = new ArrayList<>();
    public static void main(String[] args) {
        CommonFunctions.WrappedLinePrint("Hi!! My name is: " + NAME + "!\nHow may I help?");
        Scanner sc = new Scanner(System.in);
        while (run) {
            String input = sc.nextLine();
            if (input.startsWith("mark")) {
                String[] tokens = input.split(" ");
                int x = Integer.parseInt(tokens[1]) - 1;
                Task t = taskList.get(x);
                t.mark();
                CommonFunctions.WrappedLinePrint(String.format("Ooh, this task is done!\n%s",t));
            } else if (input.startsWith("unmark")) {
                String[] tokens = input.split(" ");
                int x = Integer.parseInt(tokens[1]) - 1;
                Task t = taskList.get(x);
                t.unmark();
                CommonFunctions.WrappedLinePrint(String.format("Oop, this task is not yet done\n%s",t));
            } else if (input.equals("bye")) {
                run = false;
            } else if (input.equals("list")) {
                CommonFunctions.PrintList(taskList);
            } else {
                taskList.add(new Task(input));
                CommonFunctions.WrappedLinePrint(String.format("added: %s", input));
            }
        }
        CommonFunctions.WrappedLinePrint("Bye!! See you soon!!");
    }
}
