package repository.account;

import model.Account;
import model.builder.AccountBuilder;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepositoryMySQL implements AccountRepository{

    private final Connection connection;

    public AccountRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                accounts.add(getAccountFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    @Override
    public Account findById(Long id) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account where id=" + id;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getAccountFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(id, Account.class.getSimpleName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, Account.class.getSimpleName());
        }
    }

    @Override
    public boolean save(Account account) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO account values (?, ?, ?, ?, ?)");
            insertStatement.setLong(1, account.getId());
            insertStatement.setString(2, account.getType());
            insertStatement.setDouble(3, account.getAmountOfMoney());
            insertStatement.setDate(4, new java.sql.Date(account.getCreationDate().getTime()));
            insertStatement.setLong(5, account.getCNPClient());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeById(Long id) {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from account where id=" + id;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean update(Account account) throws EntityNotFoundException {
        try {
            PreparedStatement insertStatement = connection.prepareStatement("UPDATE account set type=?, amountOfMoney=?, creationDate=?, CNPClient=? WHERE id=" + account.getId());
            insertStatement.setString(1, account.getType());
            insertStatement.setDouble(2, account.getAmountOfMoney());
            insertStatement.setDate(3, new Date(account.getCreationDate().getTime()));
            insertStatement.setDouble(4, account.getCNPClient());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void transfer(Long id1, Long id2, double amount) {
        try {
            Account accountTransfer = findById(id1);
            PreparedStatement insertStatement1 = connection.prepareStatement("UPDATE account set amountOfMoney=? WHERE id=" + id1);
            insertStatement1.setDouble(1, accountTransfer.getAmountOfMoney() - amount);
            insertStatement1.executeUpdate();

            Account accountReceive = findById(id2);
            PreparedStatement insertStatement2 = connection.prepareStatement("UPDATE account set amountOfMoney=? WHERE id=" + id2);
            insertStatement2.setDouble(1, accountReceive.getAmountOfMoney() + amount);
            insertStatement2.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processBill(Long id, double amount) {
        try {
            Account account = findById(id);
            PreparedStatement insertStatement = connection.prepareStatement("UPDATE account set amountOfMoney=? WHERE id=" + id);
            insertStatement.setDouble(1, account.getAmountOfMoney() - amount);
            insertStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from account where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Account getAccountFromResultSet(ResultSet rs) throws SQLException {
        return new AccountBuilder()
                .setId(rs.getLong("id"))
                .setType(rs.getString("type"))
                .setAmountOfMoney(rs.getDouble("amountOfMoney"))
                .setCreationDate(new Date(rs.getDate("creationDate").getTime()))
                .setCNPClient(rs.getLong("CNPClient"))
                .build();
    }
}
