package rizzler.ui.parser;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import rizzler.command.ByeCommand;
import rizzler.command.Command;
import rizzler.command.DeadlineCommand;
import rizzler.command.DeleteCommand;
import rizzler.command.EventCommand;
import rizzler.command.FindCommand;
import rizzler.command.HelpCommand;
import rizzler.command.ListCommand;
import rizzler.command.MarkCommand;
import rizzler.command.NullCommand;
import rizzler.command.TodoCommand;
import rizzler.command.UnmarkCommand;
import rizzler.ui.RizzlerException;

/**
 * Parser to take in user input and process it into the appropriate command type.
 * Also logs all past user inputs.
 */
public class Parser {
    private ArrayList<String> pastInputs;

    /**
     * Constructor for a <code>Parser</code> object that can parse user input strings.
     * Initialises <code>pastInputs</code> as an <code>ArrayList</code> of strings.
     */
    public Parser() {
        pastInputs = new ArrayList<String>();
    }

    private Command parseHelp(String[] userInputArr) {
        Command outputCommand;
        try {
            String commandToHelpWith = userInputArr[1].trim().toLowerCase();
            outputCommand = new HelpCommand(commandToHelpWith);
        } catch (IndexOutOfBoundsException e) {
            outputCommand = new HelpCommand();
        } catch (RizzlerException e) {
            // unrecognised command
            // constructor cannot yet throw this exception, but will be enabled
            outputCommand = new NullCommand("unrecognised command, apologies!");
        }
        return outputCommand;
    }

    private Command parseFind(String[] userInputArr) {
        Command outputCommand;
        try {
            String stringToMatch = userInputArr[1].trim();
            outputCommand = new FindCommand(stringToMatch);
        } catch (IndexOutOfBoundsException e) {
            outputCommand = new NullCommand("we need a term to find, love.");
        } catch (Exception e) {
            outputCommand = new NullCommand("dreadfully sorry darlin', but there's an unexpected error");
        }
        return outputCommand;
    }

    private Command parseDelete(String[] userInputArr) {
        Command outputCommand;
        try {
            int taskToDelete = Integer.parseInt(userInputArr[1]);
            outputCommand = new DeleteCommand(taskToDelete);
        } catch (IndexOutOfBoundsException e) {
            outputCommand = new NullCommand("could ya' specify the number of the task to delete?");
        } catch (NumberFormatException e) {
            outputCommand = new NullCommand("i need a task number to delete, not a task description sugar");
        } catch (Exception e) {
            outputCommand = new NullCommand("dreadfully sorry darlin', but there's an unexpected error");
        }
        return outputCommand;
    }

    private Command parseMark(String[] userInputArr) {
        Command outputCommand;
        try {
            int taskToMark = Integer.parseInt(userInputArr[1]);
            outputCommand = new MarkCommand(taskToMark);
        } catch (IndexOutOfBoundsException e) {
            outputCommand = new NullCommand("i need a task number to mark, hot stuff.");
        } catch (NumberFormatException e) {
            outputCommand = new NullCommand("i need a task number, not a description darlin'");
        } catch (Exception e) {
            outputCommand = new NullCommand("dreadfully sorry darlin', but there's an unexpected error");
        }
        return outputCommand;
    }

    private Command parseUnmark(String[] userInputArr) {
        Command outputCommand;
        try {
            int taskToUnmark = Integer.parseInt(userInputArr[1]);
            outputCommand = new UnmarkCommand(taskToUnmark);
        } catch (IndexOutOfBoundsException e) {
            outputCommand = new NullCommand("i need a task number to unmark, hot stuff.");
        } catch (NumberFormatException e) {
            outputCommand = new NullCommand("i need a task number, not a description darlin'");
        } catch (Exception e) {
            outputCommand = new NullCommand("dreadfully sorry darlin', but there's an unexpected error");
        }
        return outputCommand;
    }

    private Command parseTodo(String[] userInputArr) {
        Command outputCommand;
        try {
            String[] todoDescArr = Arrays.copyOfRange(userInputArr, 1, userInputArr.length);
            if (todoDescArr.length == 0) {
                return new NullCommand("you have to let me know what task you have to do, dearie");
            }
            String todoDesc = String.join(" ", todoDescArr).trim();
            outputCommand = new TodoCommand(todoDesc);
        } catch (DateTimeParseException e) {
            outputCommand = new NullCommand(e.getMessage());
        } catch (Exception e) {
            outputCommand = new NullCommand("dreadfully sorry darlin', but there's an unexpected error");
        }
        return outputCommand;
    }

    private Command parseDeadline(String[] userInputArr) {
        Command outputCommand;
        try {
            String[] deadlineInfoArr = Arrays.copyOfRange(userInputArr, 1, userInputArr.length);
            if (deadlineInfoArr.length == 0) {
                return new NullCommand("honey, deadlines require a description and a date/time.");
            }
            String deadlineInfo = String.join(" ", deadlineInfoArr);
            if (!deadlineInfo.contains("/by")) {
                return new NullCommand("remember to include a \"/by [date/time]\" for the deadline!");
            }
            String[] deadlineInfoBySplit = deadlineInfo.split("/by");
            if (deadlineInfoBySplit.length < 2) {
                return new NullCommand("deadlines require both a description and a date/time dear.");
            }
            String deadlineDesc = deadlineInfoBySplit[0].trim();
            if (deadlineDesc.isEmpty()) {
                return new NullCommand("your deadline is missing a description honey.");
            }
            String deadlineTime = deadlineInfoBySplit[1].trim();
            outputCommand = new DeadlineCommand(deadlineDesc, deadlineTime);
        } catch (DateTimeParseException e) {
            outputCommand = new NullCommand(e.getMessage());
        } catch (Exception e) {
            outputCommand = new NullCommand("dreadfully sorry darlin', but there's an unexpected error");
        }
        return outputCommand;
    }

    private Command parseEvent(String[] userInputArr) {
        Command outputCommand;
        try {
            String[] eventInfoArr = Arrays.copyOfRange(userInputArr, 1, userInputArr.length);
            if (eventInfoArr.length == 0) {
                return new NullCommand("honey, events require a description, start, and end.");
            }
            String eventInfo = String.join(" ", eventInfoArr);
            if (!eventInfo.contains("/from")) {
                return new NullCommand("remember to include a \"/from [start]\" for this event!");
            } else if (!eventInfo.contains("/to")) {
                return new NullCommand("remember to include a \"/to [end]\" for this event!");
            }
            String[] eventInfoBySplit = eventInfo.split("/from");
            String eventDesc = eventInfoBySplit[0].trim();
            if (eventDesc.isEmpty()) {
                return new NullCommand("your event is missing a description honey.");
            }
            String eventDuration = eventInfoBySplit[1];
            if (eventDuration.split("/to").length < 2) {
                return new NullCommand("events require a description, a start, and an end dear");
            }
            String eventStart = eventDuration.split("/to")[0].trim();
            String eventEnd = eventDuration.split("/to")[1].trim();
            outputCommand = new EventCommand(eventDesc, eventStart, eventEnd);
        } catch (DateTimeParseException e) {
            outputCommand = new NullCommand(e.getMessage());
        } catch (Exception e) {
            outputCommand = new NullCommand("dreadfully sorry darlin', but there's an unexpected error");
        }
        return outputCommand;
    }

    /**
     * Takes in input as a given input <code>String</code>, returning the appropriate command.
     * Also adds the given user input to a log of all past user inputs.
     * @param userInput <code>String</code> entered by the user.
     * @return Command of varying types depending on user input.
     */
    public Command parseInput(String userInput) {
        pastInputs.add(userInput);
        String[] userInputArr = userInput.split(" ");
        Command outputCommand;
        String userInputFirstWord = userInputArr[0].trim().toLowerCase();
        switch (userInputFirstWord) {
        case "bye":
            outputCommand = new ByeCommand();
            break;
        case "help":
            outputCommand = parseHelp(userInputArr);
            break;
        case "list":
            outputCommand = new ListCommand();
            break;
        case "find":
            outputCommand = parseFind(userInputArr);
            break;
        case "delete":
            outputCommand = parseDelete(userInputArr);
            break;
        case "mark":
            outputCommand = parseMark(userInputArr);
            break;
        case "unmark":
            outputCommand = parseUnmark(userInputArr);
            break;
        case "todo":
            outputCommand = parseTodo(userInputArr);
            break;
        case "deadline":
            outputCommand = parseDeadline(userInputArr);
            break;
        case "event":
            outputCommand = parseEvent(userInputArr);
            break;
        default:
            outputCommand = new NullCommand("sincerest apologies darlin', i don't recognise that command.");
            break;
        }
        return outputCommand;

    }
}
