package br.com.config;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


public class BDConfig {
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Conectando...");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/dbnotas", "root", "jardri07");
    }
}
