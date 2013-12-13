<%-- 
    Document   : edit.jsp
    Created on : 12.12.2013, 17:52:41
    Author     : Adam Brauner
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="record.edit.title">
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

    <s:layout-component name="body">
        <s:useActionBean beanclass="cz.muni.fi.pa165.sportactivitymanager.web.RecordActionBean" var="actionBean"/>

        <s:link beanclass="cz.muni.fi.pa165.sportactivitymanager.web.RecordActionBean" class="nav">
            <s:param name="user.id" value="${actionBean.user.id}"/>
            <f:message key="record.title"/>
        </s:link>

        <div class="new">            
            <s:form beanclass="cz.muni.fi.pa165.sportactivitymanager.web.RecordActionBean">
                <s:hidden name="record.id"/>
                <s:hidden name="user.id"/>
                <fieldset><legend><f:message key="record.edit.edit"/></legend>
                    <%@include file="form.jsp"%>
                    <s:submit name="save"><f:message key="record.edit.save"/></s:submit>
                    </fieldset>
            </s:form>
        </div>

    </s:layout-component>
</s:layout-render>
