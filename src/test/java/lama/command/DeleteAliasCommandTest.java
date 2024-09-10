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
 * Tests the DeleteAliasCommand functionality. Ensures that aliases can be correctly
 * deleted and verifies the behavior when attempting to delete non-existent aliases.
 */
public class DeleteAliasCommandTest {

    private TaskList taskList;
    private Ui ui;

    /**
     * Sets up the test environment by initializing the TaskList, Ui, and AliasManager
     * with a test storage before each test case.
     */
    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        ui = new Ui();
        AliasManager.setStorage(new Storage("./test.txt", "./testAlias.txt"));
    }

    /**
     * Tests that an alias can be deleted successfully using DeleteAliasCommand.
     * Verifies that the alias is no longer mapped to a command after deletion.
     *
     * @throws LamaException if an error occurs during the command execution.
     */
    @Test
    public void testRunDeleteAliasCommand() throws LamaException {
        AliasManager.setAlias("td", "todo");

        DeleteAliasCommand deleteAliasCommand = new DeleteAliasCommand("td");
        String result = deleteAliasCommand.run(taskList, null, ui);

        assertEquals("Alias 'td' has been deleted.", result);
        assertEquals("td", AliasManager.getCommand("td"));
    }

    /**
     * Tests the behavior when attempting to delete an alias that does not exist.
     * Verifies that a LamaException is thrown with the expected error message.
     */
    @Test
    public void testDeleteNonExistentAlias() {
        DeleteAliasCommand deleteAliasCommand = new DeleteAliasCommand("nonExistentAlias");

        Exception exception = assertThrows(LamaException.class, () -> {
            deleteAliasCommand.run(taskList, null, ui);
        });

        assertEquals("Alias 'nonExistentAlias' does not exist.", exception.getMessage());
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
