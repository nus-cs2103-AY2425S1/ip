package juno.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import juno.command.AddDeadlineCommand;
import juno.command.AddEventCommand;
import juno.command.AddTodoCommand;
import juno.command.Command;
import juno.command.ExitCommand;
import juno.command.ListCommand;
import juno.command.MarkCommand;
import juno.manager.FileManager;
import juno.manager.TaskManager;
import juno.manager.exception.TaskManagerException;
import juno.ui.JunoUi;

public class CommandParserTest {

    private CommandParser commandParser;
    private JunoUi junoUi;
    private FileManager fileManager;
    private TaskManager taskManager;

    @BeforeEach
    public void startTestEnv() {
        this.commandParser = new CommandParser();
        this.junoUi = new JunoUi();
        VBox vBox = Mockito.mock(VBox.class);
        Image image = Mockito.mock(Image.class);
        this.fileManager = new FileManager(vBox, image);
        this.taskManager = new TaskManager(null);
    }
    @Test
    public void parse_validExitCommand_success() throws TaskManagerException {
        Command command = commandParser.parse("bye", this.junoUi, this.fileManager, this.taskManager);
        assertInstanceOf(ExitCommand.class, command);
    }
    @Test
    public void parse_invalidExitCommand_exceptionThrown() {
        TaskManagerException exception = assertThrows(TaskManagerException.class, () -> {
            this.commandParser.parse("bye bye", this.junoUi, this.fileManager, this.taskManager);
        });
        assertEquals(TaskManagerException.ErrorType.INVALID_FUNCTION, exception.getErrorType());
    }

    @Test
    public void parse_validListCommand_success() throws TaskManagerException {
        Command command = commandParser.parse("list", this.junoUi, this.fileManager, this.taskManager);
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    public void parse_invalidListCommand_exceptionThrown() {
        TaskManagerException exception = assertThrows(TaskManagerException.class, () -> {
            this.commandParser.parse("list a", this.junoUi, this.fileManager, this.taskManager);
        });
        assertEquals(TaskManagerException.ErrorType.INVALID_FUNCTION, exception.getErrorType());
    }

    @Test
    public void parse_validMarkCommand_success() throws TaskManagerException {
        Command command = commandParser.parse("mark 1", this.junoUi, this.fileManager, this.taskManager);
        assertInstanceOf(MarkCommand.class, command);
    }
    @Test
    public void parse_validUnmarkCommand_success() throws TaskManagerException {
        Command command = commandParser.parse("unmark 1", this.junoUi, this.fileManager, this.taskManager);
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    public void parse_validAddTodoCommand_success() throws TaskManagerException {
        Command command = commandParser.parse("add todo Buy milk", this.junoUi, this.fileManager,
                this.taskManager);
        assertInstanceOf(AddTodoCommand.class, command);
    }

    @Test
    public void parse_validAddDeadlineCommand_success() throws TaskManagerException {
        Command command = commandParser.parse("add deadline Finish report / 2024 11 17 10.10AM", this.junoUi,
                this.fileManager, this.taskManager);
        assertInstanceOf(AddDeadlineCommand.class, command);
    }

    @Test
    public void parse_validAddEventCommand_success() throws TaskManagerException {
        Command command = commandParser.parse("add event Team meeting / 2024 11 17 10.10AM / "
                + "2024 11 17 18.10PM", this.junoUi, this.fileManager, this.taskManager);
        assertInstanceOf(AddEventCommand.class, command);
    }

    @Test
    public void parse_validEmptyInput_success() {
        TaskManagerException exception = assertThrows(TaskManagerException.class, () -> {
            commandParser.parse("", this.junoUi, this.fileManager, this.taskManager);
        });
        assertEquals(TaskManagerException.ErrorType.EMPTY_INPUT, exception.getErrorType());
        assertEquals("Well, seems like you did not input anything! Please try again.", exception.getMessage());
    }

    @Test
    public void parse_validWrongFunctionInput_success() {
        TaskManagerException exception = assertThrows(TaskManagerException.class, () -> {
            commandParser.parse("ifsa", this.junoUi, this.fileManager, this.taskManager);
        });
        assertEquals(TaskManagerException.ErrorType.INVALID_FUNCTION, exception.getErrorType());
        assertEquals("The input you provided is invalid! (\uD83D\uDCA1 Tip: You can use the following commands"
                + " \"add\", \"list\",\"mark\", \"unmark\".)", exception.getMessage());
    }
}
