<%-- 
    Document   : list
    Created on : Nov 20, 2013, 14:42:11 AM
    Author     : Kuba Dobes
    
    Výpis tabulky v¹ech u¾ivatelù 
    a pod ní formuláø (form.jsp) pro vlo¾ení nového u¾ivatele
--%>
<%@ page contentType="text/html; charset=iso-8859-2" pageEncoding="iso-8859-2" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="user.list.title">

    <%-- Jquery DATEPICKER zobrazí v user/form.jsp kalendáø pro výbìr data narození u¾ivatele--%>
    <s:layout-component name="header">
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <!--<script src="jquery.ui.datepicker-cs.js"></script>-->
        <%--  <link rel="stylesheet" href="/resources/demos/style.css" />   --%>
        <script>
            $(function() {
                $("#datepicker").datepicker($.datepicker.regional[ "cs" ]);
                $("#locale").change(function() {
                    $("#datepicker").datepicker("option",
                            $.datepicker.regional[ $(this).val() ]);
                });
            });
        </script>
    </s:layout-component>            

    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean" var="actionBean"/>

        <a href="${pageContext.request.contextPath}" class="nav">
            <f:message key="main.page"/>
        </a>

        <div class="list">
            <table>

                <%--hlavièka tabulky--%>
                <tr>
                    <th>ID</th>
                    <th><f:message key="user.firstname"/></th>
                    <th><f:message key="user.lastname"/></th>
                    <th><f:message key="user.birthday"/></th>
                    <th><f:message key="user.weight"/></th>
                    <th><f:message key="user.gender"/></th>
                        <%-- <th></th> 2 bunky hlavicky pro sloupec edit a smazat. v novem stylu nejsou potøeba
                             <th></th>--%>
                </tr>

                <%--buòky tabulky - hodnoty 
                actionBean.users - zavolá metodu findAll z DefaultHnadleru v UserActionBean--%>
                <c:forEach items="${actionBean.users}" var="user">
                    <tr>
                        <td>${user.id}</td>
                        <td><s:link beanclass="cz.muni.fi.pa165.sportactivitymanager.web.RecordActionBean">
                                <s:param name="user.id" value="${user.id}"/>
                                <c:out value="${user.firstName}"/>
                            </s:link>
                        </td>
                        <td><c:out value="${user.lastName}"/></td>
                        <td><c:out value="${user.birthDay}"/></td>
                        <td><c:out value="${user.weight}"/></td>
                        <td><c:out value="${user.gender}"/></td>

                        <%-- odkaz "edit" pro editování
                             zobrazuje se napravo v øádku
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
                                    Podle toho Stripes poznají ¾e mají volat metodu delete
                        --%>
                        <td>
                            <s:form beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean">
                                <%-- skryti user ID pro zapamatování--%>
                                <s:hidden name="user.id" value="${user.id}"/>
                                <s:submit name="delete"><f:message key="user.list.delete"/></s:submit>
                            </s:form>
                        </td>

                        <td>
                           <s:link beanclass="cz.muni.fi.pa165.sportactivitymanager.web.RecordActionBean" event="list">
                                <s:param name="user.id" value="${user.id}"/><f:message key="user.record"/></s:link>
                        </td>

                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="new">
            <s:form beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean">
                <%--Titulek formuláøe pro pøidání u¾ivatele--%>
                <fieldset><legend><f:message key="user.list.newuser"/></legend>

                    <%--vlozeny formular z FORM.jsp. 
                        Tim se zobrazi formular (5 øadkù pro zadáni nového u¾ivatele)--%>
                    <%@include file="form.jsp"%>                
                    <%--tlaèítko pod formuláøem pro vytvoøení u¾ivatele:--%>
                    <%-- submit add vyvolá metodu add--%>
                    <s:submit name="add"><f:message key="user.create"/></s:submit>
                    </fieldset>
            </s:form>
        </div>
    </s:layout-component>
</s:layout-render>