package duck.common;

/**
 * Contains constants to be used across multiple classes.
 */
public class Message {

    public static final String INVALID_INDEX_FORMAT = "Quack! you have to indicate a valid task index!\n";
    public static final String INDEX_OUT_OF_BOUNDS = "Quack! Index out of bound :( Input a valid task index.\n";
    public static final int TASK_LIST_FIRST_INDEX = 1;
    public static final String INVALID_KEYWORD_BY_FOR_ALL =
            "For target 'all', you can only sort by 'description' or 'type'.\n";
    public static final String INVALID_KEYWORD_BY_FOR_TODO =
            "For target 'todo', you can only sort by 'description'.\n";
    public static final String INVALID_KEYWORD_BY_FOR_DEADLINE =
            "For target 'deadline', you can only sort by 'description' or 'deadline'.\n";
    public static final String INVALID_KEYWORD_BY_FOR_EVENT =
            "For target 'event', you can only sort by 'description', 'start', or 'end'.\n";
}
