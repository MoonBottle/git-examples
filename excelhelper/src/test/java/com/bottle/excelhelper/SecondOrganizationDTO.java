package com.bottle.excelhelper;

import com.bottle.excelhelper.annotaion.ChildExcel;
import com.bottle.excelhelper.annotaion.Excel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SecondOrganizationDTO extends AbstractOrganizationDTO {

    /**
     * 会员架构名称
     */
    @Excel(title = "二级架构名称", column = 3, maxLength = 100, primary = true)
    private String name;

    /**
     * 备注
     */
    @Excel(title = "备注", column = 4, maxLength = 200)
    private String remark;

    /**
     * 下级会员架构
     */
    @ChildExcel(clazz = ThirdOrganizationDTO.class)
    private List<AbstractOrganizationDTO> childrenList;
}
