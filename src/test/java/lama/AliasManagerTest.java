package lama;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * AliasManagerTest is a unit test class that verifies the functionality of the AliasManager class.
 * It tests setting, deleting, and overwriting aliases, as well as default alias functionality.
 */
public class AliasManagerTest {

    /**
     * Sets up the AliasManager with a test storage before each test.
     * This ensures that aliases are persisted to a temporary file during tests.
     */
    @BeforeEach
    public void setUp() {
        AliasManager.setStorage(new Storage("./test.txt", "./testAlias.txt"));
    }

    /**
     * Tests that an alias can be set correctly and retrieved via the AliasManager.
     *
     * @throws LamaException if an error occurs while setting the alias.
     */
    @Test
    public void setAliasTest() throws LamaException {
        AliasManager.setAlias("td", "todo");
        assertEquals("todo", AliasManager.getCommand("td"));
    }

    /**
     * Tests that an alias can be deleted and ensures that the command defaults to the alias name
     * after deletion (i.e., the alias is no longer mapped to a command).
     *
     * @throws LamaException if an error occurs while deleting the alias.
     */
    @Test
    public void deleteAliasTest() throws LamaException {
        AliasManager.setAlias("td", "todo");
        AliasManager.deleteAlias("td");

        assertEquals("td", AliasManager.getCommand("td"));
    }

    /**
     * Tests that an existing alias can be overwritten with a new command.
     *
     * @throws LamaException if an error occurs while overwriting the alias.
     */
    @Test
    public void overwriteAliasTest() throws LamaException {
        AliasManager.setAlias("td", "todo");
        AliasManager.setAlias("td", "deadline");

        assertEquals("deadline", AliasManager.getCommand("td"));
    }

    /**
     * Tests that if an alias does not exist, the input string is returned as-is.
     */
    @Test
    public void aliasDoesNotExistTest() {
        assertEquals("unknown", AliasManager.getCommand("unknown"));
    }

    /**
     * Tests that the default aliases are loaded correctly.
     *
     * @throws LamaException if an error occurs while loading the aliases.
     */
    @Test
    public void defaultAliasTest() throws LamaException {
        AliasManager.loadAliases();
        assertEquals("todo", AliasManager.getCommand("t"));
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
