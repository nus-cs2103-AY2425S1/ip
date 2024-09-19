package blitz;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class InstructionTest {
    @Test
    public void checkIfGivenCommandExist_existingCommandInString_returnTrue() {
        assertTrue(Instruction.isValidCommand("bye"));
        assertTrue(Instruction.isValidCommand("todo"));
        assertTrue(Instruction.isValidCommand("deadline"));
        assertTrue(Instruction.isValidCommand("event"));
        assertTrue(Instruction.isValidCommand("list"));
        assertTrue(Instruction.isValidCommand("delete"));
        assertTrue(Instruction.isValidCommand("mark"));
        assertTrue(Instruction.isValidCommand("unmark"));
        assertTrue(Instruction.isValidCommand("find"));
    }

    @Test
    public void checkIfGivenCommandExist_nonexistingCommandInString_returnFalse() {
        assertFalse(Instruction.isValidCommand("gg"));
        assertFalse(Instruction.isValidCommand("how"));
        assertFalse(Instruction.isValidCommand("hello"));
        assertFalse(Instruction.isValidCommand("2103"));
        assertFalse(Instruction.isValidCommand("SWE"));
        assertFalse(Instruction.isValidCommand("help"));
        assertFalse(Instruction.isValidCommand("byee"));
        assertFalse(Instruction.isValidCommand("print"));
    }
}
