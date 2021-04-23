package controller;


import componentFactory.ComponentFactory;
import model.User;
import model.validation.Notification;
import repository.user.AuthenticationException;
import repository.user.UserRepository;
import service.user.AuthenticationService;
import view.AdministratorView;
import view.LoginView;
import view.RegularUserView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;


public class LoginController {
    private final LoginView loginView;
    private final AuthenticationService authenticationService;

    public LoginController(LoginView loginView, AuthenticationService authenticationService) {
        this.loginView = loginView;
        this.authenticationService = authenticationService;
        loginView.setLoginButtonListener(new LoginButtonListener());
        loginView.setRegisterButtonListener(new RegisterButtonListener());
    }

    private class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            ComponentFactory componentFactory1 = ComponentFactory.instance(false);
            UserRepository userRepository = componentFactory1.getUserRepository();
            List<User> users = userRepository.findAll();

            Notification<User> loginNotification = null;
            try {
                loginNotification = authenticationService.login(username, password);
            } catch (AuthenticationException e1) {
                e1.printStackTrace();
            }

            if (loginNotification != null) {
                if (loginNotification.hasErrors()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), loginNotification.getFormattedErrors());
                } else {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Login successful!");
                    RegularUserView regularUserView = new RegularUserView();
                    RegularUserController regularUserController = new RegularUserController(regularUserView, authenticationService, username);

                    if (loginNotification.getResult().getRoles().get(0).getRole().equals("administrator")) {
                        AdministratorView administratorView = new AdministratorView();
                        AdministratorController administratorController = new AdministratorController(administratorView, authenticationService);
                    }

                }
            }
        }
    }

    private class RegisterButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            Notification<Boolean> registerNotification = authenticationService.register(username, password);
            if (registerNotification.hasErrors()) {
                JOptionPane.showMessageDialog(loginView.getContentPane(), registerNotification.getFormattedErrors());
            } else {
                if (!registerNotification.getResult()) {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration not successful, please try again later.");
                } else {
                    JOptionPane.showMessageDialog(loginView.getContentPane(), "Registration successful!");
                }
            }
        }
    }


}
