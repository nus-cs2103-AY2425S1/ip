public class InvalidDataFormatException extends Exception {
    private String dataWithError;

    public InvalidDataFormatException(String dataWithError) {
        super();
        this.dataWithError = dataWithError;
    }

    @Override
    public String getMessage() {
        return " It seems the data file is not in the correct format :( \n Data containing error: "
                + dataWithError;
    }
}
