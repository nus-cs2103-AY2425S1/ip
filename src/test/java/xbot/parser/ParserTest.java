package xbot.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testEmpty(){
        assertEquals(false, Parser.isValidDateFormat(""));
    }

    @Test
    public void test1(){
        assertEquals(true, Parser.isValidDateFormat("05/02/2024"));
    }

    @Test
    public void test2(){
        assertEquals(true, Parser.isValidDateFormat("5/2/2024"));
    }

    @Test
    public void test3(){
        assertEquals(false, Parser.isValidDateFormat("5/2/24"));
    }

    @Test
    public void test4(){
        assertEquals(true, Parser.isValidDateFormat("30/2/2024"));
    }

    @Test
    public void test5(){
        assertEquals(true, Parser.isValidDateFormat("5/2/2024 0924"));
    }

    @Test
    public void after24Hours(){
        assertEquals(false, Parser.isValidDateFormat("5/2/2024 2450"));
    }

    @Test
    public void after60Minutes(){
        assertEquals(false, Parser.isValidDateFormat("5/2/2024 1060"));
    }

    @Test
    public void differentFormat(){
        assertEquals(false, Parser.isValidDateFormat("5/2/2024 6pm"));
    }

    @Test
    public void test6(){
        assertEquals(true, Parser.isValidDateFormat("2024-05-06"));
    }

    @Test
    public void test7(){
        assertEquals(false, Parser.isValidDateFormat("06-05-2024"));
    }

    @Test
    public void test8(){
        assertEquals(false, Parser.isValidDateFormat("2024-5-6"));
    }
}
