package system;

import task.Deadlines;
import task.Events;
import task.Task;
import task.ToDos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

public class Parser {
    Ui ui = new Ui();

    DateTimeSystem dateTimeSystem = new DateTimeSystem();

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
     * Checks if the provided input string contains the keyword "todotask".
     *
     * @param input The String input to be checked.
     * @return true if the input string contains the substring "todotask"; false otherwise.
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
     * @throws IOException If an I/O error occurs during the operation.
     */
    public String performMark(String input) throws IOException {
        int list_no = Character.getNumericValue(input.charAt(input.length() - 1));
        if (containUnmark(input)) {
            return Task.unmark_task(list_no);
        } else {
            return Task.mark_task(list_no);
        }
    }

    /**
     * Creates a new ToDoTask based on the input and adds it to the task list.
     * The 6th character of the input till the last character is the Task Name.
     * If an error occurs due to an invalid string index access or I/O issues, a customized error message will be displayed.
     *
     * @param input Input string containing the command to create a ToDoTask as well as the name of the task.
     * @throws StringIndexOutOfBoundsException If the input string is too short to contain a valid task name.
     * @throws IOException                     If an I/O error occurs while adding the task.
     */
    public String performToDo(String input) {
        String response = "";
        try {
            String name = input.substring(5);
            ToDos temp_todo = new ToDos(name);
            response = temp_todo.addTask(temp_todo);
        } catch (StringIndexOutOfBoundsException | IOException e) {
            response = ui.empty_todo();
        }

        return response;
    }

    /**
     * Creates a new Deadline task based on the input and adds it to the task list.
     * Input string contains the task name and a deadline date, separated by a "/".
     * The 10th character of the task name input till the last character is the actual task name.
     * The 3rd character of the deadline date till the last character is the actual date, which is then parsed into a LocalDateTime object.
     * If an error occurs due to invalid input or I/O issues, a customized error message will be displayed.
     *
     * @param input Input string containing the command to create a Deadline task.
     *              The format of the string should be "deadline <task name> /by <YYYY-MM-DD HHMM>".
     * @throws StringIndexOutOfBoundsException If the input string does not adhere to the expected format.
     */
    public String performDeadline(String input) {
        String response = "";
        try {
            String[] deadline_arr = input.split("/");

            if (deadline_arr.length != 2 || deadline_arr[1].length() <= 3) {
                response = ui.invalidDeadlineInput();
            } else {
                String name = deadline_arr[0].substring(9);
                String date = deadline_arr[1].substring(3);

                String[] tokens = date.split(" ");
                String[] dateTokens = tokens[0].split("-");

                if (tokens.length == 2 && dateTokens.length == 3) {
                    String time = tokens[1];
                    String year = dateTokens[0];
                    String month = dateTokens[1];
                    String day = dateTokens[2];

                    if (year.length() == 4 && !month.isEmpty() && month.length() <= 2 && !day.isEmpty() && day.length() <= 2) {
                        if (time.length() == 4) {
                            String hour = time.substring(0, 2);
                            String minute = time.substring(2);

                            LocalDateTime ldt = dateTimeSystem.createDate(year, month, day, hour, minute);
                            Deadlines tempDeadline = new Deadlines(name, ldt);
                            response = tempDeadline.addTask(tempDeadline);
                        } else {
                            response = ui.twentyFourHourClock();
                        }
                    } else {
                        response = ui.invalidDate();
                    }
                } else {
                    response = ui.invalidDate();
                }
            }
        } catch (StringIndexOutOfBoundsException |
                 IOException e) {
            response = ui.empty_deadline();
        }

        return response;
    }

    /**
     * Creates a new Event task based on the input and adds it to the task list.
     * The input string contains the event name, start time, and end time, separated by "/".
     * The 6th character of the event name input till the last character is the actual event name.
     * The 5th character of the start time input till the last character is the actual start time, which is then parsed into a LocalDateTime object.
     * The 3rd character of the end time input till the last character is the actual end time, which is then parsed into a LocalDateTime object.
     * If an error occurs due to invalid input or I/O issues, a customized error message is displayed.
     *
     * @param input Input string containing the command to create an Event task.
     *              The format of the string should be "event <event name> /from <YYYY-MM-DD HHMM> /to <YYYY-MM-DD HHMM>".
     * @throws StringIndexOutOfBoundsException If the input string does not adhere to the expected format.
     */
    public String performEvent(String input) {
        String response = "";
        try {
            String[] deadlineArr = input.split("/");
            if (deadlineArr.length != 3 || deadlineArr[0].length() <= 6 || deadlineArr[1].length() <= 5 || deadlineArr[2].length() <= 3) {
                response = ui.invalidEventInput();
            } else {
                String name = deadlineArr[0].substring(6);
                String start = deadlineArr[1].substring(5);
                String end = deadlineArr[2].substring(3);

                String[] fullDateTokenStart = start.split(" ");
                String[] dateTokenStart = fullDateTokenStart[0].split("-");
                String[] fullDateTokenEnd = end.split(" ");
                String[] dateTokenEnd = fullDateTokenEnd[0].split("-");

                if (dateTokenStart.length != 3 || dateTokenEnd.length != 3) {
                    response = ui.invalidDate();
                } else {
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

                    if (startYear.length() == 4 && !startMonth.isEmpty() && startMonth.length() <= 2 && !startDay.isEmpty() && startDay.length() <= 2
                            && endYear.length() == 4 && !endMonth.isEmpty() && endMonth.length() <= 2 && !endDay.isEmpty() && endDay.length() <= 2) {
                        if (fullDateTokenStart[1].length() == 4 && fullDateTokenEnd[1].length() == 4) {
                            LocalDateTime ldtStart = dateTimeSystem.createDate(startYear, startMonth, startDay, startHour, startMinute);
                            LocalDateTime ldtEnd = dateTimeSystem.createDate(endYear, endMonth, endDay, endHour, endMinute);

                            Events temp_event = new Events(name, ldtStart, ldtEnd);
                            response = temp_event.addTask(temp_event);
                        } else {
                            response = ui.twentyFourHourClock();
                        }
                    } else {
                        response = ui.invalidDate();
                    }
                }
            }
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
     * @throws IOException If an I/O error occurs during the deletion operation.
     */
    public String performDelete(String input) throws IOException {
        int list_no = Character.getNumericValue(input.charAt(input.length() - 1));
        return Task.delete_task(list_no);
    }
}
