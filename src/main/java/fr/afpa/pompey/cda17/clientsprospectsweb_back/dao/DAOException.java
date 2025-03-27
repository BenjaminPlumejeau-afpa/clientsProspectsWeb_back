package fr.afpa.pompey.cda17.clientsprospectsweb_back.dao;

/**
 * Exception relative au fonctionnement des classes de DAO.
 */
public class DAOException extends Exception {

    private int gravity = 0;

    /**
     * Lève une Exception de DAO avec une gravité minimum
     * accompagnée d'un message.
     *
     * @param message Le message associé à l'exception
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * Lève une Exception de DAO accompagnée d'un message
     * et d'un facteur de gravité.
     *
     * @param message Le message associé à l'exception
     * @param gravity Le facteur de gravité de l'exception
     */
    public DAOException(String message, int gravity) {
        super(message);
        this.gravity = gravity;
    }

    /**
     * Lève une Exception de DAO accompagnée d'un message,
     * d'un facteur de gravité et de l'exception l'ayant provoquée.
     *
     * @param message Le message associé à l'exception
     * @param cause   L'exception à l'origine de celle-ci
     * @param gravity Le facteur de gravité de l'exception
     */
    public DAOException(String message, Throwable cause, int gravity) {
        super(message, cause);
        this.gravity = gravity;
    }

    public int getGravity() {
        return gravity;
    }
}
