<%@ page contentType="text/html; charset=iso-8859-2" pageEncoding="iso-8859-2" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">
        <div class="h2-cont">
            <s:link beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean"><h2><f:message key="index.users.link"/></h2></s:link>
            </div>
            <div class="h2-cont">
            <s:link beanclass="cz.muni.fi.pa165.sportactivitymanager.web.ActivityActionBean"><h2><f:message key="index.activities.link"/></h2></s:link>
            </div>
            <div class="h2-cont">
            <s:link beanclass="cz.muni.fi.pa165.sportactivitymanager.web.RecordActionBean"><h2>Spr�va z�znam� aktivit</h2></s:link>
            </div>
    </s:layout-component>
</s:layout-render>
