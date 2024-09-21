package ava.files;

import ava.task.Task;
import ava.task.tasks.Deadline;
import ava.task.tasks.Event;
import ava.task.tasks.TaskType;
import ava.task.tasks.Todo;

class DataManager {

    static String serialize(Task task) {
        return task.serialize();
    }

    // CHECKSTYLE:OFF: AbbreviationAsWordInName
    // CHECKSTYLE:OFF: LocalFinalVariableName
    static Task deserialize(String task) {
        /*
         * parse for type
         * create task
         */
        if (task.isEmpty()) {
            throw new IllegalArgumentException("Task identifier missing");
        }

        final int TASK_TYPE_LOCATION = 0;
        final int IS_DONE_LOCATION = 1;
        final String SEPARATOR = ",";
        TaskType taskType = getTaskType(task.charAt(TASK_TYPE_LOCATION));
        String[] taskInfo = task.split(SEPARATOR);
        switch (taskType) {
        case TODO -> {
            final int TASK_LENGTH = 3;
            final int DESCRIPTION_POSITION = 2;
            if (taskInfo.length != TASK_LENGTH) {
                throw new IllegalArgumentException("Invalid Todo Task");
            }
            String description = taskInfo[DESCRIPTION_POSITION];
            boolean isDone = isMarkedDone(taskInfo[IS_DONE_LOCATION]);
            return new Todo(description, isDone);
        }
        case DEADLINE -> {
            final int DEADLINE_LENGTH = 4;
            final int DESCRIPTION_POSITION = 2;
            final int TIME_POSITION = 3;
            if (taskInfo.length != DEADLINE_LENGTH) {
                throw new IllegalArgumentException("Invalid Deadline Task");
            }
            String description = taskInfo[DESCRIPTION_POSITION];
            boolean isDone = isMarkedDone(taskInfo[IS_DONE_LOCATION]);
            String time = taskInfo[TIME_POSITION];
            return new Deadline(description, isDone, time);
        }
        case EVENT -> {
            final int EVENT_LENGTH = 5;
            final int DESCRIPTION_POSITION = 2;
            final int START_TIME_POSITION = 3;
            final int END_TIME_POSITION = 4;
            if (taskInfo.length != EVENT_LENGTH) {
                throw new IllegalArgumentException("Invalid Event Task");
            }
            String description = taskInfo[DESCRIPTION_POSITION];
            boolean isDone = isMarkedDone(taskInfo[IS_DONE_LOCATION]);
            String startTime = taskInfo[START_TIME_POSITION];
            String endTime = taskInfo[END_TIME_POSITION];
            return new Event(description, isDone, startTime, endTime);
        }
        default -> throw new IllegalStateException("Unexpected value: " + taskType);
        }
    }
    //CHECKSTYLE:ON: AbbreviationAsWordInName
    //CHECKSTYLE:ON: LocalFinalVariableName

    static TaskType getTaskType(char c) {
        return switch(c) {
        case 'T' -> TaskType.TODO;
        case 'D' -> TaskType.DEADLINE;
        case 'E' -> TaskType.EVENT;
        default -> throw new IllegalArgumentException("Invalid Task Type");
        };
    }

    static boolean isMarkedDone(String isDone) {
        if (isDone.equals("1")) {
            return true;
        } else if (isDone.equals("0")) {
            return false;
        } else {
            throw new IllegalArgumentException("Invalid isDone value");
        }
    }
}
