'use strict';

// var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');
var stompClient = null;
var username = null;
var groupId = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];
function connect(event) {
    username = document.querySelector('#name').value.trim();
    if(username) {
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}
$(function () {
    // usernamePage.classList.add('hidden');
    // chatPage.classList.remove('hidden');
    $("#sendPicture").click(function () {
        $("#multipartFile").click()
    })
    $("#multipartFile").change(function () {
        var formData = new FormData();
        formData.append("multipartFile",$("#multipartFile")[0].files[0]);
        console.log(formData);
        $.ajax({
            type:"POST",
            url:"/uploadPicture",
            mimeType:"multipart/form-data",
            cache:false,
            processData:false,
            contentType:false,
            data:formData,
            success:function (msg) {
                // console.log(msg);
                // $("#showImg").attr("src","img/"+msg);
                // var messageContent = messageInput.value.trim();
                if(msg && stompClient) {
                    var chatMessage = {
                        sender: username,
                        content: "![图片]("+msg+")",
                        messageType: 'CHAT'
                    };
                    stompClient.send("/app/chat/sendMessage/"+groupId, {}, JSON.stringify(chatMessage));
                    messageInput.value = '';
                }
            }
        })
    })
    groupId = $("#groupId").val();
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
    username = userInfo.userName;
    console.log(username);

})

function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public/'+groupId, onMessageReceived);

    // Tell your username to the server
    stompClient.send("/app/chat/addUser/"+groupId,
        {},
        JSON.stringify({sender: username, messageType: 'JOIN'})
    )

    connectingElement.classList.add('hidden');
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function sendMessage(event) {
    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            messageType: 'CHAT'
        };
        stompClient.send("/app/chat/sendMessage/"+groupId, {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}
function sendPicture(event){

}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    var messageElement = document.createElement('li');

    if(message.messageType === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    } else if (message.messageType === 'ONLINE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' 上线了!';
    } else if (message.messageType === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    } else {
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }
    var pattern = /!\[图片\]\((.+)\)/;
    if(pattern.test(message.content)){
        var src = RegExp.$1
        var pictureElement = document.createElement("img")
        $(pictureElement).attr("src",src);
        $(pictureElement).attr("width","100px");
        $(pictureElement).attr("height","100px");
        messageElement.appendChild(document.createElement("br"));
        messageElement.appendChild(pictureElement);
    }else {
        var textElement = document.createElement('p');
        var messageText = document.createTextNode(message.content);
        textElement.appendChild(messageText);

        messageElement.appendChild(textElement);
    }
    // var textElement = document.createElement('p');
    // var messageText = document.createTextNode(message.content);
    // textElement.appendChild(messageText);
    //
    // messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }
    var index = Math.abs(hash % colors.length);
    return colors[index];
}

// usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)
