package utilities;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {
    private Workbook workbook;

    private Sheet sheet;

    private String path;


    public ExcelUtils(String path, int sheetIndex) {

        this.path = path;

        try{
            FileInputStream fis = new FileInputStream(path);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheetAt(sheetIndex);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    // satir sayisini dondurur
    public int rowCount(){
        return sheet.getLastRowNum()+1;
    }


    // sutun sayisini dondurur
    public int colCount(){
        return sheet.getRow(0).getLastCellNum();
    }

    // exceldeki verileri List<Map<String,String>> formatina cevirerek dondurur. Javada rahat calismamizi saglar
    public List<Map<String, String>> getDataList() {

        // getting all columns
        List<String> columns = getColumnsNames();
        // method will return this
        List<Map<String, String>> data = new ArrayList<>();
        for (int i = 1; i < rowCount(); i++) {
            // get each row
            Row row = sheet.getRow(i);
            // creating map of the row using the column and value
            // key=column, value=cell
            Map<String, String> rowMap = new HashMap<String, String>();
            for (Cell cell :row) {
                int columnIndex = cell.getColumnIndex();
                rowMap.put(columns.get(columnIndex), cell.toString());
            }
            data.add(rowMap);
        }
        return data;
    }

    // Istedigimiz hucredeki veriyi almamizi saglar
    public String getCellData(int rowNum, int colNum) {
        Cell cell;
        try {
            cell = sheet.getRow(rowNum).getCell(colNum);
            String cellData = cell.toString();
            return cellData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //============getting all data into two dimentional array and returning the data===
    public String[][] getDataArray() {
        String[][] data = new String[rowCount()][colCount()];
        for (int i = 0; i < rowCount(); i++) {
            for (int j = 0; j < colCount(); j++) {
                String value = getCellData(i, j);
                data[i][j] = value;
            }
        }
        return data;
    }

    //==============going to the first row and reading each column one by one==================//
    public List<String> getColumnsNames() {
        List<String> columns = new ArrayList<>();
        for (Cell cell : sheet.getRow(0)) {
            columns.add(cell.toString());
        }
        return columns;
    }
    //=========When you enter the row and column number, returning the value===============//
    public void setCellData(String value, int rowNum, int colNum) {
        Cell cell;
        Row row;
        try {
            row = sheet.getRow(rowNum);
            cell = row.getCell(colNum);
            if (cell == null) {//if there is no value, create a cell.
                cell = row.createCell(colNum);
                cell.setCellValue(value);
            } else {
                cell.setCellValue(value);
            }
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setCellData(String value, String columnName, int row) {
        int column = getColumnsNames().indexOf(columnName);
        setCellData(value, row, column);
    }
    //this method will return data table as 2d array
    //so we need this format because of data provider.
    public String[][] getDataArrayWithoutFirstRow() {
        String[][] data = new String[rowCount()-1][colCount()];
        for (int i = 1; i < rowCount(); i++) {
            for (int j = 0; j < colCount(); j++) {
                String value = getCellData(i, j);
                data[i-1][j] = value;
            }
        }
        return data;
    }

    //get Row Count in sheet
    public int getRowCountInSheet(){
        int rowcount = sheet.getLastRowNum()- sheet.getFirstRowNum();
        return rowcount;
    }

    //set Cell Value
    public void setCellValue(int rowNum,int cellNum,String cellValue,String excelFilePath) throws IOException {
        //creating a new cell in row and setting value to it
        sheet.getRow(rowNum).createCell(cellNum).setCellValue(cellValue);

        FileOutputStream outputStream = new FileOutputStream(excelFilePath);
        workbook.write(outputStream);



    }


}
