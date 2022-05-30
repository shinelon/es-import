package com.shinelon.esimport.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.alibaba.excel.util.DateUtils;
import com.alibaba.excel.util.StringUtils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @ClassName DateConverter
 * @Author shinelon
 * @Date 16:30 2022/5/30
 * @Version 1.0
 **/
public class LocalDateStringConverter implements Converter<LocalDate> {

    @Override
    public Class<?> supportJavaTypeKey() {
        return LocalDateTime.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public LocalDate convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
                                           GlobalConfiguration globalConfiguration) throws ParseException {
        if (contentProperty == null || contentProperty.getDateTimeFormatProperty() == null) {
            return parseLocalDateTime(cellData.getStringValue(), null, globalConfiguration.getLocale());
        } else {
            return parseLocalDateTime(cellData.getStringValue(),
                    contentProperty.getDateTimeFormatProperty().getFormat(), globalConfiguration.getLocale());
        }
    }




    public static LocalDate parseLocalDateTime(String dateString, String dateFormat, Locale local) {
        if (local == null) {
            return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(dateFormat));
        } else {
            return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(dateFormat, local));
        }
    }
}
