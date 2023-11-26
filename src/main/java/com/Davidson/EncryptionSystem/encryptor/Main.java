package com.Davidson.EncryptionSystem.encryptor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Davidson Adepoju\\IdeaProjects\\FINAL2\\user_details.db");
             Statement statement = connection.createStatement()) {


            String st = "CREATE TABLE IF NOT EXISTS user_details(name TEXT, username TEXT, password TEXT, access TEXT)";
            statement.execute(st);
            statement.execute("INSERT INTO user_details(name, username, password, access) " +
                                "VALUES('Adepoju Akinade', 'davidson', 'davidson', 'Admin User')" );

        } catch (SQLException e) {
            System.out.println("Unable to create Database " + e.getMessage());
        }
    }
}
