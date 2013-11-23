<%-- 
    Document   : edit
    Created on : 22.11.2013, 23:20:56
    Author     : Petaniss
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="activity.edit.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.sportactivitymanager.web.ActivityActionBean" var="actionBean"/>
        
        <s:link beanclass="cz.muni.fi.pa165.sportactivitymanager.web.ActivityActionBean" class="nav">
            <f:message key="activity.list"/>
        </s:link>

        <div class="new">
            <s:form beanclass="cz.muni.fi.pa165.sportactivitymanager.web.ActivityActionBean">
                <s:hidden name="activity.id"/>
                <s:hidden name="calories.id"/>
                <fieldset><legend><f:message key="activity.edit"/></legend>
                    <%@include file="form.jsp"%>
                    <s:submit name="save"><f:message key="activity.save"/></s:submit>
                </fieldset>
            </s:form>
        </div>

    </s:layout-component>
</s:layout-render>