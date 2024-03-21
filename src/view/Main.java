package view;

import java.util.Scanner;


public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    handleUserMenu();
                    break;
                case 2:
                    handleAdminMenu();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный ввод. Пожалуйста, попробуйте снова.");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("Выберите тип пользователя:");
        System.out.println("1. Пользователь");
        System.out.println("2. Администратор");
        System.out.println("0. Выход");
    }

    private static void handleUserMenu() {
        boolean userMenuRunning = true;
        while (userMenuRunning) {
            printUserMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    viewBalance();
                    break;
                case 4:
                    deposit();
                    break;
                case 5:
                    withdraw();
                    break;
                case 6:
                    openAccount();
                    break;
                case 7:
                    exchangeCurrency();
                    break;
                case 8:
                    viewOperations();
                    break;
                case 9:
                    closeAccount();
                    break;
                case 10:
                    viewCurrencyRatesHistory();
                    break;
                case 0:
                    userMenuRunning = false;
                    break;
                default:
                    System.out.println("Неверный ввод. Пожалуйста, попробуйте снова.");
            }
        }
    }

    private static void printUserMenu() {
        System.out.println("Меню пользователя:");
        System.out.println("1. Регистрация нового пользователя");
        System.out.println("2. Вход в аккаунт");
        System.out.println("3. Просмотр баланса");
        System.out.println("4. Пополнение счета");
        System.out.println("5. Снятие средств со счета");
        System.out.println("6. Открытие нового счета");
        System.out.println("7. Обмен валюты");
        System.out.println("8. Просмотр истории операций");
        System.out.println("9. Закрытие счета");
        System.out.println("10. Просмотр истории курсов по валюте");
        System.out.println("0. Вернуться в главное меню");
    }

    private static void handleAdminMenu() {
        boolean adminMenuRunning = true;
        while (adminMenuRunning) {
            printAdminMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    changeExchangeRate();
                    break;
                case 2:
                    manageCurrencies();
                    break;
                case 3:
                    viewUserOperations();
                    break;
                case 4:
                    assignAdministrator();
                    break;
                case 5:
                    viewCurrencyStatistics();
                    break;
                case 0:
                    adminMenuRunning = false;
                    break;
                default:
                    System.out.println("Неверный ввод. Пожалуйста, попробуйте снова.");
            }
        }
    }

    private static void printAdminMenu() {
        System.out.println("Меню администратора:");
        System.out.println("1. Изменение курса валюты");
        System.out.println("2. Управление валютами");
        System.out.println("3. Просмотр истории операций пользователя");
        System.out.println("4. Назначение администратора");
        System.out.println("5. Просмотр статистики операций по валюте");
        System.out.println("0. Вернуться в главное меню");
    }

    private static void registerUser() {
        // Реализация регистрации нового пользователя
    }

    private static void login() {
        // Реализация входа в аккаунт
    }

    private static void viewBalance() {
        // Реализация просмотра баланса
    }

    private static void deposit() {
        // Реализация пополнения счета
    }

    private static void withdraw() {
        // Реализация снятия средств со счета
    }

    private static void openAccount() {
        // Реализация открытия нового счета
    }

    private static void exchangeCurrency() {
        // Реализация обмена валюты
    }

    private static void viewOperations() {
        // Реализация просмотра истории операций
    }

    private static void closeAccount() {
        // Реализация закрытия счета
    }

    private static void viewCurrencyRatesHistory() {
        // Реализация просмотра истории курсов по валюте
    }

    private static void changeExchangeRate() {
        // Реализация изменения курса валюты
    }

    private static void manageCurrencies() {
        // Реализация управления валютами
    }

    private static void viewUserOperations() {
        // Реализация просмотра истории операций пользователя
    }

    private static void assignAdministrator() {
        // Реализация назначения администратора
    }

    private static void viewCurrencyStatistics() {
        // Реализация просмотра статистики операций по валюте
    }
}


