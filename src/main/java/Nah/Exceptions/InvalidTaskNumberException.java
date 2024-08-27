package Nah.Exceptions;

public class InvalidTaskNumberException extends NahException{
    public InvalidTaskNumberException() {
        super("Sorry, there is currently no task in the list to work with\n");
    }
    public InvalidTaskNumberException(int i, int t) {
        super("NAH!!! Nah.Data.Task " + i + " doesn't exist. Please give an number between 1 and " + t + "\n");
    }
}
