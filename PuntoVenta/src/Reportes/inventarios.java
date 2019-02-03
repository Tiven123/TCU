/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import BaseDatos.Conexion;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author steven
 */
public class inventarios {

    Conexion con = new Conexion();

    public void iniciar() {
        try {
            java.sql.Connection cnonn = con.iniciar();
            JasperReport reporte = (JasperReport) JRLoader.loadObject("/home/steven/Desktop/TCU/TCU/PuntoVenta/src/Reportes/inventario.jasper");
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, cnonn);
            JasperViewer view = new JasperViewer(jprint, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    /*public void otra() {
        try {

            // descarga dentro del mismo proyecto
            java.sql.Connection cnonn = con.iniciar();
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    "/home/steven/Desktop/TCU/TCU/PuntoVenta/src/Reportes/inventario.jasper", null, cnonn);
            JRPdfExporter exp = new JRPdfExporter();
            exp.setExporterInput(new SimpleExporterInput(jasperPrint));
            exp.setExporterOutput(new SimpleOutputStreamExporterOutput("ReporteAlumnos.pdf"));
            SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
            exp.setConfiguration(conf);
            exp.exportReport();

            // se muestra en una ventana aparte para su descarga
            JasperPrint jasperPrintWindow = JasperFillManager.fillReport(
                    "C:\\Users\\Ecodeup\\JaspersoftWorkspace\\Reportes Escuela\\ReporteAlumnos.jasper", null,
                    Conexion.conectar());
            JasperViewer jasperViewer = new JasperViewer(jasperPrintWindow);
            jasperViewer.setVisible(true);
        } catch (Exception e) {
        }
    }
*/
}
