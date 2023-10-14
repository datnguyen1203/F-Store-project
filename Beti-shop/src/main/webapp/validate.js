var emailValidation1 = /^[A-z]+[^\@\s]+\@[^\s]+\.[^\s]{2,}$/;
var emailValidation2 = /^[A-z]+\@[^\s]+\.[^\s]{2,}$/;
var numberValidation = /^0+[0-9]{9,10}$/

function checkReset() {
    document.getElementById('emailerror').innerHTML = "";
    document.getElementById('usernameerror').innerHTML = "";
    document.getElementById('passerror').innerHTML = "";
    document.getElementById('repasserror').innerHTML = "";
    document.getElementById('birtherror').innerHTML = "";
    document.getElementById('phoneerror').innerHTML = "";
    document.getElementById('addresserror').innerHTML = "";

}

function checkValidate() {

    checkEmail();
    checkUsername();
    checkPassword();
    checkBirthday();
    checkPhone();
    checkAddress();
    event.preventDefault();
}


function checkEmail() {
    var email = document.getElementById('email').value;

    if (!emailValidation1.test(email) && !emailValidation2.test(email)) {
        document.getElementById('emailerror').innerHTML = "Email is invalid";
        document.getElementById('emailerror').style.color = "red";
    } else {
        document.getElementById('emailerror').innerHTML = "";
    }
}

function checkUsername() {
    var name = document.getElementById('username').value.trim();

    if (name == "") {
        document.getElementById('usernameerror').innerHTML = "Username cannot be empty";
        document.getElementById('usernameerror').style.color = "red";

    } else {
        document.getElementById('usernameerror').innerHTML = "";
    }
}

function checkPassword() {
    var password = document.getElementById('password').value;
    var repassword = document.getElementById('repassword').value;

    if (password.length < 6 || password.length > 20 || password == "") {
        document.getElementById('passerror').innerHTML = "Password must be between 6 and 20 characters";
        document.getElementById('passerror').style.color = "red";
    } else {
        document.getElementById('passerror').innerHTML = "";
    }

    if (repassword != password) {
        document.getElementById('repasserror').innerHTML = "Password does not match";
        document.getElementById('repasserror').style.color = "red";
    } else {
        document.getElementById('repasserror').innerHTML = "";
    }

}

function checkBirthday() {
    var birthday = document.getElementById('birthday').value;

    if (birthday == "") {
        document.getElementById('birtherror').innerHTML = "Birthday cannot be empty";
        document.getElementById('birtherror').style.color = "red";

    } else {
        document.getElementById('birtherror').innerHTML = " ";
    }
}

function checkPhone() {
    var phone = document.getElementById('phone').value;

    if (!numberValidation.test(phone)) {
        document.getElementById('phoneerror').innerHTML = "Phone is invalid";
        document.getElementById('phoneerror').style.color = "red";
    } else {
        document.getElementById('phoneerror').innerHTML = "";
    }

}

function checkAddress() {
    var address = document.getElementById('address').value;
    var address = address.replace(/\s/g, '');
    if (address == "") {
        document.getElementById('addresserror').innerHTML = " You have to provide your address";
        document.getElementById('addresserror').style.color = "red";
    } else {
        document.getElementById('addresserror').innerHTML = "";
    }

}