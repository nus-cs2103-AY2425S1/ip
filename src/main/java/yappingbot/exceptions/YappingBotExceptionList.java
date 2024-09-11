package yappingbot.exceptions;

import java.util.ArrayList;

import yappingbot.stringconstants.ReplyTextMessages;

public class YappingBotExceptionList extends ArrayList<YappingBotException> {

    /**
     * Checks if any exceptions were added, and collates them into a single generic
     * YappingBotException.
     *
     * @throws YappingBotException only if exceptions are present and added.
     */
    public void checkExceptions() throws YappingBotException {
        if (!isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (YappingBotException e : this) {
                sb.append(e.getErrorMessage());
                sb.append("\n---\n");
            }
            throw new YappingBotException(String.format(
                    ReplyTextMessages.MULTIPLE_EXCEPTIONS_1d_1s,
                    this.size(),
                    sb
            ));
        }
    }
}
