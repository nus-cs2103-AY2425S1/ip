package yappingbot.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import yappingbot.TestHelper;

class UiTest {

    @Test
    void testQuoteSinglelineText() {
        StringBuilder sb = new StringBuilder();
        Ui.quoteSinglelineText("Test", sb);
        Ui.quoteSinglelineText("Test2", sb);
        Ui.quoteSinglelineText("", sb);
        Ui.quoteSinglelineText("multiline\naccident", sb);
        final String expected =
                """
               |  Test
               |  Test2
               |
               |  multilineaccident
               """;
        assertEquals(expected, sb.toString());
    }

    @Test
    void testPrintError() throws IOException {
        final String expected =
                """
                |  Error message here!
                |  Multiline Error Message
                """;
        TestHelper h = new TestHelper();
        h.captureStdOut();
        Ui.printError("Error message here!\nMultiline Error Message");
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
        Ui.print("test 1");
        Ui.print("");
        Ui.print("multiline\nAllowed");
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
        Ui.println("test 1");
        Ui.println("");
        Ui.println("multiline\nprevented");
        h.stopCapture();
        assertEquals(expected, h.toString());
    }
}