package nen.utils;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import nen.commands.DeleteCommand;

public class ParserTest {

    @Test
    public void getIndexTest() {
        Parser parser = new Parser(new TaskList(new String[]{"D/false/blyat/2024-08-01"}));
        assertInstanceOf(DeleteCommand.class, parser.parse("delete 1"));
    }

}
