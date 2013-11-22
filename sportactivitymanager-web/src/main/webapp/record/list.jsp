<%-- 
    Document   : list
    Created on : 22.11.2013, 13:24:09
    Author     : Phaser
--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>


<s:layout-render name="/layout.jsp" titlekey="records">
    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.sportactivitymanager.web.RecordActionBean" var="actionBean"/>
        
       
        <table class="basic">
            <tr>
                <th>id</th>
                <th><f:message key="record.user"/></th>
                <th><f:message key="record.duration"/></th>
                <th><f:message key="record.distance"/></th>
                <th><f:message key="record.StartTime"/></th>
            </tr>
            <c:forEach items="${actionBean.records}" var="record">
                <tr>
                    <td>${record.id}</td>
                    <td><c:out value="${record.user}"/></td>
                    <td><c:out value="${record.duration}"/></td>
                    <td><c:out value="${record.distance}"/></td>
                    <td><c:out value="${record.StartTime}"/></td>
                </tr>
            </c:forEach>
        </table>    
        
    </s:layout-component>
</s:layout-render>
