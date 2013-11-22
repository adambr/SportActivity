<%-- 
    Document   : list.jsp
    Created on : 22.11.2013, 11:31:02
    Author     : Petaniss
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="activities">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.sportactivitymanager.web.ActivityActionBean" var="actionBean"/>
        
        <br>
        <c:forEach items="${actionBean.activities}" var="activity">
            ${activity}<br>
        </c:forEach>
            
        <s:form beanclass="cz.muni.fi.pa165.sportactivitymanager.web.ActivityActionBean">
            <fieldset><legend>nová aktivita</legend>
                <%@include file="form.jsp"%>
                <s:submit name="add">Vytvořit novou aktivitu</s:submit>
            </fieldset>
        </s:form>
        
    </s:layout-component>
</s:layout-render>
