package astor.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import astor.Storage;
import astor.TaskList;
import astor.Ui;
import astor.exception.EmptyTaskInfoException;
import astor.task.Task;


public class TodoCommandTest {

    public class StubUi extends Ui {
        @Override
        public void showOutput(String output) {
            System.out.println(output);
        }
    }

    public class StubStorage extends Storage {
        public StubStorage() {
            super();
        }
    }

    public class StubTaskList extends TaskList {
        public StubTaskList() {
            super();
        }


        @Override
        public String addTask(Task task, Storage storage) {
            String output = "Got it. I've added this task:\n  "
                    + task.toString() + "\nNow you have " + 1 + " tasks in the list.";
            return output;
        }



        @Override
        public int size() {
            return 1;
        }
    }

    private String normalizeLineEndings(String input) {
        return input.replace("\r\n", "\n"); // Normalize Windows line endings to Unix
    }

    @Test
    public void execute_oneWordStringInfo() {
        String str = "todo read";
        TodoCommand todoCommand = new TodoCommand(str);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            todoCommand.execute(new StubTaskList(), new StubUi(), new StubStorage());

            String expectedOutput = "Got it. I've added this task:\n  "
                    + "[T] [ ] read" + "\nNow you have " + 1 + " tasks in the list.\n";
            String actualOutput = outputStream.toString();
            assertEquals(normalizeLineEndings(expectedOutput).trim(), normalizeLineEndings(actualOutput).trim());
        } catch (Exception e) {
            Assertions.fail("Exception should not have been thrown");
        }
        System.setOut(originalOut);
    }

    @Test
    public void execute_twoWordStringInfo() {
        String str = "todo read and write";
        TodoCommand todoCommand = new TodoCommand(str);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            todoCommand.execute(new StubTaskList(), new StubUi(), new StubStorage());

            String expectedOutput = "Got it. I've added this task:\n  "
                    + "[T] [ ] read and write" + "\nNow you have " + 1 + " tasks in the list.\n";
            String actualOutput = outputStream.toString();
            assertEquals(normalizeLineEndings(expectedOutput).trim(), normalizeLineEndings(actualOutput).trim());
        } catch (Exception e) {
            Assertions.fail("Exception should not have been thrown");
        }
        System.setOut(originalOut);
    }

    @Test
    public void execute_twoWordStringWithEndingWhitespaceInfo() {
        String str = "todo read and write  ";
        TodoCommand todoCommand = new TodoCommand(str);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            todoCommand.execute(new StubTaskList(), new StubUi(), new StubStorage());

            String expectedOutput = "Got it. I've added this task:\n  "
                    + "[T] [ ] read and write" + "\nNow you have " + 1 + " tasks in the list.\n";
            String actualOutput = outputStream.toString();
            assertEquals(normalizeLineEndings(expectedOutput).trim(), normalizeLineEndings(actualOutput).trim());
        } catch (Exception e) {
            Assertions.fail("Exception should not have been thrown");
        }
        System.setOut(originalOut);
    }

    @Test
    public void execute_twoWordStringWithStartingWhitespaceInfo() {
        String str = "todo    read and write  ";
        TodoCommand todoCommand = new TodoCommand(str);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            todoCommand.execute(new StubTaskList(), new StubUi(), new StubStorage());

            String expectedOutput = "Got it. I've added this task:\n  "
                    + "[T] [ ] read and write" + "\nNow you have " + 1 + " tasks in the list.\n";
            String actualOutput = outputStream.toString();
            assertEquals(normalizeLineEndings(expectedOutput).trim(), normalizeLineEndings(actualOutput).trim());
        } catch (Exception e) {
            Assertions.fail("Exception should not have been thrown");
        }
        System.setOut(originalOut);
    }

    @Test
    public void execute_emptyTodoInfo() {
        String str = "todo ";
        TodoCommand todoCommand = new TodoCommand(str);

        assertThrows(EmptyTaskInfoException.class, () -> todoCommand.execute(new StubTaskList(),
                        new StubUi(), new StubStorage()));
    }

}

