package brachy.modularui.api;

/**
 * Math value interface
 * <p>
 * This interface provides only one method which is used by all
 * mathematical related classes. The point of this interface is to
 * provide generalized abstract method for computing/fetching some value
 * from different mathematical classes.
 */
public interface IMathValue {

    /**
     * Get computed or stored value
     */
    IMathValue get();

    boolean isNumber();

    void set(double value);

    void set(String value);

    double doubleValue();

    boolean booleanValue();

    String stringValue();

    class EvaluateException extends RuntimeException {

        // serialver-computed (JDK 21, pinned to the implicit UID this class already deserializes with)
        private static final long serialVersionUID = 8494899373406273089L;

        public EvaluateException(String message) {
            super(message);
        }
    }
}
