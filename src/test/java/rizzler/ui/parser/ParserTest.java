package rizzler.ui.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import rizzler.command.EventCommand;

public class ParserTest {

    @Test
    public void parseInput_todoWithoutDesc_nullCommandText() {
        assertEquals("you have to let me know what task you have to do, dearie",
                new Parser().parseInput("todo").getTextInput());
    }

    @Test
    public void parseInput_list_noOutput() {
        assertEquals("",
                new Parser().parseInput("list").getTextInput());
    }

    @Test
    public void parseInput_bye_noOutput() {
        assertEquals("",
                new Parser().parseInput("bye").getTextInput());
    }

    @Test
    public void parseInput_properEvent_event() {
        assertInstanceOf(EventCommand.class,
                new Parser().parseInput("event this is a test event /from 1999-09-11 /to 2000-05-29"));
    }

    @Test
    public void parseInput_eventWithoutFrom_nullError() {
        assertEquals("remember to include a \"/from [start]\" for this event!",
                new Parser().parseInput("event testEvent /to date").getTextInput());
    }


}
