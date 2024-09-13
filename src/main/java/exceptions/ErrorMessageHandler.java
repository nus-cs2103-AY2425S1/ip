package exceptions;

public class ErrorMessageHandler {
    public static String getInvalidTaskMessage() {
        return "THAT IS AN INVALID TASK LAH";
    }

    public static String getNoValidIndexGivenMessage() {
        return "A valid index has not been given!!";
    }

    public static String getInvalidDateMessage() {
        return "PLEASE USE THE PROPER DATE FORMAT: dd/MM/yyyy";
    }

    public static String getNoTaskDescriptionMessage() {
        return "Wah, no description then I record what?";
    }

    public static String getInvalidTagNameMessage() {
        return "You are finding a tag that doesn't exist!!";
    }
}
