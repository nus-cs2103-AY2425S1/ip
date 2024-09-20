package yappingbot.ui.gui;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

class UiGuiTest {
    @Test
    void testPrintOutputSync() {
        UiGui ui = new UiGui();
        String inputs = "Test String\nTest newline";
        String expected = "Test String\nTest newline";

        ui.printError(inputs);
        String result = ui.getNextOutputLine();
        assertEquals(expected, result);
    }

    @Test
    void testPrintlnOutputSync() {
        UiGui ui = new UiGui();

        String input1 = "Test String\nTest newline";
        String input2 = "Test String2";

        ui.println(input1);
        ui.println(input2);
        String result1 = ui.getNextOutputLine();
        String result2 = ui.getNextOutputLine();

        String expected1 = "Test String\nTest newline";
        String expected2 = "Test String2";
        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
    }

    @Test
    void testPrintfOutputSync() {
        UiGui ui = new UiGui();

        String input1 = "Test %s String\nTest %d number";
        String input2 = "Test String2 %s";

        ui.printfError(input1, "abc", 123);
        ui.printfError(input2, "doremi");
        String result1 = ui.getNextOutputLine();
        String result2 = ui.getNextOutputLine();

        String expected1 = "Test abc String\nTest 123 number";
        String expected2 = "Test String2 doremi";
        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
    }

    @Test
    void testPrintErrorOutputSync() {
        UiGui ui = new UiGui();

        String input1 = "Test String\nTest newline";
        String input2 = "Test String2";

        ui.printError(input1);
        ui.printError(input2);
        String result1 = ui.getNextOutputLine();
        String result2 = ui.getNextOutputLine();

        String expected1 = "Test String\nTest newline";
        String expected2 = "Test String2";
        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
    }

    @Test
    void testPrintfErrorOutputSync() {
        UiGui ui = new UiGui();

        String input1 = "Test %s String\nTest %d number";
        String input2 = "Test String2 %s";

        ui.printfError(input1, "abc", 123);
        ui.printfError(input2, "doremi");
        String result1 = ui.getNextOutputLine();
        String result2 = ui.getNextOutputLine();

        String expected1 = "Test abc String\nTest 123 number";
        String expected2 = "Test String2 doremi";
        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
    }

    @Test
    void testPushInputLineInputSync() {
        UiGui ui = new UiGui();

        String input1 = "Test String\nTest newline";
        String input2 = "Test String2";

        ui.pushInputLine(input1);
        ui.pushInputLine(input2);
        String result1 = ui.getNextInputLine();
        String result2 = ui.getNextInputLine();

        String expected1 = "Test String\nTest newline";
        String expected2 = "Test String2";
        assertEquals(expected1, result1);
        assertEquals(expected2, result2);
    }

    // Async

    @Test
    void testPrintOutputAsync() throws InterruptedException {
        UiGui ui = new UiGui();
        String[] inputs = {
            "Test String\nTest newline",
            "Test String2, test test test"
        };
        String[] expected = {
            "Test String\nTest newline",
            "Test String2, test test test"
        };
        ArrayList<String> results = new ArrayList<>();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Thread t = new Thread(() -> {
            for (int i = 0; ui.hasOutputLines(); i++) {
                results.add(ui.getNextOutputLine());
                if (i == (expected.length - 1)) {
                    ui.setProgramClose(true);
                }
            }
        });

        Arrays.stream(inputs).forEach(ui::printError);

        executor.execute(t);
        executor.shutdown();
        assertTrue(executor.awaitTermination(30, TimeUnit.SECONDS)); // Timeout of 1 minute.
        assertArrayEquals(expected, results.toArray());
    }

    @Test
    void testPrintlnOutputAsync() throws InterruptedException {
        UiGui ui = new UiGui();
        String[] inputs = {
            "Test String\nTest newline",
            "Test String2, test test test"
        };
        String[] expected = {
            "Test String\nTest newline",
            "Test String2, test test test"
        };
        ArrayList<String> results = new ArrayList<>();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Thread t = new Thread(() -> {
            for (int i = 0; ui.hasOutputLines(); i++) {
                results.add(ui.getNextOutputLine());
                if (i == (expected.length - 1)) {
                    ui.setProgramClose(true);
                }
            }
        });

        Arrays.stream(inputs).forEach(ui::println);

        executor.execute(t);
        executor.shutdown();
        assertTrue(executor.awaitTermination(30, TimeUnit.SECONDS)); // Timeout of 1 minute.
        assertArrayEquals(expected, results.toArray());
    }

    @Test
    void testPrintfOutputAsync() throws InterruptedException {
        UiGui ui = new UiGui();
        HashMap<String, Object[]> inputs = new HashMap<>();
        inputs.put("Test %s String\nTest %d number", new Object[]{"abc", 123});
        inputs.put("Test String2 %s", new Object[]{"doremi"});

        String[] expected = {
            "Test abc String\nTest 123 number",
            "Test String2 doremi"
        };

        ArrayList<String> results = new ArrayList<>();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Thread t = new Thread(() -> {
            for (int i = 0; ui.hasOutputLines(); i++) {
                results.add(ui.getNextOutputLine());
                if (i == (expected.length - 1)) {
                    ui.setProgramClose(true);
                }
            }
        });
        inputs.keySet().forEach(s -> ui.printf(s, inputs.get(s)));

        executor.execute(t);
        executor.shutdown();
        assertTrue(executor.awaitTermination(30, TimeUnit.SECONDS)); // Timeout of 1 minute.
        assertArrayEquals(expected, results.toArray());
    }

    @Test
    void testPrintErrorOutputAsync() throws InterruptedException {
        UiGui ui = new UiGui();
        String[] inputs = {
            "Test String\nTest newline",
            "Test String2, test test test"
        };
        String[] expected = {
            "Test String\nTest newline",
            "Test String2, test test test"
        };
        ArrayList<String> results = new ArrayList<>();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Thread t = new Thread(() -> {
            for (int i = 0; ui.hasOutputLines(); i++) {
                results.add(ui.getNextOutputLine());
                if (i == (expected.length - 1)) {
                    ui.setProgramClose(true);
                }
            }
        });

        Arrays.stream(inputs).forEach(ui::println);

        executor.execute(t);
        executor.shutdown();
        assertTrue(executor.awaitTermination(30, TimeUnit.SECONDS)); // Timeout of 1 minute.
        assertArrayEquals(expected, results.toArray());
    }

    @Test
    void testPrintfErrorOutputAsync() throws InterruptedException {
        UiGui ui = new UiGui();
        HashMap<String, Object[]> inputs = new HashMap<>();
        inputs.put("Test %s String\nTest %d number", new Object[]{"abc", 123});
        inputs.put("Test String2 %s", new Object[]{"doremi"});

        String[] expected = {
            "Test abc String\nTest 123 number",
            "Test String2 doremi"
        };

        ArrayList<String> results = new ArrayList<>();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Thread t = new Thread(() -> {
            for (int i = 0; ui.hasOutputLines(); i++) {
                results.add(ui.getNextOutputLine());
                if (i == (expected.length - 1)) {
                    ui.setProgramClose(true);
                }
            }
        });
        inputs.keySet().forEach(s -> ui.printf(s, inputs.get(s)));

        executor.execute(t);
        executor.shutdown();
        assertTrue(executor.awaitTermination(30, TimeUnit.SECONDS)); // Timeout of 1 minute.
        assertArrayEquals(expected, results.toArray());
    }

    @Test
    void testPushInputLineInputAsync() throws InterruptedException {
        UiGui ui = new UiGui();
        String[] inputs = {
            "Test String\nTest newline",
            "Test String2, test test test"
        };
        String[] expected = {
            "Test String\nTest newline",
            "Test String2, test test test"
        };
        ArrayList<String> results = new ArrayList<>();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Thread t = new Thread(() -> {
            for (int i = 0; ui.hasInputLines(); i++) {
                results.add(ui.getNextInputLine());
                if (i == (expected.length - 1)) {
                    ui.setProgramClose(true);
                }
            }
        });

        Arrays.stream(inputs).forEach(ui::pushInputLine);

        executor.execute(t);
        executor.shutdown();
        assertTrue(executor.awaitTermination(30, TimeUnit.SECONDS)); // Timeout of 1 minute.
        assertArrayEquals(expected, results.toArray());
    }
}