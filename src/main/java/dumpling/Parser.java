package dumpling;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dumpling.command.AddCommand;
import dumpling.command.ByeCommand;
import dumpling.command.Command;
import dumpling.command.CommandEnum;
import dumpling.command.DeleteCommand;
import dumpling.command.FindCommand;
import dumpling.command.ListCommand;
import dumpling.command.MarkCommand;
import dumpling.command.NoteCommand;
import dumpling.task.Deadline;
import dumpling.task.Event;
import dumpling.task.Task;
import dumpling.task.ToDo;

/**
 * Parser class to handle input manipulation
 */
public class Parser {

    private static final String DEADLINE_DATE_SEPARATOR = "/by";
    private static final String EVENT_FROM_SEPARATOR = "/from";
    private static final String EVENT_TO_SEPARATOR = "/to";

    /**
     * Parse a given full command from the user and stores
     * its information into the relevant Command object
     *
     * @param stringCommand user's input
     * @return Command object can be executed
     * @throws DumplingException Message includes information about the error experienced
     */
    public static Command parse(String stringCommand) throws DumplingException {
        String commandString = stringCommand.split(" ")[0];
        CommandEnum commandEnum = CommandEnum.retrieveCommandEnum(commandString);
        switch (commandEnum) {
        case TODO:
            // fall through
        case DEADLINE:
            // fall through
        case EVENT:
            return new AddCommand(stringCommand, commandEnum);
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            // fall through
        case UNMARK:
            try {
                int itemIdx = Integer.parseInt(stringCommand.split(" ")[1]);
                return new MarkCommand(commandEnum, itemIdx);
            } catch (NumberFormatException e) {
                throw new DumplingException(
                        "    Uh-oh... There was an issue when marking / unmarking a task! "
                                + "The argument provided was not a number.");
            } catch (IndexOutOfBoundsException e) {
                throw new DumplingException(
                        "    Uh-oh... There was an issue with indexing! Try listing the items first!");
            }
        case DELETE:
            try {
                int itemIdx = Integer.parseInt(stringCommand.split(" ")[1]);
                return new DeleteCommand(itemIdx);
            } catch (NumberFormatException e) {
                throw new DumplingException(
                        "    Uh-oh... There was an issue when deleting a task! "
                                + "The argument provided was not a number.");
            } catch (IndexOutOfBoundsException e) {
                throw new DumplingException(
                        "    Uh-oh... There was an issue with indexing! Try listing the items first!");
            }
        case FIND:
            Pair<String, Integer> pair = Parser.formSubSection(
                    stringCommand.split(" "), 1, "");
            return new FindCommand(pair.getFirst());
        case NOTE:
            try {
                int itemIdx = Integer.parseInt(stringCommand.split(" ")[1]);
                return new NoteCommand(
                        itemIdx,
                        stringCommand.split(stringCommand.split(" ")[1] + " ")[1]);
            } catch (NumberFormatException e) {
                throw new DumplingException(
                        "    Uh-oh... There was an issue when adding notes to a task! "
                                + "The argument provided was not a number.");
            } catch (IndexOutOfBoundsException e) {
                throw new DumplingException(
                        "    Uh-oh... There was an issue with indexing! Try listing the items first!");
            }
        default:
            throw new DumplingException("    Grrr... An invalid command was given! Try again.");
        }
    }

    private static Task createTaskFromHardDiskInput(String line) {
        String[] lineSplit = line.split("\\|");
        for (int i = 0; i < lineSplit.length; i++) {
            lineSplit[i] = lineSplit[i].trim();
        }
        CommandEnum commandEnum;
        String simulatedTaskStringInput;
        String taskNotes = (lineSplit.length > 4) ? lineSplit[4] : "";
        switch (lineSplit[0]) {
        case "T":
            commandEnum = CommandEnum.TODO;
            simulatedTaskStringInput = String.format("todo %s", lineSplit[2]);
            break;
        case "D":
            commandEnum = CommandEnum.DEADLINE;
            simulatedTaskStringInput = String.format("deadline %s /by %s", lineSplit[2], lineSplit[3]);
            break;
        case "E":
            commandEnum = CommandEnum.EVENT;
            String[] fromAndTo = lineSplit[3].split("&");
            simulatedTaskStringInput = String.format(
                    "event %s /from %s /to %s", lineSplit[2], fromAndTo[0], fromAndTo[1]);
            break;
        default:
            throw new DumplingException(
                    String.format("    Uh-oh... %s is not a valid task type. Data might be corrupted.", lineSplit[0]));
        }
        // since the message is not used when loading in the data, we can ignore the numItems argument
        Pair<Task, String> pair = Parser.add(simulatedTaskStringInput, commandEnum, 0);
        if (lineSplit[1].equals("1")) {
            pair.getFirst().markAsDone();
        }
        pair.getFirst().updateNotes(taskNotes);
        return pair.getFirst();
    }

    /**
     * Loads the data from the harddisk file.
     *
     * @param filePath Filepath of the harddisk file
     * @return List of Task objects that can be used to instantiate a TaskList object
     */
    public static List<Task> loadData(String filePath) {
        File dataFile = new File(filePath);
        List<Task> items = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = Parser.createTaskFromHardDiskInput(line);
                items.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // no data to load
        }
        return items;
    }

    private static Deadline createDeadlineTaskFromString(String[] splitDescription) {
        Pair<String, Integer> taskDescriptionIdxPair = Parser.formSubSection(
                splitDescription, 1, DEADLINE_DATE_SEPARATOR);
        String taskDescription = taskDescriptionIdxPair.getFirst();
        Pair<String, Integer> deadlineIdxPair = Parser.formSubSection(
                splitDescription, taskDescriptionIdxPair.getSecond() + 1, "");
        String deadline = deadlineIdxPair.getFirst();
        try {
            return new Deadline(taskDescription, deadline);
        } catch (DateTimeParseException e) {
            throw new DumplingException("    Uh-oh... Please enter the date in the correct format of YYYY-MM-DD.");
        }
    }

    private static Event createEventTaskFromString(String[] splitDescription) {
        Pair<String, Integer> taskDescriptionIdxPair = Parser.formSubSection(
                splitDescription, 1, EVENT_FROM_SEPARATOR);
        String taskDescription = taskDescriptionIdxPair.getFirst();
        Pair<String, Integer> fromIdxPair = Parser.formSubSection(
                splitDescription, taskDescriptionIdxPair.getSecond() + 1, EVENT_TO_SEPARATOR);
        Pair<String, Integer> toIdxPair = Parser.formSubSection(
                splitDescription, fromIdxPair.getSecond() + 1, "");
        String from = fromIdxPair.getFirst();
        String to = toIdxPair.getFirst();
        try {
            return new Event(taskDescription, from, to);
        } catch (DateTimeParseException e) {
            throw new DumplingException("    Uh-oh... Please enter the date in the correct format of YYYY-MM-DD.");
        }
    }

    private static ToDo createTodoTaskFromString(String[] splitDescription) {
        Pair<String, Integer> taskDescriptionIdxPair = Parser.formSubSection(
                splitDescription, 1, "");
        String taskDescription = taskDescriptionIdxPair.getFirst();
        return new ToDo(taskDescription);
    }

    /**
     * Deciphers information for commands that add a Task to the TaskList
     *
     * @param description User's input without the command
     * @param command Command representing either TODO, DEADLINE or EVENT
     * @param numItems number of items currently in the TaskList
     * @return Pair of the deciphered Task and message
     * @throws DumplingException Thrown either due to a date format issue or empty description
     */
    public static Pair<Task, String> add(
            String description, CommandEnum command, int numItems) throws DumplingException {
        String[] splitDescription = description.split(" ");
        Task task;
        task = switch (command) {
        case DEADLINE -> createDeadlineTaskFromString(splitDescription);
        case EVENT -> createEventTaskFromString(splitDescription);
        default -> createTodoTaskFromString(splitDescription);
        };
        String message = "     Nom, nom, nom. I've added this task:\n"
                + String.format("       %s\n", task)
                + String.format("     Now you have %d %s in the list. Dumpling's getting a bit fuller!",
                        numItems + 1, (numItems + 1 == 1 ? "task" : "tasks"));
        return new Pair<>(task, message);
    }

    /**
     * Helper method to obtain information from the split user input string
     *
     * @param splitDescription String array obtained by splitting the user input string
     * @param startIdx Starting index to consider
     * @param terminalString String to stop forming the subsection
     * @return Pair of formed string and ending index of this traversal
     */
    private static Pair<String, Integer> formSubSection(
            String[] splitDescription,
            int startIdx,
            String terminalString
    ) {
        String formedSection = "";
        int currIdx = startIdx;
        while (currIdx < splitDescription.length && !splitDescription[currIdx].equals(terminalString)) {
            if (!formedSection.isEmpty()) {
                formedSection += " ";
            }
            formedSection += splitDescription[currIdx];
            currIdx++;
        }
        if (formedSection.isEmpty()) {
            throw new DumplingException(
                    "    Uh-oh... Intending to extract information out of a given input, but nothing to extract."
            );
        }
        return new Pair<>(formedSection, currIdx);
    }
}
