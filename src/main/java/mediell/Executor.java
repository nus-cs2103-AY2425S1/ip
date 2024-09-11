package mediell;

import mediell.exception.IncorrectIndexException;
import mediell.exception.IncorrectInstructionFormatException;

/**
 * Represents a class that given instructions will execute the instructions
 */
public class Executor {
    public Executor() {

    }

    /**
     * Executes the instruction provided
     * @param instruction is the instructions to be executed
     * @param taskList is the taskList to execute the instructions on
     * @return Instruction if it has been edited
     */
    public Instruction executeInstruction(Instruction instruction, TaskList taskList)
            throws IncorrectIndexException, IncorrectInstructionFormatException {
        switch (instruction.getInstructionType()) {
            case MARK:
                taskList.markItem(instruction.getIndex());
                return instruction;
            case DELETE:
                instruction.setTask(taskList.getTask(instruction.getIndex()));
                taskList.deleteTask(instruction.getIndex());
                return instruction;
            case INSERT:
                taskList.addTask(instruction.getTask());
                return instruction;
            case SEARCH:
                return instruction;
            case UNMARK:
                taskList.unmarkItem(instruction.getIndex());
                return instruction;
        }
        throw new IncorrectInstructionFormatException();
    }
}
