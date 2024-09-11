package mediell;

import mediell.exception.IncorrectIndexException;
import mediell.exception.IncorrectInstructionFormatException;

/**
 * Represents a class that given instructions will execute the instructions
 */
public class Executor {
    public Executor() {

    }

    public Instruction executeInstruction(Instruction instruction, TaskList taskList)
            throws IncorrectIndexException, IncorrectInstructionFormatException {
        switch (instruction.getInstructionType()) {
            case MARK:
                assert instruction.getIndex() != -1;
                taskList.markItem(instruction.getIndex());
                return instruction;
            case DELETE:
                assert instruction.getIndex() != -1;
                instruction.setTask(taskList.getTask(instruction.getIndex()));
                taskList.deleteTask(instruction.getIndex());
                return instruction;
            case INSERT:
                assert instruction.getTask() != null;
                taskList.addTask(instruction.getTask());
                return instruction;
            case SEARCH:
                return instruction;
            case UNMARK:
                assert instruction.getIndex() != -1;
                taskList.unmarkItem(instruction.getIndex());
                return instruction;
        }
        throw new IncorrectInstructionFormatException();
    }
}
