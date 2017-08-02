function login() {
    var userId = document.getElementById('userId').value;
    var cmd = {cmd: "login", data: {userId: userId, password: "123456"}};
    window.socket.send(JSON.stringify(cmd));
}

function onLogin(data) {
    var divLogin = document.getElementById('loginDiv');
    divLogin.style.visibility = 'hidden';
    var divMain = document.getElementById('mainDiv');
    divMain.style.visibility = 'visible';

    var txtMessage = document.getElementById('txtMessage');
    txtMessage.value = data.welcomeMsg + txtMessage.value;
}


