package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdministratorView extends JFrame {

    private JPanel panelDateEmployee;
    private JPanel panelOperationsEmployee;
    private JPanel panelViewEmployee;
    private JPanel panelReport;
    private JLabel usernameEmployee;
    private JLabel idEmployee;
    private JLabel passwordEmployee;
    private JLabel userInformation;
    private JLabel startPeriod;
    private JLabel stopPeriod;
    private JLabel username;
    private JTextField textStartPeriod;
    private JTextField textStopPeriod;
    private JTextField textUsernameEmployee;
    private JTextField textUserInformation;
    private JTextField textUsername;
    private JTextField textIdEmployee;
    private JTextField textPassword;
    private JButton addEmployee;
    private JButton deleteEmployee;
    private JButton viewEmployee;
    private JButton updateEmployee;
    private JButton generateReport;

    public AdministratorView() throws HeadlessException {
        setName("Administrator view");
        setSize(1300, 300);
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(new FlowLayout());
        panelDateEmployee.add(idEmployee);
        panelDateEmployee.add(textIdEmployee);
        panelDateEmployee.add(usernameEmployee);
        panelDateEmployee.add(textUsername);
        panelDateEmployee.add(passwordEmployee);
        panelDateEmployee.add(textPassword);
        add(panelDateEmployee);

        panelOperationsEmployee.add(addEmployee);
        panelOperationsEmployee.add(updateEmployee);
        panelOperationsEmployee.add(viewEmployee);
        panelOperationsEmployee.add(deleteEmployee);
        add(panelOperationsEmployee);

        panelViewEmployee.add(userInformation);
        panelViewEmployee.add(textUserInformation);
        add(panelViewEmployee);

        panelReport.add(startPeriod);
        panelReport.add(textStartPeriod);
        panelReport.add(stopPeriod);
        panelReport.add(textStopPeriod);
        panelReport.add(username);
        panelReport.add(textUsernameEmployee);
        panelReport.add(generateReport);
        add(panelReport);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeFields() {

        panelDateEmployee = new JPanel();
        panelOperationsEmployee = new JPanel();
        panelViewEmployee = new JPanel();
        panelReport = new JPanel();
        usernameEmployee = new JLabel("Username");
        passwordEmployee = new JLabel("Password");
        idEmployee = new JLabel("Id");
        textUsername = new JTextField("", 30);
        textPassword = new JTextField("", 30);
        textIdEmployee = new JTextField("", 30);
        addEmployee = new JButton("Add employee");
        updateEmployee = new JButton("Update employee");
        viewEmployee = new JButton("View employee");
        deleteEmployee = new JButton("Delete employee");
        userInformation = new JLabel("Employee informations");
        textUserInformation = new JTextField("", 60);
        startPeriod = new JLabel("Start period");
        stopPeriod = new JLabel("Stop period");
        username = new JLabel("Username employee");
        textStartPeriod = new JTextField("", 15);
        textStopPeriod = new JTextField("", 15);
        textUsernameEmployee = new JTextField("", 20);
        generateReport = new JButton("Generate report");

    }

    public void setAddEmployeeButtonListener(ActionListener addEmployeeButtonListener) {
        addEmployee.addActionListener(addEmployeeButtonListener);
    }
    public void setUpdateEmployeeButtonListener(ActionListener updateEmployeeButtonListener) {
        updateEmployee.addActionListener(updateEmployeeButtonListener);
    }
    public void setViewEmployeeButtonListener(ActionListener viewEmployeeButtonListener) {
        viewEmployee.addActionListener(viewEmployeeButtonListener);
    }
    public void setDeleteEmployeeButtonListener(ActionListener deleteEmployeeButtonListener) {
        deleteEmployee.addActionListener(deleteEmployeeButtonListener);
    }
    public void setGenerateReportButtonListener(ActionListener generateReportButtonListener) {
        generateReport.addActionListener(generateReportButtonListener);
    }

    public JTextField getTextUsername() {
        return textUsername;
    }

    public JTextField getTextPassword() {
        return textPassword;
    }

    public JTextField getTextIdEmployee() {
        return textIdEmployee;
    }

    public JTextField getTextUserInformations() {
        return textUserInformation;
    }

    public JTextField getTextStartPeriod() {
        return textStartPeriod;
    }

    public JTextField getTextStopPeriod() {
        return textStopPeriod;
    }

    public JTextField getTextUsernameEmployee() {
        return textUsernameEmployee;
    }
}
