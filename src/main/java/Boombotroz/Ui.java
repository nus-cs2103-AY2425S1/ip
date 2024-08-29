package Boombotroz;
import java.time.LocalDate;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Throws exception when input invalid.
     */
    public void invalidInput() throws BoomException {
        throw new BoomException("That's nonsense !!");

    }

    /**
     * Throws exception when no task details.
     *
     * @param taskInput task details.
     * @throws BoomException If no task details given.
     */
    public void emptyTask(String taskInput) throws BoomException {
        if (taskInput.isEmpty()) {
            throw new BoomException(
                    "You can't do nothing !!");
        }

    }

    /**
     * Throws exception when no deadline.
     *
     * @param taskInput task details.
     * @throws BoomException If no deadline given.
     */
    public void emptyDeadline(String taskInput) throws BoomException {
        if (!taskInput.contains(" /by ")) {
            throw new BoomException(
                    "You need a deadline !!");
        }

    }

    /**
     * Throws exception when invalid time period given.
     *
     * @param taskInput task details.
     * @throws BoomException If invalid time period given.
     */
    public void emptyStartEnd(String taskInput) throws BoomException {
        if (!taskInput.contains(" /from ")
                || !taskInput.contains(" /to ")) {
            throw new BoomException(
                    "You need a start and end !!");
        }

    }

    /**
     * Throws exception when no task number given.
     *
     * @param markInput mark details.
     * @throws BoomException If no task number given.
     */
    public void isTaskNumber(String markInput) throws BoomException {
        String[] parts = markInput.split(" ");

        // Check if there is a second part after splitting
        if (parts.length < 2 || !parts[1].matches("-?\\d+")) {
            throw new BoomException(
                    "You need to give a number !!");
        }

    }

    /**
     * Throws exception when invalid range given.
     *
     * @param index task index.
     * @param task_list list of all the tasks
     * @throws BoomException If invalid range given.
     */
    public void wrongRange(int index, TaskList task_list) throws BoomException {
        if (index + 1 <= 0
                || index + 1 > task_list.size()) {
            throw new BoomException(
                    "You are out of range !!");
        }
    }

    /**
     * Throws exception when invalid deadline given.
     *
     * @param d1 task deadline.
     * @param d2 time now.
     * @throws BoomException If invalid deadline given.
     */
    public void wrongDeadline(LocalDate d1, LocalDate d2) throws BoomException {
        if (d2.isAfter(d1)) {
            throw new BoomException("Your date is wrong !!");
        }

    }

    /**
     * Throws exception when invalid time period given.
     *
     * @param d1 task start time.
     * @param d2 task end time.
     * @param d3 time now.
     * @throws BoomException If invalid time period given.
     */
    public void wrongEventTime(LocalDate d1, LocalDate d2, LocalDate d3) throws BoomException {
        if (d1.isAfter(d2) || d3.isAfter(d2)) {
            throw new BoomException("Your date is wrong !!");
        }

    }

    public void emptyWord(String wordInput) throws BoomException {
        String[] parts = wordInput.split(" ");
        if (parts.length < 2) {
            throw new BoomException("You need a keyword !!");
        }
    }

}
