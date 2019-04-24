<!DOCTYPE html>
<html>
<head>
    <title>Users List</title>
    <link type="text/css" rel="stylesheet" href="/css/styles.css"/>
    <script src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
</head>
<body>

<#-- text -->
<#list users>
    <#items as user>
        <div>
            <#if user.age < 21 >
                <#assign class="blue">
            <#elseif user.age gt 20 && user.age < 41 >
                <#assign class="green">
            <#else>
                <#assign class="red">
            </#if>

            <ul class="${class}">
                <li>ID: ${user.id}</li>
                <li>Name: ${user.name}</li>
                <li>Login: ${user.login}</li>
                <li>Age: ${user.age}</li>
                <li>Gender: ${user.gender!"unknown"}</li>
                <#if user.imageURL??>
                    <li>
                        <img width="200" src="${user.imageURL}"/>
                    </li>
                </#if>
                <li><a href="/user/edit?id=${user.id}">edit user</a></li>
                <li><a href="#" class="delete-user-link" data-id="${user.id}">delete user</a></li>
            </ul>
            <#sep><br/>=============================<br/></#sep>
        </div>
    </#items>
</#list>

<#include "copyright-footer.ftlh">
<script src="/js/user.js"></script>
</body>
</html>