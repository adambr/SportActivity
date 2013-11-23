<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">
        <div id="menu-box">
            <s:link beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean" class="users"><f:message key="index.users.link"/></s:link>
            <s:link beanclass="cz.muni.fi.pa165.sportactivitymanager.web.ActivityActionBean" class="activity"><f:message key="index.activities.link"/></s:link>
            </div>
    </s:layout-component>
</s:layout-render>
