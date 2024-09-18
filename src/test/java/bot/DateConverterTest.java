package bot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateConverterTest {

    @Test
    public void testConvertDateTimeValidFormat() {
        String output1 = DateConverter.convertDate("1/12/2000 1600");
        assertEquals("1st of december 2000, 4:00 pm", output1);

        String output2 = DateConverter.convertDate("15/8/1995 0930");
        assertEquals("15th of august 1995, 9:30 am", output2);
    }

    @Test
    public void testConvertDateTimeInvalidFormat() {
        String output1 = DateConverter.convertDate("1/122/2000 1600");
        assertEquals("Invalid date format!", output1);

        String output2 = DateConverter.convertDate("1/12/2000 160");
        assertEquals("Invalid date format!", output2);

        String output3 = DateConverter.convertDate("2000-12-12 1600");
        assertEquals("Invalid date format!", output3);
    }

    @Test
    public void testConvertDateValidFormat() {
        String output1 = DateConverter.convertDate("2000-12-12");
        assertEquals("Dec 12 2000", output1);

        String output2 = DateConverter.convertDate("1995-08-15");
        assertEquals("Aug 15 1995", output2);
    }

    @Test
    public void testConvertDateInvalidFormat() {
        String output1 = DateConverter.convertDate("2000/12/12");
        assertEquals("Invalid date format!", output1);

        String output2 = DateConverter.convertDate("12-12-2000");
        assertEquals("Invalid date format!", output2);

        String output3 = DateConverter.convertDate("12-2000-12");
        assertEquals("Invalid date format!", output3);
    }
}
