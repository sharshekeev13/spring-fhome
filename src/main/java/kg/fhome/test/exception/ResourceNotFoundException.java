package kg.fhome.test.exception;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(final String message) {
        super(message);
    }

    public ResourceNotFoundException(final Throwable cause) {
        super(cause);
    }
}
