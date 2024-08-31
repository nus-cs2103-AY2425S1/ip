package sinatra;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void newObjectFromDataTest(){
        String input = "hi,true";
       ToDo output =  sinatra.ToDo.newObjectFromData(input);
        ToDo actual =  new sinatra.ToDo("hi",true);
        assertEquals(output.getContent(), actual.getContent());
        assertEquals(output.isMarked(),output.isMarked());
    
    }

    @Test
    public void toStringTest(){
        String input = "[T][ ] sing";
        ToDo actual =  new sinatra.ToDo("sing",false);
        assertEquals(input, actual.toString());
    }
}