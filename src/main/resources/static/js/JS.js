let form = document.querySelector('#password')
form.addEventListener('keyup', function() {
let showButton = document.getElementById("registration");
const $result = $('#resultPass');
let password = form.value
    showButton.setAttribute("disabled", "disabled");
    $result.text('Минимальная длина пароля-8 символов. Он должен содержать латинские буквы, цифры и/или спец.символы( !?@#$%^&)');
    $result.css('color', 'red');
    let letters = "qwertyuiopasdfghjklzxcvbnm";
    let big_letters = "QWERTYUIOPLKJHGFDSAZXCVBNM";
    let digits = "0123456789";
    let specials = "!?@#$%^&";
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
    if(rating == 2 && password.length>=8) {
     showButton.removeAttribute("disabled")
         $result.css('color', 'green');
    }
    if(rating == 3  && password.length>=8) {
    showButton.removeAttribute("disabled")
        $result.css('color', 'green');
        //div.className = "good";
    }
});

const validateEmail = (email) => {
  return email.match(
  /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((gmail\.com)|(yandex\.ru)|(mail\.ru))$/
   );
};

const validate = () => {
  let showButton = document.getElementById("registration");
  showButton.setAttribute("disabled", "disabled");

  const $result = $('#resultEmail');
  const email = $('#email').val();
  $result.text('');

  if(validateEmail(email)){
    $result.text(email + ' Успешно');
    $result.css('color', 'green');
    showButton.removeAttribute("disabled")
  } else{
    $result.text(email + ' Разрешено использовать только сервисы yandex.ru,gmail.com,mail.ru');
    $result.css('color', 'red');
  }
  return false;
}

$('#email').on('input', validate);
