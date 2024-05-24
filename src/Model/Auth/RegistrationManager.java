package Model.Auth;
import Model.Users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationManager {
    private Connection connection;

    public RegistrationManager(Connection connection) {
        this.connection = connection;
    }

    public void registerUser(User user) throws SQLException {
        String sql = "INSERT INTO users (id, name, address, phone, role) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getIdUser());
            statement.setString(2, user.getNama());
            statement.setString(3, user.getAlamat());
            statement.setString(4, user.getNoHp());
            statement.setString(5, user.getRole());

            statement.executeUpdate();
        }
    }
}
