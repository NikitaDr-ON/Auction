let length = document.getElementById("length");

var myInput = document.getElementById("test");
console.log (myInput)

// Когда пользователь начинает вводить что-то в поле пароля
myInput.onkeyup = function () {

    // Проверка длины
    if (myInput.value.length >= 8) {
        length.classList.remove("invalid");
        length.classList.add("valid");
    } else {
        length.classList.remove("valid");
        length.classList.add("invalid");
    }

}