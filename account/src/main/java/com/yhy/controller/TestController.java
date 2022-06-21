package com.yhy.controller;


import ch.qos.logback.core.util.TimeUtil;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;


@RestController
@RequestMapping("test")
@RefreshScope
public class TestController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${config.info:111}")
    private String configInfo;

    @GetMapping("test")
    public String test() {
        try {
            logger.info("test test test");
            logger.warn("warn test test");
            logger.error("error test test");
            if (logger.isDebugEnabled()){
                logger.debug("error test test");
            }
            Thread.sleep(5000);
            int a = 1/0;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "test2" + configInfo;
    }

    @GetMapping("test-syc")
    public String testSyc() {
        return tryToGetLockAndDo();
        //return t();
    }

    private static Lock lock = new ReentrantLock();

    private String tryToGetLockAndDo(){
        boolean b = false;
        try {
             b = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println(lock);
            if (b){
                System.out.println("拿到锁");
                Thread.sleep(10000);
                return "拿到";
            }else {
                System.out.println("拿不到锁");
                return "拿不到";
            }
        }catch (Exception e){
            System.out.println("get lock failed cause :" + e.getMessage());
        }finally {
            if (b){
                lock.unlock();
                System.out.println("释放锁");
            }
        }
        return "默认值";
    }

    private synchronized String t(){
        System.out.println("start");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sleep end");
        return "aaa";
    }

    @RequestMapping(value = "reward/coins-reward-template", method = RequestMethod.GET)
    public void downloadCoinsTemplate(HttpServletResponse response) {
        BufferedWriter csvFileOutputStream=null;
        try {
            //response.reset();
            //response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            //response.setHeader("Content-Disposition", "attachment;filename=" + new String(("BatchRewardCoinsTemplate-" + LocalDateTime.now().toString()).getBytes(StandardCharsets.UTF_8), "ISO8859_1") +".csv");
            File csvFile = File.createTempFile( new String(("BatchRewardCoinsTemplate-" + LocalDateTime.now().toString()).getBytes(StandardCharsets.UTF_8)),".csv");
            //设置列名
            HashMap<String,String> map = new LinkedHashMap<>();
            map.put("1", "uid");
            map.put("2", "coins");
            csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), "UTF-8"), 1024);
            for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {
                java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
                csvFileOutputStream.write((String) propertyEntry.getValue() != null ? (String) propertyEntry.getValue() : "" );
                if (propertyIterator.hasNext()) {
                    csvFileOutputStream.write(",");
                }
            }
           /* XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("sheet1");
            //设置表头
            Row head = sheet.createRow(0);
            int h = 0;
            head.createCell(h++).setCellValue("uid");
            head.createCell(h++).setCellValue("coins");
            Row exampleRow = sheet.createRow(1);
            int c = 0;
            exampleRow.createCell(c++).setCellValue(1111111);
            exampleRow.createCell(c++).setCellValue(0);
            workbook.write(response.getOutputStream());*/
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                if (csvFileOutputStream != null){
                    csvFileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Timestamp.valueOf(LocalDateTime.now()));
    }

}
