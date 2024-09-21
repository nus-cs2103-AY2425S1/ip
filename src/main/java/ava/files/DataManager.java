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


    static Task deserialize(String task) {
        /*
         * parse for type
         * create task
         */
        if(task.isEmpty()){
            throw new IllegalArgumentException("Task identifier missing");
        }

        final int TASK_TYPE_LOCATION = 0;
        final String SEPARATOR = ",";
        TaskType taskType = getTaskType(task.charAt(TASK_TYPE_LOCATION));
        String[] taskInfo = task.split(SEPARATOR);
        switch (taskType) {
        case TODO -> {
            final int TASK_LENGTH = 2;
            final int DESCRIPTION_POSITION = 1;
            if(taskInfo.length != TASK_LENGTH){
                throw new IllegalArgumentException("Invalid Todo Task");
            }
            String description = taskInfo[DESCRIPTION_POSITION];
            return new Todo(description);
        }
        case DEADLINE -> {
            final int DEADLINE_LENGTH = 3;
            final int DESCRIPTION_POSITION = 1;
            final int TIME_POSITION = 2;
            if(taskInfo.length != DEADLINE_LENGTH){
                throw new IllegalArgumentException("Invalid Deadline Task");
            }
            String description = taskInfo[DESCRIPTION_POSITION];
            String time = taskInfo[TIME_POSITION];
            return new Deadline(description,time);
        }
        case EVENT -> {
            final int EVENT_LENGTH = 4;
            final int DESCRIPTION_POSITION = 1;
            final int START_TIME_POSITION = 2;
            final int END_TIME_POSITION = 3;
            if(taskInfo.length != EVENT_LENGTH){
                throw new IllegalArgumentException("Invalid Event Task");
            }
            String description = taskInfo[DESCRIPTION_POSITION];
            String startTime = taskInfo[START_TIME_POSITION];
            String endTime = taskInfo[END_TIME_POSITION];
            return new Event(description,startTime,endTime);
        }
        default -> throw new IllegalStateException("Unexpected value: " + taskType);
        }
    }

    static TaskType getTaskType(char c){
        return switch(c) {
        case 'T' -> TaskType.TODO;
        case 'D' -> TaskType.DEADLINE;
        case 'E' -> TaskType.EVENT;
        default -> throw new IllegalArgumentException("Invalid Task Type");
        };
    }
}
