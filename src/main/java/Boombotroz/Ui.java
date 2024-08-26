package Boombotroz;
import java.time.LocalDate;

public class Ui {

    public void invalidInput() throws BoomException {
        throw new BoomException("That's nonsense !!");

    }

    public void emptyTask(String taskInput) throws BoomException {
        if (taskInput.isEmpty()) {
            throw new BoomException(
                    "You can't do nothing !!");
        }

    }

    public void emptyDeadline(String taskInput) throws BoomException {
        if (!taskInput.contains(" /by ")) {
            throw new BoomException(
                    "You need a deadline !!");
        }

    }

    public void emptyStartEnd(String taskInput) throws BoomException {
        if (!taskInput.contains(" /from ")
                || !taskInput.contains(" /to ")) {
            throw new BoomException(
                    "You need a start and end !!");
        }

    }

    public void isTaskNumber(String markInput) throws BoomException {
        String[] parts = markInput.split(" ");

        // Check if there is a second part after splitting
        if (parts.length < 2 || !parts[1].matches("-?\\d+")) {
            throw new BoomException(
                    "You need to give a number !!");
        }

    }

    public void wrongRange(int index, TaskList task_list) throws BoomException {
        if (index + 1 <= 0
                || index + 1 > task_list.size()) {
            throw new BoomException(
                    "You are out of range !!");
        }
    }

    public void wrongDeadline(LocalDate d1, LocalDate d2) throws BoomException {
        if (d2.isAfter(d1)) {
            throw new BoomException("Your date is wrong !!");
        }

    }

    public void wrongEventTime(LocalDate d1, LocalDate d2, LocalDate d3) throws BoomException {
        if (d1.isAfter(d2) || d3.isAfter(d2)) {
            throw new BoomException("Your date is wrong !!");
        }

    }

}
