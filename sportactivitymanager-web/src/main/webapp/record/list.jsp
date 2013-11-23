<%-- 
    Document   : list
    Created on : 22.11.2013, 13:24:09
    Author     : Phaser
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%--<s:useActionBean beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean" var="actionBean"/>
--%>

<s:layout-render name="/layout.jsp" titlekey="records">
        <s:layout-component name="header">
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <script src="jquery.ui.datepicker-cs.js"></script>
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
        <s:useActionBean beanclass="cz.muni.fi.pa165.sportactivitymanager.web.RecordActionBean" var="actionBean"/>

        <table class="basic">
            <tr>
                <th>id</th>
                <th><f:message key="record.user"/></th>
                <th><f:message key="record.duration"/></th>
                <th><f:message key="record.distance"/></th>
                <th><f:message key="record.startTime"/></th>
            </tr>
            <c:forEach items="${actionBean.records}" var="record">
                <tr>
                    <td>${record.id}</td>
                    <td><c:out value="${actionBean.user.name}"/></td>
                    <td><c:out value="${record.duration}"/></td>
                    <td><c:out value="${record.distance}"/></td>
                    <td><c:out value="${record.startTime}"/></td>
                </tr>
            </c:forEach>
        </table>   

        <s:form beanclass="cz.muni.fi.pa165.sportactivitymanager.web.RecordActionBean">
            <fieldset><legend><f:message key="record.list.newRecord"/></legend>
                <%@include file="form.jsp"%>
                <s:hidden name="user.id"/>
                <s:submit name="add"><f:message key="record.list.addNewRecord"/></s:submit>
                </fieldset>
        </s:form>      

    </s:layout-component>
</s:layout-render>
