package com.nimbus;

import com.nimbus.InvalidArgumentException;
import com.nimbus.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void readOption_NormalInput_readSuccess() {
        try {
            String tmp = "A very very long string /with /some Hello World /option ";
            assertEquals(Parser.readOption(tmp, "some"), "Hello World");
            assertEquals(Parser.readOption(tmp, "with"), "");
        } catch (InvalidArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}