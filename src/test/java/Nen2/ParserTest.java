package Nen2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void getIndexTest(){
        Parser parser = new Parser(new TaskList(new String[]{"D/false/blyat/2024-08-01"}), new Ui());
        assertEquals(parser.parseInput("delete 1"), true);
    }

}

