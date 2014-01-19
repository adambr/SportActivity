<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">
        <sec:authorize access="isAuthenticated()">
            <sec:authorize access="hasRole('ADMIN')">                              
                <div id="menu-box">
                    <s:link beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean" class="users"><f:message key="index.users.link"/></s:link>
                    <s:link beanclass="cz.muni.fi.pa165.sportactivitymanager.web.ActivityActionBean" class="activity"><f:message key="index.activities.link"/></s:link>
                    </div>
            </sec:authorize>
            <sec:authorize access="hasRole('USER')"> 
                <div id="menu-box">
                    <s:link href="/users/edit" class="users"><f:message key="index.users.link.for.user"/></s:link>
                    <s:link beanclass="cz.muni.fi.pa165.sportactivitymanager.web.RecordActionBean" class="activity"><f:message key="sportRecord"/></s:link>
                    </div>
            </sec:authorize>
        </sec:authorize>

        <sec:authorize access="isAnonymous()">


            <c:if test="${not empty param['login']}" >
                <div class="error"><f:message key="bad.login"/></div>
            </c:if>

            <div class="userTitle"><f:message key="indexMessage"/></div>
            <div class="login-box">
                <form name='f' action="j_spring_security_check" method='POST'>
                    <table>
                        <tr>
                            <td><f:message key="login.user"/>:</td>
                            <td><input type='text' name='j_username' value=''></td>
                        </tr>
                        <tr>
                            <td><f:message key="login.pass"/>:</td>
                            <td><input type='password' name='j_password' /></td>
                        </tr>
                    </table>
                    <input class="submit" name="submit" type="submit" value="<f:message key="login.submit"/>" />
                </form>
            </div>
        </sec:authorize>


    </s:layout-component>
</s:layout-render>
