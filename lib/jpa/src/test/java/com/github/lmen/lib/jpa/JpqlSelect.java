package com.github.lmen.lib.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class JpqlSelect {
    
        private JpqlSelect.Select select = new Select();
        private JpqlSelect.From from = new From();
        private JpqlSelect.Where where = new Where();
        private JpqlSelect.OrderBy orderBy = new OrderBy();
        
        public JpqlSelect.Select select() {
            return select;
        }
        
        public JpqlSelect.From from() {
            return from;
        }
        
        public JpqlSelect.Where where() {
            return where;
        }
        
        public JpqlSelect.OrderBy orderBy() {
            return orderBy;
        }
        
    
    public static class Select {
        private List<String> els = new ArrayList<>();
        
        public JpqlSelect.Select col(String a) {
            els.add( a );
            return this;
        }
        
        public JpqlSelect.Select colIf(boolean condition,  String a) {
            if (condition) {
                els.add( a );
            }
            return this;
        }
        
        public  JpqlSelect.Select col(Predicate<Void> predicate,  String a) {                
            if (predicate.test( null )) {
                els.add( a );
            }
            return this;
        }
        
    }
    
    public static class From {
        private static String IN = " inner join ";
        private static String LF = " left join ";
        private String from;
        private List<String> els = new ArrayList<>();
        
        public JpqlSelect.From from(String a) {
            from = a;
            return this;
        }
        
        public JpqlSelect.From from(boolean condition, String a) {
            if (condition) {
                from = a;
            }
            return this;
        }
        
        public JpqlSelect.From innerJoin(String a) {
            els.add( IN + a );
            return this;
        }
        
        public JpqlSelect.From innerJoinIf(boolean condition , String a) {
            if (condition) {els.add( IN +a );};
            return this;
        }
        
        public JpqlSelect.From leftJoin(String a) {
            els.add( LF + a );
            return this;
        }
        
        public JpqlSelect.From leftJoin(boolean condition , String a) {
            if (condition) {els.add( LF + a );};
            return this;
        }
        
    }
    
    public static class Where {
        private List<String> els = new ArrayList<>();
        private Map<String, Object> parameters = new HashMap<>();
        
        
        public JpqlSelect.Where param(String paramName, Object paramValue) {
            parameters.put( paramName, paramValue );
            return this;
        }
        
        public JpqlSelect.Where param(boolean condition, String paramName, Object paramValue) {
            if (condition) {
                parameters.put( paramName, paramValue );
            }
            return this;
        }
        
        public JpqlSelect.Where condition(String expression, String paramName, Object paramValue) {
            els.add( expression );
            parameters.put( paramName, paramValue );
            return this;
        }
        
        public JpqlSelect.Where conditionIf(boolean condition, String expression, String paramName, Object paramValue) {
            if (condition) {
                els.add( expression );
                parameters.put( paramName, paramValue );
            }
            return this;
        }
        
        public JpqlSelect.Where condition(String expression) {
            els.add( expression );
            return this;
        }
        
        public JpqlSelect.Where conditionIf(boolean condition, String expression) {
            if (condition) {
                els.add( expression );
            }
            return this;
        }
        
        
    }
    
    
    public static class OrderBy {
        private List<String> els = new ArrayList<>();
                
        public JpqlSelect.OrderBy  sortWithNullsFirst(String a, boolean ascending, boolean nullsFirst) {
            els.add( a + addAscding( ascending ) + " " + nullsFirst( nullsFirst ));
            return this;
        }
        
        public JpqlSelect.OrderBy  sortWithNullsFirstIf(boolean condition, String a, boolean ascending, boolean nullsFirst) {
            if (condition) {
                els.add( a + addAscding( ascending ) + " " + nullsFirst( nullsFirst ));
            }
            return this;
        }

        private String nullsFirst( boolean nullsFirst ) {
            return nullsFirst ? " NULLS FIRST " : " NULLS LAST ";
        }
        
        public JpqlSelect.OrderBy  sort(String a, boolean ascending, String extra) {
            els.add( a + addAscding( ascending ));
            return this;
        }
        
        public JpqlSelect.OrderBy  sort(String a, boolean ascending) {
            els.add( a + addAscding( ascending ));
            return this;
        }
        
        public JpqlSelect.OrderBy  sortIf(boolean condition, String a, boolean ascending) {
            if (condition) {
                els.add( a + addAscding( ascending ));
            }
            return this;
        }

        private String addAscding( boolean ascending ) {
            return ascending ? " ASC " : " DESC ";
        }
        
    }
    
    public String renderJPa() {
        StringBuilder sb = new StringBuilder();
        sb.append( "select " );            
        sb.append( String.join( " , ", select.els  ));
        sb.append(" from ");
        sb.append( from.from );

        // Inner, left join is option
        if (from.els.size() > 0) {
            sb.append(" ");
            sb.append( String.join( "  ", from.els) );
        }
        
        // WHERE OPTIONAL
        if (where.els.size() > 0) {
            sb.append( " where " );
            sb.append( String.join( "  ", where.els) );
        }
        
        // SORT OPTIONAL
        if (orderBy.els.size() >0) {
            sb.append( " ORDER BY " );
            sb.append( String.join( " , ", orderBy.els) );
        }
        
        return sb.toString();
    }
    
    public String renderCount() {
        StringBuilder sb = new StringBuilder();
        sb.append( "select count(1) " );                        
        sb.append(" from ");
        sb.append( from.from );

        // Inner, left join is option
        if (from.els.size() > 0) {
            sb.append( String.join( "  ", from.els) );
        }
        
        // WHERE OPTIONAL
        if (where.els.size() > 0) {
            sb.append( " where " );
            sb.append( String.join( "  ", where.els) );
        }
                    
        return sb.toString();
    }

    
    public Map<String, Object> getParams() {
        return where.parameters;
    }
    

    
}