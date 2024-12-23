package colress.command;

import static colress.testutil.TestUtil.VALID_DATE_ARGUMENT_ONE;
import static colress.testutil.TestUtil.VALID_DATE_ARGUMENT_TWO;
import static colress.testutil.TestUtil.VALID_DATE_ONE;
import static colress.testutil.TestUtil.VALID_DATE_TWO;
import static colress.testutil.TestUtil.VALID_DESCRIPTION_ONE;
import static colress.testutil.TestUtil.VALID_DESCRIPTION_TWO;
import static colress.testutil.TestUtil.VALID_FROM_TIME_ARGUMENT_ONE;
import static colress.testutil.TestUtil.VALID_FROM_TIME_ARGUMENT_TWO;
import static colress.testutil.TestUtil.VALID_FROM_TIME_ONE;
import static colress.testutil.TestUtil.VALID_FROM_TIME_TWO;
import static colress.testutil.TestUtil.VALID_TASK_TYPE_ARGUMENT_EVENT;
import static colress.testutil.TestUtil.VALID_TASK_TYPE_EVENT;
import static colress.testutil.TestUtil.VALID_TO_TIME_ARGUMENT_ONE;
import static colress.testutil.TestUtil.VALID_TO_TIME_ARGUMENT_TWO;
import static colress.testutil.TestUtil.VALID_TO_TIME_ONE;
import static colress.testutil.TestUtil.VALID_TO_TIME_TWO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AddCommandTest {
    @Test
    public void equalsTest() {
        AddCommand addCommand = new AddCommand();

        // null values -> returns true
        assertTrue(addCommand.equals(new AddCommand()));

        addCommand = new AddCommand(new String[]{
            VALID_TASK_TYPE_ARGUMENT_EVENT, VALID_DESCRIPTION_ONE, VALID_DATE_ARGUMENT_ONE,
            VALID_FROM_TIME_ARGUMENT_ONE, VALID_TO_TIME_ARGUMENT_ONE},
                VALID_TASK_TYPE_EVENT, VALID_DESCRIPTION_ONE, VALID_DATE_ONE, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE);

        // same values -> returns true
        assertTrue(addCommand.equals(new AddCommand(new String[]{
            VALID_TASK_TYPE_ARGUMENT_EVENT, VALID_DESCRIPTION_ONE, VALID_DATE_ARGUMENT_ONE,
            VALID_FROM_TIME_ARGUMENT_ONE, VALID_TO_TIME_ARGUMENT_ONE},
                VALID_TASK_TYPE_EVENT, VALID_DESCRIPTION_ONE, VALID_DATE_ONE, VALID_FROM_TIME_ONE, VALID_TO_TIME_ONE)));

        // same object -> returns true
        assertTrue(addCommand.equals(addCommand));

        // null -> returns false
        assertFalse(addCommand.equals(null));

        // different types -> returns false
        assertFalse(addCommand.equals(17));

        // different values -> returns false
        assertFalse(addCommand.equals(new AddCommand(new String[]{
            VALID_TASK_TYPE_ARGUMENT_EVENT, VALID_DESCRIPTION_TWO, VALID_DATE_ARGUMENT_TWO,
            VALID_FROM_TIME_ARGUMENT_TWO, VALID_TO_TIME_ARGUMENT_TWO},
                VALID_TASK_TYPE_EVENT, VALID_DESCRIPTION_TWO, VALID_DATE_TWO, VALID_FROM_TIME_TWO, VALID_TO_TIME_TWO)));
    }
}
