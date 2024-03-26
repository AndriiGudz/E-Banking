package service;

// В этом классе UserService мы реализовали методы для регистрации пользователя и
// поиска пользователя по электронной почте

import model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    private static User currentUser;
    private static Map<Integer, User> users = new HashMap<>();

    // Возвращаем список всех пользователей из Map
    public static List<User> getListUsers() {
        return new ArrayList<>(users.values()); // Возвращаем список всех пользователей из Map
    }

    // Выводит список всех пользователей
    public static void displayListUsers() {
        List<User> userList = new ArrayList<>(users.values());

        if (userList.isEmpty()) {
            System.out.println("Список пользователей пуст.");
        } else {
            System.out.println("\nСписок пользователей:");
            for (User user : userList) {
                System.out.println("------------------------------------");
                System.out.println("ID пользователя: " + user.getUserId());
                System.out.println("Имя пользователя: " + user.getUsername());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Роль: " + user.getRole());
                System.out.println("------------------------------------");
            }
        }
    }

    // Метод для установки текущего пользователя
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    // Метод для получения текущего пользователя
    public static User currentUser() {
        return currentUser;
    }

    // Метод для регистрации пользователя с валидацией
    public static void registerUser(String username, String email, String password) {
        if (!isValidEmail(email)) {
            System.out.println("Неверный формат email.");
            return;
        }

        if (getUserByEmail(email) != null) {
            System.out.println("Пользователь с таким email уже зарегистрирован.");
            return;
        }

        User user = new User(username, email, password);
        users.put(user.getUserId(), user);
        System.out.println("Пользователь успешно зарегистрирован.");

        // После успешной регистрации устанавливаем этого пользователя как текущего аутентифицированного пользователя
        setCurrentUser(user);
        System.out.println("Пользователь успешно аутентифицирован.");
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

    // Метод для получения пользователя по Id
    public static User getUserById(int userId) {
        for (User user : users.values()) {
            if (user.getUserId() == userId) {
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
//    public static void assignAdministrator(User user) {
//        if (user.isAdmin()) {
//            System.out.println("Пользователь " + user.getUsername() + " уже является администратором.");
//        } else {
//            user.setAdmin(true);
//            System.out.println("Пользователь " + user.getUsername() + " назначен администратором.");
//        }
//    }

    // Метод для назначения роли ADMIN, доступный только администраторам
    public static void assignAdministrator(int userId) {
        User user = getUserById(userId);

        if (user == null) {
            throw new IllegalArgumentException("Пользователь с ID " + userId + " не найден.");
        }

        if (user.getRole() == User.Role.ADMIN) {
            System.out.println("Пользователь " + user.getUsername() + " уже является администратором.");
        } else {
            try {
                user.setRole(User.Role.ADMIN);
                System.out.println("Пользователь " + user.getUsername() + " назначен администратором.");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка при назначении прав администратора: " + e.getMessage());
            }
        }
    }

    // Метод для наполнения начальной базы пользователей.
    public static void initialUsers() {
        // Создаем несколько тестовых пользователей
        User user1 = new User("User1", "user1@example.com", "Password1!");
        User user2 = new User("User2", "user2@example.com", "Password2!");
        User admin = new User("admin", "admin@example.com", "Password3!");

        // Назначаем администратору роль "ADMIN"
        admin.setRole(User.Role.ADMIN);

        // Добавляем пользователей в базу
        users.put(user1.getUserId(), user1);
        users.put(user2.getUserId(), user2);
        users.put(admin.getUserId(), admin);

        // Выводим сообщение о количестве созданных пользователей
        // System.out.println("Добавлено " + users.size() + " пользователей.");
    }

}

