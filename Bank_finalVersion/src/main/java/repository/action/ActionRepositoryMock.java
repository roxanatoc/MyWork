package repository.action;

import model.Action;
import repository.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ActionRepositoryMock implements ActionRepository{

    private List<Action> actions;

    public ActionRepositoryMock() {
        actions = new ArrayList<>();
    }

    @Override
    public boolean save(Action action) {
        return actions.add(action);
    }

    @Override
    public List<Action> findByTimeAndUser(Long timeStart, Long timeStop, String username) throws EntityNotFoundException {
        return actions;
    }
}
