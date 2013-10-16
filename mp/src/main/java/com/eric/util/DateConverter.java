package com.eric.util;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 实现java.util.Date 和  String的互相转换
 * 
 */
public class DateConverter implements Converter {

    /**
     * 字符串和日期类型的互转方法
     * @param type String, Date 或 Timestamp类型
     * @param value 要转换的值
     * @return 转换后的对象
     */
    @SuppressWarnings("unchecked")
    public Object convert(final Class type, final Object value) {
        if (value == null) {
            return null;
        } else if (type == Timestamp.class) {
            return convertToDate(type, value, DateUtil.getDateTimePattern());
        } else if (type == Date.class) {
            return convertToDate(type, value, DateUtil.getDatePattern());
        } else if (type == String.class) {
            return convertToString(value);
        }

        throw new ConversionException("Could not convert " + value.getClass().getName() + " to " + type.getName());
    }

    /**
     * 按照指定格式将字符串转换为日期类型.
     * @param type 字符串
     * @param value 字符串值
     * @param pattern 转换格式
     * @return 转换后的对象
     */
    protected Object convertToDate(final Class<?> type, final Object value, final String pattern) {
        final DateFormat df = new SimpleDateFormat(pattern);
        if (value instanceof String) {
            try {
                if (StringUtils.isEmpty(value.toString())) {
                    return null;
                }

                final Date date = df.parse((String) value);
                if (type.equals(Timestamp.class)) {
                    return new Timestamp(date.getTime());
                }
                return date;
            } catch (final Exception e) {
                throw new ConversionException("Error converting String to Date", e);
            }
        }

        throw new ConversionException("Could not convert " + value.getClass().getName() + " to " + type.getName());
    }

    /**
     * 将java.util.Date 或 a java.sql.Timestamp 转换为String类型.
     * @param value 要转换的值
     * @return 转换后的值
     */
    protected Object convertToString(final Object value) {
        if (value instanceof Date) {
            DateFormat df = new SimpleDateFormat(DateUtil.getDatePattern());
            if (value instanceof Timestamp) {
                df = new SimpleDateFormat(DateUtil.getDateTimePattern());
            }

            try {
                return df.format(value);
            } catch (final Exception e) {
                throw new ConversionException("Error converting Date to String", e);
            }
        } else {
            return value.toString();
        }
    }
}
