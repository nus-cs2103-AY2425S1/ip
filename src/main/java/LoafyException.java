public class LoafyException extends Exception {
    LoafyException(String s) {
        super(s);
    }

    static LoafyException emptyInput() {
        return new LoafyException("Did you want to say something?");
    }

    static LoafyException invalidAction() {
        return new LoafyException("Commands 'mark', 'unmark', and 'delete' must be followed by a valid task number\n" +
                "   eg. mark 1");
    }

    static LoafyException emptyTodo() {
        return new LoafyException("'todo' should be followed by a task\n" +
                "   eg. todo wash dishes");
    }

    static LoafyException noDeadline() {
        return new LoafyException("It seems you did not specify the task or end date for your deadline\n" +
                "   eg. deadline submit homework /by tomorrow");
    }

    static LoafyException noEventDates() {
        return new LoafyException("It seems you did not specify the name, start date, or end date for your event\n" +
                "   eg. CS2103 lecture /from fri 4pm /to fri 6pm");
    }

    static LoafyException invalidCommand() {
        return new LoafyException("That is not in my vocabulary yet. ?_?\n" +
                "These are the list of commands I can understand:\n" +
                "   todo [TASK NAME]\n" +
                "   deadline [TASK NAME] /by [DEADLINE]\n" +
                "   event [TASK NAME] /from [START DATE] /to [END DATE]\n" +
                "   mark [TASK NUMBER]\n" +
                "   unmark [TASK NUMBER}\n" +
                "   list");
    }

    static LoafyException wrongDateFormat() {
        return new LoafyException("Hmm, date format should be written as D/M/YYYY HHMM\n" +
                "eg. 1/9/2024 2359");
    }

    static LoafyException loadingError() {
        return new LoafyException("Error: Cannot load storage file :(\n" +
                "Temporary task list created...\n" +
                "** Tasks will not be saved to hard drive **");
    }

    static LoafyException corruptedList() {
        return new LoafyException("Error: Corrupted storage file :(\n" +
                "New task list created...");
    }
}
