package mediell;

import mediell.exception.IncorrectDateFormatException;
import mediell.exception.IncorrectInstructionFormatException;
import mediell.exception.NotEnoughParametersException;
import mediell.task.Deadline;
import mediell.task.Event;
import mediell.task.Task;
import mediell.task.ToDo;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a parser to parse the data provided by the user returns an instruction to be performed
 */
public class Parser {
    public Parser() {

    }

    /**
     * Parsers the user input into instructions
     * @param message is the user input
     * @return Instruction to execute
     */
    public Instruction getInstruction(String message)
            throws NotEnoughParametersException, IncorrectDateFormatException, IncorrectInstructionFormatException {
        if (Objects.equals(message, "list")) {
            return new Instruction(Instruction.Type.SEARCH);
        } else if (message.startsWith("mark")) {
            return createIndexInstruction(message, Instruction.Type.MARK);
        } else if (message.startsWith("unmark")) {
            return createIndexInstruction(message, Instruction.Type.UNMARK);
        } else if (message.startsWith("delete")) {
            return createIndexInstruction(message, Instruction.Type.DELETE);
        } else if (message.startsWith("todo")) {
            return createToDoInstruction(message);
        } else if (message.startsWith("event")) {
            return createEventInstruction(message);
        } else if (message.startsWith("deadline")) {
            return createDeadlineInstruction(message);
        } else if (message.startsWith("find")) {
            return createFindInstruction(message);
        } else if (message.startsWith("sort")) {
            return new Instruction(Instruction.Type.SORT);
        } else {
            throw new IncorrectInstructionFormatException();
        }
    }

    private Instruction createIndexInstruction(String message, Instruction.Type type)
            throws NotEnoughParametersException {
        String[] messages = message.split(" ", 2);
        if (messages.length != 2) {
            throw new NotEnoughParametersException();
        }
        int index = Integer.parseInt(messages[1]) - 1;
        Instruction temp = new Instruction(type);
        temp.setIndex(index);
        return temp;
    }

    private Instruction createFindInstruction(String message) throws NotEnoughParametersException {
        String[] messages = message.split(" ", 2);
        if (messages.length != 2) {
            throw new NotEnoughParametersException();
        }
        Instruction temp = new Instruction(Instruction.Type.SEARCH);
        temp.setSearch(messages[1]);
        return temp;
    }

    private Instruction createToDoInstruction(String message) throws NotEnoughParametersException {
        String[] messages = message.split(" ", 2);
        if (messages.length != 2) {
            throw new NotEnoughParametersException();
        }
        Instruction temp = new Instruction(Instruction.Type.INSERT);
        String taskName = messages[1];
        Task task = new ToDo(taskName);
        temp.setTask(task);
        return temp;
    }

    private Instruction createEventInstruction(String message)
            throws NotEnoughParametersException, IncorrectDateFormatException {
        String[] messages = message.split(" ", 2);
        if (messages.length != 2) {
            throw new NotEnoughParametersException();
        }
        String[] taskSplit = messages[1].split("/from", 2);
        if (taskSplit.length != 2) {
            throw new NotEnoughParametersException();
        }
        String[] dateSplit = taskSplit[1].split("/to", 2);
        if (dateSplit.length != 2) {
            throw new NotEnoughParametersException();
        }
        Instruction temp = new Instruction(Instruction.Type.INSERT);
        String taskName = taskSplit[0];
        LocalDate from = null;
        LocalDate to = null;
        try {
            from = LocalDate.parse(dateSplit[0].strip());
            to = LocalDate.parse(dateSplit[1].strip());
        } catch (Exception e) {
            throw new IncorrectDateFormatException();
        }
        Task task = new Event(taskName, from, to);
        temp.setTask(task);
        return temp;
    }

    private Instruction createDeadlineInstruction(String message)
            throws NotEnoughParametersException, IncorrectDateFormatException {
        String[] messages = message.split(" ", 2);
        if (messages.length != 2) {
            throw new NotEnoughParametersException();
        }
        String[] taskSplit = messages[1].split("/by", 2);
        if (taskSplit.length != 2) {
            throw new NotEnoughParametersException();
        }
        Instruction temp = new Instruction(Instruction.Type.INSERT);
        String taskName = taskSplit[0];
        LocalDate by = null;
        try {
            by = LocalDate.parse(taskSplit[1].strip());
        } catch (Exception e) {
            throw new IncorrectDateFormatException();
        }
        Task task = new Deadline(taskName, by);
        temp.setTask(task);
        return temp;
    }
}
