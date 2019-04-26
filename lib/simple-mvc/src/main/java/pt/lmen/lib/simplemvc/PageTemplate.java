package com.slib.var.webapp.mvc;

import static j2html.TagCreator.a;
import static j2html.TagCreator.body;
import static j2html.TagCreator.div;
import static j2html.TagCreator.head;
import static j2html.TagCreator.html;
import static j2html.TagCreator.li;
import static j2html.TagCreator.link;
import static j2html.TagCreator.ul;

import j2html.tags.DomContent;

public final class PageTemplate {
    
    private  PageTemplate() {
    } 
    
    private static String h( String m ) {
        String context = "/var/admin/";
        return context + m;
    }

    private static DomContent menu() {

        return div(
            ul(
                li(a("History").withHref( h("history" ))),
                li(a("Refresh from REF").withHref( h("manualyRefRefresh" ))),
                li(a("Refresh from file").withHref( h("csvFileImport" ))),
                li(a("History Exporter").withHref( h("exportMktHistory" ))),
                li(a("Ref Static GetSecurities").withHref( h("refstaticgetsecurities" ))),
                li(a("Ref Static GetQuotes").withHref( h("refstaticgetquotes" ))),
                li(a("Holidays").withHref( h("holidays" )))
                
            ).withClasses( "horizontal_list" )    
         );
        
    }

    public static String page( DomContent content ) {
        return page( null, content );
    }

    public static String page( DomContent includeInHead, DomContent includeInContent ) {

        String cssHref = "css/page_style.css";
        
        return html(
            head(
                link().withRel( "stylesheet" ).withType("text/css").withHref( cssHref ),
                includeInHead
                ),
            body(
                menu(),
                div(includeInContent)
                )
        ).render();
    }
    
}
