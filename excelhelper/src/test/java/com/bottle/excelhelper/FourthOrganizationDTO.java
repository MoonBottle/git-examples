package com.bottle.excelhelper;

import com.bottle.excelhelper.annotaion.ChildExcel;
import com.bottle.excelhelper.annotaion.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FourthOrganizationDTO extends AbstractOrganizationDTO {

    /**
     * 会员架构名称
     */
    @Excel(title = "四级架构名称", column = 7, maxLength = 100, primary = true)
    private String name;

    /**
     * 备注
     */
    @Excel(title = "备注", column = 8, maxLength = 200)
    private String remark;

    /**
     * 下级会员架构
     */
    @ChildExcel(clazz = FifthOrganizationDTO.class)
    private List<AbstractOrganizationDTO> childrenList;
}
