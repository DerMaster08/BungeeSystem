package de.dermaster.utils;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.*;

public class MySQL {
    private static Connection con = null;
    public static boolean connect(){
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(
                    "jdbc:mysql://85.214.204.242:3306/BungeeSystem?autoReconnect=true&amp","Server","Server08!");
            Statement stmt=con.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS PlayerStats(UUID VARCHAR(255), OnTime INT, Name VARCHAR(255));");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Friends(Namef VARCHAR(255), Namet VARCHAR(255));");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Request(Namef VARCHAR(255), Namet VARCHAR(255));");
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static void saveOnTime(String UUID, int OnTime) throws SQLException {
            PreparedStatement ps = con.prepareStatement("UPDATE `PlayerStats` SET OnTime='"+OnTime+"' WHERE UUID=\""+UUID+"\";");
            ps.execute();
    }
    public static int getOnTime(String UUID) throws SQLException {
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM `PlayerStats` WHERE UUID=\""+UUID+"\";");
        while (rs.next()) {
                return rs.getInt("OnTime");
        }
        return 1;
    }
    public static boolean hasAccount(String UUID) throws SQLException{
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM `PlayerStats` WHERE UUID=\""+UUID+"\";");
        while (rs.next()) {
            if(rs.getString("Name").isEmpty()){
                return false;
            }else {
                return true;
            }
        }
        return false;
    }
    public static void registerOnTime(ProxiedPlayer p) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO PlayerStats(UUID, OnTime, Name) VALUES (?,?,?)");
        ps.setString(1, String.valueOf(p.getUniqueId()));
        ps.setInt(2, 0);
        ps.setString(3, p.getName());
        ps.execute();
    }


    public static void addFriend(String Namef, String Namet) throws SQLException{
        PreparedStatement ps = con.prepareStatement("INSERT INTO Friends(Namef, Namet) VALUES (?,?)");
        ps.setString(1, Namef);
        ps.setString(2, Namet);
        ps.execute();
        ps = con.prepareStatement("INSERT INTO Friends(Namef, Namet) VALUES (?,?)");
        ps.setString(1, Namet);
        ps.setString(2, Namef);
        ps.execute();
    }

    public static boolean arefriends(String Namef, String Namet) throws SQLException{
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM `Friends` WHERE Namef=\""+Namef+"\";");
        while (rs.next()) {
            if(rs.getString("Namet").contains(Namet)){
                return true;
            }else{ return false;}
        }
        return false;
    }
    public static void sendRequest(String Namef, String Namet) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO Request(Namef, Namet) VALUES (?,?)");
        ps.setString(1, Namef);
        ps.setString(2, Namet);
        ps.execute();
    }
    public static boolean hasSendRequest(String Namef, String Namet) throws SQLException{
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM `Request` WHERE Namef=\""+Namef+"\";");
        while (rs.next()) {
            if(rs.getString("Namet").contains(Namet)){
                return true;
            }else {return false;}
        }
        return false;
    }
    public static void removeRequest(String Namef, String Namet) throws SQLException{
        PreparedStatement ps = con.prepareStatement("DELETE FROM `Request` WHERE Namef=\""+Namef+"\" AND Namet=\""+Namet+"\";");
        ps.execute();
    }
}
