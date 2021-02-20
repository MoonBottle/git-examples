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
public class SecondCategoryDTO extends AbstractCategoryDTO {

    /**
     * 二级分类名称
     */
    @Excel(title = "二级分类名称", column = 2, maxLength = 100,primary = true)
    private String name;

    /**
     * 下级分类
     */
    @ChildExcel(clazz = ThirdCategoryDTO.class)
    private List<AbstractCategoryDTO> childrenList;

}
