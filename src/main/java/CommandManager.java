import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandManager {
    private Map<String, Command> commands;
    private Command addCommand;
    private Scanner scanner;

    public CommandManager(Calebyyy calebyyy) {
        commands = new HashMap<>();
        commands.put("bye", new ByeCommand(calebyyy));
        commands.put("list", new ListCommand(calebyyy));
        commands.put("mark", new MarkCommand(calebyyy));
        commands.put("unmark", new UnmarkCommand(calebyyy));
        addCommand = new AddCommand(calebyyy);
        scanner = new Scanner(System.in);
    }

    public void startCommandLoop() {
        while (true) {
            readAndExecuteCommand();
        }
    }

    private void readAndExecuteCommand() {
        String input = scanner.nextLine();
        executeCommand(input);
    }

    public void executeCommand(String input){
        String commandKey = input.split(" ")[0];
        Command command = commands.get(commandKey);
        if (command != null) {
            command.execute(input);
        } else {
            addCommand.execute(input);
        }
    }
}