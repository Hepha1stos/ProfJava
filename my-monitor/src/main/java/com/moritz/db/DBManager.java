package com.moritz.db;

import java.io.File;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DBManager {

    private Connection conn;
    private String dbPath;
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Constructor: Connects to the database and creates tables if the database did
     * not exists
     */
    public DBManager() {
       

        try {
            File classFile = new File(DBManager.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            File projectRoot = classFile.getParentFile().getParentFile().getParentFile();
            dbPath = new File(projectRoot, "database.db").getAbsolutePath();
            String url = "jdbc:sqlite:" + dbPath;
            

            conn = DriverManager.getConnection(url);

            if (conn != null) {
                LOGGER.info("Connection to database at {} established", dbPath);
            }
            this.create_tables();
        } catch (URISyntaxException e) {
            LOGGER.error("Error while resolving Projectpath: {}",e.getMessage());
        } catch (SQLException e) {
            LOGGER.error("Error while connecting to database: {} " ,e.getMessage());

        }
    }


    public Connection getConnection(){
        return this.conn;
    }

    /**
     * creates tables if database.db is new created
     */
    private void create_tables() {
        String createProcessTable = "CREATE TABLE IF NOT EXISTS process (\n"
                + "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "    pid INTEGER NOT NULL,\n"
                + "    name TEXT,\n"
                + "    command_line TEXT,\n"
                + "    start_time DATETIME\n"
                + ");";
    
        String createProcessMetricsTable = "CREATE TABLE IF NOT EXISTS process_metrics (\n"
                + "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "    process_id INTEGER NOT NULL,\n"
                + "    timestamp DATETIME NOT NULL,\n"
                + "    rss INTEGER NOT NULL,\n"
                + "    cpu_usage REAL NOT NULL,\n"
                + "    FOREIGN KEY (process_id) REFERENCES process(id)\n"
                + ");";
    
    
        try (Statement stmt = conn.createStatement()) {
            
            stmt.execute(createProcessTable);
            LOGGER.info("Created process table");
    
            stmt.execute(createProcessMetricsTable);
            LOGGER.info("Created process_metric table");

        } catch (SQLException e) {
            LOGGER.error("Error while creating Tables: {}", e.getMessage());
        }
    }
    

}
