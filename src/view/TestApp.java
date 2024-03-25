package view;

import model.Account;
import model.User;
import repository.AccountRepository;
import service.UserService;

public class TestApp {
    public static void main(String[] args) {
        User user = new User("Andrii", "test@.i.ua", "Qwerty1!");
        UserService.setCurrentUser(user);
        System.out.println("Текущий пользователь: " + UserService.currentUser());

        AccountRepository.openAccount(user, Account.Type.EUR, 0);

    }
}
