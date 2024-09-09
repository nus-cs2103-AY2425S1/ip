package chatgpt.task;

import chatgpt.exception.ChatBotException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *  The Events class represents a task that has a start and end datetime and
 *  contains the task description/task name, start datetime and end datetime.
 */
public class Events extends Task {
    /** Represents the start datetime of the task **/
    private LocalDateTime startDate;
    /** Represents the end datetime of the task **/
    private LocalDateTime endDate;

    /**
     * Constructor of a new event that has the description/name,
     * start and end datetime of the given event.
     *
     * @param task is the description/task name
     * @param startDate is the start date of the given event
     * @param endDate is the end date of the given event
     * @throws ChatBotException if start and end date is not given or
     * given in the wrong format
     */
    public Events(String task, String startDate, String endDate)
            throws ChatBotException {
        super(task);
        if (startDate.equals(" ") || endDate.equals(" ")){
            throw new ChatBotException("\t Oh no!!(;-;) Event period cannot be empty" +
                    "\n\t Enter the event in the format: event <Task> " +
                    "/from <Start Date/Time> /to <End Date/Time>");
        }
        try {
            LocalDate sDate = LocalDate.parse(startDate.split(" ")[1]);
            String sHours = startDate.split(" ")[2]
                    .substring(0,2);
            String sMinutes = startDate.split(" ")[2]
                    .substring(2);
            LocalTime sTime = LocalTime.of(Integer.valueOf(sHours),
                    Integer.valueOf(sMinutes));
            this.startDate = LocalDateTime.of(sDate, sTime);

            LocalDate eDate = LocalDate.parse(endDate.split(" ")[1]);
            String eHours = endDate.split(" ")[2]
                    .substring(0,2);
            String eMinutes = endDate.split(" ")[2]
                    .substring(2);
            LocalTime eTime = LocalTime.of(Integer.valueOf(eHours),
                    Integer.valueOf(eMinutes));
            this.endDate = LocalDateTime.of(eDate, eTime);
        } catch (DateTimeException e) {
            throw new ChatBotException("\t Please enter the start and end timings " +
                    "in the following format:\n\t yyyy-mm-dd hhmm (e.g 2024-09-05 1440)");
        }
    }

    /**
     * Constructor of an event that may have been completed and has
     * the description/name, start and end datetime of the given event.
     *
     * @param task is the description/task name
     * @param startDate is the start date of the given event
     * @param endDate is the end date of the given event
     * @throws ChatBotException if start and end date is not given or
     * given in the wrong format
     */
    public Events(String task, String startDate, String endDate,
                  boolean isCompleted) throws ChatBotException {
        super(task, isCompleted);
        if (startDate.equals(" ") || endDate.equals(" ")){
            throw new ChatBotException("\t Oh no!!(;-;) Event period cannot be empty" +
                    "\n\t Enter the event in the format: event <Task> " +
                    "/from <Start Date/Time> /to <End Date/Time>");
        }
        try {
            LocalDate sDate = LocalDate.parse(startDate.split(" ")[1]);
            String sHours = startDate.split(" ")[2].substring(0,2);
            String sMinutes = startDate.split(" ")[2].substring(2);
            LocalTime sTime = LocalTime.of(Integer.valueOf(sHours),
                    Integer.valueOf(sMinutes));
            this.startDate = LocalDateTime.of(sDate, sTime);

            LocalDate eDate = LocalDate.parse(endDate.split(" ")[1]);
            String eHours = endDate.split(" ")[2].substring(0,2);
            String eMinutes = endDate.split(" ")[2].substring(2);
            LocalTime eTime = LocalTime.of(Integer.valueOf(eHours),
                    Integer.valueOf(eMinutes));
            this.endDate = LocalDateTime.of(eDate, eTime);
        } catch (DateTimeException e) {
            throw new ChatBotException("\t Please enter the start and end timings " +
                    "in the following format:\n\t yyyy-mm-dd hhmm (e.g 2024-09-05 1440)");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" dd LLL uuuu HH:mm ");
        return "[E]" + super.toString() +
                "(from:" + this.startDate.format(formatter) +
                "to:" + this.endDate.format(formatter) + ")";
    }

    @Override
    public String toPrint() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" uuuu-MM-dd HHmm ");
        return "E " + super.toPrint() +
                "|" + this.startDate.format(formatter) +
                "|" + this.endDate.format(formatter);
    }
}
