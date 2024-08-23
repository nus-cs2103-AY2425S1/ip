public class MissingTaskNameException extends Exception {
    public MissingTaskNameException() {
        super(" oops! task name cannot be empty! please enter a task name after the type of task. for example:\n" +
                "      todo cs2103t quiz");
    }
}
