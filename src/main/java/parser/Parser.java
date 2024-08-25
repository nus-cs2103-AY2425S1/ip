package parser;

import ouiouibaguette.OuiOuiBaguetteException;

import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddToDoCommand;
import command.ByeCommand;
import command.Command;
import command.DeleteCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;

import tasks.DeadlineException;
import tasks.EventException;
import tasks.ToDoException;

public class Parser {
    public Command parseCommand(String cmd) throws OuiOuiBaguetteException {
        if (cmd.equals("bye")) {
            return new ByeCommand();

        } else if (cmd.equals("list")) {
            return new ListCommand();

        } else if (cmd.startsWith("mark")) {
            // Parse command to get index of task
            int index = Integer.parseInt(cmd.split(" ")[1]) - 1;
            
            return new MarkCommand(index);

        } else if (cmd.startsWith("unmark")) {
            // Parse command to get index of task
            int index = Integer.parseInt(cmd.split(" ")[1]) - 1;
            
            return new UnmarkCommand(index);

        } else if (cmd.startsWith("todo")) {
            // Add ToDo
            if (cmd.length() <= ("todo ").length()) {
                throw new ToDoException("The description of a todo cannot be empty.");
            }

            String desc = cmd.substring(("todo ").length());

            return new AddToDoCommand(desc);

        } else if (cmd.startsWith("deadline")) {
            // Add Deadline
            if (cmd.length() <= ("deadline ").length()) {
                throw new DeadlineException("The description of a deadline cannot be empty.");
            }

            String descAndDate = cmd.substring(("deadline ").length());

            // Check if format is correct
            if (!descAndDate.contains(" /by ")) {
                throw new DeadlineException("""
                    The format entered is wrong.
                    \t Please follow the format: deadline <description> /by <due date>""");
            }

            String desc = descAndDate.split(" /by ")[0];
            // Check if there is a valid description
            if (desc.length() == 0) {
                throw new DeadlineException("The description of a deadline cannot be empty.");
            } 

            // Check if there is a valid due date
            if (descAndDate.split(" /by ").length < 2) {
                throw new DeadlineException("The due date of a deadline cannot be empty.");
            } 
            String date = descAndDate.split(" /by ")[1];

            return new AddDeadlineCommand(desc, date);

        } else if (cmd.startsWith("event")) {
            // Add Event
            if (cmd.length() <= ("event ").length()) {
                throw new EventException("The description of a deadline cannot be empty.");
            }

            String descAndStartEnd = cmd.substring(("event ").length());
            
            // Check if format is correct
            if (!descAndStartEnd.contains(" /from ") || !descAndStartEnd.contains(" /to ")) {
                throw new EventException("""
                    The format entered is wrong.
                    \t Please follow the format: event <description> /from <start> /to <end>""");
            }

            String desc = descAndStartEnd.split(" /from ")[0];
            // Check if there is a valid description
            if (desc.length() == 0) {
                throw new EventException("The description of an event cannot be empty.");
            } 

            // Check if there is a valid start
            if (descAndStartEnd.indexOf("/from ") + ("/from ").length()
                    >= descAndStartEnd.indexOf(" /to")) {
                throw new EventException("The start of an event cannot be empty.");
                    }
            String start = descAndStartEnd.substring(
                    descAndStartEnd.indexOf("/from ") + ("/from ").length(),
                    descAndStartEnd.indexOf(" /to"));

            // Check if there is a valid end
            if (descAndStartEnd.split(" /to ").length < 2) {
                throw new EventException("The end of an event cannot be empty.");
            }
            String end = descAndStartEnd.split(" /to ")[1];

            return new AddEventCommand(desc, start, end);

        } else if (cmd.startsWith("delete")) {
            // Parse command to get index of task
            int index = Integer.parseInt(cmd.split(" ")[1]) - 1;

            return new DeleteCommand(index);

        } else {
            // Unknown command
            throw new UnknownCommandException();
        }
    }
}

