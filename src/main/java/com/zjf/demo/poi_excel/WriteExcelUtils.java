package com.zjf.demo.poi_excel;

import com.zjf.demo.classUtils.ClassUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WriteExcelUtils<T> {

    public void writeExcel(List<T> ts, String filePath) {
        XSSFWorkbook workbook = getWorkBook(ts);
        try (FileOutputStream output = new FileOutputStream(filePath)) {
            workbook.write(output);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private XSSFWorkbook getWorkBook(List<T> ts) {
        ClassUtils classUtils = new ClassUtils();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();

        List<String> attributes = classUtils.getAttributes(ts.get(0));
        List<List> values = classUtils.getAllValues(attributes, ts);

        for (String attr : attributes) {
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue(attr);
        }

        for (int i = 0; i < values.size(); i++) {
            XSSFRow row = sheet.createRow(i + 1);
            List temp = values.get(i);
            for (int j = 0; j < temp.size(); j++) {
                XSSFCell cell = row.createCell(j);
                cell.setCellValue(String.valueOf(temp.get(j)));
            }
        }
        return workbook;
    }
}
