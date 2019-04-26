package com.slib.var.webapp.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import j2html.tags.DomContent;

public class Sequence extends DomContent {

    private List<DomContent> els = new ArrayList<>();
    
    public  Sequence with(DomContent ...dc) {
        els.addAll( Arrays.asList( dc ) );
        return this;
    }
    
    @Override
    public void renderModel( Appendable writer, Object model ) throws IOException {        
        for ( DomContent domContent : els ) {
            domContent.render(writer);
        }
    }

}
