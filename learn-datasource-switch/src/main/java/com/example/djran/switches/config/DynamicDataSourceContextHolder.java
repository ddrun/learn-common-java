package com.example.djran.switches.config;

/**
 * @author
 * 数据源信息保存，为每个线程单独绑定一个数据源
 */
public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    public static String getDataSourceType() {
        return contextHolder.get().toString();
    }

    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}
