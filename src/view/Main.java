package view;

import model.Account;
import model.CurrencyCode;
import model.User;
import repository.AccountRepository;

import service.UserService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Scanner;
import service.CurrencyService;


public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        UserService.initialUsers();

        CurrencyService.addCurrency(CurrencyCode.USD, 1.0);
        CurrencyService.addCurrency(CurrencyCode.EUR, 1.12);
        CurrencyService.addCurrency(CurrencyCode.GBP, 1.33);
        CurrencyService.addCurrency(CurrencyCode.PLN, 0.26);
        CurrencyService.addCurrency(CurrencyCode.CZK, 0.044);

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
                    registerUser(scanner);
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    viewBalance(scanner);
                    break;
                case 4:
                    deposit(scanner);
                    break;
                case 5:
                    withdraw(scanner);
                    break;
                case 6:
                    openAccount(scanner);
                    break;
                case 7:
                    exchangeCurrency(scanner);
                    break;
                case 8:
                    viewOperations();
                    break;
                case 9:
                    closeAccount(scanner);
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
                    changeExchangeRate(scanner);
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
                    viewCurrencyStatistics(scanner);
                    break;
                case 6:
                    viewListUsers();
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
        System.out.println("6. Просмотр список всех пользователей");
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

    private static void viewBalance(Scanner scanner) {
        // Реализация просмотра баланса
        User user = UserService.currentUser();
        AccountRepository.viewAllAccountsUser();
        System.out.println("Выберите счет: ");
        String accountId = scanner.nextLine();
        AccountRepository.viewAccountBalance(user, accountId);
    }

    private static void deposit(Scanner scanner) {
        // Реализация пополнения счета
        User user = UserService.currentUser();
        AccountRepository.viewAllAccountsUser();
        System.out.println("Выберите счет: ");
        String accountId = scanner.nextLine();
        System.out.println("Введите сумму для пополнения счета: ");
        double amount = scanner.nextInt();
        AccountRepository.depositAmount(user, accountId, amount);
    }

    private static void withdraw(Scanner scanner) {
        // Реализация снятия средств со счета
        User user = UserService.currentUser();
        AccountRepository.viewAllAccountsUser();
        System.out.println("Выберите счет: ");
        String accountIdString = scanner.nextLine();
        System.out.println("Введите сумму для снятия: ");
        double amount = scanner.nextInt();
        AccountRepository.withdrawAmount(user, accountIdString, amount);
    }


    private static void openAccount(Scanner scanner) {
        // Реализация открытия нового счета
        User user = UserService.currentUser();

        System.out.println("Выберите валюту счета:" + Arrays.toString(Account.Type.values()));
        String currencyAccount = String.valueOf(Main.scanner.nextLine());

        AccountRepository.openAccount(user, Account.Type.valueOf(currencyAccount), 0);
    }

    private static void exchangeCurrency(Scanner scanner) {
        // Реализация обмена валюты
    }

    private static void viewOperations() {
        // Реализация просмотра истории операций
    }

    private static void closeAccount(Scanner scanner) {
        // Реализация закрытия счета
        User user = UserService.currentUser();
        AccountRepository.viewAllAccountsUser();
        System.out.println("Выберите счет: ");
        String accountIdString = scanner.nextLine();
        AccountRepository.closeAccount(user, accountIdString);
    }

    private static void viewCurrencyRatesHistory() {
        // Реализация просмотра истории курсов по валюте
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

    private static void assignAdministrator() {
        // Реализация назначения администратора
    }

    private static void viewCurrencyStatistics(Scanner scanner) {
        // Реализация просмотра статистики операций по валюте
        System.out.println("Укажите код валюты для просмотра:");
        CurrencyCode code = CurrencyCode.valueOf(scanner.nextLine());
        CurrencyService.displayExchangeRateHistory(code);
    }

    private static void viewListUsers() {
        // Реализация просмотра списка всех пользователей
        System.out.println(UserService.getListUsers());
    }
}


