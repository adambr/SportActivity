<%@ page contentType="text/html; charset=iso-8859-2" pageEncoding="iso-8859-2" %>
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
                    <div id="title"><f:message key="${titlekey}"/></div>
                    <div id="login">Administrator | logout</div>
                </div>
                <div id="header">
                    <div id="logo">
                        <a href="${pageContext.request.contextPath}">
                            <img src="${pageContext.request.contextPath}/img/logo.png" alt="logo">
                        </a>
                    </div>
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
                    <%--<s:messages/> - umo?nuje vypsat zpr?vy typu: kniha byla p?id?na, smaz?na--%>
                    <s:messages/>
                    <%--zde se zobraz? ta hlavn? str?nka s name=body - stejne name ma i index.jsp--%>
                    <s:layout-component name="body"/>
                </div>
            </div>
        </body>
    </html>
</s:layout-definition>
