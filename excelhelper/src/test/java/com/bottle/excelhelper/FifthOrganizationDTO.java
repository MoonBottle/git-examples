package com.bottle.excelhelper;

import com.bottle.excelhelper.annotaion.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FifthOrganizationDTO extends AbstractOrganizationDTO {

    /**
     * 会员架构名称
     */
    @Excel(title = "五级架构名称", column = 9, maxLength = 100, primary = true)
    private String name;

    /**
     * 备注
     */
    @Excel(title = "备注", column = 10, maxLength = 200)
    private String remark;

    @Override
    public List<AbstractOrganizationDTO> getChildrenList() {
        return null;
    }
}
