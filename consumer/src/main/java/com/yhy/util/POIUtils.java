package com.yhy.util;

import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.List;

public class POIUtils <T> {

    public void export(Class o,List<T> list, HttpServletResponse response) {
        try {
            //获取类上标注名字
            ExportClassDetail annotation1 = (ExportClassDetail)o.getAnnotation(ExportClassDetail.class);

//            HSSFWorkbook wb = getHSSFWorkbook(o, list);
            XSSFWorkbook wb = getHSSFWorkbook(o,list);
            if (wb == null) {
                return;
            }
            //写入response
            response.reset();
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + new String((annotation1.fileName() + ".xlsx").getBytes("UTF-8"), "ISO8859_1"));
            ServletOutputStream fileOut = response.getOutputStream();
            wb.write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public XSSFWorkbook getHSSFWorkbook(Class c,List<T> list) throws NoSuchFieldException, IllegalAccessException {
        if (CollectionUtils.isEmpty(list)){
            return null;
        }
        Field[] fields = c.getDeclaredFields();

        List<Field> needShowFields = Lists.newArrayList();
        for (Field field : fields) {
            Details annotation = c.getDeclaredField(field.getName()).getAnnotation(Details.class);
            if (annotation.isExport()){
                needShowFields.add(field);
            }
        }
        // 封装excel
        XSSFWorkbook hssfWorkbook = new XSSFWorkbook();
        //创建sheet页
        XSSFSheet sheet = hssfWorkbook.createSheet();
        Row row = sheet.createRow(0);
        XSSFCellStyle style = hssfWorkbook.createCellStyle();
        XSSFFont font = hssfWorkbook.createFont();
        font.setBold(true);
        //font.setFontHeightInPoints((short) 16);
        style.setFont(font);

        if (row != null) {
            for (int i = 0; i < needShowFields.size(); i++) {
                //获取字段详情解释
                Details annotation = c.getDeclaredField(needShowFields.get(i).getName()).getAnnotation(Details.class);
                //添加标题并赋值
                row.createCell(i).setCellValue(annotation.value());
                row.getCell(i).setCellStyle(style);
                //row.setRowStyle(style);
            }
        }

        //设置变量,便于后续方便给文本框更改样式
        int rowIndex = 0;
        //滤空

            for (int i = 0; i < list.size(); i++) {
                T p = list.get(i);
                rowIndex++;
                //新增sheet页
                Row rowF = sheet.createRow(rowIndex);
                for (int j = 0; j < needShowFields.size(); j++) {
                    //获取反射过来的属性对象
                    Field field = needShowFields.get(j);
                    //把成员变量变为公有化
                    field.setAccessible(true);
                    //获取集合中成员变量的值field.toString()
                    Object o1 = field.get(p);
                    if ("class java.util.Date".equals(field.getGenericType().toString())){
                        String format = new SimpleDateFormat("yyyy-MM-dd").format(o1);
                        o1=format;
                    }
                    //滤空赋值
                    if (o1 != null) {
                        rowF.createCell(j).setCellValue(o1.toString());
                    } else {
                        rowF.createCell(j).setCellValue("");
                    }
                }
            }

        //设置样式
        for (int i = 0; i <= rowIndex; i++) {
            sheet.setColumnWidth(i, 20 * 256);
        }
        return hssfWorkbook;
    }

}
