import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Reo {
    private TaskList tasklist;
    private Ui ui;

    public Reo() {
        TaskList taskList;
        try {
            tasklist = new TaskList(FileOperations.readFile());
        } catch (Exception e) {
            tasklist = new TaskList(new ArrayList<Task>());
        }
         ui = new Ui(tasklist);
    }
    public static void main(String[] args) {
        new Reo().run();
    }

    public void run() {
        String currInput;
        Scanner scanner = new Scanner(System.in);
        currInput = "placeholder";
        ui.welcome();
        while (!currInput.toLowerCase().equals("bye")) {
            currInput = scanner.nextLine().trim();
            Parser p = new Parser(currInput, tasklist, ui);
            p.parse();
        }
        scanner.close();
    }
}
