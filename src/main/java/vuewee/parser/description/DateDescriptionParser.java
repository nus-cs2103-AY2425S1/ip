package vuewee.parser.description;

import vuewee.task.TaskLocalDate;

/**
 * The DateDescriptionParser class is responsible for parsing and extracting
 * information from a description input string and converting it to a date.
 */
public class DateDescriptionParser extends DescriptionParser<TaskLocalDate> {
    /**
     * Return the description string as is.
     */
    @Override
    public TaskLocalDate parse(String descriptionStr) {
        return TaskLocalDate.parse(descriptionStr);
    }

    /**
     * Override getUsageFieldName to better represent the expected date input.
     */
    @Override
    public String getUsageFieldName() {
        return " <date yyyy-mm-dd>";
    }
}
