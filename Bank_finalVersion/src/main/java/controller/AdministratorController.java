package controller;

import componentFactory.ComponentFactory;
import model.Action;
import model.User;
import repository.EntityNotFoundException;
import service.action.ActionService;
import service.user.AuthenticationService;
import service.user.UserService;
import view.AdministratorView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

public class AdministratorController {

    private final AdministratorView administratorView;
    private final AuthenticationService authenticationService;
    private static int reportIndex = 1;

    public AdministratorController(AdministratorView administratorView, AuthenticationService authenticationService) {
        this.administratorView = administratorView;
        this.authenticationService = authenticationService;
        administratorView.setAddEmployeeButtonListener(new AdministratorController.AddEmployeeButtonListener());
        administratorView.setUpdateEmployeeButtonListener(new AdministratorController.UpdateEmployeeButtonListener());
        administratorView.setViewEmployeeButtonListener(new AdministratorController.ViewEmployeeButtonListener());
        administratorView.setDeleteEmployeeButtonListener(new AdministratorController.DeleteEmployeeButtonListener());
        administratorView.setGenerateReportButtonListener(new AdministratorController.GenerateReportButtonListener());

    }

    private class AddEmployeeButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String username = administratorView.getTextUsername().getText();
                String password = administratorView.getTextPassword().getText();

                ComponentFactory componentFactory = ComponentFactory.instance(false);
                AuthenticationService authenticationService = componentFactory.getAuthenticationService();
                authenticationService.register(username, password);
                JOptionPane.showMessageDialog(administratorView.getContentPane(), "Operation performed successfully");
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(administratorView.getContentPane(), "Error in adding the employee");

            }

            administratorView.getTextUsername().setText("");
            administratorView.getTextPassword().setText("");

        }
    }

    private class UpdateEmployeeButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Long id = Long.parseLong(administratorView.getTextIdEmployee().getText());
                String username = administratorView.getTextUsername().getText();
                String password = administratorView.getTextPassword().getText();


                ComponentFactory componentFactory = ComponentFactory.instance(false);
                UserService userService = componentFactory.getUserService();
                userService.update(id, username, password);
                JOptionPane.showMessageDialog(administratorView.getContentPane(), "Operation performed successfully");

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(administratorView.getContentPane(), "Error in updating the employee");
            }

            administratorView.getTextIdEmployee().setText("");
            administratorView.getTextUsername().setText("");
            administratorView.getTextPassword().setText("");
        }
    }
    private class DeleteEmployeeButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Long id = Long.parseLong(administratorView.getTextIdEmployee().getText());

                ComponentFactory componentFactory = ComponentFactory.instance(false);
                UserService userService = componentFactory.getUserService();
                userService.removeById(id);
                JOptionPane.showMessageDialog(administratorView.getContentPane(), "Operation performed successfully");

            } catch(Exception e1) {
                JOptionPane.showMessageDialog(administratorView.getContentPane(), "This id is invalid");
            }

            administratorView.getTextIdEmployee().setText("");
        }
    }

    private class ViewEmployeeButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ComponentFactory componentFactory = ComponentFactory.instance(false);
                UserService userService = componentFactory.getUserService();
                Long id = Long.parseLong(administratorView.getTextIdEmployee().getText());
                User user = userService.findById(id);
                if (user != null) {
                    administratorView.getTextUserInformations().setText("Username: " + user.getUsername()
                            + ",    Role: " + user.getRoles().get(0).getRole());
                }
            } catch (Exception entityNotFoundException) {
                JOptionPane.showMessageDialog(administratorView.getContentPane(), "This id is invalid");
            }
            administratorView.getTextIdEmployee().setText("");
        }
    }

    private class GenerateReportButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ComponentFactory componentFactory = ComponentFactory.instance(false);
            ActionService actionService = componentFactory.getActionService();
            try {
                Long startPeriod = Long.parseLong(administratorView.getTextStartPeriod().getText());
                Long stopPeriod = Long.parseLong(administratorView.getTextStopPeriod().getText());
                String username = administratorView.getTextUsernameEmployee().getText();
                List<Action> action = actionService.findByTimeAndUser(startPeriod, stopPeriod, username);
                FileOutputStream fileOutput = null;
                try {
                    fileOutput = new FileOutputStream("report" + reportIndex + ".txt");
                    reportIndex++;
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                PrintStream fileOutputChar = new PrintStream(fileOutput);
                fileOutputChar.println("Id action: " + "Action name: " + "Username:");
                for (Action i : action) {
                    fileOutputChar.println(i.getId() + "        " + i.getActionName() + "      " + i.getUsername());
                }

                if (action.isEmpty()) {
                    fileOutputChar.println("No activities performed by this user");
                }
                else
                {
                    JOptionPane.showMessageDialog(administratorView.getContentPane(), "The report was successfully generated");
                }

            } catch (EntityNotFoundException entityNotFoundException) {
                entityNotFoundException.printStackTrace();
            }
            administratorView.getTextStartPeriod().setText("");
            administratorView.getTextStopPeriod().setText("");
           administratorView.getTextUsernameEmployee().setText("");
        }
    }
}
