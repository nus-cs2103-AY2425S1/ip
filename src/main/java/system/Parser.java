package system;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
import task.*;

/**
 * The Parser class is responsible for parsing user input strings and
 * executing the corresponding tasks or commands within the system. This class
 * contains methods to determine the type of command issued by the user (e.g.,
 * adding a to-do, event, or deadline, marking/unmarking a task, etc.).
 */
public class Parser {
    private Ui ui = new Ui();
    private DateTimeSystem dateTimeSystem = new DateTimeSystem();

    /**
     * Checks if the provided input string equals to the keyword "bye".
     *
     * @param input Input string to be checked.
     * @return true if the input string contains the substring "bye"; false otherwise.
     */
    public boolean containBye(String input) {
        return input.contains(("bye"));
    }

    /**
     * Checks if the provided input string contains the keyword "list".
     *
     * @param input The String input to be checked.
     * @return true if the input string contains the substring "list"; false otherwise.
     */
    public boolean containList(String input) {
        return input.contains("list");
    }

    /**
     * Checks if the provided input string contains the keyword "hello" or "hi".
     *
     * @param input The String input to be checked.
     * @return true if the input string contains the substring "hello" or "hi"; false otherwise.
     */
    public boolean containHi(String input) {
        return input.contains("hi") || input.contains("hello");
    }

    /**
     * Checks if the provided input string contains the keyword "view".
     *
     * @param input Input string to be checked.
     * @return true if the input string contains the substring "view"; false otherwise.
     */
    public boolean containsView(String input) {
        return input.contains("view");
    }

    /**
     * Checks if the provided input string contains the keyword "mark".
     *
     * @param input Input string  to be checked.
     * @return true if the input string contains the substring "mark"; false otherwise.
     */
    public boolean containMark(String input) {
        return input.contains("mark");
    }

    /**
     * Checks if the provided input string contains the keyword "unmark".
     *
     * @param input Input string to be checked.
     * @return true if the input string contains the substring "unmark"; false otherwise.
     */
    public boolean containUnmark(String input) {
        return input.contains("unmark");
    }


    /**
     * Checks if the provided input string contains the keyword to-do task.
     *
     * @param input The String input to be checked.
     * @return true if the input string contains the substring to-do task; false otherwise.
     */
    public boolean containToDo(String input) {
        return input.contains("todo");
    }

    /**
     * Checks if the provided input string contains the keyword "deadline".
     *
     * @param input Input string to be checked.
     * @return true if the input string contains the substring "deadline"; false otherwise.
     */
    public boolean containDeadline(String input) {
        return input.contains("deadline");
    }

    /**
     * Checks if the provided input string contains the keyword "event".
     *
     * @param input The String input to be checked.
     * @return true if the input string contains the substring "event"; false otherwise.
     */
    public boolean containEvent(String input) {
        return input.contains("event");
    }

    /**
     * Checks if the provided input string contains the keyword "delete".
     *
     * @param input Input string to be checked.
     * @return true if the input string contains the substring "delete"; false otherwise.
     */
    public boolean containDelete(String input) {
        return input.contains("delete");
    }

    /**
     * Checks if the provided input string contains the keyword "find".
     *
     * @param input Input string to be checked.
     * @return true if the input string contains the substring "find"; false otherwise.
     */
    public boolean containFind(String input) {
        return input.contains("find");
    }

    /**
     * Performs a search for a task based on the provided input.
     * Input string is used to search for the task within the task list.
     * The task name starts from the 5th character onwards until the last character.
     *
     * @param input A string containing the input command followed by the task name.
     * @return A String containing the results of the search,
     * listing all tasks that match the search query.
     * @throws FileNotFoundException If the task file is not found during the search.
     */
    public String performFind(String input) throws FileNotFoundException {
        String trimmedInput = input.trim();

        if (trimmedInput.length() < 5) {
            return ui.invalidCommand();
        }

        String name = trimmedInput.substring(5);

        return Task.findTask(name);
    }

    /**
     * Performs a search for tasks based on the provided date input.
     * Input string is used to search for the task within the task list based on the date provided.
     * The task name starts from the 5th character onwards until the last character.
     *
     * @param input A string containing the input command followed by the date.
     * @return A String containing the results of the search,
     * listing all tasks that match the search query.
     * @throws FileNotFoundException If the task file is not found during the search.
     */
    public String performView(String input) throws FileNotFoundException {
        String trimmedInput = input.trim();
        if (trimmedInput.length() < 5) {
            return ui.invalidCommand();
        }

        String response = "";
        String date = trimmedInput.substring(5);
        String[] dateTokens = date.split("-");

        if (dateTokens.length != 3) {
            response = ui.invalidDate();
            return response;
        }

        String year = dateTokens[0];
        String month = dateTokens[1];
        String day = dateTokens[2];

        String formattedDate = dateTimeSystem.formatLocalDate(dateTimeSystem.createDate(year, month, day));
        response = Task.viewTask(formattedDate);
        return response;
    }

    /**
     * Lists all tasks in the task list.
     * Retrieves and prints all the tasks stored in the task list.
     *
     * @return A String containing the formatted list of tasks.
     * @throws FileNotFoundException If the task file is not found when attempting to list tasks.
     */
    public String performListTasks() throws FileNotFoundException {
        return Task.list_task();
    }

    /**
     * Display a hello message to the user.
     *
     * @return A String containing the hello message from Tanjiro
     */
    public String performHi() {
        return ui.hi() + "\n" + ui.introduction();
    }

    /**
     * Closing the application after 1.5 seconds of displaying the bye message.
     */
    public void performBye() {
        // Create a 1-second delay before closing the application
        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished(event -> Platform.exit()); // Close the JavaFX window after 1 second
        delay.play();
    }

    /**
     * Performs a marking or unmarking operation on a task based on the provided input string.
     * The last character of the input is used to determine the index of the task to be marked or unmarked.
     * If the input indicates an unmarking operation, the task is unmarked; otherwise, it is marked.
     *
     * @param input Input string that determines the task to be marked or unmarked.
     * @return A String indicating the result of marking or unmarking the task.
     * @throws IOException If an I/O error occurs during the operation.
     */
    public String performMark(String input) throws IOException {
        String trimmedInput = input.trim();
        if (trimmedInput.length() < 5) {
            return ui.invalidCommand();
        }

        int listNo;
        if (containUnmark(trimmedInput)) {
            listNo = Integer.parseInt(trimmedInput.substring(7));
            if (listNo != TaskList.tasks.size() && listNo <= 0) {
                return ui.indexOutOfBounds();
            }
            return Task.unmark_task(listNo);
        }

        listNo = Integer.parseInt(trimmedInput.substring(5));
        if (listNo != TaskList.tasks.size() && listNo <= 0) {
            return ui.indexOutOfBounds();
        }

        return Task.mark_task(listNo);
    }

    /**
     * Creates a new to-do task based on the input and adds it to the task list.
     * The 6th character of the input till the last character is the Task Name.
     * If an error occurs due to an invalid string index access or I/O issues,
     * a customized error message will be displayed.
     *
     * @param input Input string containing the command to create a to-do task as well as
     *              the name of the task.
     * @return A String indicating the result of adding the to-do task or
     * an error message if the input is invalid.
     */
    public String performToDo(String input) {
        String response = "";
        try {
            String name = input.substring(5);
            ToDos tempTodo = new ToDos(name);
            response = tempTodo.addTask(tempTodo);
        } catch (StringIndexOutOfBoundsException | IOException e) {
            response = ui.empty_todo();
        }

        return response;
    }

    /**
     * Creates a new Deadline task based on the input and adds it to the task list.
     * Input string contains the task name and a deadline date, separated by a "/".
     * The 10th character of the task name input till the last character is the actual task name.
     * The 3rd character of the deadline date till the last character is the actual date,
     * which is then parsed into a LocalDateTime object.
     * If an error occurs due to invalid input or I/O issues, a customized error message will be displayed.
     *
     * @param input Input string containing the command to create a Deadline task.
     *              The format of the string should be "deadline task name /by YYYY-MM-DD HHMM".
     * @return A String indicating the result of adding the deadline task or an error message if the input is invalid.
     * @throws StringIndexOutOfBoundsException If the input string does not adhere to the expected format.
     */
    public String performDeadline(String input) {
        String response = "";
        try {
            String[] deadlineArr = input.split("/");

            if (deadlineArr.length != 2 || deadlineArr[1].length() <= 3) {
                response = ui.invalidDeadlineInput();
                return response;
            }
            String name = deadlineArr[0].substring(9);
            String date = deadlineArr[1].substring(3);
            String[] tokens = date.split(" ");
            String[] dateTokens = tokens[0].split("-");

            if (tokens.length != 2 || dateTokens.length != 3) {
                response = ui.invalidDate();
                return response;
            }
            System.out.println(tokens.length);

            String time = tokens[1];
            String year = dateTokens[0];
            String month = dateTokens[1];
            String day = dateTokens[2];

            if (dateValidity(year, month, day)) {
                response = ui.invalidDate();
                return response;
            }

            if (time.length() != 4) {
                response = ui.twentyFourHourClock();
                return response;
            }

            String hour = time.substring(0, 2);
            String minute = time.substring(2);

            LocalDateTime ldt = dateTimeSystem.createDateTime(year, month, day, hour, minute);
            boolean isBefore = dateTimeSystem.compareDateTime(ldt);
            if (!isBefore) {
                response = ui.dateBeforeCurrent();
                return response;
            }

            Deadlines tempDeadline = new Deadlines(name, ldt);
            response = tempDeadline.addTask(tempDeadline);
            assertDeadlineDateTime(year, month, day);

        } catch (StringIndexOutOfBoundsException
                 | IOException e) {
            response = ui.empty_deadline();
        }
        return response;
    }

    /**
     * Checks the validity of the given date components (year, month, day).
     * It checks if:
     * - The year is valid (4 digits long).
     * - The month is non-empty and at most 2 digits long.
     * - The day is non-empty and at most 2 digits long.
     *
     * @param year  The year part of the date as a string.
     * @param month The month part of the date as a string.
     * @param day   The day part of the date as a string.
     * @return True if any of the date components are invalid, otherwise False.
     */
    private boolean dateValidity(String year, String month, String day) {
        boolean isMonthDayValid = Arrays.asList(month, day).stream()
                .allMatch(this::isValidMonthDay);

        boolean isYearValid = year.length() == 4;
        boolean isMonthValid = !month.isEmpty() && month.length() <= 2;
        boolean isDayValid = !day.isEmpty() && day.length() <= 2;

        return !isMonthDayValid || !isYearValid || !isMonthValid || !isDayValid;
    }

    /**
     * Asserts that the provided date components (year, month, day) are valid.
     * This method uses assertions to enforce that:
     * - The year is 2024 or later.
     * - The month is between 1 and 12, inclusive.
     * - The day is between 1 and 31, inclusive.
     *
     * @param year  The year part of the date as a string. Must represent a value greater than or equal to 2024.
     * @param month The month part of the date as a string. Must represent a value between 1 and 12.
     * @param day   The day part of the date as a string. Must represent a value between 1 and 31.
     * @throws AssertionError if the year, month, or day does not meet the required conditions.
     */
    private void assertDeadlineDateTime(String year, String month, String day) {
        assert Integer.parseInt(year) >= 2024;
        assert Integer.parseInt(month) > 0 && Integer.parseInt(month) <= 12;
        assert Integer.parseInt(day) > 0 && Integer.parseInt(day) <= 31;
    }

    /**
     * Checks the validity of the given month and day.
     *
     * @param dateTime The given date and time to be checked.
     * @return True if date and time is valid.
     */
    private boolean isValidMonthDay(String dateTime) {
        return dateTime != null && !dateTime.isEmpty() && dateTime.length() <= 2;
    }

    /**
     * Creates a new Event task based on the input and adds it to the task list.
     * The input string contains the event name, start time, and end time, separated by "/".
     * The 6th character of the event name input till the last character is the actual event name.
     * The 5th character of the start time input till the last character is the actual start time,
     * which is then parsed into a LocalDateTime object.
     * The 3rd character of the end time input till the last character is the actual end time,
     * which is then parsed into a LocalDateTime object.
     * If an error occurs due to invalid input or I/O issues, a customized error message is displayed.
     *
     * @param input Input string containing the command to create an Event task.
     *              The format of the string should be "event name
     *              /from YYYY-MM-DD HHMM /to YYYY-MM-DD HHMM".
     * @return A String indicating the result of adding the event task or an error message if the input is invalid.
     * @throws StringIndexOutOfBoundsException If the input string does not adhere to the expected format.
     */
    public String performEvent(String input) {
        String response = "";
        try {
            String[] deadlineArr = input.split("/");
            if (deadlineArr.length != 3 || deadlineArr[0].length() <= 6
                    || deadlineArr[1].length() <= 5 || deadlineArr[2].length() <= 3) {
                response = ui.invalidEventInput();
                return response;
            }

            String name = deadlineArr[0].substring(6);
            String start = deadlineArr[1].substring(5);
            String end = deadlineArr[2].substring(3);

            String[] fullDateTokenStart = start.split(" ");
            String[] dateTokenStart = fullDateTokenStart[0].split("-");
            String[] fullDateTokenEnd = end.split(" ");
            String[] dateTokenEnd = fullDateTokenEnd[0].split("-");

            if (dateTokenStart.length != 3 || dateTokenEnd.length != 3) {
                response = ui.invalidDate();
                return response;
            }

            String startYear = dateTokenStart[0];
            String startMonth = dateTokenStart[1];
            String startDay = dateTokenStart[2];
            String startHour = fullDateTokenStart[1].substring(0, 2);
            String startMinute = fullDateTokenStart[1].substring(2);

            String endYear = dateTokenEnd[0];
            String endMonth = dateTokenEnd[1];
            String endDay = dateTokenEnd[2];
            String endHour = fullDateTokenEnd[1].substring(0, 2);
            String endMinute = fullDateTokenEnd[1].substring(2);

            if (checkEventDateValidity(startYear, startMonth, startDay, endYear, endMonth, endDay)) {
                response = ui.invalidDate();
                return response;
            }

            if (fullDateTokenStart[1].length() != 4 && fullDateTokenEnd[1].length() != 4) {
                response = ui.twentyFourHourClock();
                return response;
            }

            LocalDateTime ldtStart =
                    dateTimeSystem.createDateTime(startYear, startMonth, startDay, startHour, startMinute);
            LocalDateTime ldtEnd =
                    dateTimeSystem.createDateTime(endYear, endMonth, endDay, endHour, endMinute);

            if (checkEventDateBeforeCurrent(ldtStart, ldtEnd)) {
                response = ui.dateBeforeCurrent();
                return response;
            }

            assertEventDateTime(startYear, startMonth, startDay, endYear, endMonth, endDay);

            Events tempEvent = new Events(name, ldtStart, ldtEnd);
            response = tempEvent.addTask(tempEvent);

        } catch (StringIndexOutOfBoundsException | IOException e) {
            response = ui.empty_event();
        }
        return response;
    }

    /**
     * Checks the validity of the event start and end date components (year, month, day).
     * The year is valid (4 digits long) for both start and end dates.
     * The month is non-empty and at most 2 digits long for both start and end dates.
     * The day is non-empty and at most 2 digits long for both start and end dates.
     *
     * @param startYear  The start year of the event as a string.
     * @param startMonth The start month of the event as a string.
     * @param startDay   The start day of the event as a string.
     * @param endYear    The end year of the event as a string.
     * @param endMonth   The end month of the event as a string.
     * @param endDay     The end day of the event as a string.
     * @return True if any of the start or end date components are invalid, otherwise False.
     */
    private boolean checkEventDateValidity(String startYear, String startMonth,
                                           String startDay, String endYear, String endMonth, String endDay) {
        boolean isStartYearValid = startYear.length() == 4;
        boolean isStartMonthValid = !startMonth.isEmpty() && startMonth.length() <= 2;
        boolean isStartDayValid = !startDay.isEmpty() && startDay.length() <= 2;

        boolean isEndYearValid = endYear.length() == 4;
        boolean isEndMonthValid = !endMonth.isEmpty() && endMonth.length() <= 2;
        boolean isEndDayValid = !endDay.isEmpty() && endDay.length() <= 2;

        boolean isStartMonthDayValid = Arrays.asList(startMonth, startDay).stream()
                .allMatch(this::isValidMonthDay);

        boolean isEndMonthDayValid = Arrays.asList(endMonth, endDay).stream()
                .allMatch(this::isValidMonthDay);

        return !isStartMonthDayValid || !isEndMonthDayValid
                || !isStartYearValid || !isStartMonthValid || !isStartDayValid
                || !isEndYearValid || !isEndMonthValid || !isEndDayValid;
    }

    /**
     * Verifies if the event start and end date-times are valid compared to the current system date-time.
     * The start date-time occurs before the current system time.
     * The end date-time occurs before the current system time.
     * The end date-time does not occur before the start date-time.
     *
     * @param ldtStart The start date-time of the event as a LocalDateTime.
     * @param ldtEnd   The end date-time of the event as a LocalDateTime.
     * @return True if any of the date-time comparisons are invalid, otherwise False.
     */
    private boolean checkEventDateBeforeCurrent(LocalDateTime ldtStart, LocalDateTime ldtEnd) {
        boolean isBeforeStart = dateTimeSystem.compareDateTime(ldtStart);
        boolean isBeforeEnd = dateTimeSystem.compareDateTime(ldtEnd);
        boolean isEndBeforeStart = ldtEnd.isBefore(ldtStart);

        return !isBeforeEnd || !isBeforeStart || isEndBeforeStart;
    }

    /**
     * Asserts that the provided event start and end date components (year, month, day) are valid.
     * This method enforces through assertions that:
     * - The start and end years are 2024 or later.
     * - The start and end months are between 1 and 12, inclusive.
     * - The start and end days are between 1 and 31, inclusive.
     *
     * @param startYear  The start year of the event as a string.
     * @param startMonth The start month of the event as a string.
     * @param startDay   The start day of the event as a string.
     * @param endYear    The end year of the event as a string.
     * @param endMonth   The end month of the event as a string.
     * @param endDay     The end day of the event as a string.
     * @throws AssertionError if the start or end year, month, or day does not meet the required conditions.
     */
    private void assertEventDateTime(String startYear, String startMonth,
                                     String startDay, String endYear, String endMonth, String endDay) {
        assert Integer.parseInt(startYear) >= 2024;
        assert Integer.parseInt(startMonth) > 0 && Integer.parseInt(startMonth) <= 12;
        assert Integer.parseInt(startDay) > 0 && Integer.parseInt(startDay) <= 31;

        assert Integer.parseInt(endYear) >= 2024;
        assert Integer.parseInt(endMonth) > 0 && Integer.parseInt(endMonth) <= 12;
        assert Integer.parseInt(endDay) > 0 && Integer.parseInt(endDay) <= 31;
    }

    /**
     * Deletes a task from the task list based on the input.
     * The last character of the input string is used to determine the task number to be deleted.
     *
     * @param input Input string that determines the task to be deleted.
     * @return A String indicating the result of deleting a task.
     * @throws IOException If an I/O error occurs during the deletion operation.
     */
    public String performDelete(String input) throws IOException {
        int listNo = Integer.parseInt(input.substring(7));
        if (listNo <= TaskList.tasks.size() && listNo > 0) {
            assert listNo <= TaskList.tasks.size() && listNo > 0;
            return Task.deleteTask(listNo);
        } else {
            return ui.indexOutOfBounds();
        }
    }
}
