package kita;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kita.exceptions.KitaNotFound;

public class ParserTest {

    private Commands commandsExecutor;

    @BeforeEach
    public void setUp() {
        Storage saveSys;
        TaskList tasks;
        try {
            saveSys = new Storage();
            tasks = new TaskList(new ArrayList<>());
        } catch (Exception e) {
            System.out.println("Oh no, Kita failed to create/read from the save file successfully :c");
            System.out.println(e);
            return;
        }
        commandsExecutor = new Commands(tasks, saveSys);
    }

    @Test
    @DisplayName("Invalid command entered")
    public void notFoundCommand() {
        assertThrows(
                KitaNotFound.class, () ->
                        Parser.parse("moshi moshi", commandsExecutor),
                "Expected to throw KitaNotFound when unknown command entered");
    }

    @Test
    @DisplayName("Re-ordered arguments should still work")
    public void missingArguments() {
        assertDoesNotThrow(() ->
                        Parser.parse("event hello world /to 2022-08-02 /from 2021-08-02", commandsExecutor),
                "Expected to create an Event successfully even with re-ordered parameters");
    }
}
