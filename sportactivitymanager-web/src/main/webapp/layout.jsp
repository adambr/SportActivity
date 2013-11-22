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
        </head>
        <body>
            <div id="page">
                <div id="login-panel-cont">
                    <div id="title">Sport Activity Manager</div>
                    <div id="login">Administrator | logout</div>
                </div>
                <div id="header">
                    <div id="logo"><img src="${pageContext.request.contextPath}/img/logo.png" alt="logo"></div>
                    <div id="logo-title">
                        <div class="title-line1">
                            <span class="highlight">TRACK</span> YOUR ACTIVITIES<br>
                        </div>
                        <div class="title-line2">
                            <span class="highlight">CHECK</span> CALORIES BURNED<br>
                        </div>
                        <div class="title-line3">
                            JUST BE <span class="highlight">FIT</span><br>
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