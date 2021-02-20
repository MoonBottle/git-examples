package com.bottle.excelhelper;


import java.util.List;

public abstract class AbstractCategoryDTO {

    public abstract String getName();

    public abstract List<AbstractCategoryDTO> getChildrenList();

}
