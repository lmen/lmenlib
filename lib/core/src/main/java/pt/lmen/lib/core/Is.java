package pt.lmen.lib.core;

import java.util.Collection;
import java.util.Map;

public final class Is {

    private Is() {
    }
    
    /**
     * @param obj
     * @return true if obj equal to null
     */
    public static boolean isNull(Object obj) {        
        return obj == null;
    }
    
    public static boolean isNotNull(Object obj) {        
        return !isNull( obj );
    }

    /** 
     * @param txt
     * @return true if txt == null or txt.empty
     */
    public static boolean empty(String txt) {
        return txt == null || txt.length() == 0;
    }
    
    public static boolean notEmpty(String txt) {
        return !empty( txt );
    }
    
    /** 
     * @param col
     * @return
     */
    public static <T> boolean empty(Collection<T> col) {
        return col == null || col.isEmpty();
    }        
    
    public static <T> boolean notEmpty(Collection<T> col) {
        return !empty( col );
    }
        
    public static <K, V> boolean empty(Map<K, V> map) {
        return map == null || map.isEmpty();
    }
    
    public static <K, V> boolean notEmpty(Map<K, V> map) {
        return !empty(map);
    }
        
    public static boolean eq(Object obj1, Object obj2) {
        return obj1 != null ? obj1.equals(obj2) : obj1 == obj2 ; 
    }
    
    public static boolean notEq(Object obj1, Object obj2) {
        return !eq(obj1, obj2);
    }
    
    public static <T> boolean in(T obj, T[] values ) {
        for (T v : values) {
            if ( obj.equals( v ) ) {
                return true;
            }
        }
        return false;
    }
    
}
