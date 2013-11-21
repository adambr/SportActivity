<%-- 
    Document   : edit
    Created on : Nov 21, 2013, 8:47:04 AM
    Author     : Kuba Dobes

    Stránka pro editaci uzivatele. Obsahuje nadpis formular form.jsp s udaji o uzivateli a 2 tlacitka Uloz a
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="user.edit.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean" var="actionBean"/>

        <s:form beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean">
            <%-- s:hidden pro drzeni ID editované knihy--%>
            <s:hidden name="user.id"/>
            <fieldset><legend><f:message key="user.edit.edit"/></legend>
                <%-- vložení formuláře pro zadání noých údajů
                     stejný form.jsp se používá na úvodní stránce (list.jsp) pro přidání nového uživatele--%>
                <%@include file="form.jsp"%>
                <%-- tlacitko pro Uložení--%>
                <s:submit name="save"><f:message key="user.edit.save"/></s:submit>                 <%-- edit.jsp formular se otervre podle nastaveni v UserActionBean--%>
                
                <%-- tlacitko pro Cancel Edit (Task 2)--%>
                <s:submit name="cancel"><f:message key="user.edit.cancel"/></s:submit>  
            </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>