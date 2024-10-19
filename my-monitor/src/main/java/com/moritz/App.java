package com.moritz;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.moritz.db.DBManager;
import com.moritz.process.ProcessManager;
public class App 

{
    private static final Logger LOGGER = LogManager.getLogger(App.class);
    public static void main( String[] args )
    {
        LOGGER.info("Ich komme aus der main");
        System.out.println( "Hello World!" );
        DBManager t = new DBManager();
        ProcessManager pm = new ProcessManager();
        pm.getProcessNames();
    }
}
