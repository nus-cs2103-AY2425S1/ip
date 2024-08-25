public class KobeException {
    static class InvalidListNumberException extends Exception {
        public InvalidListNumberException() {
            super("Invalid list number entered!");
        }
    }
}
