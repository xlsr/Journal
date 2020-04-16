package org.cis.app.security;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinSession;
import org.cis.app.ApplicationContextUtils;
import org.cis.app.CurrentUser;
import org.cis.backend.EJournalDAO;
import org.springframework.context.ApplicationContext;

public class BasicAccessControl implements AccessControl {

    public BasicAccessControl() {

    }

    @Override
    public boolean signIn(String username, String password) {

        ApplicationContext appCtx = ApplicationContextUtils
                .getApplicationContext();
        EJournalDAO eJournalDAO = appCtx.getBean("eJournalDAO", EJournalDAO.class);
        if (!eJournalDAO.login(username,password)){
            return false;
        }
        CurrentUser.set(username);
        return true;
    }

    @Override
    public boolean isUserSignedIn() {
        return !CurrentUser.get().isEmpty();
    }

    @Override
    public String getPrincipalName() {
        return CurrentUser.get();
    }

    @Override
    public void signOut() {
        VaadinSession.getCurrent().getSession().invalidate();
        UI.getCurrent().navigate("");
    }

}
