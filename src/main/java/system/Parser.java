package system;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

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
        return input.equals(("bye"));
    }

    /**
     * Checks if the provided input string equals, ignoring case sensitivity, to the keyword "list".
     *
     * @param input The String input to be checked.
     * @return true if the input string contains the substring "list"; false otherwise.
     */
    public boolean containList(String input) {
        return input.equalsIgnoreCase("list");
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
        String name = input.substring(5);
        return Task.findTask(name);
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
     * Performs a marking or unmarking operation on a task based on the provided input string.
     * The last character of the input is used to determine the index of the task to be marked or unmarked.
     * If the input indicates an unmarking operation, the task is unmarked; otherwise, it is marked.
     *
     * @param input Input string that determines the task to be marked or unmarked.
     * @return A String indicating the result of marking or unmarking the task.
     * @throws IOException If an I/O error occurs during the operation.
     */
    public String performMark(String input) throws IOException {
        int listNo;
        if (containUnmark(input)) {
            listNo = Integer.parseInt(input.substring(7));
            System.out.println("===debug=== listno: " + listNo);
            if (listNo != TaskList.tasks.size() && listNo <= 0) {
                return ui.indexOutOfBounds();
            }
            return Task.unmark_task(listNo);
        }

        listNo = Integer.parseInt(input.substring(5));
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

            if (tokens.length != 2 && dateTokens.length != 3) {
                response = ui.invalidDate();
                return response;
            }

            String time = tokens[1];
            String year = dateTokens[0];
            String month = dateTokens[1];
            String day = dateTokens[2];

            boolean isYearValid = year.length() == 4;
            boolean isMonthValid = !month.isEmpty() && month.length() <= 2;
            boolean isDayValid = !day.isEmpty() && day.length() <= 2;

            if (!isYearValid || !isMonthValid || !isDayValid) {
                response = ui.invalidDate();
                return response;
            }

            if (time.length() != 4) {
                response = ui.twentyFourHourClock();
                return response;
            }

            String hour = time.substring(0, 2);
            String minute = time.substring(2);

            LocalDateTime ldt = dateTimeSystem.createDate(year, month, day, hour, minute);
            boolean isBefore = dateTimeSystem.compareDateTime(ldt);
            if (!isBefore) {
                response = ui.dateBeforeCurrent();
                return response;
            }

            Deadlines tempDeadline = new Deadlines(name, ldt);
            response = tempDeadline.addTask(tempDeadline);
            assert Integer.parseInt(year) >= 2024;
            assert Integer.parseInt(month) > 0 && Integer.parseInt(month) <= 12;
            assert Integer.parseInt(day) > 0 && Integer.parseInt(day) <= 31;

        } catch (StringIndexOutOfBoundsException
                 | IOException e) {
            response = ui.empty_deadline();
        }
        return response;
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

            boolean isStartYearValid = startYear.length() == 4;
            boolean isStartMonthValid = !startMonth.isEmpty() && startMonth.length() <= 2;
            boolean isStartDayValid = !startDay.isEmpty() && startDay.length() <= 2;

            boolean isEndYearValid = endYear.length() == 4;
            boolean isEndMonthValid = !endMonth.isEmpty() && endMonth.length() <= 2;
            boolean isEndDayValid = !endDay.isEmpty() && endDay.length() <= 2;

            if (!isStartYearValid || !isStartMonthValid || !isStartDayValid
                    || !isEndYearValid || !isEndMonthValid || !isEndDayValid) {
                response = ui.invalidDate();
                return response;
            }

            if (fullDateTokenStart[1].length() != 4 && fullDateTokenEnd[1].length() != 4) {
                response = ui.twentyFourHourClock();
                return response;
            }

            LocalDateTime ldtStart =
                    dateTimeSystem.createDate(startYear, startMonth, startDay, startHour, startMinute);
            LocalDateTime ldtEnd =
                    dateTimeSystem.createDate(endYear, endMonth, endDay, endHour, endMinute);

            boolean isBeforeStart = dateTimeSystem.compareDateTime(ldtStart);
            boolean isBeforeEnd = dateTimeSystem.compareDateTime(ldtEnd);
            boolean isEndBeforeStart = ldtEnd.isBefore(ldtStart);

            if (!isBeforeEnd || !isBeforeStart || isEndBeforeStart) {
                response = ui.dateBeforeCurrent();
                return response;
            }

            assert Integer.parseInt(startYear) >= 2024;
            assert Integer.parseInt(startMonth) > 0 && Integer.parseInt(startMonth) <= 12;
            assert Integer.parseInt(startDay) > 0 && Integer.parseInt(startDay) <= 31;

            assert Integer.parseInt(endYear) >= 2024;
            assert Integer.parseInt(endMonth) > 0 && Integer.parseInt(endMonth) <= 12;
            assert Integer.parseInt(endDay) > 0 && Integer.parseInt(endDay) <= 31;

            Events tempEvent = new Events(name, ldtStart, ldtEnd);
            response = tempEvent.addTask(tempEvent);

        } catch (StringIndexOutOfBoundsException | IOException e) {
            response = ui.empty_event();
        }
        return response;
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
            return Task.delete_task(listNo);
        } else {
            return ui.indexOutOfBounds();
        }
    }
}
