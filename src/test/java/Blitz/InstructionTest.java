package blitz;

/* System import */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class InstructionTest {
    @Test
    public void checkIfGivenCommandExist_existingCommandInString_returnTrue() {
        assertTrue(Instruction.checkCommandExist("bye"));
        assertTrue(Instruction.checkCommandExist("todo"));
        assertTrue(Instruction.checkCommandExist("deadline"));
        assertTrue(Instruction.checkCommandExist("event"));
        assertTrue(Instruction.checkCommandExist("list"));
        assertTrue(Instruction.checkCommandExist("delete"));
        assertTrue(Instruction.checkCommandExist("mark"));
        assertTrue(Instruction.checkCommandExist("unmark"));
    }

    @Test
    public void checkIfGivenCommandExist_nonexistingCommandInString_returnFalse() {
        assertFalse(Instruction.checkCommandExist("gg"));
        assertFalse(Instruction.checkCommandExist("how"));
        assertFalse(Instruction.checkCommandExist("hello"));
        assertFalse(Instruction.checkCommandExist("2103"));
        assertFalse(Instruction.checkCommandExist("SWE"));
        assertFalse(Instruction.checkCommandExist("help"));
        assertFalse(Instruction.checkCommandExist("byee"));
        assertFalse(Instruction.checkCommandExist("print"));
    }
}
