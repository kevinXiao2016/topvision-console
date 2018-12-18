/**
 * 
 */
package com.topvision.console.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kelers
 * 
 */
public class JdbcUtils {

    private static class JdbcConnTask extends TimerTask {
        @Override
        public void run() {
        }
    }

    private static Logger logger = LoggerFactory.getLogger(JdbcUtils.class);
    public static final String ORACLE = "Oracle";
    public static final String MYSQL = "mysql";

    public static final String SQLSERVER = "SQLServer";

    private static Timer timer = new Timer();

    public static boolean connect(String type, String host, String port, String user, String passwd) {
        Connection conn = null;
        String url = null;
        try {
            if (ORACLE.equals(type)) {
                // 1521
                url = "jdbc:oracle:thin:@" + host + ":" + port + ":orcl";
            } else if (MYSQL.equals(type)) {
                // 3306
                url = "jdbc:mysql://" + host + ":" + port + "/";
            } else if (SQLSERVER.equals(type)) {
                // 1433
                url = "jdbc:microsoft:sqlserver://" + host + ":" + port;
            }
            conn = DriverManager.getConnection(url, user, passwd);
            Statement st = conn.createStatement();
            st.execute("CREATE DATABASE IF NOT EXISTS ems");
            return true;
        } catch (Exception ex) {
            logger.debug("JDBC Util...", ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
        return false;
    }

    public static boolean loadDriver(String driverClassName) {
        try {
            Class.forName(driverClassName).newInstance();
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        } catch (IllegalAccessException e) {
            // Constructor is private, OK for DriverManager contract
            return true;
        } catch (InstantiationException e) {
            return false;
        } catch (Throwable e) {
            return false;
        }
    }

    public static void main(String[] args) {
        loadDriver("com.mysql.jdbc.Driver");
        connect(MYSQL, "localhost", "3306", "root", "");
    }

    public static void startJdbcConnTask(String type, String host, String port, String user, String passwd) {
        timer.schedule(new JdbcConnTask(), 60000, 60000);
    }

}
