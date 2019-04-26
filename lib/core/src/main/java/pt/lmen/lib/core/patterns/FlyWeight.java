package com.slib.rmt.backtesting.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FlyWeight<K, V> {
    private Map<K, V> a = new ConcurrentHashMap<>();
    
    public V fromPool(K key, V value )  {
        return a.computeIfAbsent( key, k -> value );
    }
}
