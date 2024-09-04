package meeju;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    private TaskList taskList = new TaskList(new StorageStub());
    private Parser parser = new Parser();


    //CHECKSTYLE.OFF: MethodName
    @Test
    public void bye_Success() {
        assertEquals(" Bye. Hope to see you again soon!", parser.parse(taskList, "bye"));
    }
    @Test
    public void incorrectCommand_Success() {
        assertEquals("I'm sorry, I did not understand that =^..^=",
                parser.parse(taskList, "this is an invalid command!"));
    }

    @Test
    public void add_todo_task_Success() {
        String message = "Meow! I've added this task:\n"
                + "\t" + "[T][ ] testTodo" + "\nNow you have " + 1
                + " tasks in the list.";
        assertEquals(message,
                parser.parse(taskList, "todo testTodo"));
    }

    @Test
    public void add_deadline_task_Success() {
        String message = "Meow! I've added this task:\n"
                + "\t" + "[D][ ] deadlineTest (by: Sep 5 2024 18:00HRS)" + "\nNow you have " + 1
                + " tasks in the list.";
        assertEquals(message,
                parser.parse(taskList, "deadline deadlineTest /by 05/09/2024 1800"));
    }


    //CHECKSTYLE.ON: MethodName
}
