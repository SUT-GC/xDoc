package org.treeleafj.xdoc.converter;


import com.sun.javadoc.ParamTag;
import com.sun.javadoc.SeeTag;
import org.treeleafj.xdoc.model.ParamTagImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * 标签转换器注册器
 * <p>
 * Created by leaf on 2017/3/4.
 */
public class DocTagConverterRegistrator {

    private static Map<Class, TagConverter> registrator = new HashMap<Class, TagConverter>();

    private static TagConverter defaultTagConverter = new DefaultTagConverterImpl();

    static {
        register(ParamTag.class, new ParamTagConverter());
        register(SeeTag.class, new SeeTagConverter());
    }

    public static void register(Class classz, TagConverter tagConverter) {
        registrator.put(classz, tagConverter);
    }

    public static TagConverter get(Class classz) {
        for (Map.Entry<Class, TagConverter> entry : registrator.entrySet()) {
            if (entry.getKey().isAssignableFrom(classz)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public static TagConverter getDefault() {
        return defaultTagConverter;
    }
}
