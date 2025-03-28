package fr.afpa.pompey.cda17.clientsprospectsweb_back.utilities;

import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * Classe utilitaire de génération de token CSRF.
 */
public abstract class CSRF {

    /**
     * Génére un token CSRF composé de caractères aléatoires.
     *
     * @return String - Le token généré
     */
    public static String generateToken() {

        byte[] array = new byte[20];
        new Random().nextBytes(array);
        String token = new String(array, StandardCharsets.UTF_8);

        return token;

    }

}
