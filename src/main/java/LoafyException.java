public class LoafyException extends Exception {
    LoafyException() {
        super();
    }

    static String invalidCommand() {
        return "Hmm, that is not in my vocabulary yet. ?_?\n" +
                "Try these commands instead:\n" +
                "   todo [TASK NAME]\n" +
                "   deadline [TASK NAME] /by [DEADLINE]\n" +
                "   event [TASK NAME] /from [START DATE] /to [END DATE]\n" +
                "   mark [TASK NUMBER]\n" +
                "   unmark [TASK NUMBER}\n" +
                "   list";
    }

    static String emptyInput() {
        return "Hmm, did you want to say something?";
    }

    static String invalidMark() {
        return "Hmm, commands 'mark' or 'unmark' must be followed by a valid task number\n" +
                "   eg. mark 1";
    }

    static String emptyTodo() {
        return "Hmm, 'todo' should be followed by a task\n" +
                "   eg. todo wash dishes";
    }

    static String noDeadline() {
        return "Hmm, it seems you did not specify the task or end date for your deadline\n" +
                "   eg. deadline submit homework /by tomorrow";
    }

    static String noEventDates() {
        return "Hmm, it seems you did not specify the name, start date, or end date for your event\n" +
                "   eg. CS2103 lecture /from fri 4pm /to fri 6pm";
    }
}
