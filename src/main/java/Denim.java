import java.util.Scanner;

public class Denim {
    static final String filePath = "data/denim.txt";
    private Ui ui;
    private TaskIO taskIO;

    private TaskList taskList;

    public static void main(String[] args) {
        Denim denim = new Denim();
        denim.start();
        denim.runCommandLoopUntilExitCommand();
        denim.exit();
    }

    private void start() {
        this.taskIO = new TaskIO(filePath);
        this.taskList = new TaskList();

        try {
            Scanner tempScanner = new Scanner(System.in);
            taskIO.readTaskData(taskList, tempScanner);
        } catch (DenimException e) {
            System.out.println(e.getMessage());
            return;
        }
        this.ui = new Ui();
        ui.displayGreetingMessage();
    }

    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommandText = ui.getUserCommand();
            command = new Parser().parseCommand(userCommandText);
            CommandResult result = command.execute(this.taskList, taskIO);
            ui.displayReplyMessage(result);
        } while (!command.isExit());
    }

    private void exit() {
        System.exit(0);
    }




}


