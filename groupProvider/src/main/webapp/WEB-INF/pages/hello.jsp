<html>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {

						$(".gbutton").on("click", function() {
							postData("/groupProviders")
						});
						function postData(postUrl) {
							var groupContentData = {
								"group" : {
									"services" : [ {
										"id" : 1418653274801,
										"name" : "CurrentTravelInformation",
										"importCommand" : "GetCMSPage",
										"serviceCalls" : [ {
											"id" : 1418653295864,
											"name" : "CurrentTravelInformation English",
											"importCommandParameters" : {
												"country" : "de",
												"domain" : "lh_mobile",
												"language" : "en",
												"page" : "/CurrentTravelInformation"
											}
										} ]
									} ]
								}
							};

							$.ajax({
								url : postUrl,
								type : 'POST',
								data : JSON.stringify(groupContentData),
								contentType : 'application/json',
								success : function(data) {
									alert("Group provider with the id " + data + " was created");
								},
								error : function(data, status, er) {
									alert("The sample data is already created");
									/* alert("error: " + data + " status: "
											+ status + " er:" + er); */
								}
							});
						}
					});

	/* {\"id\": 1418653295864,\"name\": \"CurrentTravelInformation English\",\"importCommandParameters\": {\"country\": \"de\",\"domain\": \"lh_mobile\",\"language\": \"en\",\"page\": \"/CurrentTravelInformation\"}} */
</script>

<head>
<title>Group services provider</title>
</head>
<body>
	<h3>Group services provider</h3>
	<form action="">
		<input type="button" class="gbutton" value="Create test data"></input>
	</form>
	<p class="textP"></p>
</body>
</html>