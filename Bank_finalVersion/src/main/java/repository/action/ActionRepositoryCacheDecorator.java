package repository.action;

import model.Account;
import model.Action;
import repository.Cache;
import repository.EntityNotFoundException;

import java.util.List;


public class ActionRepositoryCacheDecorator extends ActionRepositoryDecorator{

    private Cache<Action> cache;

    public ActionRepositoryCacheDecorator(ActionRepository actionRepository, Cache<Action> cache) {
        super(actionRepository);
        this.cache = cache;
    }

    @Override
    public boolean save(Action action) {
        cache.invalidateCache();
        return decoratedRepository.save(action);
    }

    @Override
    public List<Action> findByTimeAndUser(Long timeStart, Long timeStop, String username) throws EntityNotFoundException {
        if (cache.hasResult()) {
            return cache.load();
        }
        List<Action> actions = decoratedRepository.findByTimeAndUser(timeStart, timeStop, username);
        cache.save(actions);
        return actions;
    }
}
