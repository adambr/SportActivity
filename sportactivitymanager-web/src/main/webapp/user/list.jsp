<%-- 
    Document   : list
    Created on : Nov 20, 2013, 14:42:11 AM
    Author     : Kuba Dobes
    
    V�pis tabulky v�ech u�ivatel� 
    a pod n� formul�� (form.jsp) pro vlo�en� nov�ho u�ivatele
--%>
<%@ page contentType="text/html; charset=iso-8859-2" pageEncoding="iso-8859-2" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="user.list.title">

    <%-- Jquery DATEPICKER zobraz� v user/form.jsp kalend�� pro v�b�r data narozen� u�ivatele--%>
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

                <%--hlavi�ka tabulky--%>
                <tr>
                    <th>ID</th>
                    <th><f:message key="user.firstname"/></th>
                    <th><f:message key="user.lastname"/></th>
                    <th><f:message key="user.birthday"/></th>
                    <th><f:message key="user.weight"/></th>
                    <th><f:message key="user.gender"/></th>
                        <%-- <th></th> 2 bunky hlavicky pro sloupec edit a smazat. v novem stylu nejsou pot�eba
                             <th></th>--%>
                </tr>

                <%--bu�ky tabulky - hodnoty 
                actionBean.users - zavol� metodu findAll z DefaultHnadleru v UserActionBean--%>
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

                        <%-- odkaz "edit" pro editov�n�
                             zobrazuje se napravo v ��dku
                             EVENT = EDIT
                             URL na webu ukazuje ID usera kter�ho budu editovat
                        --%>
                        <%-- edit.jsp formular se otevre podle nastaveni v UserActionBean--%>
                        <td>
                            <s:link beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean" event="edit">
                                <s:param name="user.id" value="${user.id}"/> <f:message key="user.list.edit"/> </s:link>
                            </td>


                        <%-- tlacitko pro smazani
                                    Kdyz ho stisknu, tak bude m�t 2 parametry: user.id a delete
                                    Podle toho Stripes poznaj� �e maj� volat metodu delete
                        --%>
                        <td>
                            <s:form beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean">
                                <%-- skryti user ID pro zapamatov�n�--%>
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
                <%--Titulek formul��e pro p�id�n� u�ivatele--%>
                <fieldset><legend><f:message key="user.list.newuser"/></legend>

                    <%--vlozeny formular z FORM.jsp. 
                        Tim se zobrazi formular (5 �adk� pro zad�ni nov�ho u�ivatele)--%>
                    <%@include file="form.jsp"%>                
                    <%--tla��tko pod formul��em pro vytvo�en� u�ivatele:--%>
                    <%-- submit add vyvol� metodu add--%>
                    <s:submit name="add"><f:message key="user.create"/></s:submit>
                    </fieldset>
            </s:form>
        </div>
    </s:layout-component>
</s:layout-render>