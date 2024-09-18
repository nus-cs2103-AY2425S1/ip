package yapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import yapper.components.Event;
import yapper.exceptions.YapperException;

public class EventTest {

    @Test
    public void testGetDesc() {
        assertEquals("| E |   | meet friends | 06:00 PM ----- 08:00 PM",
                new Event("meet friends", "1800", "2000").getDesc());
        assertEquals("| E |   | this | DEC 2 2019 ----- DEC 2 2019",
                new Event("this", "2/12/2019", "2/12/2019").getDesc());
        assertEquals("| E |   | that | DEC 2 2019 ----- JAN 31 2020",
                new Event("that", "2019/12/2", "2020/1/31").getDesc());
        assertEquals("| E |   | bake cakes | DEC 2 2019 06:00 AM ----- DEC 3 2019 11:59 PM",
                new Event("bake cakes", "2/12/2019 0600", "3/12/2019 2359").getDesc());
        assertEquals("| E |   | fight | this Sunday ----- next Sunday",
                new Event("fight", "this Sunday", "next Sunday").getDesc());
    }

    @Test
    public void testGetDesc_emptyString_exceptionThrown() {
        try {
            assertEquals("| E |   |   | from ----- to", new Event("", "from", "to").getDesc());
            System.out.println("Fail");
        } catch (YapperException e) {
            assertEquals("Please provide a description", e.getMessage());
        }

        try {
            assertEquals("| E |   |   | ", new Event("", "from", "").getDesc());
            System.out.println("Fail");
        } catch (YapperException e) {
            assertEquals("Please provide a description", e.getMessage());
        }

        try {
            assertEquals("| E |   |   | ", new Event("", "", "to").getDesc());
            System.out.println("Fail");
        } catch (YapperException e) {
            assertEquals("Please provide a description", e.getMessage());
        }

        try {
            assertEquals("| E |   | desc | ", new Event("desc", "", "to").getDesc());
            System.out.println("Fail");
        } catch (YapperException e) {
            assertEquals("Empty start/end dates given", e.getMessage());
        }

        try {
            assertEquals("| E |   | desc | ", new Event("desc", "from", "").getDesc());
            System.out.println("Fail");
        } catch (YapperException e) {
            assertEquals("Empty start/end dates given", e.getMessage());
        }

        try {
            assertEquals("| E |   | desc | ", new Event("desc", "", "to").getDesc());
            System.out.println("Fail");
        } catch (YapperException e) {
            assertEquals("Empty start/end dates given", e.getMessage());
        }
    }

    @Test
    public void testStringConversion() {
        assertEquals("[E][ ] meet friends (from: 06:00 PM to: 08:00 PM)",
                new Event("meet friends", "1800", "2000").toString());
        assertEquals("[E][ ] this (from: DEC 2 2019 to: DEC 2 2019)",
                new Event("this", "2/12/2019", "2/12/2019").toString());
        assertEquals("[E][ ] that (from: DEC 2 2019 to: JAN 31 2020)",
                new Event("that", "2019/12/2", "2020/1/31").toString());
        assertEquals("[E][ ] bake cakes (from: DEC 2 2019 06:00 PM to: DEC 3 2019 11:59 PM)",
                new Event("bake cakes", "2/12/2019 1800", "3/12/2019 2359").toString());
        assertEquals("[E][ ] fight (from: this Sunday to: next Sunday)",
                new Event("fight", "this Sunday", "next Sunday").toString());
    }

    @Test
    public void testMarkAndUnMark() {
        Event eventToTest = new Event("meet friends", "1800", "2000");
        // Mark event
        eventToTest.mark();
        assertEquals("[E][X] meet friends (from: 06:00 PM to: 08:00 PM)", eventToTest.toString());
        assertEquals("| E | X | meet friends | 06:00 PM ----- 08:00 PM", eventToTest.getDesc());
        // Unmark event
        eventToTest.unmark();
        assertEquals("[E][ ] meet friends (from: 06:00 PM to: 08:00 PM)", eventToTest.toString());
        assertEquals("| E |   | meet friends | 06:00 PM ----- 08:00 PM", eventToTest.getDesc());
    }
}
