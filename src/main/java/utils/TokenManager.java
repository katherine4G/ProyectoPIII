
package utils;

/**
 *
 * @author kathe
 */
public class TokenManager {
    
    private static TokenManager instance;
    private String token;

    // Constructor privado para evitar instanciación externa
    private TokenManager() {}

    // Método público estático para obtener la instancia de TokenManager (si no existe, se crea)
    public static TokenManager getInstance() {
        if (instance == null) {
            instance = new TokenManager();
        }
        return instance;
    }

    public String getToken() {
        return token;
    }

    //guardar el token
    public void setToken(String token) {
        this.token = token;
    }

    // verifica si el token está presente:
    public boolean hasToken() {
        return token != null && !token.isEmpty();
    }

    //  limpia el token (al cerrar sesión)
    public void clearToken() {
        this.token = null;
    }
}
