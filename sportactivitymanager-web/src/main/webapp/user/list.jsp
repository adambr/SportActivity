<%-- 
    Document   : list
    Created on : Nov 20, 2013, 14:42:11 AM
    Author     : Kuba Dobes

--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/layout.jsp" titlekey="user.list.title">


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
        <s:useActionBean beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean" var="actionBean"/>

        <a href="${pageContext.request.contextPath}" class="nav">
            <f:message key="main.page"/>
        </a>

        <sec:authorize access="hasAnyRole('USER','ADMIN')">
            <div class="list">
                <table>

                    <tr>
                        <th>ID</th>
                        <th><f:message key="user.firstName"/></th>
                        <th><f:message key="user.lastName"/></th>
                        <th><f:message key="user.birthDay"/></th>
                        <th><f:message key="user.weight"/></th>
                        <th><f:message key="user.gender"/></th>
                        <th><f:message key="user.password"/></th>
                        <th><f:message key="user.login"/></th>
                        <th><f:message key="user.credentials"/></th>
                    </tr>

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
                            <td><c:out value="${user.password}"/></td>
                            <td><c:out value="${user.login}"/></td>
                            <td><c:out value="${user.credentials}"/></td>
                            <td>
                                <s:link beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean" event="edit">
                                    <s:param name="user.id" value="${user.id}"/> <f:message key="user.list.edit"/> </s:link>
                            </td>

                            <td>
                                <s:form beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean">
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
        </sec:authorize>
        
        <sec:authorize access="hasRole('ADMIN')">
        <div class="new">
            <s:form beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean">

                <fieldset><legend><f:message key="user.list.newuser"/></legend>
                    <%@include file="form.jsp"%> 
                    <s:submit name="add"><f:message key="user.create"/></s:submit>
                    </fieldset>
            </s:form>
        </div>
        </sec:authorize>
    </s:layout-component>
</s:layout-render>