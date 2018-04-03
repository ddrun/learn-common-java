package com.example.djran.common.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/3/28
 * excel导出工具类
 * @author d.djran@gmail.com
 */
public class ExcelExportUtils {
    /**
     * 导出的excel标题
     */
    private String title;
    /**
     * 导出的excel工作表名
     */
    private String sheetName;
    /**
     * 导出的excel列名
     */
    private String[] rowName;
    /**
     * 导出的excel数据
     */
    private List<Object[]> dataList = new ArrayList<Object[]>();

    /**
     * 不使用标题的构造
     * @param sheetName
     * @param rowName
     * @param dataList
     */
    public ExcelExportUtils(String sheetName, String[] rowName, List<Object[]> dataList) {
        this.sheetName = sheetName;
        this.rowName = rowName;
        this.dataList = dataList;
    }

    /**
     * 使用标题的构造
     * @param title
     * @param sheetName
     * @param rowName
     * @param dataList
     */
    public ExcelExportUtils(String title, String sheetName, String[] rowName, List<Object[]> dataList) {
        this.title = title;
        this.sheetName = sheetName;
        this.rowName = rowName;
        this.dataList = dataList;
    }
    /**
     * 导出数据
     * @param out
     * @throws IOException
     */
    public void export2Execl(OutputStream out) throws IOException {
        // 创建工作簿对象
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = workbook.createSheet(sheetName);

        // sheet样式定义【getColumnTopStyle()/getStyle()均为自定义方法 - 在下面 - 可扩展】
        // 获取列头样式对象
        HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);
        // 单元格样式对象
        HSSFCellStyle style = this.getStyle(workbook);

        HSSFRow headRow = null;
        // 产生表格标题行
        if (title != null) {
            HSSFRow titleRow = sheet.createRow(0);
            HSSFCell titleCell = titleRow.createCell(0);
            // 设置标题跨行和跨列
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length - 1)));
            titleCell.setCellStyle(columnTopStyle);
            titleCell.setCellValue(title);
            // 有标题,在索引2的位置创建行(最顶端的行开始的第二行)
            headRow = sheet.createRow(2);
        } else {
            // 没有标题,直接在第一行创建列头
            headRow = sheet.createRow(0);
        }

        // 定义所需列数
        int columnNum = rowName.length;

        // 将列头设置到sheet的单元格中
        for (int i = 0; i < columnNum; i++) {
            // 创建列头对应个数的单元格
            HSSFCell headCell = headRow.createCell(i);
            // 设置列头单元格的数据类型
            headCell.setCellType(HSSFCell.CELL_TYPE_STRING);
            RichTextString text = new HSSFRichTextString(rowName[i]);
            // 设置列头单元格的值
            headCell.setCellValue(text);
            // 设置列头单元格样式
            headCell.setCellStyle(columnTopStyle);
        }

        // 将查询出的数据设置到sheet对应的单元格中
        for (int i = 0; i < dataList.size(); i++) {
            int rowIndex = title == null ? 1 : 3;
            // 遍历每个对象
            Object[] obj = dataList.get(i);
            // 创建所需的行
            HSSFRow row = sheet.createRow(i + rowIndex);

            for (int j = 0; j < obj.length; j++) {
                // 设置单元格的数据类型
                HSSFCell cell = row.createCell(j, HSSFCell.CELL_TYPE_STRING);
                if (obj[j] == null) {
                    cell.setCellValue("");
                } else {
                    // 设置单元格的值
                    cell.setCellValue(obj[j].toString());
                }
                // 设置单元格样式
                cell.setCellStyle(style);
            }
        }
        // 让列宽随着导出的数据自动适应
        for (int colNum = 0; colNum < columnNum; colNum++) {
            int columnWidth = sheet.getColumnWidth(colNum) / 256;
            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                HSSFRow currentRow = sheet.getRow(rowNum);
                if (currentRow != null) {
                    HSSFCell currentCell = currentRow.getCell(colNum);
                    if (currentCell != null) {
                        if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                            int length = currentCell.getStringCellValue().length();
                            if (columnWidth < length) {
                                columnWidth = length;
                            }
                        }
                    }
                }
            }
            sheet.setColumnWidth(colNum, (columnWidth + 6) * 256);
        }
        workbook.write(out);
    }

    /**
     * 列头单元格样式
     * @param workbook
     * @return
     */
    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体大小
        font.setFontHeightInPoints((short) 11);
        // 字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }

    /**
     * 列数据信息单元格样式
     * @param workbook
     * @return
     */
    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        // 设置字体大小
        // font.setFontHeightInPoints((short)10);
        // 字体加粗
        // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 设置字体名字
        font.setFontName("Courier New");
        // 设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 在样式用应用设置的字体;
        style.setFont(font);
        // 设置自动换行;
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }
}
