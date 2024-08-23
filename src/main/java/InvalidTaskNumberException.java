public class InvalidTaskNumberException extends FredException{
    InvalidTaskNumberException(String message) {
        super("OOPS!!! That's not a valid task number!");
    }
}
