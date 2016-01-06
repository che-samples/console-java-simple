package com.codenvy.example.mysql;

import com.mysql.jdbc.JDBC4PreparedStatement;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        try {
            new Application().run();
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load MySQL JDBC Driver.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void run() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        final String host = String.format("jdbc:mysql://" + System.getenv("MYSQL_PORT_3306_TCP_ADDR") + ":" + System.getenv("MYSQL_PORT_3306_TCP_PORT") + "/%s", "test");
        final String user = "root";
        final String pass = System.getenv("MYSQL_ENV_MYSQL_ROOT_PASSWORD");

        System.out.println(String.format("Connected to %s with user: %s, password: %s", host, user, pass));

        try (Connection sqlConnection = DriverManager.getConnection(host, user, pass)) {
            createTable(sqlConnection);
            insertData(sqlConnection);
            readData(sqlConnection);

            updateData(sqlConnection);
            readData(sqlConnection);

            deleteData(sqlConnection);
            readData(sqlConnection);
        }

    }

    private void createTable(Connection sqlConnection) throws SQLException {
        System.out.println("\nCreate new table `new_user`");
        try (JDBC4PreparedStatement statement = (JDBC4PreparedStatement)sqlConnection.prepareStatement(getCreateTableSQL())) {
            System.out.println(String.format("Process SQL query:\n%s", statement.asSql()));
            statement.execute();
        }
    }

    private String getCreateTableSQL() {
        StringBuilder sb = new StringBuilder();

        Scanner sc = new Scanner(ClassLoader.getSystemResourceAsStream("table_test_user.sql"));
        while (sc.hasNextLine()) {
            sb.append(sc.nextLine()).append("\n");
        }

        return sb.toString();
    }

    private void insertData(Connection sqlConnection) throws SQLException {
        Random random = new Random();
        System.out.println("\nInsert new rows into table `new_user`");

        final String insertSQL = "INSERT INTO new_user (userName, firstName, lastName, birthday, email) VALUES (?, ?, ?, ?, ?)";
        for (int i = 0; i < 5; i++) {
            try (JDBC4PreparedStatement statement = (JDBC4PreparedStatement)sqlConnection.prepareStatement(insertSQL)) {
                statement.setString(1, String.format("Codenvy_%d", (random.nextInt(900) + 100)));
                statement.setString(2, String.format("John_%d", (random.nextInt(900) + 100)));
                statement.setString(3, String.format("Doe_%d", (random.nextInt(900) + 100)));
                statement.setDate(4, new Date(System.currentTimeMillis()));
                statement.setString(5, String.format("user%d@site.com", (random.nextInt(900) + 100)));
                System.out.println(String.format("Process SQL query: %s", statement.asSql()));
                statement.execute();
            }
        }
    }

    private void readData(Connection sqlConnection) throws SQLException {
        final String selectSQL = "SELECT * FROM new_user";
        final String output = "ID: %d, UserName: %s, First Name: %s, Last Name: %s, Birthday: %s, Email: %s";

        System.out.println("\nSelect all rows from `new_user`");

        try (JDBC4PreparedStatement statement = (JDBC4PreparedStatement)sqlConnection.prepareStatement(selectSQL)) {
            System.out.println(String.format("Process SQL query: %s", statement.asSql()));
            System.out.println("Result set:");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println(String.format(output,
                                                     resultSet.getInt("id"),
                                                     resultSet.getString("userName"),
                                                     resultSet.getString("firstName"),
                                                     resultSet.getString("lastName"),
                                                     resultSet.getDate("birthday").toString(),
                                                     resultSet.getString("email")));
                }
            }
        }
    }

    private void updateData(Connection sqlConnection) throws SQLException {
        final String updateSQL = "UPDATE new_user SET firstName='Michael', lastName='Smith' WHERE id=3";

        System.out.println("\nUpdate row in table `new_user`");

        try (JDBC4PreparedStatement statement = (JDBC4PreparedStatement)sqlConnection.prepareStatement(updateSQL)) {
            System.out.println(String.format("Process SQL query: %s", statement.asSql()));
            statement.execute();
        }
    }

    private void deleteData(Connection sqlConnection) throws SQLException {
        final String deleteSQL = "DELETE FROM new_user WHERE id=3";

        System.out.println("\nDelete row from table `new_user`");

        try (JDBC4PreparedStatement statement = (JDBC4PreparedStatement)sqlConnection.prepareStatement(deleteSQL)) {
            System.out.println(String.format("Process SQL query: %s", statement.asSql()));
            statement.execute();
        }
    }
}
