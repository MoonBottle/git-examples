package com.bottle.excelhelper.annotaion;

/**
 * Created by hejiawen on 2017/5/13.
 */

import java.lang.annotation.*;

/**
 * <Description> Excel导入导出辅助注解注解，用于实体类的属性上 <br>
 *
 * @author hejiawen<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2017/5/12 <br>
 * @see com.ningpai.excel.annotaion <br>
 * @since V1.0<br>
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Excel {

    //属性对应Excel的表头
    String title();

    //属性对应Excel的列 (minValue = 1);
    int column();

    //属性值的最大长度,默认为不校验长度
    int maxLength() default 0;

    //属性的值是否必填,默认不必填
    boolean required() default false;

    //列是否隐藏，默认不隐藏
    boolean hidden() default false;

    //字段对应字典,用于导入导出时做转换
    String dictionary() default "";

    //备注
    String comment() default "";

    //时间类型的属性，导出时格式化输出，导入时校验合法性
    String format() default "yyyy-MM-dd";

    //标记列是否是主列（用于自动计算合并行）
    boolean primary() default false;
}
