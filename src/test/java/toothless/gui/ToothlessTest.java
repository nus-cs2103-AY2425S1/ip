package toothless.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import toothless.command.Command;
import toothless.exceptions.ToothlessExceptions;
import toothless.storage.Storage;
import toothless.task.TaskList;
import toothless.ui.Ui;

public class ToothlessTest {

    private Toothless toothless;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Command command;

    @BeforeEach
    public void setUp() {
        storage = new Storage();
        taskList = new TaskList(new ArrayList<>());
        ui = new Ui();
        command = new Command() {
            @Override
            public String executeCommand(TaskList tasks, Ui ui, Storage storage) {
                return "Command executed";
            }
        };
        toothless = new Toothless();
    }

    @Test
    public void getResponse_returnsByeMessageForByeInput() {
        String expectedResponse = "Until next time, dragon rider!\n"
                + "Toothless the Night Fury, signing off.\n"
                + "See you soon!\n\n"
                + "(The input is disabled, restart to chat with Toothless again!)\n\n";
        assertEquals(expectedResponse, toothless.getResponse("bye"));
    }

    @Test
    public void getResponse_returnsGreetingMessageForHiInput() {
        String expectedResponse = "Hello! I'm Toothless\nHow can I help today dragon rider?\n\n";
        assertEquals(expectedResponse, toothless.getResponse("hi"));
    }

    @Test
    public void getResponse_executesCommandForOtherInput() throws ToothlessExceptions {
        String expectedResponse = "I'm sorry, I do not understand what you mean.\n"
                + "Please enter a valid command.\n\n"
                + "Enter 'help' to see the list of commands.\n\n";
        assertEquals(expectedResponse, toothless.getResponse("test"));
    }
}
