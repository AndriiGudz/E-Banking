package view;

import model.Account;
import model.User;
import repository.AccountRepository;
import service.UserService;

public class TestApp {
    public static void main(String[] args) {
//        User user = new User("Andrii", "test@.i.ua", "Qwerty1!");
//        User user1 = new User("Ivan", "test@.gmail.com", "Qwerty1!");
//        UserService.setCurrentUser(user);
//        System.out.println("Текущий пользователь: " + UserService.currentUser());
//
//        AccountRepository.openAccount(user, Account.Type.EUR, 0);
//        AccountRepository.openAccount(user, Account.Type.USD, 100);
//        UserService.setCurrentUser(user1);
//        AccountRepository.openAccount(user1, Account.Type.EUR, 50);
//
//        AccountRepository.getAllAccounts();
//
//        AccountRepository.viewAllAccountsUser();

        UserService.initialUsers();
        System.out.println(UserService.getListUsers());


    }
}
