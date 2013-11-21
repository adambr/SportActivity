<%-- 
    Document   : list
    Created on : Nov 20, 2013, 14:42:11 AM
    Author     : Kuba Dobes
    
    Výpis tabulky všech uživatelů 
    a pod ní formulář (form.jsp) pro vložení nového uživatele
--%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="user.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean" var="actionBean"/>

        <p><f:message key="user.list.allusers"/></p>

        <table class="basic">
           
            <%--hlavička tabulky--%>
            <tr>
                <th>id</th>
                <th><f:message key="user.firstname"/></th>
                <th><f:message key="user.lastname"/></th>
                <th><f:message key="user.birthday"/></th>
                <th><f:message key="user.weight"/></th>
                <th><f:message key="user.gender"/></th>
                <th></th>
                <th></th>
            </tr>
            
            <%--buňky tabulky - hodnoty --%>
            <c:forEach items="${actionBean.users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td><c:out value="${user.firstname}"/></td>
                    <td><c:out value="${user.lastname}"/></td>
                    <td><c:out value="${user.birthday}"/></td>
                    <td><c:out value="${user.weight}"/></td>
                    <td><c:out value="${user.gender}"/></td>
                    <td>
                     <%-- edit.jsp formular se otevre podle nastaveni v UserActionBean--%>
                     <%-- odkaz "edit" pro editování
                          zobrazuje se napravo v řádku
                          EVENT = EDIT
                     --%>
                     <s:link beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean" event="edit">
                         <s:param name="user.id" value="${user.id}"/> edit </s:link>
                    </td>
                    
                    <td>
                        <s:form beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean">
                           <%-- skryti user ID pro zapamatování--%>
                            <s:hidden name="user.id" value="${user.id}"/>
                            <%-- tlacitko pro smazani--%>
                            <s:submit name="delete"><f:message key="user.list.delete"/></s:submit>
                        </s:form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <s:form beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean">
            <%--Titulek formuláře pro přidání uživatele--%>
            <fieldset><legend><f:message key="user.list.newuser"/></legend>
               
                <%--vlozeny formular z FORM.jsp. 
                    Tim se zobrazi formular (5 řadků pro zadáni nového uživatele)--%>
                <%@include file="form.jsp"%>                
                <%--tlačítko pod formulářem pro vytvoření uživatele:--%>
                <s:submit name="add">Vytvořit nového uživatele</s:submit>
            </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>