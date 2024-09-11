package makima.command;

import makima.task.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * State to handle event creation
 */
public class EventState extends State {
    private enum Variable {
        NAME,
        START_TIME,
        END_TIME
    }
    private String name;
    private LocalDateTime startTime;
    private Variable state = Variable.NAME;
    @Override
    public String getResponse(String input, Makima makima) {
        switch (state) {
        case NAME:
            name = input;
            state = Variable.END_TIME;
            return "What is the start time?\n";
        case START_TIME:
            try {
                startTime = LocalDateTime.parse(input);
                state = Variable.END_TIME;
                return "What is the end time?\n";
            } catch (DateTimeParseException e) {
                return "Invalid date format! Please input the date as follows: YYYY-MM-DD HH:MM"
                        + "replacing the space with a T\n";
            }
        case END_TIME:
            try {
                LocalDateTime endTime = LocalDateTime.parse(input);
                makima.addTask(new Event(name, startTime, endTime));
                makima.setState(new WaitingState());
                return "Task addedd successfully!\n";
            } catch (DateTimeParseException e) {
                return "Invalid date format! Please input the date as follows: YYYY-MM-DD HH:MM"
                        + "replacing the space with a T\n";
            }
        default:
            return null;
        }
    }
}
