<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<s:layout-definition>
    <!DOCTYPE html>
    <html lang="${pageContext.request.locale}">
        <head>
            <title><f:message key="${titlekey}"/></title>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css" />
            <s:layout-component name="header"/>
            
            <%-- Jquery DATEPICKER zobrazí v user/form.jsp kalendář pro výběr data narození uživatele--%>
            <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
            <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
            <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
            <%--  <link rel="stylesheet" href="/resources/demos/style.css" />   --%>
            <script>
                $(function() {
                  $( "#datepicker" ).datepicker();
                });
            </script>
  
        </head>
        <body>
            <div id="page">
                <div id="login-panel-cont">
                    <div id="title"><f:message key="${titlekey}"/></div>
                    <div id="login">Administrator | logout</div>
                </div>
                <div id="header">
                    <div id="logo"><img src="${pageContext.request.contextPath}/img/logo.png" alt="logo"></div>
                    <div id="logo-title">
                        <div class="title-line1">
                            <span class="highlight"><f:message key="header.track"/> </span> <f:message key="header.track.subject"/> <br>
                        </div>
                        <div class="title-line2">
                            <span class="highlight"><f:message key="header.check"/></span> <f:message key="header.check.subject"/><br>
                        </div>
                        <div class="title-line3">
                            <f:message key="header.just"/><span class="highlight"> <f:message key="header.just.subject"/></span><br>
                        </div>
                    </div>
                </div>
                <div id="content">
                    <%--<s:messages/> - umožnuje vypsat zprávy typu: kniha byla přidána, smazána--%>
                    <s:messages/>
                    <%--zde se zobrazí ta hlavní stránka s name=body - stejne name ma i index.jsp--%>
                    <s:layout-component name="body"/>
                </div>
            </div>
        </body>
    </html>
</s:layout-definition>