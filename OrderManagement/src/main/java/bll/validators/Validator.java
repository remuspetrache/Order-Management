package bll.validators;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 16, 2021
 */
public interface Validator<T> {
    /**
     * Used to validate certain data
     * @param t generic object that has to be verified
     */
    void validate(T t);
}
