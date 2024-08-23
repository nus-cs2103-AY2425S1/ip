public class RapGodException extends RuntimeException{
    public RapGodException(String message) {
        super(message);
    }

    public class NoInputException extends RapGodException {

        public NoInputException(String message) {
            super(message);
        }
    }

    public class RudeInputException extends RapGodException {

        public RudeInputException(String message) {
            super(message);
        }
    }
}
