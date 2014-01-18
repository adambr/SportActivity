package cz.muni.fi.pa165.sportactivitymanager.web;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import org.apache.taglibs.standard.functions.Functions;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Base actionBean implementing the required methods for setting and getting
 * context. For not write set/get method in every ActionBean
 *
 * @author Dobes Kuba
 */
public abstract class BaseActionBean implements ActionBean {

    private ActionBeanContext context;
    protected String authRole;
    protected String authName;
    protected static final String ADMIN = "ADMIN";
    protected static final String USER = "USER";

    @Override
    public void setContext(ActionBeanContext context) {
        this.context = context;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }

    public static String escapeHTML(String s) {
        return Functions.escapeXml(s);
    }
}
