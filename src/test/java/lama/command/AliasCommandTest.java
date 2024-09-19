package lama.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lama.AliasManager;
import lama.LamaException;
import lama.Storage;
import lama.TaskList;
import lama.Ui;


/**
 * Tests the functionality of the AliasCommand. This class ensures that the alias command
 * works as expected, allowing users to set valid aliases and throwing exceptions for invalid commands.
 */
public class AliasCommandTest {

    private TaskList taskList;
    private Ui ui;

    /**
     * Sets up the test environment by initializing the TaskList, Ui, and AliasManager
     * with a test storage file before each test case.
     */
    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        ui = new Ui();
        AliasManager.setStorage(new Storage("./test.txt", "./testAlias.txt"));
    }

    /**
     * Tests that a valid alias can be set using the AliasCommand.
     * Verifies that the alias is correctly mapped to the command.
     *
     * @throws LamaException if an error occurs during the command execution.
     */
    @Test
    public void testRunAliasCommand() throws LamaException {
        AliasCommand aliasCommand = new AliasCommand("td", "todo");

        String result = aliasCommand.run(taskList, null, ui);

        assertEquals("Alias set: td -> todo", result);
        assertEquals("todo", AliasManager.getCommand("td"));
    }

    /**
     * Tests the behavior when attempting to set an alias for an invalid command.
     * Verifies that a LamaException is thrown with the expected error message.
     */
    @Test
    public void testInvalidCommandAlias() {
        AliasCommand aliasCommand = new AliasCommand("x", "invalidCommand");

        Exception exception = assertThrows(LamaException.class, () -> {
            aliasCommand.run(taskList, null, ui);
        });

        assertEquals("Invalid command: 'invalidCommand'. Cannot set alias.", exception.getMessage());
    }

    /**
     * Cleans up by deleting the test alias file after each test to ensure a clean state.
     */
    @AfterEach
    public void reset() {
        try {
            Files.deleteIfExists(new File("./testAlias.txt").toPath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
