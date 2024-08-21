
import java.util.Scanner;
import java.util.ArrayList;
public class Simon {
    final String HOR_LINE = "\t____________________________________________________________\n";
    final String WLC_MSG = "Hello! I'm Simon \n" +
            " \tWhat can I do for you?";
    final String EXT_MSG = " Bye. Hope to see you again soon!";
    int taskCount = 0;
    ArrayList<Task> taskList = new ArrayList<Task>();

    private String printMessage(String msg) {
        return HOR_LINE + "\t" + msg + "\n" + HOR_LINE;
    }
    public void run() {
        System.out.print(printMessage(WLC_MSG));
        Scanner sc = new Scanner(System.in);
        String input;
        while (!(input = sc.nextLine().trim()).equals("bye")) {
            if (input.equals("list")) {
                System.out.print(HOR_LINE);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("\t" + (i + 1) + ". " + taskList.get(i).toString());
                }
                System.out.println(HOR_LINE);
            }
            else {
                taskCount++;
                Task task = new Task(input, taskCount);
                taskList.add(task);
                System.out.println(printMessage("added: " + input));
            }

        }
        System.out.println(printMessage(EXT_MSG));
    }
    public static void main(String[] args) {
        new Simon().run();
    }
}
