package Tasks;

import Exceptions.EmptyDescException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ToDos extends Task {

    /**
     * Calls constructor of super class
     *
     * @throws EmptyDescException If desc is empty.
     */
    public ToDos(String desc) throws EmptyDescException {
        super(desc);
        if (desc.isEmpty()) {
            //throws exception if not desc is given
            throw new EmptyDescException
                    ("OOPS!!! The description of a todo cannot be empty leh. " +
                            "Pls provide in the following format: " +
                            "todo read book");
        }
    }

    @Override
    public String print() {

        return "[T]" + super.print();
    }

    /**
     * @param updatedDate
     */
    @Override
    public void setDate(LocalDate updatedDate) {
        //do nothing cause todo has no date attribute
    }

    /**
     * @param updatedDateTime the new date and time to be assigned to the task.
     */
    @Override
    public void setDateTime(LocalDateTime updatedDateTime) {
        // do nothing

    }

    /**
     * @param updatedDeadlineTime
     */
    @Override
    public void setTime(LocalTime updatedDeadlineTime) {
        //do nothing tasks has no time attribute
    }

    /**
     * @param updatedEventStartTime
     */
    @Override
    public void setStartTime(LocalTime updatedEventStartTime) {
        //do nothing tasks has no time attribute
    }

    /**
     * @param updatedEventEndTime
     */
    @Override
    public void setEndTime(LocalTime updatedEventEndTime) {
        //do nothing tasks has no time attribute
    }

    /**
     * @param newValue
     */
    @Override
    public void setDesc(String newValue) {
    }

}
