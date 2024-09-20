package chacha.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import chacha.ChaCha;
import chacha.command.stub.ChaChaExceptionStub;
import chacha.command.stub.StorageStub;
import chacha.command.stub.TaskListStub;
import chacha.command.stub.UiStub;
import chacha.command.stub.WrongCommandFormatExceptionStub;
import chacha.command.stub.WrongDateFormatExceptionStub;
import chacha.command.stub.WrongTimeFormatExceptionStub;
import chacha.exception.WrongDateFormatException;
import chacha.exception.WrongTimeFormatException;
import chacha.task.EventTask;

public class EventCommandTest {
    // Used AI to learn how to make stubs and how to use it in EventCommandTest.
    @Test
    public void testExecute_expectedOutcome() throws WrongDateFormatException, WrongTimeFormatException, IOException {
        ChaCha chacha = new ChaCha();
        EventTask task = new EventTask("Meeting", false,
                LocalDate.of(2024, 9, 25),
                LocalTime.of(10, 0),
                LocalTime.of(11, 0));

        TaskListStub taskListStub = new TaskListStub(task);
        UiStub uiStub = new UiStub("Task added");
        StorageStub storageStub = new StorageStub("./src/test/java/chacha/data/chacha.txt");

        EventCommand eventCommand = new EventCommand(chacha);

        String result = eventCommand.execute("event Meeting /at 2024-09-25 10.00am-11.00am",
                storageStub, uiStub, taskListStub);

        assertEquals("Task added", result);
    }

    @Test
    public void testExecute_missingEventField_exceptionThrown() throws IOException {
        ChaCha chacha = new ChaCha();
        TaskListStub taskListStub = new TaskListStub(null, new ChaChaExceptionStub());
        UiStub uiStub = new UiStub("Task added");
        StorageStub storageStub = new StorageStub("./src/test/java/chacha/data/chacha.txt");

        EventCommand eventCommand = new EventCommand(chacha);

        String result = eventCommand.execute("event", storageStub, uiStub, taskListStub);

        assertEquals("You are missing some info needed (task description, date, start time, end time). \n"
                        + "Please type again!", result);
    }

    @Test
    public void testExecute_wrongCommandFormatTime_exceptionThrown() throws IOException {
        ChaCha chacha = new ChaCha();
        TaskListStub taskListStub = new TaskListStub(null, new WrongCommandFormatExceptionStub("start"));
        UiStub uiStub = new UiStub("Task added");
        StorageStub storageStub = new StorageStub("./src/test/java/chacha/data/chacha.txt");

        EventCommand eventCommand = new EventCommand(chacha);

        String result = eventCommand.execute("event submit /2024-04-09 /10pm /to 12am",
                storageStub, uiStub, taskListStub);

        assertEquals("Please type start time in the form of \'from ...\'.", result);
    }

    @Test
    public void testExecute_wrongDateFormat_exceptionThrown() throws IOException {
        ChaCha chacha = new ChaCha();
        TaskListStub taskListStub = new TaskListStub(null, new WrongDateFormatExceptionStub());
        UiStub uiStub = new UiStub("Task added");
        StorageStub storageStub = new StorageStub("./src/test/java/chacha/data/chacha.txt");

        EventCommand eventCommand = new EventCommand(chacha);

        String result = eventCommand.execute("event Meeting /at invalid-date",
                storageStub, uiStub, taskListStub);

        assertEquals("Please input the date in the format YYYY-MM-DD. ", result);
    }

    @Test
    public void testExecute_wrongTimeFormat_exceptionThrown() throws IOException {
        ChaCha chacha = new ChaCha();
        TaskListStub taskListStub = new TaskListStub(null, new WrongTimeFormatExceptionStub());
        UiStub uiStub = new UiStub("Task added");
        StorageStub storageStub = new StorageStub("./src/test/java/chacha/data/chacha.txt");

        EventCommand eventCommand = new EventCommand(chacha);

        String result = eventCommand.execute("event Meeting /2024-09-25 /invalid-time",
                storageStub, uiStub, taskListStub);

        assertEquals("Please input a valid time in the format HH.MMam or HH.MMpm (e.g. 10.22am). ", result);
    }
}
