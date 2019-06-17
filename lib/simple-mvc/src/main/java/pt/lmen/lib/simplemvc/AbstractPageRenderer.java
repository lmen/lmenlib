package pt.lmen.lib.simplemvc;

public abstract class AbstractPageRenderer<M extends Model> {

    private PageState state;
    private M model;

    public AbstractPageRenderer( PageState state, M model ) {
        this.state = state;
        this.model = model;
    }

    public PageState getState() {
        return state;
    }
    
    
    public M getModel() {
        return model;
    }

    public abstract String render();

}
