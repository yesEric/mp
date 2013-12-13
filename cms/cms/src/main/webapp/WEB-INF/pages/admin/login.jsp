<%@ include file="/common/taglibs.jsp" %>


<head>
    <title><fmt:message key="login.title"/></title>
    <meta name="menu" content="Login"/>
</head>
<body id="login">
<form method="post" id="loginForm" action="login.do"
     class="form-signin" autocomplete="off">


    <input type="text" name="userName" id="userName" class="input-block-level"
           placeholder="<fmt:message key="label.username"/>" required tabindex="1">
    <input type="password" class="input-block-level" name="password" id="password" tabindex="2"
           placeholder="<fmt:message key="label.password"/>" required>



    <button type="submit" class="btn btn-large btn-primary" name="login" tabindex="4">
        <fmt:message key='button.login'/>
    </button>
</form>

</body>