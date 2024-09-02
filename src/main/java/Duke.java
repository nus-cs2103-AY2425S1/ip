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
        String res = Parser.addHorizontalLinesAndIndentation("Got it. I've added this task: \n"
                + String.format("  %s", task) + "\n" + String.format("Now you have %d tasks in the list.", taskList.size()));
        System.out.println(res);
    }


    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        while (true) {
            
        }
        
    }
}
