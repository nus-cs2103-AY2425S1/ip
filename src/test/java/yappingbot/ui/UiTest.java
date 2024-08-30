package yappingbot.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class UiTest {

    @Test
    void testQuoteSinglelineText() {
        StringBuilder sb = new StringBuilder();
        Ui.quoteSinglelineText("Test", sb);
        Ui.quoteSinglelineText("Test2", sb);
        Ui.quoteSinglelineText("", sb);
        Ui.quoteSinglelineText("multiline\naccident", sb);
        String expected =
 """
|  Test
|  Test2
|
|  multilineaccident""";
        assertEquals(expected, sb.toString());
    }

    @Test
    void testPrintError() {
    }

    @Test
    void testPrint() {
    }

    @Test
    void testPrintln() {
    }
}