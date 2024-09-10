package monique;

/**
 * The <code>GuideText</code> class stores a guide text message that informs users how to use the commands
 * in the Monique application.
 */
public class GuideText {
    //this file is created to store the long string message to inform users how to use the commands

    /**
     * A constant string that provides detailed instructions on how to use various commands in the Monique application.
     * It covers commands such as listing tasks, marking/unmarking tasks, adding tasks with deadlines or events,
     * and deleting tasks.
     */
    public static final String GUIDE = """
    Here are the following commands:

            1. To list out current things on your todo list, type "list"

            2. To mark or unmark out an item as complete/incomplete, type "mark"/"unmark" followed by the item
            number. Note that this is done on 1-based indexing

            For example, "mark 1" will mark item 1 as complete.
            Similarly, "unmark 2" will mark item 2 as incomplete.

            3. To exit, type "bye"

            4. To create a "todo" item, type "todo" followed by the item description
            e.g: "todo do homework" adds "do homework" as an todo task.

            5. To create a "deadline" item, type "deadline" followed by the item description, and finally
            followed by a deadline "/by deadline"


            6. To create an "event" item, type "event" followed by the item description, and followed by a start time
            "/from start time", and finally followed by an end time "/to end time".
            e.g: "event CS2103 lecture /from <from date> /to <to date>" adds an event for CS2103 lecture, from some
            <from date> to some <to date>. Note that the acceptable date format is
            either: M/d/yyyy HHmm, M/d/yyyy, M-d-yyyy HHmm or M-d-yyyy.

                7. To delete an item, type "delete" followed by the item number.""";
}

