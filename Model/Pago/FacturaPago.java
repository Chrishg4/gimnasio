/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Pago;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.FileOutputStream;

/**
 *
 * @author pamel
 */
public class FacturaPago {
    public static void generarReportePDF(String rutaArchivo, Pago pago) {
        try {
            // Crear el documento
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter.getInstance(document, new FileOutputStream(rutaArchivo));
            document.open();

            // Fuentes para el documento
            Font tituloFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLUE);
            Font subTituloFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.DARK_GRAY);
            Font textoFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);

            // Título
            Paragraph titulo = new Paragraph("Factura del Pago", tituloFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20);
            document.add(titulo);

            // Línea separadora
            LineSeparator line = new LineSeparator();
            document.add(line);

            // ID del pago y cliente
            Paragraph ids = new Paragraph();
            ids.add(new Chunk("ID Pago: ", subTituloFont));
            ids.add(new Chunk(String.valueOf(pago.getIdpago()), textoFont));
            ids.add(Chunk.NEWLINE);
            ids.add(new Chunk("ID Cliente: ", subTituloFont));
            ids.add(new Chunk(pago.getCustomer().getCedula(), textoFont));
            ids.setSpacingAfter(10);
            document.add(ids);

            // Otra línea separadora
            document.add(line);

            // Fecha del pago
            Paragraph fecha = new Paragraph();
            fecha.add(new Chunk("Fecha: ", subTituloFont));
            fecha.add(new Chunk(String.valueOf(pago.getFecha()), textoFont));
            fecha.setSpacingAfter(10);
            document.add(fecha);

            // Subtotal
            Paragraph subTotal = new Paragraph();
            subTotal.add(new Chunk("Subtotal: ", subTituloFont));
            subTotal.add(new Chunk("₡" + String.format("%.2f", pago.getSubtotal()), textoFont));
            subTotal.setSpacingAfter(10);
            document.add(subTotal);

            // Impuesto
            Paragraph impuesto = new Paragraph();
            impuesto.add(new Chunk("Impuesto: ", subTituloFont));
            impuesto.add(new Chunk("₡" + String.format("%.2f", pago.getImpuesto()), textoFont));
            impuesto.setSpacingAfter(10);
            document.add(impuesto);

            // Total
            Paragraph total = new Paragraph();
            total.add(new Chunk("Total: ", subTituloFont));
            total.add(new Chunk("₡" + String.format("%.2f", pago.getTotal()), textoFont));
            total.setSpacingAfter(10);
            document.add(total);

            // Cerrar documento
            document.close();

            System.out.println("Reporte PDF generado exitosamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
