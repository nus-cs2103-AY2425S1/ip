import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.text.MessageFormat;

public class Carly {
    private ArrayList<Task> taskList;

    public Carly() {
        this.taskList = new ArrayList<Task>();
    }

    private void mark(String firstPart, Integer taskNum){
        Task t = this.taskList.get(taskNum - 1);
        Task updatedT = t.markAsDone();
        this.taskList.set(taskNum - 1, updatedT);
        System.out.println(updatedT.getMarkAction());
    }

    private void unmark(String firstPart, Integer taskNum){
        Task t = this.taskList.get(taskNum - 1);
        Task updatedT = t.unmarkAsDone();
        this.taskList.set(taskNum - 1, updatedT);
        System.out.println(updatedT.getMarkAction());
    }


    private void chat() {
        String welcomeMsg = "Hello! I'm Carly\nWhat can I do for you?\n";
        String exitMsg = "Bye. Hope to see you again soon!";
        Scanner scan = new Scanner(System.in);
        String input;

        System.out.println(welcomeMsg);

        while (true) {
            input = scan.nextLine();
            String[] parts = input.split(" ");
            String firstPart = parts[0];

            if (firstPart.equals("bye")) {
                System.out.println(exitMsg);
                break;
            } else if (firstPart.equals("list")) {
                printTaskList();
            } else if (firstPart.equals("mark")) {
                int taskNum = Integer.parseInt(parts[1]);
                this.mark(firstPart, taskNum);
            } else if (firstPart.equals("unmark")) {
                int taskNum = Integer.parseInt(parts[1]);
                this.unmark(firstPart, taskNum);
            } else {
                Task t = new Task(input);
                this.taskList.add(t);
                System.out.println("added: " + input);
            }
        }
    }

    private void printTaskList(){
        IntStream.range(0, taskList.size())
                .forEach(i -> System.out.println(
                        MessageFormat.format("{0}.{1}", i + 1, taskList.get(i).getListing())));
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
