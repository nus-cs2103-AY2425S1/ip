package denim;

import denim.commands.Command;
import denim.commands.CommandResult;
import denim.exceptions.DenimException;
import denim.storage.TaskIo;

import java.util.Scanner;

public class Denim {
    static final String filePath = "data/denim.txt";
    private Ui ui;
    private TaskIo taskIo;

    private TaskList taskList;

    public static void main(String[] args) {
        Denim denim = new Denim();
        denim.start();
        denim.runCommandLoopUntilExitCommand();
        denim.exit();
    }

    private void start() {
        taskIo = new TaskIo(filePath);
        taskList = new TaskList();

        try {
            Scanner tempScanner = new Scanner(System.in);
            taskIo.readTaskData(taskList, tempScanner);
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
            CommandResult result = command.execute(taskList, taskIo);
            ui.displayReplyMessage(result);
        } while (!command.isExit());
    }

    private void exit() {
        System.exit(0);
    }
}


