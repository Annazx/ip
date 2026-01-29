package jane;

/**
 * Represents an exception specific to the Jane application.
 * This exception is used to signal application-level errors
 * that should be handled and shown to the user.
 */
public class JaneException extends Exception {

    /**
     * Constructs a {@code JaneException} with the specified detail message.
     *
     * @param message A descriptive error message
     */
    public JaneException(String message) {
        super(message);
    }
}
