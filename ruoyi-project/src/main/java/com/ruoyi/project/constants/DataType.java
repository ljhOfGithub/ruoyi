package com.ruoyi.project.constants;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.function.BiFunction;

public enum DataType {
    /**
     * 整数
     */
    INT("int", Integer.class, (d1, d2) -> Integer.compare((Integer) d1, (Integer) d2), "Int32", "int", "int", "number"),
    /**
     * 小数
     */
    DOUBLE("double", Double.class, (d1, d2) -> Double.compare((Double) d1, (Double) d2), "Float64", "double", "double", "float"),
    /**
     * 日期（年月日）
     */
    DATE("date", Date.class, (d1, d2) -> ((Date) d1).compareTo((Date) d2), "Date", "Date", "date"),
    /**
     * 日期(年月日 时分秒)
     */
    DATETIME("datetime", Date.class, (d1, d2) -> ((Date) d1).compareTo((Date) d2), "DateTime", "timestamp", "date", "datetime", "timestamp"),
    /**
     * 字符串
     */
    STRING("string", String.class, (d1, d2) -> ((String) d1).compareTo((String) d2), "String", "string", "string", "varchar2"),
    /**
     * 布尔值
     */
    BOOLEAN("boolean", Boolean.class, (d1, d2) -> ((Boolean) d1).compareTo((Boolean) d2), "Bool", "boolean", "boolean");

    private static Map<String, DataType> PMML_DATA_TYPE_MAP = new HashMap<String, DataType>() {
        {
            put("int", INT);
            put("integer", INT);
            put("double", DOUBLE);
            put("float", DOUBLE);
            put("date", DATE);
            put("datetime", DATETIME);
            put("string", STRING);
            // 如果 PMML 文件未定义 Output 字段, PMML4S 解析后将向 Model 添加的默认 OutputField, real 类型
            // 此时将 real 类型映射为 DOUBLE
            put("real", DOUBLE);
        }

        ;
    };

    private final String name;
    private final Class<?> javaClass;
    private final BiFunction<Object, Object, Integer> comparator;
    private final String clickHouseDataType;
    private final String hiveDataType;
    private final Set<String> databaseStringSet;

    DataType(String name, Class<?> javaClass, BiFunction<Object, Object, Integer> comparator, String clickHouseDataType, String hiveDataType,
             String... databaseString) {
        this.name = name;
        this.javaClass = javaClass;
        this.comparator = comparator;
        this.clickHouseDataType = clickHouseDataType;
        this.hiveDataType = hiveDataType;
        this.databaseStringSet = new HashSet<String>(Arrays.asList(databaseString));
    }

    public static boolean isNumber(DataType type) {
        return type==DataType.INT || type==DataType.DOUBLE;
    }
    public static boolean isNumber(String type) {
        DataType dataType = valueOfByName(type);
        return dataType!=null && (dataType==DataType.INT || dataType==DataType.DOUBLE);
    }

    public int compare(Object d1, Object d2) {
        return comparator.apply(d1, d2);
    }

    public static DataType valueOfByDbString(String dbString) {
        if (dbString == null || dbString.isEmpty()) {
            return null;
        }
        for (DataType value : DataType.values()) {
            if (value.databaseStringSet.contains(dbString.toLowerCase())) {
                return value;
            }
        }
        return null;
    }

    public static DataType valueOfPmmlName(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        return PMML_DATA_TYPE_MAP.get(name.toLowerCase());
    }

    public static DataType valueOfName(String name) {
        for (DataType en : DataType.values()) {
            if (en.getName().equalsIgnoreCase(name)) {
                return en;
            }
        }
        return null;
    }

    public static DataType valueOfByName(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        for (DataType value : DataType.values()) {
            if (value.name().contains(name)) {
                return value;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public Class<?> getJavaClass() {
        return javaClass;
    }

    public String getClickHouseDataType() {
        return clickHouseDataType;
    }

    public String getHiveDataType() {
        return hiveDataType;
    }

    public static DataType judgedTypeBy(Object obj) {
        if (null == obj) {
            return null;
        }
        if (org.springframework.util.StringUtils.isEmpty(obj)) {
            return null;
        }
        if (obj instanceof Number) {
            if ("Infinity".equalsIgnoreCase(String.valueOf(obj)) || "-Infinity".equalsIgnoreCase(String.valueOf(obj)) ||
                    "nan".equalsIgnoreCase(String.valueOf(obj))) {
                return null;
            }
            try {
                Integer.parseInt(String.valueOf(obj));
                return INT;
            } catch (NumberFormatException e) {
                return DOUBLE;
            }
        } else if (obj instanceof String) {
            if (((String) obj).trim().isEmpty() ||
                    "Infinity".equalsIgnoreCase(((String) obj).trim()) ||
                    "-Infinity".equalsIgnoreCase(((String) obj).trim()) ||
                    "nan".equalsIgnoreCase(((String) obj).trim())) {
                return null;
            }
            try {
                Integer.parseInt((String) obj);
                return INT;
            } catch (NumberFormatException e) {
                try {
                    Double.parseDouble((String) obj);
                    return DOUBLE;
                } catch (NumberFormatException ex) {
                    return STRING;
                }
            }
        } else {
            return STRING;
        }
    }
}
