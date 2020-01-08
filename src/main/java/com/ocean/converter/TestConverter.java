package com.ocean.converter;

import com.ocean.utils.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TestConverter implements Converter<String, String> {

    @Override
    public String convert(String s) {
        if (StringUtils.isNotEmpty(s) && s.contains("c")) {
            return "convert-to:" + s;
        } else {
            return s;
        }
    }
}
