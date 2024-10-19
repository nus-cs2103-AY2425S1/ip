package main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import exception.CommandFoundButInvalidException;
import task.Task;
import task.ToDos;

public class UiTest {
    @Test
    public void testDelete() {
        try {
            String result = "Noted. I've removed this task:" + "\n"
                    + " [T][ ] gym" + "\n"
                    + "Now you have 5 tasks in the list";
            Task curr = new ToDos("gym");
            Ui ui = new Ui();
            assertEquals(result, ui.deleteMessage(curr, 5));
        } catch (CommandFoundButInvalidException e) {
            System.out.println("Error");
        }
    }

}
