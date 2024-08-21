public class TodoWrongFormatException extends TaskWrongFormatException {
    private String cause;
    public TodoWrongFormatException(String cause) {
        super();
        this.cause = cause;
    }

    @Override
    public String getMessage() {
        StringBuilder stringBuilder = new StringBuilder(super.getMessage());
        stringBuilder.append("todo: ");
        stringBuilder.append(cause);
        return stringBuilder.toString();
    }
}
