package com.github.lmen.lib.jpa;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

@ApplicationScoped
public class JpqlSelectExecutor {

	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
    
    EntityManager entitymanager = emfactory.createEntityManager( );
	     
    
    public  Long execCountQuery(JpqlSelect jpql) {
        String jpa = jpql.renderCount();
        
        TypedQuery<Long> query = entitymanager.createQuery( jpa, Long.class );
        
        fillQueryParameters( jpql, query );
        
        return query.getSingleResult();
    }

    private <T> void fillQueryParameters( JpqlSelect jpql, TypedQuery<T> query ) {
        if (jpql.getParams().size() > 0) {
            for (java.util.Map.Entry<String,Object> e :  jpql.getParams().entrySet() ) {
                query.setParameter( e.getKey(), e.getValue() );
            }
        }
    }
    
    public <T> List<T> execPagQuery(JpqlSelect jpql, Class<T> clazz, int firstResult, int length) {
        String jpa = jpql.renderJPa();
        
        TypedQuery<T> query = entitymanager.createQuery( jpa, clazz );
        
        fillQueryParameters( jpql, query );
        
        if (firstResult > -1) {
            query.setFirstResult( firstResult );
        }
        if (length > -1) {
            query.setMaxResults( length );
        }
        
        return query.getResultList();
    }
            
    public <T> List<T> executeQuery(JpqlSelect jpql, Class<T> clazz) {
         int firstResult = -1; 
         int length = -1;
        return execPagQuery( jpql, clazz, firstResult, length );
    }
    
    public <T> List<T> executeBoundedQuery(JpqlSelect jpql, Class<T> clazz, int maxRows) {
        int firstResult = -1; 
        int length = -maxRows;
       return execPagQuery( jpql, clazz, firstResult, length );
   }
    
    public <T> TypedQuery<T> createQuery(JpqlSelect jpql, Class<T> clazz) {
        String jpa = jpql.renderJPa();
        
        TypedQuery<T> query = entitymanager.createQuery( jpa, clazz );
        
        fillQueryParameters( jpql, query );
        
        return query;
    }
    
    public <T> TypedQuery<T> createCountQuery(JpqlSelect jpql, Class<T> clazz) {
        String jpa = jpql.renderCount();
        
        TypedQuery<T> query = entitymanager.createQuery( jpa, clazz );
        
        fillQueryParameters( jpql, query );
        
        return query;
    }
    
}
