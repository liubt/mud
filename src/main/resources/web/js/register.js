function showRegister() {

    var divMenu = document.getElementById('indexDiv_menu');
    divMenu.setAttribute("class", "hidden");
    var divRegister = document.getElementById('indexDiv_register');
    divRegister.setAttribute("class", "");
}


function doRegister() {

    var userId = document.getElementById('register_userId').value;
    var password = document.getElementById('register_password').value;
    if(!userId || !password) {
        alert("用户名和密码必须输入");
        return;
    }

    var cmd = {cmd: "register", data: {userId: userId, password: password}};
    window.socket.send(JSON.stringify(cmd));
}

function onRegister(data) {
    console.log(data);
    if(!data.success) {
        alert(data.message);
    }
}

