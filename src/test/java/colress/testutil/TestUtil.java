package colress.testutil;

import java.time.LocalDate;
import java.time.LocalTime;

import colress.TaskType;

/**
 * A utility class for test cases.
 */
public class TestUtil {
    public static final String WHITESPACE = " ";
    public static final String DELIMITER = ",";
    public static final String EMPTY_STRING = "";

    public static final String VALID_DESCRIPTION_ONE = "go jogging";
    public static final String VALID_KEYWORD_ONE = "jog";
    public static final String VALID_DESCRIPTION_TWO = "go running";
    public static final String VALID_KEYWORD_TWO = "run";
    public static final String VALID_DESCRIPTION_THREE = "go sprinting";
    public static final String VALID_KEYWORD_THREE = "sprint";
    public static final String VALID_KEYWORD_ALL = "go";
    public static final String VALID_KEYWORD_NONE = "no";

    public static final String VALID_DATE_ARGUMENT_ONE = "2024-05-04";
    public static final LocalDate VALID_DATE_ONE = LocalDate.of(2024, 5, 4);
    public static final String VALID_DATE_ARGUMENT_TWO = "2024-05-05";
    public static final LocalDate VALID_DATE_TWO = LocalDate.of(2024, 5, 5);
    public static final String INVALID_DATE_ARGUMENT = "2024/05/05";

    public static final String VALID_FROM_TIME_ARGUMENT_ONE = "14:30";
    public static final LocalTime VALID_FROM_TIME_ONE = LocalTime.of(14, 30);
    public static final String VALID_TO_TIME_ARGUMENT_ONE = "15:00";
    public static final LocalTime VALID_TO_TIME_ONE = LocalTime.of(15, 0);
    public static final String VALID_FROM_TIME_ARGUMENT_TWO = "12:00";
    public static final LocalTime VALID_FROM_TIME_TWO = LocalTime.of(12, 0);
    public static final String VALID_TO_TIME_ARGUMENT_TWO = "13:00";
    public static final LocalTime VALID_TO_TIME_TWO = LocalTime.of(13, 0);
    public static final String INVALID_TIME_ARGUMENT = "10.00";


    public static final String VALID_TASK_TYPE_ARGUMENT_TODO = "TodO";
    public static final TaskType VALID_TASK_TYPE_TODO = TaskType.TODO;
    public static final String VALID_TASK_TYPE_ARGUMENT_DEADLINE = "DeADLinE";
    public static final TaskType VALID_TASK_TYPE_DEADLINE = TaskType.DEADLINE;
    public static final String VALID_TASK_TYPE_ARGUMENT_EVENT = "EVenT";
    public static final TaskType VALID_TASK_TYPE_EVENT = TaskType.EVENT;
    public static final String INVALID_TASK_TYPE_ARGUMENT = "goodbye";

    public static final String VALID_MULTIPLE_TASK_NUMBERS_ARGUMENT = "1 2 3";
    public static final int[] VALID_MULTIPLE_TASK_NUMBERS = new int[]{1, 2, 3};
    public static final String VALID_ONE_TASK_NUMBER_ARGUMENT = "1";
    public static final int[] VALID_ONE_TASK_NUMBER = new int[]{1};
    public static final String INVALID_TASK_NUMBERS_ARGUMENT = "three";

    public static final String VALID_COMMAND_WORD_ADD = "aDd";
    public static final String VALID_COMMAND_WORD_CHECK = "cHeCk";
    public static final String VALID_COMMAND_WORD_DATE = "dATe";
    public static final String VALID_COMMAND_WORD_DELETE = "dElETe";
    public static final String VALID_COMMAND_WORD_EXIT = "ExIT";
    public static final String VALID_COMMAND_WORD_FIND = "fiND";
    public static final String VALID_COMMAND_WORD_LIST = "LISt";
    public static final String VALID_COMMAND_WORD_TOGGLE = "tOGgLe";
    public static final String VALID_COMMAND_WORD_UNCHECK = "UnCHeCk";
    public static final String INVALID_COMMAND_WORD = "hello";
}
