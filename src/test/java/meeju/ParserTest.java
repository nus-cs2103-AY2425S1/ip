package meeju;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ParserTest {
    private TaskList taskList = new TaskList(new StorageStub());
    private Parser parser = new Parser();


    //CHECKSTYLE.OFF: MethodName
    @Test
    public void bye_Success() {
        try {
            assertEquals(" Bye. Hope to see you again soon!", parser.parse(taskList, "bye"));
        } catch (MeejuException e) {
            fail();
        }
    }
    @Test
    public void incorrectCommand_Success() {
        try {
            assertEquals("I'm sorry, I did not understand that =^..^=",
                parser.parse(taskList, "this is an invalid command!"));
        } catch (MeejuException e) {
            fail();
        }
    }

    @Test
    public void addTodoTask_Success() {
        String message = "Meow! I've added this task:\n"
                + "\t" + "[T][ ] testTodo" + "\nNow you have " + 1
                + " tasks in the list.";
        try {
            assertEquals(message,
                    parser.parse(taskList, "todo testTodo"));
        } catch (MeejuException e) {
            fail();
        }
    }

    @Test
    public void addDeadlineTask_Success() {
        String message = "Meow! I've added this task:\n"
                + "\t" + "[D][ ] deadlineTest (by: Sep 5 2024 18:00HRS)" + "\nNow you have " + 1
                + " tasks in the list.";
        try {
            assertEquals(message,
                parser.parse(taskList, "deadline deadlineTest /by 05/09/2024 1800"));
        } catch (MeejuException e) {
            fail();
        }
    }


    //CHECKSTYLE.ON: MethodName
}
