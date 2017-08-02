
function onNotice(data) {
    var txtMessage = document.getElementById('txtMessage');
    txtMessage.value = data.message+ txtMessage.value;
}


