public class IllegalInputException extends RuntimeException {
    private String desc;
    private String offender;
    private String fix;
    public IllegalInputException(String desc, String offender, String fix) {
        super("OOPS!!! It appears your instructions were unclear.");
        this.desc = desc;
        this.offender = offender;
        this.fix = fix;
    }
    public String[] getLines() {
        return new String[] { this.getMessage(), this.desc, this.offender, this.fix};
    }
    @Override
    public String toString() {
        return this.getMessage() + "\n"
                + this.desc + "\n"
                + this.offender + "\n"
                + this.fix;
    }
}
