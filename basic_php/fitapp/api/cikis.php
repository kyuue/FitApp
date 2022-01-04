<?php
	try {
		if(!isset($_GET['customer_id'])) {
			print("no customer id");
			die();
		}

		$customerId = $_GET['customer_id'];

		require 'db.php';

		$dbh = createPDO();

		$sth = $dbh->prepare('INSERT INTO entry_exit(customer_id, type, date) VALUES(?, 1, NOW())');

		$sth->execute(array($customerId));
		
		print("added to list");
	   
	} catch (PDOException $e) {
		print "Hata!: " . $e->getMessage() . "<br/>";
		die();
	}
?>