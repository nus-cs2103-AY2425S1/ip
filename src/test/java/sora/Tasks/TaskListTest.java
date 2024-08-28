package sora.Tasks;

import org.junit.jupiter.api.Test;
import sora.SoraException;
import sora.Ui;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {

    TaskList taskList = new TaskList();
    @Test
    public void markTaskTest_nonIntegerValue() {
        assertThrows(SoraException.class,
                () -> taskList.markTask("Not An Integer"),
                "\tPlease Enter - Mark (int)\n" + Ui.HORIZONTAL_LINE);
    }

    @Test
    public void markTaskTest_exceedSize() {
        assertThrows(SoraException.class,
                () -> taskList.markTask(String.valueOf(Integer.MAX_VALUE)),
                "\tPlease Enter Integer Value within List Size\n" + Ui.HORIZONTAL_LINE);
    }

    @Test
    public void unmarkTaskTest_nonIntegerValue() {
        assertThrows(SoraException.class,
                () -> taskList.unmarkTask("Not An Integer"),
                "\tPlease Enter - Mark (int)\n" + Ui.HORIZONTAL_LINE);
    }

    @Test
    public void unmarkTaskTest_exceedSize() {
        assertThrows(SoraException.class,
                () -> taskList.unmarkTask(String.valueOf(Integer.MAX_VALUE)),
                "\tPlease Enter Integer Value within List Size\n" + Ui.HORIZONTAL_LINE);
    }

    @Test
    public void addTaskTest_deadline_badParsedCommand() {
        ArrayList<String> parsedCommand = new ArrayList<>();
        parsedCommand.add("deadline"); parsedCommand.add("Bad Parsed Command");
        assertThrows(IndexOutOfBoundsException.class,
                () -> taskList.addTask("deadline", parsedCommand),
                "\tPlease Enter - Sora.Deadline (Description) /by (dd/MM/yy HHmm)");
    }

    @Test
    public void addTaskTest_event_badParsedCommand() {
        ArrayList<String> parsedCommand = new ArrayList<>();
        parsedCommand.add("event"); parsedCommand.add("Bad Parsed Command");
        assertThrows(IndexOutOfBoundsException.class,
                () -> taskList.addTask("event", parsedCommand),
                "\tPlease Enter - Event (Description) /from (dd/MM/yy HHmm) /to (dd/MM/yy HHmm)");
    }

    @Test
    public void addTaskTest_event_badParsedCommand_withFrom() {
        ArrayList<String> parsedCommand = new ArrayList<>();
        parsedCommand.add("event"); parsedCommand.add("Bad Parsed Command /from Bad Parsed Command");
        assertThrows(IndexOutOfBoundsException.class,
                () -> taskList.addTask("event", parsedCommand),
                "\tPlease Enter - Event (Description) /from (dd/MM/yy HHmm) /to (dd/MM/yy HHmm)");
    }

    @Test
    public void deleteTest_nonIntegerValue() {
        assertThrows(SoraException.class,
                () -> taskList.deleteTask("Not An Integer"),
                "\tPlease Enter - Delete (int)\n" + Ui.HORIZONTAL_LINE);
    }

    @Test
    public void deleteTaskTest_exceedSize() {
        assertThrows(SoraException.class,
                () -> taskList.deleteTask(String.valueOf(Integer.MAX_VALUE)),
                "\tPlease Enter Integer Value within List Size\n" + Ui.HORIZONTAL_LINE);
    }
}
