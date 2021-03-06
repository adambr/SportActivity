<%-- 
    Document   : form
    Created on : Nov 21, 2013, 8:00:03 AM
    Author     : Kuba Dobes

--%>

<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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

<s:errors/>    
<table>
    <tr>
        <th><s:label for="b7" name="user.login"/></th>
        <td><s:text id="b7" name="user.login"/></td>
    </tr>
    <tr>
        <th><s:label for="b6" name="user.password"/></th>
        <td><s:password id="b6" name="user.password"/></td>
    </tr>

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


    <sec:authorize access="hasRole('ADMIN')">
        <tr>
            <th><s:label for="b5" name="selectedRole"/></th>
            <td><s:select id="b5" name="selectedRole">
                    <s:options-enumeration enum="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean.MyRole"/>
                </s:select></td>
        </tr>
    </sec:authorize>
</table>

<%-- tlacitko pro smazani
                       Kdyz ho stisknu, tak bude m�t 2 parametry: user.id a delete
                       Podle toho Stripes poznaj� ?e maj� volat metodu delete

<s:form beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean">
                //   skryti user ID pro zapamatov�n�
                   <s:hidden name="user.id" value="${user.id}"/>
                   <s:submit name="delete"><f:message key="user.list.delete"/></s:submit> 
               </s:form>--%>