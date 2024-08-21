public class InvalidListEnquiry extends Exception {
    public InvalidListEnquiry() {
        super("Dear traveller. If you want to check out the current list, please type in 'list' only.");
    }
}
