package calebyyy;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import calebyyy.commands.AddCommand;
import calebyyy.commands.ByeCommand;
import calebyyy.commands.Command;
import calebyyy.commands.ListCommand;
import calebyyy.commands.MarkCommand;
import calebyyy.commands.UnmarkCommand;
import calebyyy.exceptions.InvalidCommandException;
import calebyyy.exceptions.CalebyyyException;

public class CommandManager {
    private Map<String, Command> commands;
    private Map<String, Command> commandsWithArguments;
    private Command addCommand;
    private Command listCommand;
    private Command markCommand;
    private Command unmarkCommand;
    private Command byeCommand;
    private Scanner scanner;

    public CommandManager(Calebyyy calebyyy) {
        commands = new HashMap<>();
        commandsWithArguments = new HashMap<>();
        addCommand = new AddCommand(calebyyy);
        listCommand = new ListCommand(calebyyy);
        markCommand = new MarkCommand(calebyyy);
        unmarkCommand = new UnmarkCommand(calebyyy);
        byeCommand = new ByeCommand(calebyyy);
        commands.put("bye", byeCommand);
        commands.put("list", listCommand);
        commands.put("mark", markCommand);  
        commands.put("unmark", unmarkCommand);
        commands.put("todo", addCommand);
        commands.put("deadline", addCommand);
        commands.put("event", addCommand);
        commandsWithArguments.put("todo", addCommand);
        commandsWithArguments.put("deadline", addCommand);
        commandsWithArguments.put("event", addCommand);
        commandsWithArguments.put("mark", markCommand);
        commandsWithArguments.put("unmark", unmarkCommand);
        scanner = new Scanner(System.in);
    }

    public void startCommandLoop() {
        while (true) {
            readAndExecuteCommand();
        }
    }

    private void readAndExecuteCommand() {
        String input = scanner.nextLine();
        try {
            executeCommand(input);
        } catch (CalebyyyException e) {
            System.out.println(e);
        }
    }

    public void executeCommand(String input) throws CalebyyyException {
        String commandKey = input.split(" ")[0];
        Command command = commands.get(commandKey);
        if (commands.get(commandKey) != null) {
            command.execute(input);
        } else {
            throw new InvalidCommandException();
        }
    }
}