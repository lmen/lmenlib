package pt.lmen.lib.simplemvc;

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
        String context = "/app/admin/";
        return context + m;
    }

    private static DomContent menu() {

        return div(
            ul(
                li(a("menu1").withHref( h("menu1.pag" ))),
                li(a("menu2").withHref( h("menu2.pag" ))),                
                li(a("menu3").withHref( h("menu3.pag" )))
                
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
