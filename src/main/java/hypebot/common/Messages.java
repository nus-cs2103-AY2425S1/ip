package hypebot.common;

public class Messages {
    public static final String LOGO = """
                 ('-. .-.               _ (`-.    ('-. .-. .-')                .-') _  \s
                ( OO )  /              ( (OO  ) _(  OO)\\  ( OO )              (  OO) ) \s
                ,--. ,--.  ,--.   ,--._.`     \\(,------.;-----.\\  .-'),-----. /     '._\s
                |  | |  |   \\  `.'  /(__...--'' |  .---'| .-.  | ( OO'  .-.  '|'--...__)
                |   .|  | .-')     /  |  /  | | |  |    | '-' /_)/   |  | |  |'--.  .--'
                |       |(OO  \\   /   |  |_.' |(|  '--. | .-. `. \\_) |  |\\|  |   |  |  \s
                |  .-.  | |   /  /\\_  |  .___.' |  .--' | |  \\  |  \\ |  | |  |   |  |  \s
                |  | |  | `-./  /.__) |  |      |  `---.| '--'  /   `'  '-'  '   |  |  \s
                `--' `--'   `--'      `--'      `------'`------'      `-----'    `--'  \s
                """;
    public static final String HELP_MESSAGE = "";
    public static final String GREET_INTRO = "AYO WHAT'S UP IT'S ME YOUR\n\n";
    public static final String GREET_OUTRO = "\nWhat can I do for you, my wonderful homie?\n";
    public static final String ERROR_INTRO = "I might be tripping bro, my bad, my bad - \n";
    public static final String LOADING_TASKLIST_MESSAGE = "LOADING YOUR TASKS IN /data/tasklist.txt...\n";
    public static final String LOADING_TASKLIST_ERROR = "but I couldn't find the file with your saved tasks.\n";
    public static final String SAVING_TASKLIST_MESSAGE = "Alright homie, saving your tasks to /data/tasklist.txt...\n";
    public static final String SAVING_TASKLIST_ERROR = "but I couldn't find the file to save your tasks.\n";
    public static final String LIST_INTRO = "ALRIGHT, Here's that list!\n";
    public static final String HAPPENING_INTRO = "ALRIGHT, Here's everything that's going down on ";
    public static final String TASK_NAME_EMPTY_ERROR = "drop the name of the task, bro I gotta know!\n";
    public static final String ADDED_TASK_MESSAGE = "HECK YEAH, ADDED:\n  ";
    public static final String DELETED_TASK_MESSAGE = "Say no more, BABY BYE BYE BYE to this task:\n ";
    public static final String MARKED_TASK_MESSAGE = "AIGHT, ABSOLUTELY CONQUERED THIS TASK:\n  ";
    public static final String UNMARKED_TASK_MESSAGE = "AIGHT, LET'S GET READY TO CONQUER THIS TASK:\n  ";
    public static final String NUMBER_OF_TASKS_LEFT_INTRO = "!\nYOU'VE NOW GOT ";
    public static final String NUMBER_OF_TASKS_LEFT_OUTRO = " TASKS TO GO!\n";
    public static final String TASK_NUMBER_TO_MARK_MISSING_ERROR = "try indicating the index of the task you wanna "
            + "mark CONQUERED as a number!\n";
    public static final String TASK_NUMBER_TO_MARK_OUT_OF_BOUNDS_ERROR = "try indicating the index of an existing task "
            + "you wanna mark CONQUERED!\n";
    public static final String TASK_NUMBER_TO_UNMARK_MISSING_ERROR = "try indicating the index of the task you wanna "
            + "TAKE ON AGAIN as a number!\n";
    public static final String TASK_NUMBER_TO_UNMARK_OUT_OF_BOUNDS_ERROR = "try indicating the index of an existing "
            + "task you wanna TAKE ON AGAIN!\n";
    public static final String TASK_NUMBER_TO_DELETE_MISSING_ERROR = "try indicating the index of the task you wanna "
            + "delete as a number!\n";
    public static final String TASK_NUMBER_TO_DELETE_OUT_OF_BOUNDS_ERROR = "try indicating the index of an existing "
            + "task you wanna delete!\n";
    public static final String DUE_DATE_MISSING_ERROR = """
            make sure you got the due date for that SWAGGIN' \
            deadline you got!
            Put your due date after a '/' to indicate that you're inputting a due date!
            """;
    public static final String EVENT_TIME_MISSING_ERROR = """
            make sure you got that start time AND end time for \
            that AWESOME event you got!
            Put a '/' before your start time and end time to indicate you're inputting a time!
            """;
    public static final String SEARCH_DATE_MISSING_ERROR = """
            make sure you got the date you're searching for!
            Put your due date after a '/' to indicate that you're inputting a search date!
            """;
    public static final String DUE_DATE_PARSE_ERROR = """
            but I couldn't catch that due date.
            Try formatting your date in this format: yyyy-MM-dd
            """;
    public static final String EVENT_TIME_PARSE_ERROR = """
            but I couldn't catch the dates you put.
            Try formatting your dates in this format: yyyy-MM-dd HH:mm
            """;
    public static final String SEARCH_DATE_PARSE_ERROR = """
                            but I couldn't catch the search date that you put.
                            Try formatting your due date in this format: yyyy-MM-dd
                            """;
    public static final String UNKNOWN_COMMAND_ERROR_INTRO = "but I don't think we're vibing when you say '";
    public static final String UNKNOWN_COMMAND_ERROR_OUTRO = "'.\nMind if I ask you for anything else, homie?\n";
    public static final String EXIT_MESSAGE = """
                Alright homie, it's been a BLAST hanging out with you. \
                Have a wonderful day, and catch you soon again you ABSOLUTE BALLER!
                """;
}
