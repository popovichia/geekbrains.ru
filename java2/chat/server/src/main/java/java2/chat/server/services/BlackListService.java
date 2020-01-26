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
import java.util.ArrayList;
import java2.chat.server.entity.User;

/**
 *
 * @author igor
 */
public class BlackListService {
    private static Connection connection;
    private static Statement stmt;
    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:main.db");
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getBlackListByNickName(User blackListOwner) {
        ArrayList<String> resultList = new ArrayList<>();
        String sql = String.format(
                "SELECT ublocked.nickname FROM blacklist AS b "
                + "INNER JOIN users AS uowner ON b.user_id=uowner.id "
                + "INNER JOIN users AS ublocked ON b.block_user_id=ublocked.id "
                + "WHERE uowner.nickname ='%s'",
                blackListOwner.getNickName());
        try {
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                resultList.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
    public static int getRowId() {
        int result = 0;
        String sql = "SELECT MAX(id) FROM blacklist;";
        try {
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static void saveInBlackListByNickName(User blackListOwner, User user) {
        int rowId = getRowId() + 1;
        String sql = String.format(
                "INSERT INTO blacklist(id, user_id, block_user_id) VALUES(%d, %d, %d);",
                rowId, blackListOwner.getId(), user.getId());
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
}
