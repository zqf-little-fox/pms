package com.ujiuye.utils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelUtils {

    public static String getCellValue(Cell c){
        String o = "";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        switch(c.getCellType()){
            case Cell.CELL_TYPE_BLANK:
                o = "";
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                o = String.valueOf(c.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                o = String.valueOf(c.getCellFormula());
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if(HSSFDateUtil.isCellDateFormatted(c)){
                    Date date = c.getDateCellValue();
                    o = sdf.format(date);
                }else {
                    o = c.getNumericCellValue()+"";
                }
                break;
            case Cell.CELL_TYPE_STRING:
                o = c.getStringCellValue();
                break;
            default:
                o = null;
                break;
        }
        return o;
    }
}
