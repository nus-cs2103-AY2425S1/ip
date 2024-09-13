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

    public static final String GUIDE =
            "Here are the following commands:\n"
            + "1. **List Tasks**:\n"
            + "   To list all current tasks, type:\n"
            + "   \"list\"\n"
            + "   This will display all tasks with their statuses.\n\n"

            + "2. **Mark/Unmark a Task**:\n"
            + "   To mark a task as complete, type:\n"
            + "   \"mark {task_number}\"\n"
            + "   Example: \"mark 1\" marks task 1 as complete.\n\n"

            + "   To unmark a task as incomplete, type:\n"
            + "   \"unmark {task_number}\"\n"
            + "   Example: \"unmark 2\" marks task 2 as incomplete.\n\n"

            + "   Note: Tasks are indexed from 1.\n\n"

            + "3. **Add a Todo Task**:\n"
            + "   To add a todo item, type:\n"
            + "   \"todo {description}\"\n"
            + "   Example: \"todo read a book\" adds \"read a book\" to your tasks.\n\n"

            + "4. **Add a Deadline Task**:\n"
            + "   To add a deadline item, type:\n"
            + "   \"deadline {description} /by {date} {optional_time}\"\n"
            + "   Example: \"deadline submit report /by 12-12-2024 1700\" adds a task with a deadline of 12th "
            + "December 2024, 5:00 PM.\n\n"

            + "   Valid date formats:\n"
            + "   - Days of the week: \"Monday\" to \"Sunday\" or abbreviations \"mon\" to \"sun\"\n"
            + "   - Date formats: \"DD/MM/YYYY\", \"DD-MM-YYYY\", or \"tomorrow\"\n\n"

            + "   Valid time formats:\n"
            + "   - \"HHmm\" (24-hour format)\n"
            + "   - \"h:mmam\" or \"h:mmpm\" (12-hour format with am/pm)\n\n"

            + "5. **Add an Event Task**:\n"
            + "   To add an event item, type:\n"
            + "   \"event {description} /from {start_date} {start_time} /to {end_date} {end_time}\"\n"
            + "   Example: \"event meeting /from tomorrow 1400 /to 12-12-2024 1700\" schedules an event from tomorrow "
            + "at 2:00 PM until 12th December 2024, 5:00 PM.\n\n"

            + "6. **Delete a Task**:\n"
            + "   To delete a task, type:\n"
            + "   \"delete {task_number}\"\n"
            + "   Example: \"delete 3\" deletes task number 3.\n\n"

            + "7. **Exit the Application**:\n"
            + "   To exit the application, type:\n"
            + "   \"bye\"\n"
            + "   This will save your progress and close the application.";

}
