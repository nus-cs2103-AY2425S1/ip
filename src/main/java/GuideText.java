public class GuideText {
    //this file is created to store the long string message to inform users how to use the commands

    public static final String GUIDE = """
    Here are the following commands:

            1. To list out current things on your todo list, type "list"

            2. To mark or unmark out an item as complete/incomplete, type "mark"/"unmark" followed by the item number. Note that this is done on 1-based indexing

    For example, "mark 1" will mark item 1 as complete.
    Similarly, "unmark 2" will mark item 2 as incomplete.

            3. To exit, type "bye"

            4. To create a "todo" item, type "todo" followed by the item description
    e.g: "todo do homework" adds "do homework" as an todo task.

            5. To create a "deadline" item, type "deadline" followed by the item description, and finally followed by a deadline "/by deadline"
    e.g: "deadline do homework /by tomorrow" adds a deadline for homework, due tomorrow.

            6. To create an "event" item, type "event" followed by the item description, and followed by a start time "/from start time", and finally followed by an end time "/to end time".
    e.g: "event CS2103 lecture /from tomorrow 12pm /to 4pm" adds an event for CS2103 lecture, from 12pm to 4pm tomorrow.
            
""";
}

