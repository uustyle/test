<?php
header("Content-type: text/plain; charset=UTF-8");
if (isset($_POST['request']))
{
    //ここに何かしらの処理を書く（DB登録やファイルへの書き込みなど）
    echo "OK";
}
else
{
    echo 'The parameter of "request" is not found.';
}
?>