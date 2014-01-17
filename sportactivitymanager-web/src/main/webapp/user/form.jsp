<%-- 
    Document   : form
    Created on : Nov 21, 2013, 8:00:03 AM
    Author     : Kuba Dobes

--%>

<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<s:errors/>    
<table>
    <tr>
        <th><s:label for="b1" name="user.firstName"/></th> 
        <td><s:text id="b1" name="user.firstName"/></td> 
    </tr>
    <tr>
        <th><s:label for="b2" name="user.lastName"/></th>
        <td><s:text id="b2" name="user.lastName"/></td>
    </tr>
    <tr>
        <th><s:label for="datepicker" name="user.birthDay"/></th>
        <td><s:text id="datepicker" name="user.birthDay"/></td>
    </tr>
    <tr>
        <th><s:label for="b4" name="user.weight"/></th>
        <td><s:text id="b4" name="user.weight"/></td>
    </tr>
    <tr>
        <th><s:label for="b5" name="user.gender"/></th>
        <td><s:select id="b5" name="user.gender">
                <s:options-enumeration enum="cz.muni.fi.pa165.sportactivitymanager.dto.Gender"/>
            </s:select></td>
    </tr>
    <tr>
        <th><s:label for="b6" name="user.password"/></th>
        <td><s:text id="b6" name="user.password"/></td>
    </tr>
    <tr>
        <th><s:label for="b7" name="user.login"/></th>
        <td><s:text id="b7" name="user.login"/></td>
    </tr>
    
    <sec:authorize access="hasRole('ADMIN')">
    <tr>
        <th><s:label for="b8" name="user.credentials"/></th>
        <td><s:text id="b8" name="user.credentials"/></td>
    </tr>
    </sec:authorize>
</table>

<%-- tlacitko pro smazani
                       Kdyz ho stisknu, tak bude mít 2 parametry: user.id a delete
                       Podle toho Stripes poznají ?e mají volat metodu delete

<s:form beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean">
                //   skryti user ID pro zapamatování
                   <s:hidden name="user.id" value="${user.id}"/>
                   <s:submit name="delete"><f:message key="user.list.delete"/></s:submit> 
               </s:form>--%>