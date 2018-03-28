package com.example.djran.utils.excel;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtils {
    /**
     * bean 转化为实体
     *
     * @param bean
     * @return
     */
    public static HashMap<String, Object> beanToMap(Object bean) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        if (null == bean) {
            return map;
        }
        Class<?> clazz = bean.getClass();
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(clazz);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor descriptor : descriptors) {
            String propertyName = descriptor.getName();
            if (!"class".equals(propertyName)) {
                Method method = descriptor.getReadMethod();
                Object result;
                try {
                    result = method.invoke(bean);
                    if (null != result) {
                        map.put(propertyName, result);
                    } else {
                        map.put(propertyName, "");
                    }
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return map;
    }

    /**
     * map 转化为 bean
     *
     * @param clazz
     * @param map
     * @return
     */
    public static <T> T mapToBean(Class<T> clazz, Map map) {
        T object = null;
        try {
            object = clazz.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);

            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor descriptor : descriptors) {
                String propertyName = descriptor.getName();
                if (map.containsKey(propertyName)) {
                    Object value = map.get(propertyName);
                    Object[] args = new Object[1];
                    args[0] = value;
                    descriptor.getWriteMethod().invoke(object, args);
                }
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return object;
    }


    /**
     * 复制sour里属性不为空的值到obje为空的属性(保留源值)
     *
     * @param sour 源实体类
     * @param obje 目标实体类
     * @return obje
     */
    public static void copy(Object sour, Object obje) {
        copy(sour, obje, true);
    }

    /**
     * 复制sour里属性不为空的值到obje为空的属性
     *
     * @param sour    源实体类
     * @param obje    目标实体类
     * @param isCover 是否保留obje类里不为null的属性值(保留源值，属性为null则赋值)
     * @return obje
     */
    public static void copy(Object sour, Object obje, boolean isCover) {
        Field[] fields = sour.getClass().getDeclaredFields();
        for (int i = 0, j = fields.length; i < j; i++) {
            String propertyName = fields[i].getName();
            Object propertyValue = getProperty(sour, propertyName);
            if (isCover) {
                Object setProperty = setProperty(obje, propertyName,
                        propertyValue);
            } else {
                if (getProperty(obje, propertyName) == null
                        && propertyValue != null) {
                    Object setProperty = setProperty(obje, propertyName,
                            propertyValue);
                }
            }

        }
    }

    /**
     * 得到值
     *
     * @param bean
     * @param propertyName
     * @return
     */
    private static Object getProperty(Object bean, String propertyName) {
        Class clazz = bean.getClass();
        try {
            Field field = clazz.getDeclaredField(propertyName);
            Method method = clazz.getDeclaredMethod(
                    getGetterName(field.getName()), new Class[]{});
            return method.invoke(bean, new Object[]{});
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 给bean赋值
     *
     * @param bean
     * @param propertyName
     * @param value
     * @return
     */
    private static Object setProperty(Object bean, String propertyName,
                                      Object value) {
        Class clazz = bean.getClass();
        try {
            Field field = clazz.getDeclaredField(propertyName);
            Method method = clazz.getDeclaredMethod(
                    getSetterName(field.getName()),
                    new Class[]{field.getType()});
            return method.invoke(bean, new Object[]{value});
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 根据变量名得到get方法
     *
     * @param propertyName
     * @return
     */
    private static String getGetterName(String propertyName) {
        String method = "get" + propertyName.substring(0, 1).toUpperCase()
                + propertyName.substring(1);
        return method;
    }

    /**
     * 得到setter方法
     *
     * @param propertyName 变量名
     * @return
     */
    private static String getSetterName(String propertyName) {
        String method = "set" + propertyName.substring(0, 1).toUpperCase()
                + propertyName.substring(1);
        return method;
    }

}  