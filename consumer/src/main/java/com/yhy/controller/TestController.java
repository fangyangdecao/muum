package com.yhy.controller;

import com.google.common.collect.Lists;
import com.yhy.fergin_rpc.TestService;
import com.yhy.util.POIUtils;
import com.yhy.util.RewardPack;
import com.yhy.util.TestClass;
import com.yhy.util.Users;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("consumer")
public class TestController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TestService testService;

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @GetMapping("test")
    public String test(){
        //return restTemplate.getForObject("http://account/test/test",String.class);
        try {
            return testService.test();
        }catch (Exception r){
            System.out.println(r.getMessage());
            System.out.println("aaaa");
            if ("a".equals(r.getMessage())){
                System.out.println("aaaaa");
            }
        }
        return "a";
    }


    @GetMapping("export")
    public void export(HttpServletResponse response){
        POIUtils<TestClass> objectPOIUtils = new POIUtils<>();
        List<TestClass> list = Lists.newArrayList();
        TestClass build1 = TestClass.builder().docNumber("12131").assetsAffUnit("test").documentId(132131354L).build();
        TestClass build2 = TestClass.builder().docNumber("12131").assetsAffUnit("test").documentId(132131354L).build();
        TestClass build3 = TestClass.builder().docNumber("12131").assetsAffUnit("test").documentId(132131354L).build();
        list.add(build1);
        list.add(build2);
        list.add(build3);
        objectPOIUtils.export(TestClass.class,list,response);
    }

    @GetMapping("upload")
    public List<RewardPack> export(@RequestParam MultipartFile csvMF) throws IOException {
        List<RewardPack> rewardPackList = new ArrayList<>();
        System.out.println(csvMF.getOriginalFilename());
        InputStream inputStream = csvMF.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheetAt = workbook.getSheetAt(0);
        for (int i = 1; i < sheetAt.getPhysicalNumberOfRows(); i++) {
            RewardPack rewardPack = new RewardPack();
            Row row = sheetAt.getRow(i);
            int c=0;
                String uids = row.getCell(c++).getStringCellValue();
                if(uids == null || uids.equals("")){
                    rewardPack.setUids(Lists.newArrayList());
                }else{
                    String[] split = uids.split(";");
                    rewardPack.setUids(Stream.of(split).map(Integer::valueOf).collect(Collectors.toList()));
                }
                rewardPack.setExp((int)row.getCell(c++).getNumericCellValue());
                rewardPackList.add(rewardPack);
            }

        return rewardPackList;
    }

    public static void main(String[] args) throws InterruptedException {
        LocalDateTime now = LocalDateTime.now().minusDays(18);
        System.out.println(now.toInstant(ZoneOffset.UTC).toEpochMilli());
    }
}
