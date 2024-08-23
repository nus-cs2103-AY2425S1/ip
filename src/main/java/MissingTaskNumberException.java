public class MissingTaskNumberException extends Exception {
    public MissingTaskNumberException() {
        super(" which task would you like to mark/unmark/delete? Please specify with the task number. for example:\n" +
                "      mark 3");
    }
}
