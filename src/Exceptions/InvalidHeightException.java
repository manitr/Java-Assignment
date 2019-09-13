package Exceptions;

public class InvalidHeightException extends Exception {
        final String message = "Height must be integral and non-negative";

        @Override
        public String toString() {
            return "Exceptions.InvalidHeightException: "  + message;
        }
}
