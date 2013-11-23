<%-- 
    Document   : edit
    Created on : Nov 21, 2013, 8:47:04 AM
    Author     : Kuba Dobes

    Str�nka pro editaci uzivatele. Obsahuje nadpis formular form.jsp s udaji o uzivateli a 2 tlacitka Uloz a
--%>

<%@ page contentType="text/html; charset=iso-8859-2" pageEncoding="iso-8859-2" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:layout-render name="/layout.jsp" titlekey="user.edit.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean" var="actionBean"/>

        <s:form beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean">
            <%-- s:hidden pro drzeni ID editovan� knihy--%>
            <s:hidden name="user.id"/>
            <fieldset><legend><f:message key="user.edit.edit"/></legend>
                <%-- vlo�en� formul��e pro zad�n� no�ch �daj�
                     stejn� form.jsp se pou��v� na �vodn� str�nce (list.jsp) pro p�id�n� nov�ho u�ivatele--%>
                <%@include file="form.jsp"%>
                <%-- tlacitko pro Ulo�en�--%>
                <s:submit name="save"><f:message key="user.edit.save"/></s:submit>                 <%-- edit.jsp formular se otervre podle nastaveni v UserActionBean--%>
                
                <%-- tlacitko pro Cancel Edit (Task 2)--%>
                <s:submit name="cancel"><f:message key="user.edit.cancel"/></s:submit>  
            </fieldset>
        </s:form>

    </s:layout-component>
</s:layout-render>