package colress;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A utility class for test cases.
 */
public class TestUtil {
    public static final String WHITESPACE = " ";
    public static final String DELIMITER = ",";
    public static final String VALID_DESCRIPTION_ONE = "go jogging";
    public static final String VALID_DESCRIPTION_TWO = "go running";

    public static final String VALID_KEYWORD_ONE = "go";
    public static final String VALID_KEYWORD_TWO = "jogging";

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

    public static final String VALID_TASK_NUMBERS_ARGUMENT_ONE = "1 2 3";
    public static final int[] VALID_TASK_NUMBERS_ONE = new int[]{1, 2, 3};
    public static final String VALID_TASK_NUMBERS_ARGUMENT_TWO = "3";
    public static final int[] VALID_TASK_NUMBERS_TWO = new int[]{3};
    public static final String INVALID_TASK_NUMBERS_ARGUMENT = "three";

    public static final String VALID_COMMAND_ADD = "aDd";
    public static final String VALID_COMMAND_CHECK = "cHeCk";
    public static final String VALID_COMMAND_DATE = "dATe";
    public static final String VALID_COMMAND_DELETE = "dElETe";
    public static final String VALID_COMMAND_EXIT = "ExIT";
    public static final String VALID_COMMAND_FIND = "fiND";
    public static final String VALID_COMMAND_LIST = "LISt";
    public static final String VALID_COMMAND_TOGGLE = "tOGgLe";
    public static final String VALID_COMMAND_UNCHECK = "UnCHeCk";
    public static final String INVALID_COMMAND = "hello";
}
