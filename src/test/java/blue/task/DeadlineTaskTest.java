package blue.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTaskTest {
    @Test
    public void testAdd() {
        DeadlineTask deadlineTask = new DeadlineTask("return book", "12/9/2024 1800");
        String result = deadlineTask.toFileString();
        assertEquals("D | 0 | return book | 12/9/2024 1800", result);
    }
    @Test
    public void testAdd2(){
        DeadlineTask deadlineTask = new DeadlineTask("CS2105 Assignment", "5/9/2024 2359");
        String result = deadlineTask.toFileString();
        assertEquals("D | 0 | CS2105 Assignment | 5/9/2024 2359", result);
    }
}

