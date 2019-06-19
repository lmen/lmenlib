package com.github.lmen.lib.simplemvc;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.lmen.lib.simplemvc.AbstractPageRenderer;
import com.github.lmen.lib.simplemvc.AbstractRequestHandler;
import com.github.lmen.lib.simplemvc.PageState;


class HolidaysRequestHandler extends AbstractRequestHandler<HolidaysModel> {

    public static final String ASK_SECURITIES = "AskSecurities";
    public static final String FROM_DATE = "fromDate";
    public static final String MIC = "mic";
    
    private static final String[] STATE_KEYS = {};

    public HolidaysRequestHandler(  ) {
        super( STATE_KEYS );
    }

    @Override
    public HolidaysModel handleAction( String action, HttpServletRequest req, HttpServletResponse resp, PageState state ) {

        HolidaysModel model = new HolidaysModel();
        model.marketDefaultHolidaySet = null;
        model.holidaysSetList = null;
        
        model.marketHolidaysList = null;        

        return model;
    }

    @Override
    public AbstractPageRenderer<HolidaysModel> createPageRenderer( PageState state, HolidaysModel model ) {
        return new HolidaysPageRender( state, model );
    }

}
