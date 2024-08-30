package Kayo;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class KayoTests {
    @Test
    public void storage_invalidPath_success(){
        try{
            Kayo.Storage storage = new Kayo.Storage("wrongFilepath");
            List<Kayo.Task> tasks = storage.load();
            assertEquals(0,tasks.size());
        } catch (RuntimeException e) {
            fail();
        }
    }
    @Test
    public void deadline_incorrectFormat_fail(){
        try{
            Kayo.Deadline deadline = new Kayo.Deadline("myTask",false, "wrong date format");
            fail();
        } catch (RuntimeException e) {
            //Pass test case
        }
    }
}