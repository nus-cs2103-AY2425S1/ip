package meeju;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    TaskList taskList = new TaskList(new StorageStub());
    Parser parser = new Parser();


    @Test
    public void bye_Success(){
        assertEquals(-1, parser.parse(taskList, "bye"));
    }
    @Test
    public void incorrectCommand_Success(){
        assertEquals(5, parser.parse(taskList, "this is an invalid command!"));
    }
}