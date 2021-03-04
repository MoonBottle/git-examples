package com.bottle.excelhelper;

import org.junit.Test;

import java.io.File;
import java.util.List;

public class ParseExcelTest {

    @Test
    public void test() throws Exception {
        File file = new File("src/test/resources/1.xlsx");
        List<FirstOrganizationDTO> firstOrganizationDTOList = ExcelHelper2.parseExcel2Object(file, FirstOrganizationDTO.class, 1);
        System.out.println(firstOrganizationDTOList);
    }

    @Test
    public void test2() throws Exception {
        File file = new File("src/test/resources/2.xlsx");
        List<FirstOrganizationDTO> firstOrganizationDTOList = ExcelHelper2.parseExcel2Object(file, FirstOrganizationDTO.class, 1);
        System.out.println(firstOrganizationDTOList);
    }

    @Test
    public void test3() throws Exception {
        File file = new File("src/test/resources/3.xlsx");
        List<FirstCategoryDTO> firstOrganizationDTOList = ExcelHelper2.parseExcel2Object(file, FirstCategoryDTO.class, 1);
        System.out.println(firstOrganizationDTOList);
    }

}
