<%-- 
    Document   : form
    Created on : Nov 21, 2013, 8:00:03 AM
    Author     : Kuba Dobes

    Formulá? pro zobrazení 5 údaj? o u?ivateli pro vypln?ní/editaci
    Neobsahuje tla?ítko pro ulo?ení. To se p?idává a? v .jsp který form.jsp
--%>

<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
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
        <td><s:checkbox id="b3" name="user.birthday"/></td>
        
        <td><s: id="b3" name="user.birthday"/></td>
    
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