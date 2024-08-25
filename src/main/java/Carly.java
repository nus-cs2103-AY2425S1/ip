import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.text.MessageFormat;

public class Carly {
    private ArrayList<String> taskList;

    public Carly() {
        this.taskList = new ArrayList<String>();
    }

    private void chat() {
        String welcomeMsg = "Hello! I'm Carly\nWhat can I do for you?\n";
        String exitMsg = "Bye. Hope to see you again soon!";
        Scanner scan = new Scanner(System.in);
        String input;

        System.out.println(welcomeMsg);

        while (true) {
            input = scan.nextLine();
            if (input.equals("bye")) {
                System.out.println(exitMsg);
                break;
            } else if (input.equals("list")) {
                printTaskList();
                break;
            }
            this.taskList.add(input);
            System.out.println("added: " + input);
        }
    }

    private void printTaskList(){
        IntStream.range(0, taskList.size())
                .forEach(i -> System.out.println(
                        MessageFormat.format("{0}. {1}", i + 1, taskList.get(i))));
    }

    public static void main(String[] args) {
        String logo = " ,-----.              ,--.          \n"
                + "'  .--./,--,--.,--.--.|  |,--. ,--. \n"
                + "|  |   ' ,-.  ||  .--'|  | \\  '  / \n"
                + "'  '--'\\ '-'  ||  |   |  |  \\   ' \n"
                + " `-----'`--`--'`--'   `--'.-'  /    \n"
                + "                          `---'  ";

        System.out.println("Hello from\n" + logo);
        Carly carly = new Carly();
        carly.chat();
    }


}
