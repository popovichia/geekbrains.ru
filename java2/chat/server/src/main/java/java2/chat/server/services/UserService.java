/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java2.chat.server.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author igor
 */
public class UserService {
    private static Connection connection;
    private static Statement statement;
    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:main.db");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getFieldValueByNickName(String fieldName, String nickName) {
        String resultString = null;
        String sql = String.format("SELECT %s FROM users where nickname = '%s'", fieldName, nickName);
        System.out.println(sql);
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                resultString = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return resultString;
        }
        return resultString;
    }
    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
