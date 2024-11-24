import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PengelolaanKontakHelper {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pbo_db";
    private static final String USER = "root";  // Sesuaikan dengan username MySQL Anda
    private static final String PASSWORD = "";  // Sesuaikan dengan password MySQL Anda

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Connection to MySQL has been established.");
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
        return conn;
    }

    // Method to create the contacts table
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS kontak ("
                + "id INTEGER PRIMARY KEY AUTO_INCREMENT,"
                + "nama TEXT NOT NULL,"
                + "telepon TEXT NOT NULL,"
                + "alamat TEXT NOT NULL,"
                + "gender TEXT NOT NULL,"
                + "kategori TEXT NOT NULL"
                + ");";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'kontak' created successfully (if it didn't already exist).");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }
}
