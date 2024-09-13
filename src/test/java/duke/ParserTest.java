package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void ParseString_toDoInput_success() {
        //Arrange
        ArrayList<String> commands = new ArrayList<>();
        commands.add("todo");
        String todo = "todo get";
        String [] expected = new String[] {"","get"};

        //Act
        String[] result = Parser.ParseString(todo, commands);

        //Assert
        for (int i = 0; i < expected.length; i++) {
           assertEquals(expected[i], result[i]);
        }
    }

    @Test
    public void ParseString_deadLineInput_success() {
        //Arrange
        ArrayList<String> commands = new ArrayList<>();
        commands.add("deadline");
        commands.add("/by");
        String todo = "deadline get /by 24-03-2020 1700";
        String [] expected = new String[] {"","get", "24-03-2020 1700"};

        //Act
        String[] result = Parser.ParseString(todo, commands);

        //Assert
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result[i]);
        }
    }

    @Test
    public void ParseString_EventInput_success() {
        //Arrange
        ArrayList<String> commands = new ArrayList<>();
        commands.add("event");
        commands.add("/from");
        commands.add("/to");
        String todo = "event get /from 24-03-2020 1700 /to 26-03-2020 1700";
        String [] expected = new String[] {"","get", "24-03-2020 1700", "26-03-2020 1700"};

        //Act
        String[] result = Parser.ParseString(todo, commands);

        //Assert
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], result[i]);
        }
    }
}
