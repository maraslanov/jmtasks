<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" class="jee.task22.pojo.UserPojo"/>

<h1>Authorisation</h1>
<form method="post" action="${pageContext.request.contextPath}/authorisation" autocomplete="off">
    <div class="form-group">
        <label for="login">Login</label>
        <input name="login" type="text" class="form-control" id="login">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input name="password" type="text" class="form-control" id="password">
    </div>
    <button type="submit" class="btn btn-primary" name="button" value="regbutton">Register</button>
    <button type="submit" class="btn btn-primary" name="button" value="signinbutton">Sign In</button>
</form>


