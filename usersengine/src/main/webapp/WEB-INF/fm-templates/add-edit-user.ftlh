<!DOCTYPE html>
<html>
<head>
    <title>Add/Edit user template</title>
    <script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
</head>
<body>
<#if errors??>
    <#list errors>
        <ul>
            <#items as error>
                <li style="color: #ff0000; font-weight: bold; font-size: 14px;x`">${error}</li>
            </#items>
        </ul>
    </#list>
</#if>
<h1>Add/Edit user from UserServlet</h1>
<form method="post" id="add-edit-user" action="/user/add" enctype="multipart/form-data">
    ID: <input type="text" name="id" value="${(user.id)!}"/><br/><br/>
    Name: <input type="text" name="name" value="${(user.name)!}"/><br/><br/>
    Login: <input type="text" name="login" value="${(user.login)!}"/><br/><br/>
    Password: <input type="password" name="password"/><br/><br/>
    Age: <input type="text" name="age" value="${(user.age)!}"/><br/><br/>
    Image: <input type="file" name="image"/><br/><br/>
    <input type="submit" name="save" class="user ${action!}" value="save"/><br/><br/>
</form>
<script src="/js/user.js"></script>
</body>
</html>