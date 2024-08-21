public class TalkieNoTaskFoundException extends TalkieException {

    @Override
    public String toString() {
        return "------------------------------------------------------------\n"
                + super.toString() + " I cannot find this task in my system!\n"
                + "Hint: Use the command 'list' to check the list of tasks.\n"
                + "Please try again! :D\n"
                + "------------------------------------------------------------\n";
    }
}
