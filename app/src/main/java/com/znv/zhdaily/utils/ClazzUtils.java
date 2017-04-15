package com.znv.zhdaily.utils;

import java.lang.reflect.ParameterizedType;

/**
 * 根据class 创建对象，学习了https://github.com/Freelander/Elephant 在构造器中获取泛型反射出对象的实现
 * Created by znv on 2017/3/29.
 */

public class ClazzUtils {
    public static <T> T getGenericInstance(Object o, int position) {
        try {
            return ((Class<T>) ((ParameterizedType) (o.getClass()
                    .getGenericSuperclass())).getActualTypeArguments()[position])
                    .newInstance();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return null;



    }
}
