package com.bottle.excelhelper.annotaion;

import java.lang.annotation.*;

/**
 * @Author hejiawen
 * @Description:
 * @Date 2017/6/18 上午11:22
 * @Modify By:
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ChildExcel {

    Class clazz();
}
