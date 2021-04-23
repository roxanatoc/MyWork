package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class RegularUserView extends JFrame {

    private JButton addClient;
    private JButton updateClient;
    private JButton viewClient;
    private JButton deleteClient;
    private JButton createAccount;
    private JButton updateAccount;
    private JButton deleteAccount;
    private JButton viewAccount;
    private JButton transferMoney;
    private JPanel panelOperationsClient;
    private JPanel panelOperationsAccount;
    private JPanel panelDateClient;
    private JPanel panelDateAccount;
    private JPanel panelViewClient;
    private JPanel panelViewAccount;
    private JPanel panelForTransfer;
    private JPanel panelProcessBill;
    private JLabel CNP;
    private JLabel nameClient;
    private JLabel cardNumber;
    private JLabel address;
    private JTextField textCNP;
    private JTextField textName;
    private JTextField textCardNumber;
    private JTextField textAddress;
    private JLabel clientInformation;
    private JTextField viewClientInformation;
    private JLabel accountInformation;
    private JTextField viewAccountInformation;
    private JLabel id;
    private JLabel typeAccount;
    private JLabel amountOfMoney;
    private JLabel creationDate;
    private JLabel CNPClient;
    private JTextField textId;
    private JTextField textType;
    private JTextField textAmountOfMoney;
    private JTextField textCreationDate;
    private JTextField textCNPClient;
    private JLabel idForTransfer1;
    private JLabel idForTransfer2;
    private JLabel amountOfMoneyForTransfer;
    private JTextField textIdForTransfer1;
    private JTextField textIdForTransfer2;
    private JTextField textAmountOfMoneyForTransfer;
    private JLabel utility;
    private JTextField textUtility;
    private JLabel idCont;
    private JTextField textIdCont;
    private JLabel amountOfMoneyForPay;
    private JTextField textAmountOfMoneyForPay;
    private JButton processBill;
    private JTextField textProcessBill;
    private JLabel processBillInformation;
    private JPanel panelViewBill;


    public RegularUserView() throws HeadlessException {
        setName("Regular user view");
        setSize(1300, 400);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new FlowLayout());
        panelDateClient.setLayout(new FlowLayout());
        panelDateClient.add(CNP);
        panelDateClient.add(textCNP);
        panelDateClient.add(nameClient);
        panelDateClient.add(textName);
        panelDateClient.add(cardNumber);
        panelDateClient.add(textCardNumber);
        panelDateClient.add(address);
        panelDateClient.add(textAddress);
        add(panelDateClient);

        panelOperationsClient.setLayout(new FlowLayout());
        panelOperationsClient.add(addClient);
        panelOperationsClient.add(updateClient);
        panelOperationsClient.add(deleteClient);
        panelOperationsClient.add(viewClient);
        add(panelOperationsClient);

        panelViewClient.setLayout(new FlowLayout());
        panelViewClient.add(clientInformation);
        panelViewClient.add(viewClientInformation);
        add(panelViewClient);

        panelDateAccount.setLayout(new FlowLayout());
        panelDateAccount.add(id);
        panelDateAccount.add(textId);
        panelDateAccount.add(typeAccount);
        panelDateAccount.add(textType);
        panelDateAccount.add(amountOfMoney);
        panelDateAccount.add(textAmountOfMoney);
        panelDateAccount.add(creationDate);
        panelDateAccount.add(textCreationDate);
        panelDateAccount.add(CNPClient);
        panelDateAccount.add(textCNPClient);
        add(panelDateAccount);

        panelOperationsAccount.setLayout(new FlowLayout());
        panelOperationsAccount.add(createAccount);
        panelOperationsAccount.add(updateAccount);
        panelOperationsAccount.add(deleteAccount);
        panelOperationsAccount.add(viewAccount);
        add(panelOperationsAccount);

        panelViewAccount.setLayout(new FlowLayout());
        panelViewAccount.add(accountInformation);
        panelViewAccount.add(viewAccountInformation);
        add(panelViewAccount);

        panelForTransfer.setLayout(new FlowLayout());
        panelForTransfer.add(idForTransfer1);
        panelForTransfer.add(textIdForTransfer1);
        panelForTransfer.add(idForTransfer2);
        panelForTransfer.add(textIdForTransfer2);
        panelForTransfer.add(amountOfMoneyForTransfer);
        panelForTransfer.add(textAmountOfMoneyForTransfer);
        panelForTransfer.add(transferMoney);
        add(panelForTransfer);

        panelProcessBill.add(idCont);
        panelProcessBill.add(textIdCont);
        panelProcessBill.add(utility);
        panelProcessBill.add(textUtility);
        panelProcessBill.add(amountOfMoneyForPay);
        panelProcessBill.add(textAmountOfMoneyForPay);
        panelProcessBill.add(processBill);
        add(panelProcessBill);

        panelViewBill.add(processBillInformation);
        panelViewBill.add(textProcessBill);
        add(panelViewBill);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {

        addClient = new JButton("Add client");
        updateClient = new JButton("Update client");
        viewClient = new JButton("View client");
        deleteClient = new JButton("Delete client");
        createAccount = new JButton("Create account");
        updateAccount = new JButton("Update account");
        deleteAccount = new JButton("Delete account");
        viewAccount = new JButton("View account");
        panelOperationsClient = new JPanel();
        panelOperationsAccount = new JPanel();
        panelDateClient = new JPanel();
        panelDateAccount = new JPanel();
        panelViewClient = new JPanel();
        panelViewAccount = new JPanel();
        panelProcessBill = new JPanel();
        CNP = new JLabel("CNP");
        nameClient = new JLabel("Name");
        cardNumber = new JLabel("Card Number");
        address = new JLabel("Adress");
        textCNP = new JTextField("", 20);
        textName = new JTextField("", 20);
        textCardNumber = new JTextField("", 20);
        textAddress = new JTextField("", 20);
        clientInformation = new JLabel("Client informations");
        accountInformation = new JLabel("Account informations");
        viewClientInformation = new JTextField("", 80);
        viewAccountInformation = new JTextField("", 80);
        id = new JLabel("Identification number");
        typeAccount = new JLabel("Type");
        amountOfMoney = new JLabel("Amount of money");
        creationDate = new JLabel("Creation date");
        CNPClient = new JLabel("CNP Client");
        textId = new JTextField("", 10);
        textType = new JTextField("", 10);
        textAmountOfMoney = new JTextField("", 10);
        textCreationDate = new JTextField("", 15);
        textCreationDate.setText( ZonedDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        textCNPClient = new JTextField("", 15);
        idForTransfer1 = new JLabel("Identification number1");
        idForTransfer2 = new JLabel("Identification number2");
        amountOfMoneyForTransfer = new JLabel("Amount of money for transfer");
        textIdForTransfer1 = new JTextField("", 10);
        textIdForTransfer2 = new JTextField("", 10);
        textAmountOfMoneyForTransfer = new JTextField("", 10);
        transferMoney = new JButton("Transfer money");
        panelForTransfer = new JPanel();
        utility = new JLabel("Utility");
        textUtility = new JTextField("", 20);
        idCont = new JLabel("Identification number");
        textIdCont = new JTextField("", 10);
        amountOfMoneyForPay = new JLabel("Amount of money for pay");
        textAmountOfMoneyForPay = new JTextField("", 10);
        processBill = new JButton("Process bill");
        textProcessBill = new JTextField("", 80);
        processBillInformation = new JLabel("Bill");
        panelViewBill = new JPanel();
    }

    public void setAddClientButtonListener(ActionListener addClientButtonListener) {
        addClient.addActionListener(addClientButtonListener);
    }

    public void setUpdateClientButtonListener(ActionListener updateClientButtonListener) {
        updateClient.addActionListener(updateClientButtonListener);
    }

    public void setDeleteClientButtonListener(ActionListener deleteClientButtonListener) {
        deleteClient.addActionListener(deleteClientButtonListener);
    }

    public void setViewClientButtonListener(ActionListener viewClientButtonListener) {
        viewClient.addActionListener(viewClientButtonListener);
    }

    public void setCreateAccountButtonListener(ActionListener createAccountButtonListener) {
        createAccount.addActionListener(createAccountButtonListener);
    }

    public void setUpdateAccountButtonListener(ActionListener updateAccountButtonListener) {
        updateAccount.addActionListener(updateAccountButtonListener);
    }

    public void setDeleteAccountButtonListener(ActionListener deleteAccountButtonListener) {
        deleteAccount.addActionListener(deleteAccountButtonListener);
    }

    public void setViewAccountButtonListener(ActionListener viewAccountButtonListener) {
        viewAccount.addActionListener(viewAccountButtonListener);
    }

    public void setTransferMoneyButtonListener(ActionListener transferMoneyButtonListener) {
        transferMoney.addActionListener(transferMoneyButtonListener);
    }

    public void setProcessBillButtonListener(ActionListener processBillButtonListener) {
        processBill.addActionListener(processBillButtonListener);
    }

    public JTextField getTextCNP() {
        return textCNP;
    }

    public JTextField getTextName() {
        return textName;
    }

    public JTextField getTextCardNumber() {
        return textCardNumber;
    }

    public JTextField getTextAddress() {
        return textAddress;
    }

    public JTextField getTextId() {
        return textId;
    }

    public JTextField getTextType() {
        return textType;
    }

    public JTextField getTextAmountOfMoney() {
        return textAmountOfMoney;
    }

    public JTextField getTextCreationDate() {
        return textCreationDate;
    }

    public JTextField getTextCNPClient() {
        return textCNPClient;
    }

    public JTextField getTextIdForTransfer1() {
        return textIdForTransfer1;
    }

    public JTextField getTextIdForTransfer2() {
        return textIdForTransfer2;
    }

    public JTextField getTextAmountOfMoneyForTransfer() {
        return textAmountOfMoneyForTransfer;
    }

    public JTextField getViewClientInformation() {
        return viewClientInformation;
    }

    public JTextField getViewAccountInformation() {
        return viewAccountInformation;
    }

    public JTextField getTextUtility() {
        return textUtility;
    }

    public JTextField getTextIdCont() {
        return textIdCont;
    }

    public JTextField getTextAmountOfMoneyForPay() {
        return textAmountOfMoneyForPay;
    }

    public JTextField getTextProcessBill() {
        return textProcessBill;
    }

}
