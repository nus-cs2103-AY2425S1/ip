import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class LuToDo {

    private Storage storage;
    private TaskList tasks;


    public LuToDo(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        Ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = Ui.readCommand();
                Ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                Ui.showError(e.getMessage());
            } finally {
                Ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new LuToDo("docs/taskListFile.txt").run();
    }
    /*
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File taskListFile = new File(Utility.FILE_PATH);
        if (!taskListFile.exists()) {
            try {
                taskListFile.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
            Utility.fileToTaskList(taskListFile);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        Utility.greeting();
        while (true) {
            String message = sc.nextLine();
            System.out.print(Utility.LINE);
            if (message.equals("bye")) {
                Utility.bye();
                System.out.print(Utility.LINE);
                break;
            }
            Utility.handleMessage(message);
            System.out.print(Utility.LINE);
            Utility.taskListToFile();
        }
    }
    */
}
