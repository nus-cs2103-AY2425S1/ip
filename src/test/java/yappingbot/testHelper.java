package yappingbot;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class testHelper {
    final ByteArrayOutputStream uiPrintOut = new ByteArrayOutputStream();
    public void captureStdOut() {
        System.setOut(new PrintStream(uiPrintOut));
    }
    public void stopCapture() throws IOException {
        System.setOut(System.out);
        uiPrintOut.close();
    }

    public String getCapturedOutput() {
        return uiPrintOut.toString();
    }
}
