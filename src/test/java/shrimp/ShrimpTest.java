package shrimp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shrimp.exception.ShrimpException;
import shrimp.task.TaskList;
import shrimp.utility.Parser;
import shrimp.utility.Ui;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ShrimpTest {

    private Ui ui;
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        ui = new Ui();
        taskList = new TaskList();
    }

    @Test
    void testGetTaskNumberValidInput() throws ShrimpException {
        String userInput = "mark 2";
        int taskNumber = Shrimp.getTaskNumber(userInput, Parser.CommandType.MARK);
        assertEquals(1, taskNumber); // Task numbers are zero-indexed
    }

    @Test
    void testGetTaskNumberInvalidInput() {
        String userInput = "mark invalid";
        ShrimpException exception = assertThrows(ShrimpException.NumberFormatException.class, () -> {
            Shrimp.getTaskNumber(userInput, Parser.CommandType.MARK);
        });
        String expectedMessage = "Your values seems wrong, maybe try again?";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testGetTaskNumberMissingArgument() {
        String userInput = "mark";
        ShrimpException exception = assertThrows(ShrimpException.MissingArgumentException.class, () -> {
            Shrimp.getTaskNumber(userInput, Parser.CommandType.MARK);
        });
        String expectedMessage = "You didn't indicate which task to mark...";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testGetDateTimeValidInput() throws Exception {
        String dateTimeInput = "2/12/2019 1800";
        LocalDateTime expectedDateTime = LocalDateTime.of(2019, 12, 2, 18, 0);
        assertEquals(expectedDateTime, Shrimp.getDateTime(dateTimeInput));
    }

    @Test
    void testGetDateTimeInvalidInput() {
        String dateTimeInput = "invalid";
        Exception exception = assertThrows(ShrimpException.InvalidDateTimeException.class, () -> {
            Shrimp.getDateTime(dateTimeInput);
        });
        String expectedMessage = "The datetime format you inserted is wrong (DD/MM/YYYY)... ";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
