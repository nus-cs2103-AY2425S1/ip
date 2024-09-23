package yappingbot.ui.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import yappingbot.TestHelper;

class UiCliTest {

    final UiCli ui = new UiCli();

    @Test
    void testPrintError() throws IOException {

        final String expected =  " |  Error message here!\n"
                                 + " |  Multiline Error Message\n";
        TestHelper h = new TestHelper();
        h.captureStdOut();
        ui.printError("Error message here!\nMultiline Error Message");
        h.stopCapture();
        assertEquals(expected, h.toString());
    }

    @Test
    void testPrint() throws IOException {
        final String expected =
                " |  test 1\n"
                + "\n"
                + " |\n"
                + "\n"
                + " |  multiline\n"
                + " |  Allowed\n"
                + "\n"
                + " |  This is a very very very very longggggg line qwertyqwertyqwertyqwerty123\n"
                + " |  4567890qwerty1234567890qwerty1234567890qwerty1234567890qwertyqwerty12345\n"
                + " |  67890qwerty1234567890qwerty1234567890qwerty1234567890 Random space here\n"
                + " |  qwerty1234567890qwerty1234567890qwerty1234567890qwerty1234567890djalkdja\n"
                + " |  lskdjaslkdjasldjas\n"
                + "\n";

        TestHelper h = new TestHelper();
        h.captureStdOut();
        ui.print("test 1");
        ui.print("");
        ui.print("multiline\nAllowed");
        ui.print("This is a very very very very longggggg line "
                 + "qwertyqwertyqwertyqwerty1234567890qwerty1234567890qwerty1234567890qwerty123456"
                 + "7890qwertyqwerty1234567890qwerty1234567890qwerty1234567890qwerty1234567890 "
                 + "Random space here qwerty1234567890qwerty1234567890qwerty1234567890qwerty123456"
                 + "7890djalkdjalskdjaslkdjasldjas");
        h.stopCapture();
        assertEquals(expected, h.toString());
    }

    @Test
    void testPrintln() throws IOException {
        final String expected = " |  test 1\n"
                                + "\n"
                                + " |\n"
                                + "\n"
                                + " |  multilineprevented\n"
                                + "\n";
        TestHelper h = new TestHelper();
        h.captureStdOut();
        ui.println("test 1");
        ui.println("");
        ui.println("multiline\nprevented");
        h.stopCapture();
        assertEquals(expected, h.toString());
    }
}