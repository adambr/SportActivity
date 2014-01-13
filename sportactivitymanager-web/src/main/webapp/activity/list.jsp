<%-- 
    Document   : list.jsp
    Created on : 22.11.2013, 11:31:02
    Author     : Petr Jelínek
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:layout-render name="/layout.jsp" titlekey="activity.list.title">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.sportactivitymanager.web.ActivityActionBean" var="actionBean"/>

        <a href="${pageContext.request.contextPath}" class="nav">
            <f:message key="main.page"/>
        </a>

        <div class="list">
            <table>
                <tr>
                    <th ROWSPAN=2><f:message key="activity.name"/></th>
                    <th COLSPAN=4><f:message key="calories"/></th>
                </tr>
                <tr>
                    <th><f:message key="calories.calories60Kg"/></th>
                    <th><f:message key="calories.calories70Kg"/></th>
                    <th><f:message key="calories.calories80Kg"/></th>
                    <th><f:message key="calories.calories90Kg"/></th>
                </tr>
                <c:forEach items="${actionBean.activities}" var="activity">
                    <tr>
                        <td>${activity.name}</td>
                        <td>${activity.calories.calories60Kg}</td>
                        <td>${activity.calories.calories70Kg}</td>
                        <td>${activity.calories.calories80Kg}</td>
                        <td>${activity.calories.calories90Kg}</td>
                        <td width="20">
                            <s:link beanclass="cz.muni.fi.pa165.sportactivitymanager.web.ActivityActionBean" event="edit">
                                <s:param name="activity.id" value="${activity.id}"/><f:message key="activity.edit"/></s:link>
                            </td>
                            <td width="20">
                            <s:form beanclass="cz.muni.fi.pa165.sportactivitymanager.web.ActivityActionBean">
                                <s:hidden name="activity.id" value="${activity.id}"/>
                                <s:submit name="delete"><f:message key="activity.del"/></s:submit>
                            </s:form>
                        </td>
                    </tr>
                </c:forEach>
            </table>    
        </div>
        <div class="new">
            <s:form beanclass="cz.muni.fi.pa165.sportactivitymanager.web.ActivityActionBean">
                <fieldset><legend><f:message key="activity.new.title"/></legend>
                    <%@include file="form.jsp"%>
                    <s:submit name="add"><f:message key="activity.create"/></s:submit>
                    </fieldset>
            </s:form>
        </div>

    </s:layout-component>
</s:layout-render>
