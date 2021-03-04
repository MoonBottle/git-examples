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
public class FirstCategoryDTO extends AbstractCategoryDTO {

    /**
     * 一级分类名称
     */
    @Excel(title = "一级分类名称", column = 1, maxLength = 100, primary = true)
    private String name;

    /**
     * 下级分类
     */
    @ChildExcel(clazz = SecondCategoryDTO.class)
    private List<AbstractCategoryDTO> childrenList;

}
