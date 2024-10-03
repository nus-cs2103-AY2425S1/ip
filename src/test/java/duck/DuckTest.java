package duck;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import duck.data.exception.DuckException;


/**
 * Tests the DuckException class.
 */
public class DuckTest {

    private static Duck duck;

    /**
     * Sets up the test environment.
     */
    @BeforeAll
    public static void setUp() {
        duck = new Duck("data/testing.txt");
    }

    /**
     * Tears down the test environment.
     */
    @AfterAll
    public static void tearDown() {
        assertDoesNotThrow(() -> duck.clearCache());
    }


    /**
     * Tests the processInput method of the Duck with valid deadline command input.
     */
    @Test
    public void processInput_deadlineCommand_success() {
        String input1 = "deadline test    /by 2021-08-25     1800";
        String input2 = "deADline test /by 2021/08/25    1800";
        String input3 = "  deadline  test   /by     2021-08-25    1800   ";

        assertDoesNotThrow(() -> duck.processInput(input1));
        assertDoesNotThrow(() -> duck.processInput(input2));
        assertDoesNotThrow(() -> duck.processInput(input3));
    }

    /**
     * Tests the processInput method of the Duck with valid delete command input.
     */
    @AfterAll
    public static void processInput_deleteCommand_success() {
        String input1 = "deLete 1";
        String input2 = "delEte    1";
        String input3 = "delete    1  ";
        String input4 = "   DELETE     1    ";

        assertDoesNotThrow(() -> duck.processInput(input1));
        assertDoesNotThrow(() -> duck.processInput(input2));
        assertDoesNotThrow(() -> duck.processInput(input3));
        assertDoesNotThrow(() -> duck.processInput(input4));
    }

    /**
     * Tests the processInput method of the Duck with valid event command input.
     */
    @Test
    public void processInput_eventCommand_success() {
        String input1 = "event test /from 2021-08-25 1800 /to       2021-08-25 1900";
        String input2 = "eVent test /from 2021/08/25       1800 /to 2021/08/25      1900";
        String input3 = "  event test /from      2021/08/25 1800 /to       2021-08-25    1900   ";

        assertDoesNotThrow(() -> duck.processInput(input1));
        assertDoesNotThrow(() -> duck.processInput(input2));
        assertDoesNotThrow(() -> duck.processInput(input3));
    }

    /**
     * Tests the processInput method of the Duck with valid find command input.
     */
    @Test
    public void processInput_findCommand_success() {
        String input1 = "find test";
        String input2 = "Find test ";
        String input3 = "fInd test  ";
        String input4 = "find     test  ";
        String input5 = "   FIND    f    ";

        assertDoesNotThrow(() -> duck.processInput(input1));
        assertDoesNotThrow(() -> duck.processInput(input2));
        assertDoesNotThrow(() -> duck.processInput(input3));
        assertDoesNotThrow(() -> duck.processInput(input4));
        assertDoesNotThrow(() -> duck.processInput(input5));
    }


    /**
     * Tests the processInput method of the Duck with valid help command input.
     */
    @Test
    public void processInput_helpCommand_success() {
        String input1 = "help";
        String input2 = "    Help ";
        String input3 = "hElp  ";
        String input4 = "help  ";
        String input5 = "   HELP        ME ";
        String input6 = "HELP ME";

        assertDoesNotThrow(() -> duck.processInput(input1));
        assertDoesNotThrow(() -> duck.processInput(input2));
        assertDoesNotThrow(() -> duck.processInput(input3));
        assertDoesNotThrow(() -> duck.processInput(input4));
        assertDoesNotThrow(() -> duck.processInput(input5));
    }

    /**
     * Tests the processInput method of the Duck with valid list command input.
     */
    @Test
    public void processInput_listCommand_success() {
        String input1 = "list";
        String input2 = "List ";
        String input3 = " lIst  ?";
        String input4 = "list    2 ";
        String input5 = "   LIST  ";

        assertDoesNotThrow(() -> duck.processInput(input1));
        assertDoesNotThrow(() -> duck.processInput(input2));
        assertDoesNotThrow(() -> duck.processInput(input3));
        assertDoesNotThrow(() -> duck.processInput(input4));
        assertDoesNotThrow(() -> duck.processInput(input5));
    }

    /**
     * Tests the processInput method of the Duck with valid mark command input.
     */
    @AfterAll
    public static void processInput_markCommand_success() {
        String input1 = "mark 1";
        String input2 = "Mark 1 ";
        String input3 = "     mArk     1  ";
        String input4 = "mark 1  ";
        String input5 = "   MARK     1 ";

        assertDoesNotThrow(() -> duck.processInput(input1));
        assertDoesNotThrow(() -> duck.processInput(input2));
        assertDoesNotThrow(() -> duck.processInput(input3));
        assertDoesNotThrow(() -> duck.processInput(input4));
        assertDoesNotThrow(() -> duck.processInput(input5));
    }

    /**
     * Tests the processInput method of the Duck with valid on command input.
     */
    @Test
    public void processInput_onCommand_success() {
        String input1 = "oN   2021-08-25";
        String input2 = "    ON   2021-08-25       ";

        assertDoesNotThrow(() -> duck.processInput(input1));
        assertDoesNotThrow(() -> duck.processInput(input2));
    }

    /**
     * Tests the processInput method of the Duck with valid sort command target all input.
     */
    @Test
    public void processInput_sortCommandTargetAll_success() {
        // sorting all tasks
        String targetAllByDescription = "sort   /target  all /by     description";
        String targetAllByType = "sort  /target   all  /by     type";

        assertDoesNotThrow(() -> duck.processInput(targetAllByDescription));
        assertDoesNotThrow(() -> duck.processInput(targetAllByType));
    }

    /**
     * Tests the processInput method of the Duck with valid sort command input target todo input.
     */
    @Test
    public void processInput_sortCommandTargetToDo_success() {
        // sorting todo tasks and place them on top
        String targetToDoByDescription = "   sort    /target todo /by description  ";

        assertDoesNotThrow(() -> duck.processInput(targetToDoByDescription));
    }

    /**
     * Tests the processInput method of the Duck with valid sort command input target deadline input.
     */
    @Test
    public void processInput_sortCommandTargetDeadline_success() {
        // sorting deadline tasks and place them on top
        String targetDeadlineByDescription = "sort /target deadline /by    description    ";
        String targetDeadlineByDeadline = "sort /target     deadline /by      deadline";

        assertDoesNotThrow(() -> duck.processInput(targetDeadlineByDescription));
        assertDoesNotThrow(() -> duck.processInput(targetDeadlineByDeadline));
    }

    /**
     * Tests the processInput method of the Duck with valid sort command input target event input.
     */
    @Test
    public void processInput_sortCommandTargetEvent_success() {
        // sorting event tasks and place them on top
        String targetEventByDescription = "sort    /target    event /by description";
        String targetEventByStart = "sort /by start /target event";
        String targetEventByEnd = "sort     /target     event    /by      end";

        assertDoesNotThrow(() -> duck.processInput(targetEventByDescription));
        assertDoesNotThrow(() -> duck.processInput(targetEventByStart));
        assertDoesNotThrow(() -> duck.processInput(targetEventByEnd));
    }

    /**
     * Tests the processInput method of the Duck with valid todo command input.
     */
    @Test
    public void processInput_toDoCommand_success() {
        String input1 = "todo test";
        String input2 = "ToDo test ";
        String input3 = "tOdo     test  ";
        String input4 = "todo  test  ";
        String input5 = "   TODO    test";

        assertDoesNotThrow(() -> duck.processInput(input1));
        assertDoesNotThrow(() -> duck.processInput(input2));
        assertDoesNotThrow(() -> duck.processInput(input3));
        assertDoesNotThrow(() -> duck.processInput(input4));
        assertDoesNotThrow(() -> duck.processInput(input5));
    }

    /**
     * Tests the processInput method of the Duck with invalid unmark command input.
     */
    @AfterAll
    public static void processInput_unmarkCommand_exceptionThrown() {
        String input1 = "unmark 1";
        String input2 = "Unmark 1 ";
        String input3 = "uNmark     1  ";
        String input4 = "unmark 1  ";
        String input5 = "   UNMARK    1 ";

        assertDoesNotThrow(() -> duck.processInput(input1));
        assertDoesNotThrow(() -> duck.processInput(input2));
        assertDoesNotThrow(() -> duck.processInput(input3));
        assertDoesNotThrow(() -> duck.processInput(input4));
        assertDoesNotThrow(() -> duck.processInput(input5));
    }

    /**
     * Tests the processInput method of the Duck with random invalid input.
     */
    @Test
    public void processInput_randomInvalidInput_exceptionThrown() {
        String input1 = "invalid";
        String input2 = "";
        String input3 = " ";
        String input4 = "   ?    ";

        assertThrows(DuckException.class, () -> duck.processInput(input1));
        assertThrows(DuckException.class, () -> duck.processInput(input2));
        assertThrows(DuckException.class, () -> duck.processInput(input3));
        assertThrows(DuckException.class, () -> duck.processInput(input4));
    }

    /**
     * Tests the processInput method of the Duck with invalid deadline command input.
     */
    @Test
    public void processInput_invalidDeadlineCommand_exceptionThrown() {
        String input1 = "deadline test /by 2021/08-25 1800";
        String input2 = "deADline test /by 2021/08/25 18000";
        String input3 = "  deadline  test   /by     2021-08-25 2500   ";

        assertThrows(DuckException.class, () -> duck.processInput(input1));
        assertThrows(DuckException.class, () -> duck.processInput(input2));
        assertThrows(DuckException.class, () -> duck.processInput(input3));
    }

    /**
     * Tests the processInput method of the Duck with invalid delete command input.
     */
    @Test
    public void processInput_invalidDeleteCommand_exceptionThrown() {
        String input1 = "deLete 0";
        String input2 = "delEte 100000000000000000";
        String input3 = "delete         1 1";

        assertThrows(DuckException.class, () -> duck.processInput(input1));
        assertThrows(DuckException.class, () -> duck.processInput(input2));
        assertThrows(DuckException.class, () -> duck.processInput(input3));
    }

    /**
     * Tests the processInput method of the Duck with invalid event command input.
     */
    @Test
    public void processInput_invalidEventCommand_exceptionThrown() {
        String input1 = "?event test /from 2021/08/25 1800 /to 2021-08-25 1900";
        String input2 = "eVent test /from 2021/08/25 18000 /to 2021/08/25 1900";
        String input3 = "  event test /from    2021/08/25 1800 /to       2021-08-25 2500   ";
        String input4 = "event test /to 2021-08-25 1900 /from 2021-08-25 1800";
        String input5 = "event test /from 2021-08-25 1800 /to 2021-08-25 1900 /from 2021-08-25 1800";
        String input6 = "event test /to 2021-08-25 1900 /from 2021-08-25 1900";

        assertThrows(DuckException.class, () -> duck.processInput(input1));
        assertThrows(DuckException.class, () -> duck.processInput(input2));
        assertThrows(DuckException.class, () -> duck.processInput(input3));
        assertThrows(DuckException.class, () -> duck.processInput(input4));
        assertThrows(DuckException.class, () -> duck.processInput(input5));
        assertThrows(DuckException.class, () -> duck.processInput(input6));
    }

    /**
     * Tests the processInput method of the Duck with invalid find command input.
     */
    @Test
    public void processInput_invalidFindCommand_exceptionThrown() {
        String input1 = "?find";
        String input2 = "Find ";

        assertThrows(DuckException.class, () -> duck.processInput(input1));
        assertThrows(DuckException.class, () -> duck.processInput(input2));
    }

    /**
     * Tests the processInput method of the Duck with invalid list command input.
     */
    @Test
    public void processInput_invalidListCommand_exceptionThrown() {

        String input2 = "? List  1 ";
        String input3 = "?lIst 1 ?";
        String input4 = "list? 1  ";


        assertThrows(DuckException.class, () -> duck.processInput(input2));
        assertThrows(DuckException.class, () -> duck.processInput(input3));
        assertThrows(DuckException.class, () -> duck.processInput(input4));
    }

    /**
     * Tests the processInput method of the Duck with invalid mark command input.
     */
    @Test
    public void processInput_invalidMarkCommand_exceptionThrown() {
        String input1 = "mark 0";
        String input2 = "Mark 100000000000000000";
        String input3 = "mark         1 1";
        String input4 = "mark? 1  ";

        assertThrows(DuckException.class, () -> duck.processInput(input1));
        assertThrows(DuckException.class, () -> duck.processInput(input2));
        assertThrows(DuckException.class, () -> duck.processInput(input3));
        assertThrows(DuckException.class, () -> duck.processInput(input4));
    }

    /**
     * Tests the processInput method of the Duck with invalid on command input.
     */
    @Test
    public void processInput_invalidOnCommand_exceptionThrown() {
        String input1 = "on 2021/08-25";
        String input2 = "    ON 2021/08-25       ";
        String input3 = "on 2021-08-25 1800";
        String input4 = "on 2021-08-25 2021-08-25";
        String input5 = "on? 2021-08-25 1800";
        String input6 = "on 2021-08-32";

        assertThrows(DuckException.class, () -> duck.processInput(input1));
        assertThrows(DuckException.class, () -> duck.processInput(input2));
        assertThrows(DuckException.class, () -> duck.processInput(input3));
        assertThrows(DuckException.class, () -> duck.processInput(input4));
        assertThrows(DuckException.class, () -> duck.processInput(input5));
        assertThrows(DuckException.class, () -> duck.processInput(input6));
    }

    /**
     * Tests the processInput method of the Duck with invalid sort /by parameter for /target all.
     */
    @Test
    public void processInput_invalidSortCommandTargetAll_exceptionThrown() {
        String input1 = "sort /target all /by deadline";
        String input2 = "sort /target all /by start";
        String input3 = "sort /target all /by end";

        assertThrows(DuckException.class, () -> duck.processInput(input1));
        assertThrows(DuckException.class, () -> duck.processInput(input2));
        assertThrows(DuckException.class, () -> duck.processInput(input3));
    }

    /**
     * Tests the processInput method of the Duck with invalid sort /by parameter for /target todo.
     */
    @Test
    public void processInput_invalidSortCommandTargetToDo_exceptionThrown() {
        String input1 = "sort /target todo /by type";
        String input2 = "sort /target todo /by deadline";
        String input3 = "sort /target todo /by start";
        String input4 = "sort /target todo /by end";

        assertThrows(DuckException.class, () -> duck.processInput(input1));
        assertThrows(DuckException.class, () -> duck.processInput(input2));
        assertThrows(DuckException.class, () -> duck.processInput(input3));
        assertThrows(DuckException.class, () -> duck.processInput(input4));
    }

    /**
     * Tests the processInput method of the Duck with invalid sort /by parameter for /target deadline.
     */
    @Test
    public void processInput_invalidSortCommandTargetDeadline_exceptionThrown() {
        String input1 = "sort /target deadline /by type";
        String input2 = "sort /target deadline /by start";
        String input3 = "sort /target deadline /by end";

        assertThrows(DuckException.class, () -> duck.processInput(input1));
        assertThrows(DuckException.class, () -> duck.processInput(input2));
        assertThrows(DuckException.class, () -> duck.processInput(input3));
    }

    /**
     * Tests the processInput method of the Duck with invalid sort /by parameter for /target event.
     */
    @Test
    public void processInput_invalidSortCommandTargetEvent_exceptionThrown() {
        String input1 = "sort /target event /by type";
        String input2 = "sort /target event /by deadline";

        assertThrows(DuckException.class, () -> duck.processInput(input1));
        assertThrows(DuckException.class, () -> duck.processInput(input2));
    }

    /**
     * Tests the processInput method of the Duck with invalid sort command /target or /by parameters.
     */
    @Test
    public void processInput_invalidSortCommandInvalidParams_exceptionThrown() {
        String input1 = "sort /target /by";
        String input2 = "sort /by type /target";
        String input3 = "sort /target all";
        String input4 = "sort /by start";
        String input5 = "sort /target all /by ??";


        assertThrows(DuckException.class, () -> duck.processInput(input1));
        assertThrows(DuckException.class, () -> duck.processInput(input2));
        assertThrows(DuckException.class, () -> duck.processInput(input3));
        assertThrows(DuckException.class, () -> duck.processInput(input4));
        assertThrows(DuckException.class, () -> duck.processInput(input5));
    }

    /**
     * Tests the processInput method of the Duck with invalid todo command input.
     */
    @Test
    public void processInput_invalidToDoCommand_exceptionThrown() {
        String input1 = "todo";
        String input2 = "ToDo ";
        String input3 = "tOdo  ";
        String input4 = "?todo hello";

        assertThrows(DuckException.class, () -> duck.processInput(input1));
        assertThrows(DuckException.class, () -> duck.processInput(input2));
        assertThrows(DuckException.class, () -> duck.processInput(input3));
        assertThrows(DuckException.class, () -> duck.processInput(input4));
    }

    /**
     * Tests the processInput method of the Duck with invalid unmark command input.
     */
    @Test
    public void processInput_invalidUnmarkCommand_exceptionThrown() {
        String input1 = "unmark 0";
        String input2 = "Unmark 100000000000000000";
        String input3 = "unmark         a";
        String input4 = "unmark? 1  ";
        String input5 = "unmark 1 1";

        assertThrows(DuckException.class, () -> duck.processInput(input1));
        assertThrows(DuckException.class, () -> duck.processInput(input2));
        assertThrows(DuckException.class, () -> duck.processInput(input3));
        assertThrows(DuckException.class, () -> duck.processInput(input4));
        assertThrows(DuckException.class, () -> duck.processInput(input5));
    }


}

