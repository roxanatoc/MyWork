package service.action;

import model.Action;
import repository.EntityNotFoundException;
import repository.action.ActionRepository;

import java.util.List;

public class ActionServiceImpl implements ActionService{

    private final ActionRepository repository;

    public ActionServiceImpl(ActionRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean save(Action action) {
        return repository.save(action);
    }

    @Override
    public List<Action> findByTimeAndUser(Long timeStart, Long timeStop, String username) throws EntityNotFoundException {
        return repository.findByTimeAndUser(timeStart, timeStop, username);
    }
}
