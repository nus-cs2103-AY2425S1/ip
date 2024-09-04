package Nave;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void successfulCreation() {
        try {
            assertEquals(new Deadline("deadline test 1", LocalDate.parse("2024-01-01")).toString(),
                    Deadline.handleInput("deadline test 1 /by 2024-01-01").toString());
        } catch (WrongInputException e) {
            fail();
        }
    }

    @Test
    public void failedCreation_noName_1() {
        try {
            Deadline.handleInput("");
            fail();
        } catch (WrongInputException e) {
            assertEquals("Hmmm... This deadline doesn't have a name!", e.getMessage());
        }
    }

    @Test
    public void failedCreation_noName_2() {
        try {
            Deadline.handleInput(" /by");
            fail();
        } catch (WrongInputException e) {
            assertEquals("Hmmm... This deadline doesn't have a name!", e.getMessage());
        }
    }

    @Test
    public void failedCreation_noName_3() {
        try {
            Deadline.handleInput(" /by ");
            fail();
        } catch (WrongInputException e) {
            assertEquals("Hmmm... This deadline doesn't have a name!", e.getMessage());
        }
    }

    @Test
    public void failedCreation_noName_4() {
        try {
            Deadline.handleInput("/by 2024-01-01");
            fail();
        } catch (WrongInputException e) {
            assertEquals("Hmmm... This deadline doesn't have a name!", e.getMessage());
        }
    }

    @Test
    public void failedCreation_noName_5() {
        try {
            Deadline.handleInput("/by abc");
            fail();
        } catch (WrongInputException e) {
            assertEquals("Hmmm... This deadline doesn't have a name!", e.getMessage());
        }
    }

    @Test
    public void failedCreation_noDate_1() {
        try {
            Deadline.handleInput("no date test 1 ");
            fail();
        } catch (WrongInputException e) {
            assertEquals("You forgot to specify a due date!", e.getMessage());
        }
    }

    @Test
    public void failedCreation_noDate_2() {
        try {
            Deadline.handleInput("no date test 2 /by");
            fail();
        } catch (WrongInputException e) {
            assertEquals("You forgot to specify a due date!", e.getMessage());
        }
    }

    @Test
    public void failedCreation_wrongDate_1(){
        try {
            Deadline.handleInput("wrong date test 1 /by 2024-1-1");
        } catch (WrongInputException e) {
            assertEquals("Your date format is wrong!", e.getMessage());
        }
    }

    @Test
    public void failedCreation_wrongDate_2(){
        try {
            Deadline.handleInput("wrong date test 2 /by today");
        } catch (WrongInputException e) {
            assertEquals("Your date format is wrong!", e.getMessage());
        }
    }

    @Test
    public void failedCreation_wrongDate_3(){
        try {
            Deadline.handleInput("wrong date test 3 /by 01-01-2024");
        } catch (WrongInputException e) {
            assertEquals("Your date format is wrong!", e.getMessage());
        }
    }

    @Test
    public void failedCreation_others_1(){
        try {
            Deadline.handleInput(" others   test 1 /by 2024-01-01");
        } catch (WrongInputException e) {
            assertEquals("Something's wrong!", e.getMessage());
        }
    }

    @Test
    public void failedCreation_others_2(){
        try {
            Deadline.handleInput("deadlinetest3/by 2024-01-01");
        } catch (WrongInputException e) {
            assertEquals("Something's wrong!", e.getMessage());
        }
    }
}
