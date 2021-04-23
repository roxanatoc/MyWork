package repository.action;


public abstract class ActionRepositoryDecorator implements ActionRepository{

    protected ActionRepository decoratedRepository;

    public ActionRepositoryDecorator(ActionRepository actionRepository) {
        this.decoratedRepository = actionRepository;
    }
}
