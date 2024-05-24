package Model.Auth;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService extends Login {
    private Connection connection;

    public LoginService(Connection connection, String username, String password) {
        super(username, password);
        this.connection = connection;
    }

    @Override
    public boolean Authenticate() {
        String query = "SELECT COUNT(*) AS count FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, getUsername());
            statement.setString(2, getPassword());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}