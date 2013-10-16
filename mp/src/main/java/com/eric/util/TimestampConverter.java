package com.eric.util;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 实现字符串和日期格式之间的互转，格式为时间戳.
 * 
 * 
 */
public class TimestampConverter extends DateConverter {
    /**
     * 根据ApplicationResources.properties中定义的日期格式定义要转换的日期格式.
     */
    public static final String TS_FORMAT = DateUtil.getDatePattern() + " HH:mm:ss.S";

    /**
     * 将字符串转换为日期
     * @param type java.util.Date
     * @param value 字符串对象
     * @return 转换后的日期对象
     */
    protected Object convertToDate(Class type, Object value) {
        DateFormat df = new SimpleDateFormat(TS_FORMAT);
        if (value instanceof String) {
            try {
                if (StringUtils.isEmpty(value.toString())) {
                    return null;
                }

                return df.parse((String) value);
            } catch (Exception pe) {
                throw new ConversionException("Error converting String to Timestamp");
            }
        }

        throw new ConversionException("Could not convert "
                + value.getClass().getName() + " to " + type.getName());
    }

    /**
     * 将java.util.Date转换为String
     * @param type java.lang.String
     * @param value 日期对象
     * @return 转换后的字符串对象
     */
    protected Object convertToString(Class type, Object value) {
        DateFormat df = new SimpleDateFormat(TS_FORMAT);
        if (value instanceof Date) {
            try {
                return df.format(value);
            } catch (Exception e) {
                throw new ConversionException("Error converting Timestamp to String");
            }
        }

        return value.toString();
    }
}