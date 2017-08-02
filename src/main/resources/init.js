if (!window.WebSocket) {
    window.WebSocket = window.MozWebSocket;
}
if (window.WebSocket) {
    window.socket = new WebSocket("ws://localhost:8888/ws");
    socket.onmessage = function(event) {
        var data = JSON.parse(event.data);
        if(data.cmd == "login") {
            onLogin(data);
        } else if(data.cmd == "notice") {
            onNotice(data);
        }
    };
    socket.onopen = function(event) {
    };
    socket.onclose = function(event) {
    };
} else {
    alert("你的浏览器不支持 WebSocket！");
}