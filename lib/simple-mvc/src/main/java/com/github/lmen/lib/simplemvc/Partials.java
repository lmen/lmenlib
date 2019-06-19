package com.github.lmen.lib.simplemvc;

import static j2html.TagCreator.div;
import static j2html.TagCreator.each;
import static j2html.TagCreator.form;
import static j2html.TagCreator.h4;
import static j2html.TagCreator.input;
import static j2html.TagCreator.label;
import static j2html.TagCreator.table;
import static j2html.TagCreator.td;
import static j2html.TagCreator.textarea;
import static j2html.TagCreator.th;
import static j2html.TagCreator.tr;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.Supplier;

import j2html.tags.ContainerTag;
import j2html.tags.DomContent;
import j2html.tags.EmptyTag;

public class Partials {

    public static final String RENDER_EXEC_ACTION_KEY = "EXEC_ACTION";
    
    // bilder Component
    public static ContainerTag postForm(DomContent ...dc) {
        return form(dc).withMethod( "post" );
    }
    
    public static ContainerTag startPostForm( String name, PageState pageState , DomContent ...dc ) {
        return form().withMethod( "post" ).withName(name).with( pageState.renderA()).with( dc );         
    }
    
    public static DomContent iff(boolean cond, Supplier<DomContent> iftrueDo) {
        return cond ? iftrueDo.get() : seq();
    }
    
    public static DomContent iffElse(boolean cond, Supplier<DomContent> iftrueDo, Supplier<DomContent> ifFalseDo) {
        return cond ? iftrueDo.get() : ifFalseDo.get();
    }
    
    public static DomContent inputText(String labelI, String name, String text ) {
        return seq(
                    label(labelI + ": "),
                    input().withType("text").withName(name).withValue( text )
                 );            
    }
    
    public static EmptyTag inputHidden( String name, String text ) {
        return input().withType("hidden").withName(name).withValue( text );
    }
    
    public static DomContent inputLocalDate(String labelI, String name, String text ) {
        return seq(
                    label(labelI + ": "),
                    input().withType("text").withName(name).withValue(  text )
                 );            
    }
    
    public static DomContent textArea( String labelI, String name, String text ) {
        return seq(
            label(labelI + ": "),
            textarea().withName(name).withValue(  text )
         );
    }
        
    
    public static EmptyTag inputSubmit( String name, String text ) {        
        return input().withType("submit").withName(name).withValue( text );
    }
        
    
    public static DomContent constantText( String labelI, String text ) {
        return seq(
            label(labelI + ": "),
            input().withType("text").withValue(  text ).attr( "readonly" )
         );            
    }
    
    public static DomContent warning( DomContent ...dc ) {
        return div(  dc ).withClass( "warning" );           
    }
    
    public static EmptyTag action( String actionId, String text ) {
        return input().withType("submit").withValue(  text ).withName( RENDER_EXEC_ACTION_KEY );
    }
    
    public static EmptyTag actionWithConfirmation( String actionId, String text, String confirmText ) {
        String confirm = "return confirm('" + confirmText + "') ";
        return input().withType("submit").withValue(  text ).withName( RENDER_EXEC_ACTION_KEY ).attr( "onclick", confirm );
    }

    
    public static ContainerTag constantField( String label, Object val ) {
        String txt =  label  + ": " + val;
        return div().withText( txt )  ;
    }
    
    public static ContainerTag error( String txt ) {
        return div().withText(txt).withClass( "error" );        
    }
    
    public static Sequence seq(DomContent ...els ) {
        return new Sequence().with( els );
    }
    
    
    public static class Table {
        
        private Table() {
        }
        
        public static ContainerTag noRows( String text ) {
            return h4(" No  " +  text + " found");
        }

        public static ContainerTag start( String... headers ) {
            
            return table(
                   tr(
                       each( Arrays.asList( headers ), s -> th(s) ) 
                   )
                ).withClass( "app_table" );                       
        }

        public static ContainerTag row( Object... values ) {
            
            return tr( each( Arrays.asList( values ) , s -> {
                
                if (s instanceof DomContent) {
                    return td((DomContent)s);
                }
                
                String str = (s == null) ? "(null)" : ((s instanceof BigDecimal) ? ((BigDecimal)s).toPlainString() : s.toString());
                return td(str);
            } ) );
                        
        }

    }

    
    
}
