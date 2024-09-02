import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> taskList = new ArrayList<>();

    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    

    public static void addToList(String dialog) {
        Task task = Task.of(dialog);
        taskList.add(task);
        String res = addHorizontalLinesAndIndentation("Got it. I've added this task: \n"
                + String.format("  %s", task) + "\n" + String.format("Now you have %d tasks in the list.", taskList.size()));
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

    public static void delete(int index) {
        Task task = taskList.remove(index - 1);
        System.out.println(
                addHorizontalLinesAndIndentation(
                        String.format("Noted. I've removed this task:\n"
                                + task
                                + " Now you have %d tasks in the list.", taskList.size())
                )
        );
    }

    public static void main(String[] args) {
        String hi = "Hello! I'm Foo\n" +
                "What can I do for you?";
        System.out.println(addHorizontalLinesAndIndentation(hi));
        Scanner sc = new Scanner(System.in);
        while (true) {
            
        }
        System.out.println(addHorizontalLinesAndIndentation("Bye. Hope to see you again soon!"));
    }
}
