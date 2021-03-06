/**************************************************************************************** 
 Copyright © 2003-2012 hbasesoft Corporation. All rights reserved. Reproduction or       <br>
 transmission in whole or in part, in any form or by any means, electronic, mechanical <br>
 or otherwise, is prohibited without the prior written consent of the copyright owner. <br>
 ****************************************************************************************/
package com.bottle.excelhelper.utils;

/**
 * <Description> <br>
 * 
 * @author hejiawen <br>
 * @version 1.0 <br>
 * @CreateDate 2017/5/12 <br>
 * @see com.ningpai.excel.utils <br>
 */
public interface GlobalConstants {

	/** 分隔符 */
	String SPLITOR = ",";

	/** 分隔符 */
	String PARAM_SPLITOR = "&";

	/** 路径分割符 */
	String PATH_SPLITOR = "/";
	
	/** 竖线*/
	String VERTICAL_LINE = "|";

	/** SQL语句分隔符 */
	String SQL_SPLITOR = ";";

	/** 等号分隔符 */
	String EQUAL_SPLITER = "=";

    /** 下划线 */
    char UNDERLINE = '_';
    
    /** 横杠 */
    String LINE = "-";

	/** 空白 */
	String BLANK = "";

	/** 默认编码 */
	String DEFAULT_CHARSET = "utf-8";

	/** 默认语言 */
	String DEFAULT_LANGUAGE = "zh_CN";

	/** NULL */
	String NULL = "NULL";

	/** YES */
	String YES = "Y";

	/** NO */
	String NO = "N";

	/** POST */
	String METHOD_POST = "POST";

	/** GET */
	String METHOD_GET = "GET";

	/** 百分号 */
	String PERCENT = "%";

	/** 句号 */
	String PERIOD = ".";

	/** 开发模式 */
	String DEV_MODEL = "dev";

	String SYMBOL_REGULAR = "[\\pP\\p{Punct}]";

}
