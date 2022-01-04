<?php

function createPDO()
{
	return $dbh = new PDO('mysql:host=localhost;dbname=fitapp', "fitapp", "");
}
?>