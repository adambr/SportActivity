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
    
    <%-- Jquery DATEPICKER zobrazí v user/form.jsp kalendář pro výběr data narození uživatele--%>
    <s:layout-component name="header">
           <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
            <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
            <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
            <%--  <link rel="stylesheet" href="/resources/demos/style.css" />   --%>
            <script>
                $(function() {
                  $( "#datepicker" ).datepicker();
                });
            </script>
     </s:layout-component>            
            
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
            
            <%--buňky tabulky - hodnoty 
            actionBean.users - zavolá metodu findAll z DefaultHnadleru v UserActionBean--%>
            <c:forEach items="${actionBean.users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td><c:out value="${user.firstName}"/></td>
                    <td><c:out value="${user.lastName}"/></td>
                    <td><c:out value="${user.birthDay}"/></td>
                    <td><c:out value="${user.weight}"/></td>
                    <td><c:out value="${user.gender}"/></td>
                    
                     <%-- odkaz "edit" pro editování
                          zobrazuje se napravo v řádku
                          EVENT = EDIT
                          URL na webu ukazuje ID usera kterého budu editovat
                     --%>
                     <%-- edit.jsp formular se otevre podle nastaveni v UserActionBean--%>
                    <td>
                     <s:link beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean" event="edit">
                         <s:param name="user.id" value="${user.id}"/> <f:message key="user.list.edit"/> </s:link>
                    </td>
                    
                    
                    <%-- tlacitko pro smazani
                                Kdyz ho stisknu, tak bude mít 2 parametry: user.id a delete
                                Podle toho Stripes poznají že mají volat metodu delete
                    --%>
                    <td>
                        <s:form beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean">
                           <%-- skryti user ID pro zapamatování--%>
                            <s:hidden name="user.id" value="${user.id}"/>
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
                <%-- submit add vyvolá metodu add--%>
                <s:submit name="add">Vytvořit nového uživatele</s:submit>
            </fieldset>
        </s:form>
    </s:layout-component>
</s:layout-render>