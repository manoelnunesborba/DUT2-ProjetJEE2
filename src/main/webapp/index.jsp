<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>

<head>
	<meta charset="utf-8">
	<title>Identification</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>

<body class="text-center">

	<div class="container">
		<div class="row mt-5">

			<form class="form-signin col-10 col-lg-6 col-xl-4 mx-auto mt-5" action="auth" method="post">

				<h1 class="h3 mb-3 font-weight-normal mb-4 mt-5"><%= "Connexion" %></h1>

				<div class="form-group">
					<label class="sr-only" for="fname">Pseudo</label>
					<input name="fname" type="text" id="fname" class="form-control" value="" placeholder="Nom" required autofocus />
				</div>

				<div class="form-group">
					<label class="sr-only" for="lmdp">Mot de passe</label>
					<input name="fmdp" type="password" id="lmdp" class="form-control" value="" placeholder="Mot de passe" required />
				</div>

				<div class="form-group">
					<input type="submit" class="btn btn-lg btn-primary btn-block" value="Se connecter" />
				</div>

			</form>

		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

</body>

</html>