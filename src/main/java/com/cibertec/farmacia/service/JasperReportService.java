package com.cibertec.farmacia.service;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.sf.jasperreports.engine.*;

@Service
public class JasperReportService {

    @Autowired
    private DataSource dataSource;

    public byte[] generarReportePdf(String nombreReporte, Map<String, Object> parametros) throws Exception {
        Connection conn = null;
        try {
            // Busca el diseño en la carpeta resources/reports que creaste
            InputStream reportStream = getClass().getResourceAsStream("/reports/" + nombreReporte + ".jrxml");
            
            if (reportStream == null) {
                throw new Exception("No se encontró el archivo: /reports/" + nombreReporte + ".jrxml");
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
            conn = dataSource.getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conn);
            
            return JasperExportManager.exportReportToPdf(jasperPrint);
            
        } catch (Exception e) {
            throw new Exception("Error en Jasper: " + e.getMessage());
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }
}