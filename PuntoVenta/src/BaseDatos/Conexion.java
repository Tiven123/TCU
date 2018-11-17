/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Steven Ortiz
 */
public class Conexion {

    private static final String DRIVER = "org.postgresql.Driver";
    private static final String TIPO = "jdbc:postgresql://";
    private static final String SERVER = "localhost";
    private static final String PUERTO = "5432";
    private static final String DB = "tcu";
    private static final String USER = "postgres";
    private static final String PASS = "1234";

    public static Connection iniciar() throws Exception {
        Connection c = null;
        try {
            Class.forName(DRIVER);
            c = DriverManager.getConnection(TIPO + SERVER + ":" + PUERTO
                    + "/" + DB, USER, PASS);
            return c;
        } catch (Exception e) {
            throw e;
        }
    }
}
