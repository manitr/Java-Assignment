package Exceptions;

public class EmptyNameFieldException extends Throwable {
    final String message = "Username can't be Emnpty.";

    @Override
    public String toString() {
        return "EmptyNameFieldException: "  + message;
    }
}
