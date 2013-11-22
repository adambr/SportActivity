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
        <div class="list">
            <table>
                <tr>
                    <th ROWSPAN=2>Název</th>
                    <th COLSPAN=4>Kalorie</th>
                </tr>
                <tr>
                    <th>60KG</th>
                    <th>70KG</th>
                    <th>80KG</th>
                    <th>90KG</th>
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
                                <s:param name="activity.id" value="${activity.id}"/>editovat</s:link>
                        </td>
                        <td width="20">
                            <s:form beanclass="cz.muni.fi.pa165.sportactivitymanager.web.ActivityActionBean">
                                <s:hidden name="activity.id" value="${activity.id}"/>
                                <s:submit name="delete">Smazat</s:submit>
                            </s:form>
                        </td>
                    </tr>
                </c:forEach>
            </table>    
        </div>
        <div class="new">
            <s:form beanclass="cz.muni.fi.pa165.sportactivitymanager.web.ActivityActionBean">
                <fieldset><legend>nová aktivita</legend>
                    <%@include file="form.jsp"%>
                    <s:submit name="add">Vytvořit novou aktivitu</s:submit>
                    </fieldset>
            </s:form>
        </div>

    </s:layout-component>
</s:layout-render>
