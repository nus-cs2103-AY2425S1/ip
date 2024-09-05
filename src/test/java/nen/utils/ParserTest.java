package nen.utils;

import nen.commands.DeleteCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ParserTest {

    @Test
    public void getIndexTest() {
        Parser parser = new Parser(new TaskList(new String[]{"D/false/blyat/2024-08-01"}));
        assertInstanceOf(DeleteCommand.class, parser.parse("delete 1"));
    }

}

