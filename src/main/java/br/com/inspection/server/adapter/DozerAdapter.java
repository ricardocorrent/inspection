package br.com.inspection.server.adapter;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class DozerAdapter {

     private static final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <O, D> D parseObject(final O origin, final Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(final List<O> originList, final Class<D> destination) {
        final List<D> destinationObjects = new ArrayList<>();
        for (final Object o : originList) {
            destinationObjects.add(mapper.map(o, destination));
        }
        return destinationObjects;
    }

}
