package meerkatpack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void toParseableString_taskNameEat_tueat() {
        Todo todoTask = new Todo("eat");
        String outputName = todoTask.toParseableString();
        assertEquals("t,u,eat", outputName);
    }
}
