package yappingbot.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import yappingbot.TestHelper;

class UiCliTest {

    UiCli ui = new UiCli();

    @Test
    void testPrintError() throws IOException {
        final String expected =
                """
                |  Error message here!
                |  Multiline Error Message
                """;
        TestHelper h = new TestHelper();
        h.captureStdOut();
        ui.printError("Error message here!\nMultiline Error Message");
        h.stopCapture();
        assertEquals(expected, h.toString());
    }

    @Test
    void testPrint() throws IOException {
        final String expected =
                """
                |  test 1
                |
                |  multiline
                |  Allowed
                        """;
        TestHelper h = new TestHelper();
        h.captureStdOut();
        ui.print("test 1");
        ui.print("");
        ui.print("multiline\nAllowed");
        h.stopCapture();
        assertEquals(expected, h.toString());
    }

    @Test
    void testPrintln() throws IOException {
        final String expected =
                """
                |  test 1
                |
                |  multilineprevented
                        """;
        TestHelper h = new TestHelper();
        h.captureStdOut();
        ui.println("test 1");
        ui.println("");
        ui.println("multiline\nprevented");
        h.stopCapture();
        assertEquals(expected, h.toString());
    }
}