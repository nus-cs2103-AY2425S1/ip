package monique;

/**
 * The <code>GuideText</code> class stores a guide text message that informs users how to use the commands
 * in the Monique application.
 *
 * This class provides a clear explanation of how to use different commands such as listing tasks,
 * marking/unmarking tasks, adding todos, deadlines, and events, and managing them with delete and exit commands.
 */
public class GuideText {
    /**
     * A constant string that provides detailed instructions on how to use various commands in the Monique application.
     *
     * It covers the following commands:
     * <ul>
     *  <li>Listing tasks</li>
     *  <li>Marking and unmarking tasks</li>
     *  <li>Adding todos, deadlines, and events</li>
     *  <li>Deleting tasks</li>
     *  <li>Exiting the application</li>
     * </ul>
     */
    public static final String GUIDE = """
            Here are the following commands:
            1. **List Tasks**:
               To list all current tasks, type:
               "list"
               This will display all tasks with their statuses.

            2. **Mark/Unmark a Task**:
               To mark a task as complete, type:
               "mark {task_number}"
               Example: "mark 1" marks task 1 as complete.

               To unmark a task as incomplete, type:
               "unmark {task_number}"
               Example: "unmark 2" marks task 2 as incomplete.

               Note: Tasks are indexed from 1.

            3. **Add a Todo Task**:
               To add a todo item, type:
               "todo {description}"
               Example: "todo read a book" adds "read a book" to your tasks.

            4. **Add a Deadline Task**:
               To add a deadline item, type:
               "deadline {description} /by {date} {optional_time}"
               Example: "deadline submit report /by 12-12-2024 1700" adds a task with a deadline of 12th 
               December 2024, 5:00 PM.

               Valid date formats:
               - Days of the week: "Monday" to "Sunday" or abbreviations "mon" to "sun"
               - Date formats: "DD/MM/YYYY", "DD-MM-YYYY", or "tomorrow"

               Valid time formats:
               - "HHmm" (24-hour format)
               - "h:mmam" or "h:mmpm" (12-hour format with am/pm)

            5. **Add an Event Task**:
               To add an event item, type:
               "event {description} /from {start_date} {start_time} /to {end_date} {end_time}"
               Example: "event meeting /from tomorrow 1400 /to 12-12-2024 1700" schedules an event from tomorrow at 2:00 PM until 12th December 2024, 5:00 PM.

            6. **Delete a Task**:
               To delete a task, type:
               "delete {task_number}"
               Example: "delete 3" deletes task number 3.

            7. **Exit the Application**:
               To exit the application, type:
               "bye"
               This will save your progress and close the application.""";
}
