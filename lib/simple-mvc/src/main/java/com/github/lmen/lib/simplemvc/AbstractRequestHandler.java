package com.github.lmen.lib.simplemvc;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractRequestHandler<M extends Model> {

    private String[] stateKeys;

    private boolean renderPageFromModel = true;

    public AbstractRequestHandler( String[] stateKeys ) {
        super();
        this.stateKeys = stateKeys;
    }

    public void handleRequest( HttpServletRequest req, HttpServletResponse resp ) throws IOException {
        PageState state = new PageState( stateKeys );
        state.load( req );

        String action = req.getParameter( Const.RENDER_EXEC_ACTION_KEY );

        M model = handleAction( action, req, resp, state );

        if ( renderPageFromModel ) {
            AbstractPageRenderer<M> pageRenderer = createPageRenderer( state, model );

            String sb = pageRenderer.render();
            resp.getWriter().append( sb );
        }
    }

    public abstract AbstractPageRenderer<M> createPageRenderer( PageState state, M model );

    public abstract M handleAction( String action, HttpServletRequest req, HttpServletResponse resp, PageState state );

    protected boolean isRenderPageFromModel() {
        return renderPageFromModel;
    }

    protected void setRenderPageFromModel( boolean renderPageFromModel ) {
        this.renderPageFromModel = renderPageFromModel;
    }

}
