<%-- 
    Document   : form
    Created on : Nov 21, 2013, 8:00:03 AM
    Author     : Kuba Dobes

    Formular pro zobrazen� 5 �daju o uzivateli pro vyplnen�/editaci
    Neobsahuje tlcitko pro ulozen�. To se prid�v� az v .jsp kter� form.jsp
--%>

<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>     <%--zde se zobrazuj� chyby (tam kde je tag s:errors)--%>
<table>
    <tr>
        <th><s:label for="b1" name="user.firstname"/></th> <%--nazev pole, bere podle lokalizace proto vse male--%>
        <td><s:text id="b1" name="user.firstName"/></td> <%--nazev atributu usera. Bere z UserDTO, proto camelCase--%>
    </tr>
    <tr>
        <th><s:label for="b2" name="user.lastname"/></th>
        <td><s:text id="b2" name="user.lastName"/></td>
    </tr>
    <tr>
        <th><s:label for="b3" name="user.birthday"/></th>
        <td><s:text id="datepicker" name="user.birthDay"/></td>
    </tr>
    <tr>
        <th><s:label for="b4" name="user.weight"/></th>
        <td><s:text id="b4" name="user.weight"/></td>
    </tr>
    <tr>
        <th><s:label for="b5" name="user.gender"/></th>
        <td><s:select id="b5" name="user.gender">
                <s:options-enumeration enum="cz.muni.fi.pa165.sportactivitymanager.Gender"/>
            </s:select></td>
    </tr>    
</table>
        
         <%-- tlacitko pro smazani
                                Kdyz ho stisknu, tak bude m�t 2 parametry: user.id a delete
                                Podle toho Stripes poznaj� ?e maj� volat metodu delete
        
        <s:form beanclass="cz.muni.fi.pa165.sportactivitymanager.web.UserActionBean">
                         //   skryti user ID pro zapamatov�n�
                            <s:hidden name="user.id" value="${user.id}"/>
                            <s:submit name="delete"><f:message key="user.list.delete"/></s:submit> 
                        </s:form>--%>