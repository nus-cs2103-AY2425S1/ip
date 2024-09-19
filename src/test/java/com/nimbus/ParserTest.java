package com.nimbus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.exceptions.InvalidArgumentException;

public class ParserTest {
    @Test
    public void readOption_normalInput_readSuccess() {
        try {
            String tmp = "A very very long string /with /some Hello World /option ";
            assertEquals(Parser.readOption(tmp, "some"), "Hello World");
            assertEquals(Parser.readOption(tmp, "with"), "");
        } catch (InvalidArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
