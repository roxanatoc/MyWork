package repository.action;

import model.Action;
import model.builder.ActionBuilder;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActionRepositoryMySQL implements ActionRepository{

    private final Connection connection;

    public ActionRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean save(Action action) {
        try {
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO action values (null, ?, ?)");
            insertStatement.setString(1, action.getUsername());
            insertStatement.setString(2, action.getActionName());
            insertStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Action> findByTimeAndUser(Long timeStart, Long timeStop, String username) throws EntityNotFoundException {
        try {
            List<Action> actions = new ArrayList<>();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM action WHERE id>=" + timeStart + " and id<=" + timeStop + " and username= '" + username + "'";
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                actions.add(getActionFromResultSet(rs));
            }
            return actions;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException((long) 0, Action.class.getSimpleName());
        }
    }

    private Action getActionFromResultSet(ResultSet rs) throws SQLException {
        return new ActionBuilder()
                .setId(rs.getLong("id"))
                .setUsername(rs.getString("username"))
                .setActionName(rs.getString("actionName"))
                .build();
    }
}
