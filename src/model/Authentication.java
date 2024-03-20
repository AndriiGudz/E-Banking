package model;

import java.util.HashMap;
import java.util.Map;

public class Authentication {
    private Map<String, String> credentials;

    // Конструктор класса
    public Authentication() {
        this.credentials = new HashMap<>();
    }

    // Метод для регистрации нового пользователя
    public void registerUser(String username, String password) {
        credentials.put(username, password);
    }

    // Метод для проверки аутентификации пользователя
    public boolean authenticateUser(String username, String password) {
        return credentials.containsKey(username) && credentials.get(username).equals(password);
    }

// Другие методы, связанные с аутентификацией, могут быть добавлены по мере необходимости
}