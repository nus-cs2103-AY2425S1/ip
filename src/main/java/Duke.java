import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> taskList = new ArrayList<>();

    public static String addHorizontalLinesAndIndentation(String dialog) {
        String res =  "    ____________________________________________________________\n";
        Scanner sc = new Scanner(dialog);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            res += "      " + line + "\n";
        }
        res += "    ____________________________________________________________";
        return res;
    }

    public static void addToList(String dialog) {
        taskList.add(new Task(dialog));
        String res = addHorizontalLinesAndIndentation("added: " + dialog );
        System.out.println(res);
    }

    public static void displayList() {
        String lString = "";
        int index = 1;
        for (Task task : taskList) {
            lString += String.valueOf(index) + "."  + task.toString();
            if (index == taskList.size()) {
                break;
            }
            lString += "\n";
            index++;
        }
        lString = addHorizontalLinesAndIndentation(lString);
        System.out.println(lString);
    }

    public static void mark(int index) {
        taskList.get(index - 1).setIsDone(true);
        System.out.println(addHorizontalLinesAndIndentation(
                        "Nice! I've marked this task as done:\n" +
                                taskList.get(index - 1) + "\n"
                ));
    }

    public static void unmark(int index) {
        taskList.get(index - 1).setIsDone(false);
        System.out.println(
                addHorizontalLinesAndIndentation(
                        "Nice! I've marked this task as undone:\n" +
                                taskList.get(index - 1) + "\n"
                )
        );
    }

    public static void main(String[] args) {
        String hi = "Hello! I'm Foo\n" +
                "What can I do for you?";
        System.out.println(addHorizontalLinesAndIndentation(hi));
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                displayList();
                continue;
            } else if (line.length() >= 6 && line.substring(0, 4).equals("mark")) {
                int index = Integer.parseInt(line.substring(5));
                mark(index);
                continue;
            } else if (line.length() >= 8 && line.substring(0, 6).equals("unmark")) {
                int index = Integer.parseInt(line.substring(7));
                unmark(index);
                continue;
            }
            addToList(line);
        }
        System.out.println(addHorizontalLinesAndIndentation("Bye. Hope to see you again soon!"));
    }
}
