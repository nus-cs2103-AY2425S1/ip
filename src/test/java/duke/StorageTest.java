package duke;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void load_validInput_success() throws ParseException, FileNotFoundException {
        //Arrange
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(new DeadLine("return book", Parser.parseDate("02-12-2019 1800")));
        expected.add(new Event("project meeting",
                Parser.parseDate("02-12-2019 1800"),
                Parser.parseDate("24-12-2019 1800")));
        expected.add(new ToDo("join sports club"));

        //Act
        ArrayList<Task> result = new Storage("data/test.txt").load();

        //Assert
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i).toString(), result.get(i).toString());
        }

    }
}
