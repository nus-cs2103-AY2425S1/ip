
import command.Parser;
import command.Ui;
import storage.Storage;
import task.TaskList;

import java.util.Scanner;

public class ChatterBox{
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;


    public ChatterBox(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        taskList = new TaskList();
        parser = new Parser();
        storage.saveTasks(taskList.getTasks());
    }

    public void run() {
        ui.greetUser();
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;

        while(isRunning) {
            String input = sc.nextLine().trim();
            if(input.equals("bye")) {
                parser.parseExecute(input, taskList, storage, ui);
                isRunning = false;
            } else {
                parser.parseExecute(input, taskList, storage, ui);
            }
        }
    }

    public static void main(String[] args) {
        String filePath = "./data/chatterbox.txt";
        ChatterBox chatterBox = new ChatterBox(filePath);
        chatterBox.run();
    }
}
