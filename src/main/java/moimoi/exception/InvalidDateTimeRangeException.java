package moimoi.exception;

public class InvalidDateTimeRangeException extends MoiMoiException {

    @Override
    public String getMessage() {
        return super.getMessage() + "Need some fixing on the date range here!\n"
                + "** Pro-tip: the starting time shouldn't be later than the ending time ;)";
    }

}
