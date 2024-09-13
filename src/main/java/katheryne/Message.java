package katheryne;

/**
 * Message Class is to store the strings that will be used in
 * generation of corresponding responses.
 */

public class Message {
    public static final String DIVIDE  = "✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧✦✧";
    public static final String MESSAGE_GOODBYE = "Katheryne: " +
            "Farewell, Adventurer, and thank you for supporting the Adventurers' Guild.";
    public static final String MESSAGE_GREETING = "Katheryne: " +
            "Ad astra abyssosque! I'm Katheryne, the receptionist here at the Adventurers' Guild. Welcome!";
    public static final String MESSAGE_LIST = "Katheryne: " +
            "Here are your tasks on record:\n";
    public static final String MESSAGE_ADD = "Katheryne: " +
            "Got it. I've added this task:\n    %s\n Now you have %d tasks in the list.";
    public static final String MESSAGE_HAS_DUPLICATE = "Katheryne: This task is already in your task list.";
    public static final String MESSAGE_MARK = "Katheryne: Congrats, I've marked this task as done:\n    %s";
    public static final String MESSAGE_UNMARK = "Katheryne: Sure, I've marked this task as not done yet:\n    %s";
    public static final String MESSAGE_DELETE = "Katheryne: OK, I've deleted this task from your list:\n    %s\nNow you have %d tasks in the list.";
    public static final String MESSAGE_DATE_FORMAT_ERROR = "Please enter date in 'yyyy-MM-dd' format";
    public static final String MESSAGE_INDEX_ERROR = "Katheryne: That task number doesn't exist. Please try again.";
    public static final String MESSAGE_LOADING_ERROR = "Katheryne: No saved task found. Will start a new one.";
    public static final String MESSAGE_FIND_RESULT = "Katheryne: Here are the matching tasks in your list:";
    public static final String MESSAGE_TASK_NOT_FOUND = "Katheryne: There is no matching task in your list.";
}
