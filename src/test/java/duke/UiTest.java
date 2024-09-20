package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class UiTest {

    @Test
    public void stringListTest() throws TaskListOutOfBoundsException {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("do work TODAY"));
        taskList.add(new Deadline("assignment 3 for General Relativity", LocalDate.parse("2012-12-12")));
        taskList.add(new Event("meteor shower", "Mon 4am", "Fri 6am"));
        Ui ui = new Ui();
        /*
        IntelliJ IDEA AI recommended changing string concatenation to a text block, which improved the code quality upon
        implementation.
         */
        assertEquals(ui.stringList(taskList), """
                1.[T][ ] do work TODAY
                2.[D][ ] assignment 3 for General Relativity (by: Dec 12 2012)
                3.[E][ ] meteor shower (from: Mon 4am to: Fri 6am)
                """);
    }

    @Test
    public void guiDisplayTest() throws TaskListOutOfBoundsException {
        TaskList taskList = new TaskList();
        taskList.add(new Todo("do work TODAY"));
        taskList.add(new Deadline("assignment 3 for General Relativity", LocalDate.parse("2012-12-12")));
        taskList.add(new Event("meteor shower", "Mon 4am", "Fri 6am"));
        Ui ui = new Ui();
        /*
        IntelliJ IDEA AI recommended changing string concatenation to a text block, which improved the code quality upon
        implementation.
         */
        assertEquals(ui.guiDisplay(taskList), """
                Behold, the tasks you have envisioned are now before your eyes:
                1.[T][ ] do work TODAY
                2.[D][ ] assignment 3 for General Relativity (by: Dec 12 2012)
                3.[E][ ] meteor shower (from: Mon 4am to: Fri 6am)
                """);
    }
}
