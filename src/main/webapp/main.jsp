<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="datetag" uri="/WEB-INF/date.tld"  %>
<%@taglib prefix="myTags" tagdir="/WEB-INF/tags" %>

<myTags:template>
    <jsp:attribute name="header">
        <h1>Mobiles</h1>
        (<datetag:DateTag plus="1"/>)
    </jsp:attribute>
    <jsp:body>
        <ul>
            <li><a href="${pageContext.request.contextPath}/allmobiles">List mobiles</a></li>
            <li><a href="${pageContext.request.contextPath}/addmobile">Add mobile</a></li>
        </ul>
    </jsp:body>
</myTags:template>