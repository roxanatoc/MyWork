package repository.client;

import model.Client;
import model.builder.ClientBuilder;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepositoryMySQL implements ClientRepository{

    private final Connection connection;

   public ClientRepositoryMySQL(Connection connection) {
       this.connection = connection;
   }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from client";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                clients.add(getClientFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    @Override
    public Client findByCNP(Long CNP) throws EntityNotFoundException {
       try {
            Statement statement = connection.createStatement();
            String sql = "Select * from client where CNP=" + CNP;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getClientFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(CNP, Client.class.getSimpleName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(CNP, Client.class.getSimpleName());
        }
    }

    @Override
    public boolean save(Client client) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO client values (?, ?, ?, ?)");
            insertStatement.setLong(1, client.getCNP());
            insertStatement.setString(2, client.getName());
            insertStatement.setInt(3, client.getCardNumber());
            insertStatement.setString(4, client.getAddress());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Client client) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("UPDATE client set name=?, cardNumber=?, address=? WHERE CNP=" + client.getCNP());
            insertStatement.setString(1, client.getName());
            insertStatement.setInt(2, client.getCardNumber());
            insertStatement.setString(3, client.getAddress());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeByCNP(Long CNP) {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from client where CNP = " + CNP;
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from client where CNP >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Client getClientFromResultSet(ResultSet rs) throws SQLException {
        return new ClientBuilder()
                .setCNP(rs.getLong("CNP"))
                .setName(rs.getString("name"))
                .setCardNumber(rs.getInt("cardNumber"))
                .setAddress(rs.getString("address"))
                .build();
    }
}
