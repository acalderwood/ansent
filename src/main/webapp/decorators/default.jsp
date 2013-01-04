<!DOCTYPE html>
<%@ include file="/common/taglibs.jsp"%>
<html lang="en">
    <head>
        <title><decorator:title/> | <fmt:message key="webapp.name"/></title>
    </head>
    <body>
        default
        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container-fluid">
                    <%@ include file="/common/menu.jsp" %>
                </div>
            </div>
        </div>

        <div id="footer">
            <span class="left"><fmt:message key="webapp.version"/></span>
            <span class="right">
                &copy; <fmt:message key="copyright.year"/> <fmt:message key="company.name"/>
            </span>
        </div>
    </body>
</html>
