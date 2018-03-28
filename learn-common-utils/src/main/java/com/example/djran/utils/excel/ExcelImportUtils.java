package com.example.djran.utils.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/3/28
 * excel导入工具类（poi）
 * @author d.djran@gmail.com
 */
public class ExcelImportUtils {
    /**
     * 导入数据到List
     * @param tClass 导入数据实体
     * @param workbook 导入文件
     * @param <T>
     * @return
     * @throws Exception
     */
    public  static <T> List<T> import2List(Class<T> tClass, Workbook workbook)throws Exception{
        List<T> list=new ArrayList<>();
        //获取第一个选项卡
        Sheet sheet=workbook.getSheetAt(0);
        Row row = null;
        Cell cell = null;
        //获取tClass对象所有属性
        Field[] fields=tClass.getDeclaredFields();
        int rowNum=sheet.getLastRowNum()+1;
        for(int j=1;j<rowNum;j++){
            int i=0;
            row=sheet.getRow(j);
            //实例化对象
            T t=tClass.newInstance();
            for(Field field:fields){
                String methodName="set"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
                String value="";
                cell=row.getCell(i++);
                if (cell == null) {
                    continue;
                }
                value=getCellStringValue(cell).toString();
                Method method=t.getClass().getMethod(methodName,field.getType());
                // 在excel中获取的值都是String,所以要根据属性的类型进行值的转换
                if (field.getType().toString().equals("class java.lang.String")) {
                    // 调用实例对象obj的set方法进行对属性的数值
                    method.invoke(t, value);
                } else if (field.getType().toString().equals("int") || field.getType().toString().equals("Integer")) {
                    method.invoke(t, Integer.valueOf(value));
                }
            }
            list.add(t);
        }
        workbook.close();
        return list;
    }

    /**
     * 获取cell内容
     * @param cell
     * @return
     */
    private static Object getCellStringValue(Cell cell) {
        Object value="";
        cell.setCellType(Cell.CELL_TYPE_STRING);
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                // String类型
                value = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                // 日期数据返回LONG类型的时间戳
                if ("yyyy\"年\"m\"月\"d\"日\";@".equals(cell.getCellStyle().getDataFormatString())) {
                } else {
                    // 数值类型返回double类型的数字
                    value = cell.getNumericCellValue();
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                // 布尔类型
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                // 空单元格
                break;
            default:
                value = cell.toString();
        }
        return value;
    }
}
