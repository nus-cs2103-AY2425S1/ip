package dumpling;

import dumpling.command.*;
import dumpling.task.Deadline;
import dumpling.task.Event;
import dumpling.task.Task;
import dumpling.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {

    private static final String DEADLINE_DATE_SEPARATOR = "/by";
    private static final String EVENT_FROM_SEPARATOR = "/from";
    private static final String EVENT_TO_SEPARATOR = "/to";

    public static Command parse(String stringCommand) throws DumplingException {
        String commandString = stringCommand.split(" ")[0];
        CommandEnum commandEnum;
        try {
            commandEnum = CommandEnum.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new DumplingException(
                    String.format(
                            "%s is not a valid command!\n" +
                                    "     To list items, use 'list'.\n" +
                                    "     To mark or unmark an item as done, use '<mark/unmark> <item index>'.\n" +
                                    "     To add a new item, use '<todo/deadline/event> <task name> <args>'.",
                            stringCommand
                    )
            );
        }
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
                        "There was an issue when marking / unmarking a task! The argument provided was not a number.");
            } catch (IndexOutOfBoundsException e) {
                throw new DumplingException(
                        "There was an issue with indexing! Try listing the items first!");
            }
        case DELETE:
            try {
                int itemIdx = Integer.parseInt(stringCommand.split(" ")[1]);
                return new DeleteCommand(itemIdx);
            } catch (NumberFormatException e) {
                throw new DumplingException(
                        "There was an issue when marking / unmarking a task! The argument provided was not a number.");
            } catch (IndexOutOfBoundsException e) {
                throw new DumplingException(
                        "There was an issue with indexing! Try listing the items first!");
            }
        case FIND:
            Pair<String, Integer> pair = Parser.formSubSection(stringCommand.split(" "), 1, "");
            return new FindCommand(pair.getFirst());
        default:
            throw new DumplingException("An invalid command was given! Try again.");
        }
    }

    public static List<Task> loadData(String filePath) {
        File dataFile = new File(filePath);
        List<Task> items = new ArrayList<>();
        try {
            // load data
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(" \\| ");
                Pair<Task, String> pair;
                switch (line[0]) {
                    case "T":
                        pair = Parser.add(String.format("todo %s", line[2]), CommandEnum.TODO, items.size());
                        items.add(pair.getFirst());
                        break;
                    case "D":
                        pair = Parser.add(
                                String.format("deadline %s /by %s", line[2], line[3]),
                                CommandEnum.DEADLINE,
                                items.size());
                        items.add(pair.getFirst());
                        break;
                    case "E":
                        String[] fromAndTo = line[3].split("-");
                        pair = Parser.add(
                                String.format("event %s /from %s /to %s", line[2], fromAndTo[0], fromAndTo[1]),
                                CommandEnum.EVENT,
                                items.size());
                        items.add(pair.getFirst());
                        break;
                    default:
                        throw new DumplingException(
                                String.format("%s is not a valid task type. Data might be corrupted.", line[0]));
                }
                if (line[1].equals("1")) {
                    items.get(items.size() - 1).markAsDone();
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // no data to load
        }
        return items;
    }

    public static Pair<Task, String> add(
            String description, CommandEnum command, int numItems) throws DumplingException {
        String[] splitDescription = description.split(" ");
        String taskDescription;
        Task task;
        Pair<String, Integer> taskDescriptionIdxPair;
        switch (command) {
            case DEADLINE:
                try {
                    taskDescriptionIdxPair = Parser.formSubSection(splitDescription, 1, DEADLINE_DATE_SEPARATOR);
                    taskDescription = taskDescriptionIdxPair.getFirst();
                    Pair<String, Integer> deadlineIdxPair = Parser.formSubSection(
                            splitDescription, taskDescriptionIdxPair.getSecond() + 1, "");
                    String deadline = deadlineIdxPair.getFirst();
                    task = new Deadline(taskDescription, deadline);
                } catch (DateTimeParseException e) {
                    throw new DumplingException("Please enter the date in the correct format of YYYY-MM-DD.");
                }
                break;
            case EVENT:
                taskDescriptionIdxPair = Parser.formSubSection(splitDescription, 1, EVENT_FROM_SEPARATOR);
                taskDescription = taskDescriptionIdxPair.getFirst();
                Pair<String, Integer> fromIdxPair = Parser.formSubSection(
                        splitDescription, taskDescriptionIdxPair.getSecond() + 1, EVENT_TO_SEPARATOR);
                Pair<String, Integer> toIdxPair = Parser.formSubSection(
                        splitDescription, fromIdxPair.getSecond() + 1, "");
                String from = fromIdxPair.getFirst();
                String to = toIdxPair.getFirst();
                task = new Event(taskDescription, from, to);
                break;
            default:
                taskDescriptionIdxPair = Parser.formSubSection(splitDescription, 1, "");
                taskDescription = taskDescriptionIdxPair.getFirst();
                task = new ToDo(taskDescription);
                break;
        }
        if (taskDescription.isEmpty()) {
            throw new DumplingException(
                    "Like a dumpling, tasks cannot be empty! Please provide a descriptive name."
            );
        }
        String message = "     Got it. I've added this task:\n" +
                String.format("       %s\n", task.toString()) +
                String.format("     Now you have %d %s in the list.",
                        numItems + 1, (numItems + 1 == 1 ? "task" : "tasks"));
        return new Pair<>(task, message);
    }

    public static Pair<String, Integer> formSubSection(
            String[] splitDescrption,
            int startIdx,
            String terminalString
    ) {
        String formedSection = "";
        int currIdx = startIdx;
        while (currIdx < splitDescrption.length && !splitDescrption[currIdx].equals(terminalString)) {
            if (!formedSection.isEmpty()) {
                formedSection += " ";
            }
            formedSection += splitDescrption[currIdx];
            currIdx++;
        }
        return new Pair<>(formedSection, currIdx);
    }
}
