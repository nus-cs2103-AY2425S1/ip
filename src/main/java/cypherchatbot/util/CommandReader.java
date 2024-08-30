package cypherchatbot.util;

import cypherchatbot.CypherException;
import cypherchatbot.command.ByeCommand;
import cypherchatbot.command.Command;
import cypherchatbot.command.DeadlineCommand;
import cypherchatbot.command.DeleteCommand;
import cypherchatbot.command.EventCommand;
import cypherchatbot.command.HelpCommand;
import cypherchatbot.command.ListCommand;
import cypherchatbot.command.MarkCommand;
import cypherchatbot.command.ToDoCommand;
import cypherchatbot.command.UnmarkCommand;



public class CommandReader {

    private static enum Commands {
        LIST, TODO, EVENT, DEADLINE, MARK, UNMARK, BYE, HELP, DELETE
    }

    public static Command parse(String input) throws CypherException {
        String[] command = input.split(" ", 2);
        try {
            switch (Commands.valueOf(command[0].toUpperCase())) {
            case LIST:
                return new ListCommand();
            case TODO:
                if (command.length != 2 || command[1].trim().isEmpty()) {
                    throw new CypherException("No task is given. The format of the todo command is:\n todo <Description of task>");
                }
                return new ToDoCommand(command);

            case DEADLINE:
                String[] deadlineSplit = command[1].split("/by", 2);

                if (deadlineSplit[0].isEmpty()) {
                    throw new CypherException("No task is given. The format of the deadline command is:\n deadline <Description of task> /by yyyy-MM-dd HH:mm");
                } else if (deadlineSplit.length != 2 || deadlineSplit[1].trim().isEmpty()) {
                    throw new CypherException("No deadline is given. The format of the deadline command is:\n deadline <Description of task> /by yyyy-MM-dd HH:mm");
                }
                return new DeadlineCommand(deadlineSplit);

            case EVENT:
                String[] eventSplit = command[1].split("/from|/to ", 3);
                if (eventSplit[0].isEmpty()) {
                    throw new CypherException("No task is given. The format of the event command is:\n event <Description of task> /from yyyy-MM-dd HH:mm> /to yyyy-MM-dd HH:mm");
                } else if (eventSplit.length != 3 || eventSplit[1].trim().isEmpty() || eventSplit[2].trim().isEmpty()) {
                    throw new CypherException("To/from is not given properly. The format of the deadline command is:\n event <Description of task> /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm");
                }
                return new EventCommand(eventSplit);

            case MARK:
                // Need check if that is number
                int markVal = Integer.parseInt(command[1]) - 1;
                return new MarkCommand(markVal);

            case UNMARK:
                // Need check if that is number
                int unmarkVal = Integer.parseInt(command[1]) - 1;
                return new UnmarkCommand(unmarkVal);
            case BYE:
                return new ByeCommand();
            case HELP:
                return new HelpCommand();
            case DELETE:
                int delVal = Integer.parseInt(command[1]) - 1;
                return new DeleteCommand(delVal);
            default:
                System.out.printf("\"%s\" is not a valid command. Type help in order to see the list of valid commands (This feature is still under construction)\n", command[0]);
                return new HelpCommand();
            }
        } catch (NumberFormatException exp) {
            throw new CypherException("That is not a valid command. You need to enter a valid integer. Type help in order to see the list of valid commands (This feature is still under construction)");
        } catch (IllegalArgumentException exp) {
            throw new CypherException("That is not a valid command. Type help in order to see the list of valid commands (This feature is still under construction)");
        }
    }
}
