package mendel.mendelexception;

public class ConditionalExceptionHandler {
    private final boolean isConditional;

    private ConditionalExceptionHandler(boolean isConditional) {
        this.isConditional = isConditional;
    }

    public static ConditionalExceptionHandler of(){
        return new ConditionalExceptionHandler(false);
    }

    public ConditionalExceptionHandler orConditionTriggerException(boolean isMet) throws MendelException {
        if (isMet) {
            return new ConditionalExceptionHandler(true);
        }
        else return new ConditionalExceptionHandler(false);
    }

    public ConditionalExceptionHandler andConditionTriggerException(boolean isMet, String errorMsg) throws MendelException {
        if (this.isConditional && isMet) {
            throw new MendelException(errorMsg);
        }
        else return new ConditionalExceptionHandler(true);
    }

    public ConditionalExceptionHandler conditionTriggerException(boolean isMet, String errorMsg) throws MendelException {
        if (isMet) throw new MendelException(errorMsg);
        else return new ConditionalExceptionHandler(false);
    }

}
