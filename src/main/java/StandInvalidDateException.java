public class StandInvalidDateException extends StrandException{
    protected String date;

    public StandInvalidDateException(String date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return this.date + " is not a valid date " + super.toString()
                + "\nPlease include a date of the correct format (e.g. 2/12/2019 1800)";
    }
}
