package com.bottle.excelhelper;

import java.util.List;

public abstract class AbstractOrganizationDTO {

    public abstract String getName();

    public abstract String getRemark();

    public abstract List<AbstractOrganizationDTO> getChildrenList();

}
