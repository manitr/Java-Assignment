package Exceptions;

public class IllegalParametersListException extends Throwable {
    final String message = "Method must be called with either 0 or 1 varargs parameters";

    @Override
    public String toString() {
        return "IllegalParametersListException: "  + message;
    }
}
