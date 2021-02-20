package com.bottle.excelhelper;

import com.bottle.excelhelper.annotaion.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThirdCategoryDTO extends AbstractCategoryDTO {

    /**
     * 三级分类名称
     */
    @Excel(title = "三级分类名称", column = 3, maxLength = 100,primary = true)
    private String name;

    @Override
    public List<AbstractCategoryDTO> getChildrenList() {
        return null;
    }
}
