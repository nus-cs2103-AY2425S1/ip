public class WrongDeadlineFormatException extends Exception {
    public WrongDeadlineFormatException() {
        super("Wrong deadline format, Follow DD/MM/YYYY 24HR Time \n" +
                "Eg: 2/12/2019 1800");
    }
}
