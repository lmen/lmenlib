package com.github.lmen.lib.core;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream utils class
 * 
 * @author mendeslu
 *
 */
public final class S {

    private S() {        
    }
    
    public static <T> List<T> colToList(Stream<T> s) {
        return s.collect( Collectors.toList() );
    }

}
