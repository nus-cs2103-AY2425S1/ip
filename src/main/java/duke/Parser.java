package duke;

public class Parser {

    /**
     * Returns a Parser object for parsing user commands
     */
    public Parser() {
        userInput = "";
        this.ptr = 0;
    }

    /**
     * Returns a Parser object for parsing user commands
     *
     * @param userInput the entire command the user has sent
     * @return Parser object
     */
    public Parser(String userInput) {
        this.userInput = userInput;
        this.ptr = 0;
    }


    /**
     * Returns void, just resets the parser state to match new
     * user command that was inputted
     *
     * @param newUserInput the new command the user has inputted
     */
    public void readInput(String newUserInput) {
        this.userInput = newUserInput;
        this.ptr = 0;
    }

    /**
     * Returns a substring of this.userInput starting from ptr
     * until the first instance of the escapeCharacter that
     * appears past ptr.
     *
     * @param escapeCharacter the escape character that determines
     * the end of the substring we should return
     * @return the substring from ptr to escapeCharacter (exclusive)
     */
    public String getArgument(char escapeCharacter) {
        int len = userInput.length();
        int startIndex = ptr;

        while(ptr < len) {
            if(userInput.charAt(ptr) == escapeCharacter) {
                break;
            }
            ptr++;
        }

        return userInput.substring(startIndex, ptr++);
    }

    /**
     * Returns a substring of this.userInput starting from ptr
     * until the first instance of the escapeCharacter that
     * appears past ptr, and then advances the ptr by advanceAmount
     *
     * @param escapeCharacter the escape character that determines
     * the end of the substring we should return
     * @return the substring from ptr to escapeCharacter (exclusive)
     */
    public String getArgument(char escapeCharacter, int advanceAmount) {
        int len = userInput.length();
        int startIndex = ptr;

        while(ptr < len) {
            if(userInput.charAt(ptr) == escapeCharacter) {
                break;
            }
            ptr++;
        }

        int oldPtr = ptr;
        ptr += advanceAmount;

        return userInput.substring(startIndex, oldPtr);
    }

    /**
     * Returns a substring of this.userInput starting from ptr
     * until the first instance of the escapeString that
     * appears past ptr, and then advances the ptr by advanceAmount
     *
     * @param escapeString the escape character that determines
     * the end of the substring we should return
     * @return the substring from ptr to escapeCharacter (exclusive)
     */
    public String getArgumentStr(String escapeString, int advanceAmount) throws ArgumentNotFoundException {
        int len = userInput.length();
        int escapeLen = escapeString.length();
        int startIndex = ptr;

        while(ptr < len) {
            if(ptr + escapeLen < len && userInput.substring(ptr, ptr + escapeLen).equals(escapeString)) {
                break;
            }
            ptr++;
        }

        if(ptr >= len) {
            String errMsg = escapeString + " argument not present!";
            ArgumentNotFoundException e = new ArgumentNotFoundException(errMsg);
            throw e;
        }

        int oldPtr = ptr;
        ptr += advanceAmount;

        return userInput.substring(startIndex, oldPtr);
    }

    String userInput;
    int ptr;
}
