package com.bottle.excelhelper;

import com.bottle.excelhelper.annotaion.ChildExcel;
import com.bottle.excelhelper.annotaion.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class FirstOrganizationDTO extends AbstractOrganizationDTO {

    /**
     * 会员架构名称
     */
    @Excel(title = "一级架构名称", column = 1, maxLength = 100, primary = true)
    private String name;

    /**
     * 备注
     */
    @Excel(title = "备注", column = 2, maxLength = 200)
    private String remark;

    /**
     * 下级会员架构
     */
    @ChildExcel(clazz = SecondOrganizationDTO.class)
    private List<AbstractOrganizationDTO> childrenList;

    public static void main(String[] args) throws Exception {
        File file = new File("D:\\1.xlsx");
        List<FirstOrganizationDTO> firstOrganizationDTOList = ExcelHelper2.parseExcel2Object(file, FirstOrganizationDTO.class, 1);
        System.out.println(firstOrganizationDTOList);
    }

}
