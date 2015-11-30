var websocket = new WebSocket(
		"ws://localhost:8080/JSONWebSocket/JsonServerEndpoint");

//define function which gets called on incoming message
websocket.onmessage = onMessage;

function onMessage(event) {
	var data = JSON.parse(event.data);
	document.getElementById("firstName").value = data.firstName;
	document.getElementById("lastName").value = data.lastName;
	
	if(data.action==="showAlert"){
		alert("Welcome Session" + data.sessionId);
	}
}

function onChangeClick() {
	console.log("Click Event triggered");

	var firstName = document.getElementById("firstName").value;
	var lastName = document.getElementById("lastName").value;

	var oPerson = {
		firstName : firstName,
		lastName : lastName
	};
	websocket.send(JSON.stringify(oPerson));
}