package com.ayshiktest.util;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AyshikUtil {

    @Autowired
    DozerBeanMapper mapper;

    public <FROM, TO> List<TO> mapList(List<FROM> fromList, final Class<TO> toClass) {
        return fromList
                .stream()
                .map(from -> mapper.map(from, toClass))
                .collect(Collectors.toList());
    }
}
