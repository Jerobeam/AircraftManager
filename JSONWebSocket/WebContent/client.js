var websocket = new WebSocket("ws://localhost:8080/JSONWebSocket/JsonServerEndpoint");
		websocket.onmessage = function processMessage(message){
			var jsonData = JSON.parse(message.data);
			if(jsonData.message != null){
				messagesTextArea.value += jsonData.message + "\n";
			}
		}
		function onChangeClick(){
			console.log("Click Event triggered");
			
			websocket.send("hallo");
		}