package jackson.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jackson.exceptions.DuplicatedTaskException;
import jackson.tasks.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UiTest {

    @Test
    public void printFormatGuide_invalidInput_correctFormatGuide() {
        Ui ui = new Ui();

        assertEquals("""
                    More about bye:
                        --> bye
                    Exits the chatbot.""", ui.printFormatGuide("bye"));

        assertEquals("""
                    More about delete:
                        --> delete [index]
                    Deletes task at index [index].""", ui.printFormatGuide("delete"));

        assertEquals( """
                    More about unmark:
                        --> unmark [index]
                    Marks task at index [index] as incomplete.""", ui.printFormatGuide("unmark"));

        assertEquals( """
                    More about mark:
                        --> mark [index]
                    Marks task at index [index] as complete.""", ui.printFormatGuide("mark"));

        assertEquals( """
                    More about list:
                        --> list
                    Lists all tasks.""", ui.printFormatGuide("list"));

        assertEquals( """
                    More about find:
                        --> find [keywords]
                    Finds tasks that contain the keyword(s) in their names.""", ui.printFormatGuide("find"));

        assertEquals( """
                    More about event:
                        --> event [task-name] /from [start-date] /to [end-date]
                    Creates an event class.
                    All dates must be in DD-MM-YYYY HH:MM format (HH:MM is optional)""",
                ui.printFormatGuide("event"));

        assertEquals( """
                    More about deadline:
                        --> deadline [task-name] /by [due-date]
                    Creates a deadline task.
                    All dates must be in DD-MM-YYYY HH:MM format (HH:MM is optional)""",
                ui.printFormatGuide("deadline"));

        assertEquals( """
                    More about todo:
                        --> todo [task-name]
                    Creates a todo task.
                    No dates allowed at all.""", ui.printFormatGuide("todo"));

        assertEquals( """
                    More about sort:
                        --> sort [attribute] [/a or /d]
                    where attribute is one of: 'name', 'startdatetime', 'enddatetime', 'tasktype', 'status'""" + """
                    and /a is ascending, whilst /d is for descending""", ui.printFormatGuide("sort"));
    }

    @Test
    public void printIndexGuide_invalidInput_correctIndexErrorMessage() {
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        try {
            assertEquals("""
                    Alamak, you got 0 items on the list only leh...
                        --> You've got no items in the list! Add some stuff first!""", ui.printIndexGuide(taskList));

            taskList.addTask(new Todo("Play guitar"));
            assertEquals("""
                    Alamak, you got 1 items on the list only leh...
                        --> Enter 1 to mark/unmark/delete the task in the list!""", ui.printIndexGuide(taskList));

            taskList.addTask(new Todo("I'm only human after all"));
            assertEquals("""
                    Alamak, you got 2 items on the list only leh...
                        --> Enter a number between 1 and 2 when marking/unmarking/deleting tasks!""",
                    ui.printIndexGuide(taskList));
        } catch (DuplicatedTaskException e) {
            Assertions.fail("Error thrown incorrectly by taskList!");
        }
    }
}
