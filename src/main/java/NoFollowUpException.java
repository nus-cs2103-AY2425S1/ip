public class NoFollowUpException extends DukeException {
    NoFollowUpException() {
        super("test");
    }

    @Override
    public String toString() {
        return "eh paiseh ah bro, you need to tell me one task along with your \n " +
                "command in this format 'todo/event/deadline *insert your task here*'";
    }
}
