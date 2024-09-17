package yapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class TaskListTest {

    @Test
    public void stringToDateTimeTest0() {
        String dateTime = "2024/12/31";
        LocalDateTime actual = TaskList.convertStringToDateTime(dateTime, 0);
        LocalDateTime expected = LocalDateTime.of(2024, 12, 31, 00, 00);
        assertEquals(expected, actual);
    }

    @Test
    public void stringToDateTimeTest1() {
        String dateTime = "2024/12/31";
        LocalDateTime actual = TaskList.convertStringToDateTime(dateTime, 1);
        LocalDateTime expected = LocalDateTime.of(2024, 12, 31, 23, 59);
        assertEquals(expected, actual);
    }

    @Test
    public void stringToDateTimeTestWithTime() {
        String dateTime = "2024/12/31 1800";
        LocalDateTime actual = TaskList.convertStringToDateTime(dateTime, 0);
        LocalDateTime expected = LocalDateTime.of(2024, 12, 31, 18, 00);
        assertEquals(expected, actual);
    }
}
