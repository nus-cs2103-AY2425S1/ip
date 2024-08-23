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
            if (input.equals("bye")) {
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
