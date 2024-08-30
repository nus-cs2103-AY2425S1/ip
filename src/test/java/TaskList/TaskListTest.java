/*
package TaskList;

import Parser.DateParser;
import Parser.Parser;
import Exceptions.DelphiException;
import Storage.Storage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

public class TaskListTest {
    class StorageStub extends Storage {
         @Override
         public List<String> readFromHardDisk() throws IOException{
             List<String> res = new ArrayList<>();
             res.add("[D][ ] do hw (by: 30th august 2024, 4:00pm)");
             res.add("[T][ ] get food");
             res.add("[T][X] go for run");
             return res;
        }
    }
    class ParserStub extends Parser {

    }
     TaskList testTaskList = new TaskList();
    @Test
    public void loadStorageToTasks() {
        StorageStub s = new StorageStub();
        testTaskList.loadStorageToTasks(s);
        assertEquals();
    }
}
*/
