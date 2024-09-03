package meeju;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    private TaskList taskList = new TaskList(new StorageStub());
    private Parser parser = new Parser();


    //CHECKSTYLE.OFF: MethodName
    @Test
    public void bye_Success() {
        assertEquals(-1, parser.parse(taskList, "bye"));
    }
    @Test
    public void incorrectCommand_Success() {
        assertEquals(5, parser.parse(taskList, "this is an invalid command!"));
    }
    //CHECKSTYLE.ON: MethodName
}
