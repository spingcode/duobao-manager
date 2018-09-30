<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link href="/css/index.css" rel="stylesheet"/>
</head>
<body>
<div class="container">

    <form action="/redisCacheController/searchCacheByKey" method="post">
        <table>
            <tr>
                <td>
                    查询key
                </td>
            </tr>
            <tr>
                <td>请输入要查询的key :<input type="text" name="key" value="${key!}"></td>
            </tr>
            <tr>
                <td>值为 :${msg!}</td>
            </tr>
        </table>
        <input type="submit" value="查询">
    </form>

    <br/>
    <br/>
    <form action="/redisCacheController/setExpriseByKey" method="post">
        <table>
            <tr>
                <td>
                    设置某个key的有效期时间（单位为秒）
                </td>
            </tr>
            <tr>
                <td>请输入要设置的key :<input type="text" name="key1" value="${key1!}"></td>
            </tr>
            <tr>
                <td>请设置过期时间 :<input type="text" name="seconds">(秒)</td>
            </tr>
        </table>
        <input type="submit" value="设置">
    </form>
    <br/>
    <br/>
    <form action="/redisCacheController/setAllKeyExprise" method="post">
        <table>
            <tr>
                <td>
                    设置所有key的有效期时间（单位为天，最少2天）
                </td>
            </tr>
            <tr>
                <td>请设置过期时间 :<input type="text" name="days">(天)</td>
            </tr>
        </table>
        <input type="submit" value="设置">
    </form>



</div>
</body>
</html>