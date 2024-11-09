package Exception;

public class FileAlreadyOpenException extends RuntimeException {
    public FileAlreadyOpenException(String message) {
        super(message);
    }
}
