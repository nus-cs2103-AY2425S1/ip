import command.Command;
import command.CommandParser;
import command.CommandResponse;
import exception.EchoBotException;
import exception.UnknownCommandException;
import io.FileManagement;
import task.TaskList;

public class EchoBot {
    private FileManagement fileManagement;
    private TaskList taskList;
    private UI ui;

    public static void main(String[] args) {
        new EchoBot().run(args);
    }

    public void run(String[] args) {
        this.start(args);
        this.runUntilExitCommand();
        this.exit();
    }

    private void start(String[] args) {
        this.ui = new UI();
        this.fileManagement = new FileManagement();
        this.taskList = fileManagement.load();
        this.ui.greeting();
    }

    private CommandResponse executeCommand(Command command) throws EchoBotException {
        command.setTaskList(this.taskList);
        command.setFileManagement(this.fileManagement);
        return command.execute();
    }

    private void runUntilExitCommand() {
        Command command;
        CommandResponse commandResponse;
        try {
            do {
                String input = this.ui.getUserInput();
                command = new CommandParser().parse(input);
                commandResponse = this.executeCommand(command);
                this.ui.printCommandResponse(commandResponse);
            } while (!commandResponse.isExitCommand());
        } catch (UnknownCommandException e) {
            this.ui.printErrorMessage(e);
            this.runUntilExitCommand();
        } catch (EchoBotException e) {
            this.ui.printErrorMessage(e);
        }
    }

    private void exit() {
        this.ui.exit();
    }
}
