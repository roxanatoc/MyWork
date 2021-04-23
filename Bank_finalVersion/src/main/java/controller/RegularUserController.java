package controller;

import componentFactory.ComponentFactory;
import model.Account;
import model.Action;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ActionBuilder;
import model.builder.ClientBuilder;
import model.validation.Notification;
import repository.EntityNotFoundException;
import service.account.AccountService;
import service.action.ActionService;
import service.client.ClientService;
import service.user.AuthenticationService;
import view.RegularUserView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class RegularUserController {

    private final RegularUserView regularUserView;
    private final AuthenticationService authenticationService;
    private final String username;
    private static int billIndex = 1;

    public RegularUserController(RegularUserView regularUserView, AuthenticationService authenticationService, String username) {
        this.regularUserView = regularUserView;
        this.authenticationService = authenticationService;
        this.username = username;
        regularUserView.setAddClientButtonListener(new RegularUserController.AddClientButtonListener());
        regularUserView.setDeleteClientButtonListener(new RegularUserController.DeleteClientButtonListener());
        regularUserView.setViewClientButtonListener(new RegularUserController.ViewClientButtonListener());
        regularUserView.setUpdateClientButtonListener(new RegularUserController.UpdateClientButtonListener());
        regularUserView.setCreateAccountButtonListener(new RegularUserController.CreateAccountButtonListener());
        regularUserView.setUpdateAccountButtonListener(new RegularUserController.UpdateAccountButtonListener());
        regularUserView.setViewAccountButtonListener(new RegularUserController.ViewAccountButtonListener());
        regularUserView.setDeleteAccountButtonListener(new RegularUserController.DeleteAccountButtonListener());
        regularUserView.setTransferMoneyButtonListener(new RegularUserController.TransferMoneyButtonListener());
        regularUserView.setProcessBillButtonListener(new RegularUserController.ProcessBillButtonListener());
    }

    private class AddClientButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                Long CNP = Long.parseLong(regularUserView.getTextCNP().getText());
                String name = regularUserView.getTextName().getText();
                int cardNumber = Integer.parseInt(regularUserView.getTextCardNumber().getText());
                String address = regularUserView.getTextAddress().getText();

                ClientBuilder clientBuilder = new ClientBuilder();
                clientBuilder.setCNP(CNP);
                clientBuilder.setName(name);
                clientBuilder.setCardNumber(cardNumber);
                clientBuilder.setAddress(address);
                Client client = clientBuilder.build();

                ComponentFactory componentFactory = ComponentFactory.instance(false);
                ClientService clientService = componentFactory.getClientService();

                Notification<Boolean> notification = clientService.save(client);
                if (notification.hasErrors()) {
                    JOptionPane.showMessageDialog(regularUserView.getContentPane(), notification.getFormattedErrors());
                }
                else {
                    ActionService actionService = componentFactory.getActionService();
                    ActionBuilder actionBuilder = new ActionBuilder();
                    actionBuilder.setUsername(username);
                    actionBuilder.setActionName("Add client");
                    Action action = actionBuilder.build();
                    actionService.save(action);
                    JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Operation performed successfully");
                }
                } catch (Exception e1) {
                JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Error in adding the client");
            }
            regularUserView.getTextCNP().setText("");
            regularUserView.getTextName().setText("");
            regularUserView.getTextCardNumber().setText("");
            regularUserView.getTextAddress().setText("");
        }
    }

    private class UpdateClientButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                Long CNP = Long.parseLong(regularUserView.getTextCNP().getText());
                String name = regularUserView.getTextName().getText();
                int cardNumber = Integer.parseInt(regularUserView.getTextCardNumber().getText());
                String address = regularUserView.getTextAddress().getText();

                ClientBuilder clientBuilder = new ClientBuilder();
                clientBuilder.setCNP(CNP);
                clientBuilder.setName(name);
                clientBuilder.setCardNumber(cardNumber);
                clientBuilder.setAddress(address);
                Client client = clientBuilder.build();

                ComponentFactory componentFactory = ComponentFactory.instance(false);
                ClientService clientService = componentFactory.getClientService();
                clientService.update(client);

                ActionService actionService = componentFactory.getActionService();
                ActionBuilder actionBuilder = new ActionBuilder();
                actionBuilder.setUsername(username);
                actionBuilder.setActionName("Update client");
                Action action = actionBuilder.build();
                actionService.save(action);
                JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Operation performed successfully");


            } catch (Exception e1) {
                JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Error in updating the client");
            }
            regularUserView.getTextCNP().setText("");
            regularUserView.getTextName().setText("");
            regularUserView.getTextCardNumber().setText("");
            regularUserView.getTextAddress().setText("");
        }
    }

    private class DeleteClientButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ComponentFactory componentFactory = ComponentFactory.instance(false);
                ClientService clientService = componentFactory.getClientService();
                Long CNP = Long.parseLong(regularUserView.getTextCNP().getText());
                clientService.removeByCNP(CNP);

                ActionService actionService = componentFactory.getActionService();
                ActionBuilder actionBuilder = new ActionBuilder();
                actionBuilder.setUsername(username);
                actionBuilder.setActionName("Delete client");
                Action action = actionBuilder.build();
                actionService.save(action);
                JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Operation performed successfully");

            } catch (EntityNotFoundException entityNotFoundException) {
                JOptionPane.showMessageDialog(regularUserView.getContentPane(), "This CNP is invalid");
            }

            regularUserView.getTextCNP().setText("");
        }
    }

    private class ViewClientButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ComponentFactory componentFactory = ComponentFactory.instance(false);
                ClientService clientService = componentFactory.getClientService();
                Long CNP = Long.parseLong(regularUserView.getTextCNP().getText());
                Client client = clientService.findByCNP(CNP);
                if (client != null) {
                    regularUserView.getViewClientInformation().setText("CNP: " + client.getCNP()
                            + ",    Name: " + client.getName()
                            + ",    Card Number: " + client.getCardNumber()
                            + ",    Address: " + client.getAddress());

                    ActionService actionService = componentFactory.getActionService();
                    ActionBuilder actionBuilder = new ActionBuilder();
                    actionBuilder.setUsername(username);
                    actionBuilder.setActionName("View client");
                    Action action = actionBuilder.build();
                    actionService.save(action);
                }

            } catch (EntityNotFoundException entityNotFoundException) {
                JOptionPane.showMessageDialog(regularUserView.getContentPane(), "This CNP is invalid");
            }
            regularUserView.getTextCNP().setText("");
        }
    }

    private class CreateAccountButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Long id = Long.parseLong(regularUserView.getTextId().getText());
                String type = regularUserView.getTextType().getText();
                double amountOfMoney = Double.parseDouble(regularUserView.getTextAmountOfMoney().getText());
                String creationDate = regularUserView.getTextCreationDate().getText();
                Long CNPClient = Long.parseLong(regularUserView.getTextCNPClient().getText());

                AccountBuilder accountBuilder = new AccountBuilder();
                accountBuilder.setId(id);
                accountBuilder.setType(type);
                accountBuilder.setAmountOfMoney(amountOfMoney);
                accountBuilder.setCreationDate(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(creationDate));
                accountBuilder.setCNPClient(CNPClient);
                Account account = accountBuilder.build();

                ComponentFactory componentFactory = ComponentFactory.instance(false);
                AccountService accountService = componentFactory.getAccountService();
                ClientService clientService = componentFactory.getClientService();
                Client client = clientService.findByCNP(CNPClient);
                if (client != null) {
                    accountService.save(account);

                    ActionService actionService = componentFactory.getActionService();
                    ActionBuilder actionBuilder = new ActionBuilder();
                    actionBuilder.setUsername(username);
                    actionBuilder.setActionName("Add account");
                    Action action = actionBuilder.build();
                    actionService.save(action);
                    JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Operation performed successfully");
                }
                else {
                    JOptionPane.showMessageDialog(regularUserView.getContentPane(),  "Non-existent customer");
                }

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Error in creating a new account");
            }

            regularUserView.getTextId().setText("");
            regularUserView.getTextType().setText("");
            regularUserView.getTextAmountOfMoney().setText("");
            regularUserView.getTextCNPClient().setText("");

        }
    }

    private class UpdateAccountButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Long id = Long.parseLong(regularUserView.getTextId().getText());
                String type = regularUserView.getTextType().getText();
                double amountOfMoney = Double.parseDouble(regularUserView.getTextAmountOfMoney().getText());
                String creationDate = regularUserView.getTextCreationDate().getText();
                Long CNPClient = Long.parseLong(regularUserView.getTextCNPClient().getText());

                AccountBuilder accountBuilder = new AccountBuilder();
                accountBuilder.setId(id);
                accountBuilder.setType(type);
                accountBuilder.setAmountOfMoney(amountOfMoney);
                accountBuilder.setCreationDate(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(creationDate));
                accountBuilder.setCNPClient(CNPClient);
                Account account = accountBuilder.build();

                ComponentFactory componentFactory = ComponentFactory.instance(false);
                AccountService accountService = componentFactory.getAccountService();
                ClientService clientService = componentFactory.getClientService();
                Client client = clientService.findByCNP(CNPClient);
                if (client != null) {
                    accountService.update(account);

                    ActionService actionService = componentFactory.getActionService();
                    ActionBuilder actionBuilder = new ActionBuilder();
                    actionBuilder.setUsername(username);
                    actionBuilder.setActionName("Update account");
                    Action action = actionBuilder.build();
                    actionService.save(action);
                    JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Operation performed successfully");
                }
                else {
                    JOptionPane.showMessageDialog(regularUserView.getContentPane(),  "Non-existent customer");
                }

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Error in updating the account");
            }

            regularUserView.getTextId().setText("");
            regularUserView.getTextType().setText("");
            regularUserView.getTextAmountOfMoney().setText("");
            regularUserView.getTextCreationDate().setText("");
            regularUserView.getTextCNPClient().setText("");
        }
    }

    private class DeleteAccountButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Long id = Long.parseLong(regularUserView.getTextId().getText());

                ComponentFactory componentFactory = ComponentFactory.instance(false);
                AccountService accountService = componentFactory.getAccountService();
                Account account = accountService.findById(id);
                if (account != null) {
                    accountService.removeById(id);

                    ActionService actionService = componentFactory.getActionService();
                    ActionBuilder actionBuilder = new ActionBuilder();
                    actionBuilder.setUsername(username);
                    actionBuilder.setActionName("Delete account");
                    Action action = actionBuilder.build();
                    actionService.save(action);
                    JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Operation performed successfully");
                }

            } catch(Exception e1) {
                JOptionPane.showMessageDialog(regularUserView.getContentPane(), "This identification number is invalid");
            }

            regularUserView.getTextId().setText("");

        }
    }

    private class ViewAccountButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ComponentFactory componentFactory = ComponentFactory.instance(false);
                AccountService accountService = componentFactory.getAccountService();
                Long id = Long.parseLong(regularUserView.getTextId().getText());
                Account account = accountService.findById(id);
                if (account != null) {
                    regularUserView.getViewAccountInformation().setText("identificationNumber: " + account.getId()
                            + ",    Type: " + account.getType()
                            + ",    Amount of money: " + account.getAmountOfMoney()
                            + ",    Creation date: " + account.getCreationDate()
                            + ",    CNP client: " + account.getCNPClient());

                    ActionService actionService = componentFactory.getActionService();
                    ActionBuilder actionBuilder = new ActionBuilder();
                    actionBuilder.setUsername(username);
                    actionBuilder.setActionName("View account");
                    Action action = actionBuilder.build();
                    actionService.save(action);
                }

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(regularUserView.getContentPane(), "This identification number is invalid");
            }
            regularUserView.getTextId().setText("");
        }
    }

    private class TransferMoneyButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ComponentFactory componentFactory = ComponentFactory.instance(false);
                AccountService accountService = componentFactory.getAccountService();
                Long idTransfer = Long.parseLong(regularUserView.getTextIdForTransfer1().getText());
                Long idReceive = Long.parseLong(regularUserView.getTextIdForTransfer2().getText());
                Double amount = Double.parseDouble(regularUserView.getTextAmountOfMoneyForTransfer().getText());
                Account account = accountService.findById(idTransfer);
                if (account.getAmountOfMoney() - amount > 0) {
                    accountService.transfer(idTransfer, idReceive, amount);

                    ActionService actionService = componentFactory.getActionService();
                    ActionBuilder actionBuilder = new ActionBuilder();
                    actionBuilder.setUsername(username);
                    actionBuilder.setActionName("Transfer money");
                    Action action = actionBuilder.build();
                    actionService.save(action);
                    JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Operation performed successfully");
                }
                else
                {
                    JOptionPane.showMessageDialog(regularUserView.getContentPane(),"Insufficiently sold");
                }

            } catch (Exception exception) {
                JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Date entered incorrectly");
            }
            regularUserView.getTextIdForTransfer1().setText("");
            regularUserView.getTextIdForTransfer2().setText("");
            regularUserView.getTextAmountOfMoneyForTransfer().setText("");
        }
    }

    private class ProcessBillButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            FileOutputStream fileOutput = null;
            try {
                fileOutput = new FileOutputStream("bill" + billIndex + ".txt");
                billIndex++;
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            PrintStream fileOutputChar = new PrintStream(fileOutput);
            try {
                ComponentFactory componentFactory = ComponentFactory.instance(false);
                AccountService accountService = componentFactory.getAccountService();
                Long id = Long.parseLong(regularUserView.getTextIdCont().getText());
                Double amount = Double.parseDouble(regularUserView.getTextAmountOfMoneyForPay().getText());
                String utility = regularUserView.getTextUtility().getText();
                Account account = accountService.findById(id);
                if (account.getAmountOfMoney() - amount > 0) {
                    accountService.processBill(id, amount);
                    fileOutputChar.println("Id account: " + id);
                    fileOutputChar.println("The paid utility is: " + utility);
                    fileOutputChar.println("The amount paid is: " + amount + " lei");
                    fileOutputChar.println("Date: " + ZonedDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));

                    regularUserView.getTextProcessBill().setText("Id account: " + id
                            + ",     The paid utility is: " + utility
                            + ",     The amount paid is: " + amount + " lei"
                            + ",     Date: " + ZonedDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));

                    ActionService actionService = componentFactory.getActionService();
                    ActionBuilder actionBuilder = new ActionBuilder();
                    actionBuilder.setUsername(username);
                    actionBuilder.setActionName("Process bill");
                    Action action = actionBuilder.build();
                    actionService.save(action);
                    JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Operation performed successfully");
                }
                else
                {
                    JOptionPane.showMessageDialog(regularUserView.getContentPane(),"Insufficiently sold");
                }

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(regularUserView.getContentPane(), "Date entered incorrectly");
            }
            regularUserView.getTextIdCont().setText("");
            regularUserView.getTextAmountOfMoneyForPay().setText("");
            regularUserView.getTextUtility().setText("");
        }
    }
}
