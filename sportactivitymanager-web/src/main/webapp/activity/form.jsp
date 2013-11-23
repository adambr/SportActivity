<%-- 
    Document   : form
    Created on : 22.11.2013, 18:45:29
    Author     : Petaniss
--%>

<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="name" name="activity.name"/></th>
        <td COLSPAN=4><s:text id="name" name="activity.name"/></td>
    </tr>
    <tr>
        <th ROWSPAN=2><f:message key="calories"/></th>
        <td><s:label for="c1" name="calories.calories60Kg"/></td>
        <td><s:label for="c2" name="calories.calories70Kg"/></td>
        <td><s:label for="c3" name="calories.calories80Kg"/></td>
        <td><s:label for="c4" name="calories.calories90Kg"/></td>
    </tr>
    <tr>
        <td><s:text id="c1" name="calories.calories60Kg"/></td>
        <td><s:text id="c2" name="calories.calories70Kg"/></td>
        <td><s:text id="c3" name="calories.calories80Kg"/></td>
        <td><s:text id="c4" name="calories.calories90Kg"/></td>
    </tr>
</table>
