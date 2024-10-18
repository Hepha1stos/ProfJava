package com.moritz.db;

import java.io.File;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager{

    private Connection conn;
    private String dbPath;

    /**
     * Constructor: Connects to the database and creeates tables if the database did not exists
     */
    public DBManager(){
        try {
            File classFile = new File(DBManager.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            File projectRoot = classFile.getParentFile().getParentFile().getParentFile();
            dbPath = new File(projectRoot, "datenbank.db").getAbsolutePath();
            String url = "jdbc:sqlite:" + dbPath;

            System.out.println("Datenbankpfad: " + dbPath);

            conn = DriverManager.getConnection(url);

            if (conn != null){
                System.out.println("Connection established");
            }
            this.create_tables();
        } catch (URISyntaxException e) {
            System.out.println("Error while resolving Projectpath: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error while connecting to database: " + e.getMessage());
            
        }
    }

    /**
     * creates tables if database.db is new created
     */   
    private void create_tables(){
        String sql_statement = "CREATE TABLE IF NOT EXISTS recipe (\n"
           +  "id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
           + " name TEXT NOT NULL, \n"
           +" ingredients TEXT NOT NULL \n"
           + ");";
           try (Statement stmt = conn.createStatement()){
                System.out.println("Hilfe");
               stmt.execute(sql_statement);
           } catch (SQLException e) {
            System.out.println("Error while creating Tables");
           }
    }

}