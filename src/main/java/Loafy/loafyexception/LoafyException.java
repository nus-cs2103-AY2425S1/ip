package loafy.loafyexception;

public class LoafyException extends Exception {
    LoafyException(String s) {
        super(s);
    }

    public static LoafyException ofEmptyInput() {
        return new LoafyException(
                "       Did you want to say something?");
    }

    public static LoafyException ofInvalidAction() {
        return new LoafyException(
                "       Commands 'mark', 'unmark', and 'delete' must be followed by a valid task number\n" +
                "           eg. mark 1");
    }

    public static LoafyException ofEmptyTodo() {
        return new LoafyException(
                "       'todo' should be followed by a task\n" +
                "           eg. todo wash dishes");
    }

    public static LoafyException ofNoDeadline() {
        return new LoafyException(
                "       It seems you did not specify the task or end date for your deadline\n" +
                "           eg. deadline submit homework /by 30/8/2024 4pm");
    }

    public static LoafyException ofNoEventDates() {
        return new LoafyException(
                "       It seems you did not specify the name, start date, or end date for your event\n" +
                "           eg. CS2103 lecture /from 30/8/2024 4pm /to 30/8/2024 6pm");
    }

    public static LoafyException ofInvalidCommand() {
        return new LoafyException(
                "       That is not in my vocabulary yet. ?_?\n" +
                "           These are the list of commands I can understand:\n" +
                "           todo [TASK NAME]\n" +
                "           deadline [TASK NAME] /by [DEADLINE]\n" +
                "           event [TASK NAME] /from [START DATE] /to [END DATE]\n" +
                "           mark [TASK NUMBER]\n" +
                "           unmark [TASK NUMBER}\n" +
                "           list");
    }

    public static LoafyException ofWrongDateFormat() {
        return new LoafyException(
                "       Date format should be written as D/M/YYYY HHMM\n" +
                "           eg. 1/9/2024 2359");
    }

    public static LoafyException ofLoadingError() {
        return new LoafyException(
                "Error: Cannot load storage file :(\n" +
                "Temporary task list created...\n" +
                "** Tasks will not be saved to hard drive **");
    }

    public static LoafyException ofCorruptedList() {
        return new LoafyException(
                "Error: Corrupted storage file :(\n" +
                "New task list created...");
    }
}
