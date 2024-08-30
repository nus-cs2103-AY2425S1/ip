package evan.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void encodeAsString_validInputs_success() {
        // by: STRING
        Deadline d1 = new Deadline("Deadline description", "tomorrow");
        assertEquals("D | 0 | Deadline description | tomorrow", d1.encodeAsString());
        d1.markAsDone();
        assertEquals("D | 1 | Deadline description | tomorrow", d1.encodeAsString());

        // by: DATE
        Deadline d2 = new Deadline("Deadline description", "2024-05-02");
        assertEquals("D | 0 | Deadline description | 02 May 2024", d2.encodeAsString());
        d2.markAsDone();
        assertEquals("D | 1 | Deadline description | 02 May 2024", d2.encodeAsString());

        // by: DATETIME
        Deadline d3 = new Deadline("Deadline description", "2024-05-02 2009");
        assertEquals("D | 0 | Deadline description | 02 May 2024, 08:09 pm", d3.encodeAsString());
        d3.markAsDone();
        assertEquals("D | 1 | Deadline description | 02 May 2024, 08:09 pm", d3.encodeAsString());
    }
}
