public class Bye {
    public Bye(String description) throws CommandFoundButInvalidException{
        if (!description.isEmpty()) {
            throw new InvalidSyntaxException("bye");
        }
    }

    public String message() {
        return "Bye. Hope to see you again soon!";
    }
}
