package view;

import model.Account;
import model.CurrencyCode;
import model.Transaction;
import model.User;
import repository.AccountRepository;

import repository.TransactionRepository;
import service.AccountService;
import service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import java.util.UUID;


import service.CurrencyService;

import static service.CurrencyService.viewAllTransactions;


public class Main {

    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        UserService.initialUsers();

        CurrencyService.addCurrency(CurrencyCode.USD, 1.0);
        CurrencyService.addCurrency(CurrencyCode.EUR, 1.12);
        CurrencyService.addCurrency(CurrencyCode.GBP, 1.33);

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
            if (!scanner.hasNextInt()) {
                System.out.println("Ошибка: Введите числовое значение.");
                scanner.next(); // очистка некорректного ввода
                continue; // перезапуск цикла
            }
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    registerUser(scanner); // реализовано
                    pause();
                    break;
                case 2:
                    login(); // реализовано - проверить
                    pause();
                    break;
                case 3:
                    AccountRepository.viewBalance(scanner); // реализовано
                    pause();
                    break;
                case 4:
                    AccountRepository.deposit(scanner); // реализовано
                    pause();
                    break;
                case 5:
                    AccountRepository.withdraw(scanner); // реализовано
                    pause();
                    break;
                case 6:
                    openAccount(scanner); // реализовано
                    pause();
                    break;
                case 7:
                    exchangeCurrency(scanner); // - реализован только трансфер
                    pause();
                    break;
                case 8:
                    viewOperations(); // реализовано
                    pause();
                    break;
                case 9:
                    AccountRepository.closeAccount(scanner); // реализовано
                    pause();
                    break;
                case 10:
                    viewCurrencyRatesHistory(scanner); // реализовано
                    pause();
                    break;
                case 11:
                    AccountRepository.viewAllAccountsUser(); // реализовано
                    pause();
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
        System.out.println("11. Просмотреть все счета пользователя");
        System.out.println("0. Вернуться в главное меню");
    }

    private static void handleAdminMenu() {
        boolean adminMenuRunning = true;
        while (adminMenuRunning) {
            printAdminMenu();
            if (!scanner.hasNextInt()) {
                System.out.println("Ошибка: Введите числовое значение.");
                scanner.next(); // очистка некорректного ввода
                continue; // перезапуск цикла
            }
            int choice = scanner.nextInt();
            scanner.nextLine(); // очистка буфера после считывания int
            // User currentUser = UserService.currentUser(); // Получаем текущего пользователя
            // if (currentUser != null && currentUser.getRole() == User.Role.ADMIN) { // Проверяем, является ли пользователь администратором
            switch (choice) {
                case 1:
                    changeExchangeRate(scanner); // реализовано
                    break;
                case 2:
                    manageCurrencies(); // реализовано - просмотр всех валют
                    break;
                case 3:
                    viewUserOperations();
                    break;
                case 4:
                    assignAdministrator(scanner); // реализовано
                    break;
                case 5:
                    viewCurrencyStatistics(scanner); // реализовано
                    break;
                case 6:
                    viewListUsers(); // реализовано
                    break;
                case 7:
                    AccountRepository.getAllAccounts(); // реализовано
                    break;
                case 0:
                    adminMenuRunning = false;
                    break;
                default:
                    System.out.println("Неверный ввод. Пожалуйста, попробуйте снова.");
            }
//            } else {
//                System.out.println("Ошибка: Доступ запрещен. Требуется роль администратора.");
//                adminMenuRunning = false; // Выходим из цикла, если у пользователя нет прав администратора

        }
    }

    private static void printAdminMenu() {
        System.out.println("Меню администратора:");
        System.out.println("1. Изменение курса валюты");
        System.out.println("2. Управление валютами");
        System.out.println("3. Просмотр истории операций пользователя");
        System.out.println("4. Назначение администратора");
        System.out.println("5. Просмотр статистики операций по валюте");
        System.out.println("6. Просмотр список всех пользователей");
        System.out.println("7. Просмотреть все счета всех пользователей");
        System.out.println("0. Вернуться в главное меню");
    }

    private static void registerUser(Scanner scanner) {
        // Реализация регистрации нового пользователя
        System.out.println("Введите Имя пользователя:");
        String username = scanner.nextLine();

        System.out.println("Введите ваш email:");
        String email = scanner.nextLine();

        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        UserService.registerUser(username, email, password);
    }

    private static void login() {
        // Реализация входа в аккаунт
        System.out.println("Введите ваш email:");
        String email = scanner.nextLine();

        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        UserService.authenticateUser(email, password);
    }

    private static void openAccount(Scanner scanner) {
        // Реализация открытия нового счета
        User user = UserService.currentUser();

        System.out.println("Выберите валюту счета:" + Arrays.toString(Account.Type.values()));
        String currencyAccount = String.valueOf(Main.scanner.nextLine());

        AccountRepository.openAccount(user, Account.Type.valueOf(currencyAccount), 0);
    }


    private static void exchangeCurrency(Scanner scanner) {
        // Реализация обмена валюты - реализован только трансфер

        User user = UserService.currentUser();
        AccountRepository.viewAllAccountsUser();

        // Запрос номера счета отправителя
        System.out.println("Введите номер счета отправителя: ");
        String sourceAccountIdString = scanner.nextLine();
        UUID sourceAccountId = UUID.fromString(sourceAccountIdString);

        // Запрос номера счета получателя
        System.out.println("Введите номер счета получателя: ");
        String targetAccountIdString = scanner.nextLine();
        UUID targetAccountId = UUID.fromString(targetAccountIdString);

        // Запрос суммы для перевода
        System.out.println("Введите сумму для перевода: ");
        double amount = scanner.nextDouble();

        try {
            AccountRepository.transferMoney(user, sourceAccountId, targetAccountId, amount);
            System.out.println("Перевод выполнен успешно.");
        } catch (
                IllegalArgumentException e) {
            System.out.println("Ошибка при выполнении перевода: " + e.getMessage());
        }
    }

    private static void viewOperations() {
        // Реализация просмотра истории операций
        User user = UserService.currentUser();
        List<Transaction> transactions = TransactionRepository.getAllTransactions();
        viewAllTransactions(transactions);
    }

    private static void viewCurrencyRatesHistory(Scanner scanner) {
        // Реализация просмотра истории курсов по валюте
        System.out.println("Укажите код валюты для просмотра:");
        CurrencyCode code = CurrencyCode.valueOf(scanner.nextLine());
        CurrencyService.displayExchangeRateHistory(code);
    }

    private static void changeExchangeRate(Scanner scanner) {
        // Реализация изменения курса валюты
        System.out.println("Введите код валюты:");
        CurrencyCode code = CurrencyCode.valueOf(scanner.nextLine());
        System.out.println("Введите обменный курс:");
        double newExchangeRate = scanner.nextDouble();

        CurrencyService.updateExchangeRate(code, newExchangeRate, LocalDateTime.now());
    }

    private static void manageCurrencies() {
        // Реализация управления валютами - просмотреть список всех валют
        CurrencyService.displayAllCurrencies();
    }

    private static void viewUserOperations() {
        // Реализация просмотра истории операций пользователя
    }

    private static void assignAdministrator(Scanner scanner) {
        // Реализация назначения администратора
        UserService.displayListUsers();
        System.out.println("Выберите Id пользователя для назначения Администратором:");
        int userId = scanner.nextInt();

        UserService.assignAdministrator(userId);
    }

    private static void viewCurrencyStatistics(Scanner scanner) {
        // Реализация просмотра статистики операций по валюте
        System.out.println("Укажите код валюты для просмотра:");
        CurrencyCode code = CurrencyCode.valueOf(scanner.nextLine());
        CurrencyService.displayExchangeRateHistory(code);
    }

    private static void viewListUsers() {
        // Реализация просмотра списка всех пользователей
        // System.out.println(UserService.getListUsers());
        UserService.displayListUsers();
    }


    public static void pause() {
        // Пауза для удобства просмотра информации в меню
        System.out.println("\nНажмите Enter, чтобы продолжить...");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}