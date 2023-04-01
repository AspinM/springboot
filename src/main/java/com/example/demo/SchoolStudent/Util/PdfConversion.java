package com.example.demo.SchoolStudent.Util;

import com.example.demo.SchoolStudent.SchoolEntity.Registerent;
//import com.itextpdf.text.*;
//import com.itextpdf.text.Phrase;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import com.lowagie.text.*;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PdfConversion {
    private List<Registerent> listUsers;


    public PdfConversion(List<Registerent> listUsers)
    {
        this.listUsers = listUsers;
    }
//    @Autowired
//    Environment environment;

    private void writeTableHeader(PdfPTable table)
    {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(8);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        ((com.lowagie.text.Font) font).setColor(Color.WHITE);

        cell.setPhrase(new Phrase("id", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Firstname", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Lastname", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Dob", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Mail", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Phone", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("City", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Gender", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Pincode", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("State", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Address", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Hobbies", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Qualification10", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Qualification12", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Courses", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for (Registerent user : listUsers)
        {
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getFirstname());
            table.addCell(user.getLastname().toString());
            table.addCell(user.getDob().toString());
            table.addCell(String.valueOf(user.getMail()));
            table.addCell(user.getPhone().toString());
            table.addCell(user.getCity());
            table.addCell(user.getGender());
            table.addCell(user.getPincode());
            table.addCell(user.getState());
            table.addCell(user.getAddress());
            table.addCell(Arrays.toString(user.getHobbies()));
            table.addCell(Arrays.toString(user.getQualification10()));
            table.addCell(Arrays.toString(user.getQualification12()));
            table.addCell(user.getCourses());

        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy_HHmmss");
        String currentDateTime = dateFormatter.format(new Date());

        String pathPdf ="E:\\"+"studentList_"+currentDateTime+".pdf";

        Document document = new Document(PageSize._11X17);
        PdfWriter.getInstance(document,new FileOutputStream(pathPdf));


        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(5);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Users", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);
        font.setSize(2);
        PdfPTable table = new PdfPTable(15);
        table.setWidthPercentage(110f);
        table.setWidths(new float[] {20.5f, 40f, 40f, 40f, 40f, 40f, 40f, 40f,40f, 40f, 40f, 55f, 55f, 55f, 55f});
        table.setSpacingBefore(2);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }


}
