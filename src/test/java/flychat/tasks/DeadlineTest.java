package flychat.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.InputMismatchException;

import org.junit.jupiter.api.Test;

import flychat.Deadline;

public class DeadlineTest {

    @Test
    void testCreateNewDeadline_withValidDescriptionAndDeadlineAndUnmarked() {
        String description = "Assignment 1";
        String deadlineText = "2024-11-11";
        boolean isMarked = false;

        Deadline newDeadline = Deadline.createNewDeadline(description, deadlineText, isMarked);
        assertEquals("[D][ ] Assignment 1 (by: 11 11 2024)", newDeadline.toString(),
                "The deadline task should be created with the correct description, end date and unmarked");
    }

    @Test
    void testCreateNewDeadline_withValidDescriptionAndDeadlineAndMarked() {
        String description = "Assignment 1";
        String deadlineText = "2024-11-11";
        boolean isMarked = true;

        Deadline newDeadline = Deadline.createNewDeadline(description, deadlineText, isMarked);
        assertEquals("[D][X] Assignment 1 (by: 11 11 2024)", newDeadline.toString(),
                "The deadline task should be created with the correct description, end date and marked");
    }

    @Test
    void testCreateNewDeadline_withEmptyDescriptionAndValidDeadline_ExceptionThrown() {
        String description = "";
        String deadlineText = "2024-11-11";
        boolean isMarked = false;
        InputMismatchException exception = assertThrows(InputMismatchException.class, () -> {
            Deadline.createNewDeadline(description, deadlineText, isMarked);
        });
        assertEquals("Please ensure that input contains a description TT", exception.getMessage(),
                "The exception message should match the expected output");
    }

    @Test
    void testCreateNewDeadline_withValidDescriptionAndInvalidDeadline_ExceptionThrown() {
        String description = "Assignment 1";
        String deadlineText = "2024-15-11";
        boolean isMarked = false;
        InputMismatchException exception = assertThrows(InputMismatchException.class, () -> {
            Deadline.createNewDeadline(description, deadlineText, isMarked);
        });
        assertEquals("Please ensure that input contains end date formatted in the yyyy-mm-dd format",
                exception.getMessage(), "The exception message should match the expected output");
    }

    @Test
    void testCreateNewDeadline_withValidDescriptionAndEmptyDeadline_ExceptionThrown() {
        String description = "Assignment 1";
        String deadlineText = "";
        boolean isMarked = false;
        InputMismatchException exception = assertThrows(InputMismatchException.class, () -> {
            Deadline.createNewDeadline(description, deadlineText, isMarked);
        });
        assertEquals("Please ensure that input contains end date formatted in the yyyy-mm-dd format",
                exception.getMessage(), "The exception message should match the expected output");
    }
}
