public class InvalidTaskNumber extends Exception{
    InvalidTaskNumber() {
        super("Sorry, there is currently no task in the list to work with\n");
    }
    InvalidTaskNumber(int i, int t) {
        super("NAH!!! Task " + i + " doesn't exist. Please give an number between 1 and " + t + "\n");
    }
}
