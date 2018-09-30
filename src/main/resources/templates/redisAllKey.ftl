<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link href="/css/index.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <table>
        <tr>
            <td width="100">key</td>
        </tr>
    <#list msg as being>
        <tr>
            <td>${being}</td>
        <tr/>
    </#list>
</div>
</body>
</html>