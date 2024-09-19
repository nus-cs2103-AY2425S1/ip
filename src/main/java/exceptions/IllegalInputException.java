package exceptions;

public class IllegalInputException extends RuntimeException {
    private String desc;
    private String offender;
    private String fix;
    public IllegalInputException(String desc, String offender, String fix) {
        super("OOPS!!! It appears your instructions were unclear.\n"
                + desc + "\n"
                + offender + "\n"
                + fix);
        this.desc = desc;
        this.offender = offender;
        this.fix = fix;
    }
}
