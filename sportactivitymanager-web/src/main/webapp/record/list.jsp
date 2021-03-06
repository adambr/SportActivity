<%-- 
    Document   : list
    Created on : 22.11.2013, 13:24:09
    Author     : Adam Brauner
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/layout.jsp" titlekey="record.title">
    <s:layout-component name="header">
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
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

        <sec:authorize access="hasRole('ADMIN')">
            <s:link beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean" class="nav"> 
                <f:message key="record.list.userlist"/>
            </s:link>
        </sec:authorize>
        <sec:authorize access="hasRole('USER')">
            <a href="${pageContext.request.contextPath}" class="nav">
                <f:message key="main.page"/>
            </a>
        </sec:authorize>
        
        <div class="userTitle">${actionBean.user.firstName} ${actionBean.user.lastName}</div>
        <div class="list">
            <table>
                <tr>
                    <sec:authorize access="hasRole('ADMIN')"><th>id</th></sec:authorize>
                    <th><f:message key="record.aktivity"/></th>
                    <th><f:message key="record.startTime"/></th>
                    <th><f:message key="record.duration"/></th>
                    <th><f:message key="record.distance"/></th>
                </tr>
                <c:forEach items="${actionBean.records}" var="record">
                    <tr>
                        <sec:authorize access="hasRole('ADMIN')"><td>${record.id}</td></sec:authorize>
                        <td><c:out value="${record.activityDTO.name}"/></td>
                        <td><c:out value="${record.startTime}"/></td>
                        <td><c:out value="${record.duration}"/></td>
                        <td><c:out value="${record.distance}"/></td>
                        <td>
                            <s:link beanclass="cz.muni.fi.pa165.sportactivitymanager.web.RecordActionBean" event="edit">
                                <s:param name="record.id" value="${record.id}"/>
                                <sec:authorize access="hasRole('ADMIN')">
                                    <s:param name="user.id" value="${actionBean.user.id}"/>
                                </sec:authorize>
                                
                                <f:message key="user.list.edit"/> </s:link>
                            </td>                        
                            <td width="20">
                            <s:form beanclass="cz.muni.fi.pa165.sportactivitymanager.web.RecordActionBean">
                                <s:hidden name="record.id" value="${record.id}"/>
                                <s:hidden name="user.id" value="${user.id}"/>
                                <s:submit name="delete"><f:message key="record.list.delete"/></s:submit>
                            </s:form>
                        </td>
                    </tr>
                </c:forEach>
            </table>   
        </div>
        <div class="new">
            <s:form beanclass="cz.muni.fi.pa165.sportactivitymanager.web.RecordActionBean">
                <fieldset><legend><f:message key="record.list.newRecord"/></legend>
                    <%@include file="form.jsp"%>
                    <s:submit name="add"><f:message key="record.list.addNewRecord"/></s:submit>
                    </fieldset>
            </s:form>     
        </div>

    </s:layout-component>
</s:layout-render>
