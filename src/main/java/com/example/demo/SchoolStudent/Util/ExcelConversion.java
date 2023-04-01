package com.example.demo.SchoolStudent.Util;

import com.example.demo.SchoolStudent.SchoolEntity.Registerent;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ExcelConversion {

    private List<Registerent> studentList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelConversion(List <Registerent> studentList)
    {
        this.studentList = studentList;
        workbook = new XSSFWorkbook();
    }
    private void writeHeader() {
        sheet = workbook.createSheet("Student");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "id", style);
        createCell(row, 1, "Firstname", style);
        createCell(row, 2, "Lastname", style);
        createCell(row, 3, "dateofbirth", style);
        createCell(row, 4, "Mail", style);
        createCell(row, 5, "Phone", style);
        createCell(row, 6, "Gender", style);
        createCell(row, 7, "Address", style);
        createCell(row, 8, "City", style);
        createCell(row, 9, "Pincode", style);
        createCell(row, 10, "State", style);
        createCell(row, 11, "Country", style);
        createCell(row, 12, "Hobbies", style);
        createCell(row, 13, "Qualification10()", style);
        createCell(row, 14, "Qualification12", style);
        createCell(row, 15, "Courses", style);




    }
    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer)
        {
            cell.setCellValue((Integer) valueOfCell);
        }
        else if (valueOfCell instanceof Long)
        {
            cell.setCellValue((Long) valueOfCell);
        }
        else if (valueOfCell instanceof String)
        {
            cell.setCellValue((String) valueOfCell);
        }
        else if (valueOfCell instanceof Double)
        {
            cell.setCellValue((Double) valueOfCell);
        }
//        else
//        {
//            cell.setCellValue((Boolean) valueOfCell);
//        }
//          else
//        {
//            cell.setCellValue((Boolean) valueOfCell);
//        }
        cell.setCellStyle(style);
    }
    private void write() {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        for (Registerent record: studentList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, record.getId(), style);
            createCell(row, columnCount++, record.getFirstname(), style);
            createCell(row, columnCount++, record.getLastname(), style);
            createCell(row, columnCount++, record.getDob().toString(), style);
            createCell(row, columnCount++, record.getMail(), style);
            createCell(row, columnCount++, record.getPhone(), style);
            createCell(row, columnCount++, record.getGender(), style);
            createCell(row, columnCount++, record.getAddress(), style);
            createCell(row, columnCount++, record.getCity(), style);
            createCell(row, columnCount++, record.getPincode(), style);
            createCell(row, columnCount++, record.getState(), style);
            createCell(row, columnCount++, record.getCountry(), style);
            createCell(row, columnCount++, Arrays.toString(record.getHobbies()), style);
            createCell(row, columnCount++, Arrays.toString(record.getQualification10()), style);
            createCell(row, columnCount++, Arrays.toString(record.getQualification12()), style);
            createCell(row, columnCount++, record.getCourses(), style);



        }
    }
    //    private static final File TEMP_DIRECTORY = new File(System.getProperty("java.io.tmpdir"));
    public void generateExcelFile(HttpServletResponse response) throws IOException
    {
        writeHeader();
        write();
        DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String currentDateTime = dateFormatter.format(new Date());

        String pathXls ="E:\\"+"studentList_"+currentDateTime+".xlsx";
        FileOutputStream outputStream = new FileOutputStream(pathXls);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
