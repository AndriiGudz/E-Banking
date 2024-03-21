package service;

// В этом классе UserService мы реализовали методы для регистрации пользователя и
// поиска пользователя по электронной почте

import model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    private static Map<Integer, User> users = new HashMap<>();

    // Метод для регистрации пользователя с валидацией
    public static void registerUser(String email, String password) {
        if (!isValidEmail(email)) {
            System.out.println("Неверный формат email.");
            return;
        }

        if (getUserByEmail(email) != null) {
            System.out.println("Пользователь с таким email уже зарегистрирован.");
            return;
        }

        User user = new User(email, password);
        users.put(user.getId(), user);
        System.out.println("Пользователь успешно зарегистрирован.");
    }

    // Метод для получения пользователя по email
    public static User getUserByEmail(String email) {
        for (User user : users.values()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null; // Пользователь не найден
    }

    // Метод для проверки формата email
    private static boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Метод для проверки безопасности пароля
    private static boolean isValidPassword(String password) {
        // Пароль должен содержать хотя бы одну букву и одну цифру
        boolean containsLetter = false;
        boolean containsDigit = false;

        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                containsLetter = true;
            } else if (Character.isDigit(c)) {
                containsDigit = true;
            }
        }

        // Пароль должен содержать хотя бы один специальный символ
        boolean containsSpecialCharacter = password.matches("[!@#$%^&*()+\\-={};:'\",.<>/?|_]");

        // Пароль должен быть длиной от 8 до 20 символов
        boolean isValidLength = password.length() >= 8 && password.length() <= 20;

        // Все условия должны быть выполнены
        return containsLetter && containsDigit && containsSpecialCharacter && isValidLength;
    }

    // Метод для аутентификации пользователя при входе в аккаунт
    public static User authenticateUser(String email, String password) {
        User user = getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Вы успешно вошли в аккаунт.");
            return user;
        } else {
            System.out.println("Неправильный email или пароль. Попробуйте еще раз.");
            return null;
        }
    }

    // Метод для назначения нового администратора
    public static void assignAdministrator(User user) {
        if (user.isAdmin()) {
            System.out.println("Пользователь " + user.getUsername() + " уже является администратором.");
        } else {
            user.setAdmin(true);
            System.out.println("Пользователь " + user.getUsername() + " назначен администратором.");
        }
    }
}

