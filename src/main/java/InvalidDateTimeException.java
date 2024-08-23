public class InvalidDateTimeException extends MoiMoiException {

    @Override
    public String getMessage() {
        return super.getMessage() + "This date-time doesn't make sense to me.. This calls for a fix!\n"
                + "** Pro-tip: pass me valid date-times in the format 'yyyy-MM-dd HH:mm' ;)";
    }

}
