public class Parser {
    public static Task parseStorageFileLine(String line) throws InvalidStorageFileException {
        char START_IDX = 7;
        char taskType = line.charAt(1);
        Task t;
        String taskDescription;
        
        switch (taskType) {
            case 'T':
                taskDescription = line.substring(START_IDX);
                if (taskDescription.equals("")) {
                    throw new InvalidStorageFileException();
                }
                t = new Todo(taskDescription);
                break;
            case 'D':
                String byDelimeter = " (by: ";
                int byIdx = line.indexOf(byDelimeter);
                if (byIdx == -1) {
                    throw new InvalidStorageFileException();
                }
                taskDescription = line.substring(START_IDX, byIdx);
                String deadline = line.substring(byIdx + byDelimeter.length(), line.length() - 1);
                t = new Deadline(taskDescription, deadline);
                break;
            case 'E':
                String fromDelimeter = " (from: ";
                String toDelimeter = " to: ";
                int fromIdx = line.indexOf(fromDelimeter);
                int toIdx = line.indexOf(toDelimeter);
                if (fromIdx == -1 || toIdx == -1) {
                    throw new InvalidStorageFileException();
                }
                taskDescription = line.substring(START_IDX, fromIdx);
                String from = line.substring(fromIdx + fromDelimeter.length(), toIdx);
                String to = line.substring(toIdx, line.length() - 1);
                t = new Event(taskDescription, from, to);
                break;
            default:
                throw new InvalidStorageFileException();
        }
        
        char doneLabel = line.charAt(4);
        if (doneLabel == 'X') {
            t.mark();
        }

        return t;
    }
}