package noisy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    Parser parser = new Parser();
    @Test
    public void deadlineTest() {
        assertEquals(new Deadline("test", true, parser.parseDate("2019-10-15")).formatTask(), "D | test | 1 | " + parser.parseDate("2019-10-15"));
    }
}
