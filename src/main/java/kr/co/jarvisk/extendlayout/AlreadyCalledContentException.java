package kr.co.jarvisk.extendlayout;

public class AlreadyCalledContentException extends LayoutException {
    public AlreadyCalledContentException(String message) {
        super(message);
    }

    public AlreadyCalledContentException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyCalledContentException(Throwable cause) {
        super(cause);
    }
}
