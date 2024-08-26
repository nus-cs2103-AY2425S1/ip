public class ChatterBoxDataFileError extends ChatterBoxError {
    public ChatterBoxDataFileError(String fileName) {
        super("____________________________________________________________\n"
                + "The file " + fileName +" is not found\n"
                + "____________________________________________________________");
    }

    public ChatterBoxDataFileError() {
        super("____________________________________________________________\n"
                + "The file is not found\n"
                + "____________________________________________________________");
    }
}
