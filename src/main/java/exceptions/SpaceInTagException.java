package exceptions;

public class SpaceInTagException extends Exception{
    /**
     * Default error message to show users
     */
    public SpaceInTagException() {
        super("Error: The tag cannot have white space!");
    }
}
