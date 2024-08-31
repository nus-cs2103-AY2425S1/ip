public class WrongDateTimeFormatException extends Exception {
    public WrongDateTimeFormatException() {
        super("Wrong DateTime format, Follow DD/MM/YYYY 24HR Time \n" +
                "Eg: 2/12/2019 1800");
    }
}
