import java.util.Scanner;
import java.util.ArrayList;

public class UI {
    private static final String line = "____________________________________________________________";

    private Scanner reader;

    public UI() {
        this.reader = new Scanner(System.in);
    }

    private void printLineSeparator() {
        System.out.println(line);
    }

    private void printErrorMessage(Exception e) {
        printLineSeparator();
        System.out.println(e);
        printLineSeparator();
    }

    public void printTaskList(ArrayList<Task> taskList, int taskCount) {
        printLineSeparator();
        for (int i = 0; i < taskCount; i++) {
            System.out.println(String.format("%d. %s", i+1, taskList.get(i)));
        }
        printLineSeparator();
    }

    public void output(String text) {
        printLineSeparator();
        System.out.println(text);
        printLineSeparator();
    }

    public String getInput() {
        if (reader.hasNext()) {
            String nextLine = reader.nextLine();
            return nextLine;
        }
        return "";
    }
}