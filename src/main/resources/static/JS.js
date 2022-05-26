let form = document.querySelector('#password')
form.addEventListener('keyup', function() {
let showButton = document.getElementById("registration");
let password = form.value
    showButton.setAttribute("disabled", "disabled");
    let letters = "qwertyuiopasdfghjklzxcvbnm";
    let big_letters = "QWERTYUIOPLKJHGFDSAZXCVBNM";
    let digits = "0123456789";
    let specials = "!@#$%^&*()_-+=\|/.,:;[]{}";
    let is_s = false;
    let is_b = false;
    let is_d = false;
    let is_sp = false;
    for (var i = 0; i < password.length; i++) {
    if (!is_s && letters.indexOf(password[i]) != -1) is_s = true;
    else if (!is_b && big_letters.indexOf(password[i]) != -1) {is_b = true;}

    else if (!is_d && digits.indexOf(password[i]) != -1) is_d = true;
    else if (!is_sp && specials.indexOf(password[i]) != -1) is_sp = true;
    }
    let rating = 0;
    let text = "";
    if (is_s) rating++;
    if (is_b) rating++;
    if (is_d) rating++;
    if (is_sp) rating++;
    if(rating ==1){
    }
    if(rating == 2) {
     showButton.removeAttribute("disabled")
    }
    if(rating == 3) {
    showButton.removeAttribute("disabled")
        div.className = "good";
    }
});
