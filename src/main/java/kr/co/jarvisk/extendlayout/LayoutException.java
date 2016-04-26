package kr.co.jarvisk.extendlayout;

public class LayoutException extends RuntimeException {

    public LayoutException(String message) {
        super(message);
    }

    public LayoutException(String message, Throwable cause) {
        super(message, cause);
    }

    public LayoutException(Throwable cause) {
        super(cause);
    }
}
