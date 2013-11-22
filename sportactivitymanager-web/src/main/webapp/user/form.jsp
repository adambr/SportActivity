<%-- 
    Document   : form
    Created on : Nov 21, 2013, 8:00:03 AM
    Author     : Kuba Dobes

    Formular pro zobrazení 5 údaju o uzivateli pro vyplnení/editaci
    Neobsahuje tlcitko pro ulození. To se pridává az v .jsp který form.jsp
--%>

<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%--zde se zobrazují chyby (tam kde je tag s:errors)--%>
<s:errors/>
<table>
    <tr>
        <th><s:label for="b1" name="user.firstname"/></th>
        <td><s:text id="b1" name="user.firstname"/></td>
    </tr>
    <tr>
        <th><s:label for="b2" name="user.lastname"/></th>
        <td><s:text id="b2" name="user.lastname"/></td>
    </tr>
    <tr>
        <th><s:label for="b3" name="user.birthday"/></th>
        <td><s:text id="datepicker" name="user.birthday"/></td>
    <%-- 
        <th><s:label for="b3" name="user.birthday"/></th>
        <td><s:text id="b3" name="user.birthday"/></td>
        
    REDO dataPicker    <td><s: id="b3" name="user.birthday"/></td> --%>
    
    </tr>
    <tr>
        <th><s:label for="b4" name="user.weight"/></th>
        <td><s:text id="b4" name="user.weight"></td>
    </tr>
    <tr>
        <th><s:label for="b5" name="user.gender"/></th>
        <td><s:select id="b5" name="user.gender">
                <s:options-enumeration enum="cz.muni.fi.pa165.sportactivitymanager.Gender"/>
            </s:select></td>
        
    </tr>
</table>