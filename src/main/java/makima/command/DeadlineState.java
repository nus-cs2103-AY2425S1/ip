package makima.command;

import makima.task.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * State to handle creation of new deadline
 */
public class DeadlineState extends State {

    private enum Variable {
        NAME,
        END_TIME
    }
    private String name;
    private Variable state = Variable.NAME;
    @Override
    public String getResponse(String input, Makima makima) {
        switch (state) {
        case NAME:
            name = input;
            state = Variable.END_TIME;
            return "What is the deadline?\n";
        case END_TIME:
            assert name != null;
            try {
                LocalDateTime endTime = LocalDateTime.parse(input);
                makima.addTask(new Deadline(name, endTime));
                makima.setState(new WaitingState());
                return "Task added successfully!\n";
            } catch (DateTimeParseException e) {
                return "Invalid date format! Please input the date as follows: YYYY-MM-DD HH:MM"
                        + "replacing the space with a T\n";
            }
        default:
            return null;
        }
    }
}
