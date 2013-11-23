<%-- 
    Document   : form
    Created on : 22.11.2013, 18:04:53
    Author     : Phaser
--%>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<s:errors/>
<table>
    <tr>
        <th><s:label for="b1" name="record.duration"/></th>
        <td><s:text id="b1" name="record.duration"/></td>
    </tr>
    <tr>
        <th><s:label for="b2" name="record.distance"/></th>
        <td><s:text id="b2" name="record.distance"/></td>
    </tr>
    <tr>
        <th><s:label for="datepicker" name="record.startTime"/></th>
        <td><s:text id="datepicker" name="record.startTime"/></td>
    </tr>
    <tr>
        <th><s:label for="b4" name="record.aktivity"/></th>
        <td>
            <s:select name="aktivita">
                <s:options-collection collection="${actionBean.activity}" value="id" label="name"/>
            </s:select>
            <s:hidden name="user.id"/>
        </td>
    </tr>

</table>