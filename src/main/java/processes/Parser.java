package processes;


public class Parser {

    public PrefixString parseCommand (String input) {
        return PrefixString.checkPrefixString(input);
    }

    public boolean checkValidIndex(String command, int start) {
        String trimmed = command.substring(start);
        try {
            Integer.parseInt(trimmed);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public int checkIndex (String command, int start) {
        String trimmed = command.substring(start);
        try {
            return Integer.parseInt(trimmed);
        } catch (Exception ex) {
            return Integer.MAX_VALUE;
        }
    }
}
