<%-- 
    Document   : form
    Created on : 22.11.2013, 18:45:29
    Author     : Petaniss
--%>

<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th>Název</th>
        <td COLSPAN=4><s:text name="activity.name"/></td>
    </tr>
    <tr>
        <th ROWSPAN=2>Kalorie</th>
        <td>60KG</td>
        <td>70KG</td>
        <td>80KG</td>
        <td>90KG</td>
    </tr>
    <tr>
        <td><s:text name="calories.calories60Kg"/></td>
        <td><s:text name="calories.calories70Kg"/></td>
        <td><s:text name="calories.calories80Kg"/></td>
        <td><s:text name="calories.calories90Kg"/></td>
    </tr>
</table>
