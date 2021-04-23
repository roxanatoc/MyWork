package componentFactory;

import database.DBConnectionFactory;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;
import repository.action.ActionRepository;
import repository.action.ActionRepositoryMySQL;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.account.AccountService;
import service.account.AccountServiceImpl;
import service.action.ActionService;
import service.action.ActionServiceImpl;
import service.client.ClientService;
import service.client.ClientServiceImpl;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceMySQL;
import service.user.UserService;
import service.user.UserServiceImpl;

import java.sql.Connection;

public class ComponentFactory {

    private final AuthenticationService authenticationService;

    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;

    private static ComponentFactory instance;

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final ClientService clientService;
    private final ClientRepository clientRepository;
    private final UserService userService;
    private final ActionService actionService;
    private final ActionRepository actionRepository;


    public static ComponentFactory instance(Boolean componentsForTests) {
        if (instance == null) {
            instance = new ComponentFactory(componentsForTests);
        }
        return instance;
    }

    private ComponentFactory(Boolean componentsForTests) {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(componentsForTests).getConnection();
        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
        this.authenticationService = new AuthenticationServiceMySQL(this.userRepository, this.rightsRolesRepository);
        accountRepository = new AccountRepositoryMySQL(connection);
        accountService = new AccountServiceImpl(accountRepository);
        clientRepository = new ClientRepositoryMySQL(connection);
        clientService = new ClientServiceImpl(clientRepository);
        userService = new UserServiceImpl(userRepository);
        actionRepository = new ActionRepositoryMySQL(connection);
        actionService = new ActionServiceImpl(actionRepository);
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public UserService getUserService() {
        return userService;
    }

    public RightsRolesRepository getRightsRolesRepository() {
        return rightsRolesRepository;
    }

    public static ComponentFactory getInstance() {
        return instance;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public AccountRepository getAccountRepository() {
        return accountRepository;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public ClientRepository getClientRepository() {
        return clientRepository;
    }

    public ActionService getActionService() {
        return actionService;
    }

    public ActionRepository getActionRepository() {
        return actionRepository;
    }
}