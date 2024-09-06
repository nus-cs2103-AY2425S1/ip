package froggy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void toString_date_correctParse() {

        // Recognise date
        assertEquals("[D][ ] test (by: Nov 13 2024)", new Deadline("test", "2024/11/13").toString());

        // Month over 12 not detected
        assertEquals("[D][ ] test (by: 2024/13/11)", new Deadline("test", "2024/13/11").toString());

        // Month not double-digit not detected
        assertEquals("[D][ ] test (by: 2024/3/11)", new Deadline("test", "2024/3/11").toString());

        // Date format not recognised
        assertEquals("[D][ ] test (by: 2024.13.11)", new Deadline("test", "2024.13.11").toString());
    }

    public void toTxt_description_noDateParse() throws Exception {

        // Correct Date format but still printed normally
        assertEquals("D 0 test | 2024/11/13", new Deadline("test", "2024/11/13").toString());

        // Wrong Date format
        assertEquals("D 0 test | 2024/13/11", new Deadline("test", "2024/13/11").toString());

        // Normal by input
        assertEquals("D 0 test | test", new Deadline("test", "test").toString());
    }
}
