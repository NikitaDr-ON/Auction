$(document).ready(function (){

    checkFavProducts();


	$('body').on('click', '.number-minus, .number-plus', function(){
		var $row = $(this).closest('.number');
		var $input = $row.find('.number-text');
		var step = $row.data('step');
		var val = parseFloat($input.val());
		if ($(this).hasClass('number-minus')) {
			val -= step;
		} else {
			val += step;
		}
		$input.val(val);
		$input.change();
		return false;

	});

	$('body').on('change', '.number-text', function(){
		var $input = $(this);
		var $row = $input.closest('.number');
		var step = $row.data('step');
		var min = parseInt($row.data('min'));
		var max = parseInt($row.data('max'));
		var val = parseFloat($input.val());
		if (isNaN(val)) {
			val = step;
		} else if (min && val < min) {
			val = min;
		} else if (max && val > max) {
			val = max;
		}

		var quant=document.getElementsByClassName('quant');
		const idQuant =quant[0].id;
		console.log("idQuant="+idQuant)
		var maxQuant=document.getElementById(idQuant).innerHTML
		console.log("maxQuant="+maxQuant)
		if(val>maxQuant){
		 alert("Превышено количество товара!")
		 $input.val(maxQuant);
		}else{
		$input.val(val);
		}
		console.log("количество="+document.querySelector('.number-text').value)

	});

});


/*Обработка кнопки Купить(class=buy)*/
	$('body').on('click', '#buy', function(){
    	    var array = []
    	    var idServiceVal=0;
            var checkboxes = document.querySelectorAll('input[type=checkbox]:checked')
            let idServiceEl=document.querySelector('input[name="service"]:checked');
            for (var i = 0; i < checkboxes.length; i++) {
                          array.push(checkboxes[i].value)
                        }


            if(array.length>0 && idServiceEl){
                    idServiceVal=idServiceEl.value;
                    console.log("idService="+idServiceVal);
                    console.log(array);

            var postData = {
                           arraySale: array,
                           "service": idServiceVal
                           }

                      /* Ключ: тип Ajax, url, dataType, атрибуты данных */
                   $.ajax({
                           async : false,
                           cache : false,
                           type : 'POST',
                           url : '/ajax/buy',
                           dataType : "json",
                           data : postData,

                           success : function(data) {
                              console.log("Отправлен массив и сервис")
                           }

                       });
            }
             else{alert("Товар/товары или сервис для доставки не выбраны!");}

    	});

/*отмена оплаты корзины*/
$('body').on('click', '#cancel', function(){
var cancel=1;
$.ajax({
                           async : false,
                           cache : false,
                           type : 'POST',
                           url : '/ajax/cancel',
                           dataType : "json",
                           data : {"cancel" : cancel}
});
});
/*оплата корзины*/
$('body').on('click', '#pay', function(){
var pay=1;
$.ajax({
                           async : false,
                           cache : false,
                           type : 'POST',
                           url : '/ajax/pay',
                           dataType : "json",
                           data : {"pay" : pay}
});
alert("Товар оплачен! Письмо с информацией о доставке отправили на ваш email ");

});



/*Обработка кнопки (избранное-сердце)*/
$('#favoriteout').on('click', '.fav', function(e) {

   const whiteHeart = '\u2661';
   const blackHeart = '\u2665';
   var idd = $(this).attr('id');
   const buttone = this.textContent;
   var status = $("#stat_fav_prod").val();
   if(buttone==whiteHeart) {
   document.getElementById('stat_fav_prod').value=1;
   status=1;
       this.textContent=blackHeart;
     } else {
     document.getElementById('stat_fav_prod').value=0;
     status=0;
       this.textContent=whiteHeart;
     }

   $.ajax({
                  url: "/ajax/add_fav_product",
                  method: 'POST',
                  cache: false,
                  contentType: 'application/json',
                  data: JSON.stringify({
                      favoriteProd: idd,
                      stat: status
                  }),
                  success: function () {
                    checkFavProducts();
                  }
              });

});

function checkFavProducts(){
var status = $("#stat_fav_prod").val();
console.log("stat_fav= "+status);
const blackHeart = '\u2665';
let out = document.querySelector('.fav');
if (status==1){
out.textContent=blackHeart;
}
}


function deletePurchaseSale(clicked_id){

    var idSale=document.getElementById(clicked_id).value
    console.log("Удалить эл="+idSale)

$.ajax({
               async : false,
               cache : false,
               type : 'POST',
               url : '/ajax/delete_purchase_sale',
               dataType : "json",
               data : {"idSale" : idSale},

               success : function(data) {
                   showPurchaseSale();
               }

           });
           showPurchaseSale();
}
function addPurchaseSale(clicked_id) {

    var product_id = clicked_id;
    var purchase_quant = $("#number-text").val();

    var postData = {
               "product_id": product_id,
               "purchase_quant": purchase_quant
               }

       $.ajax({
               async : false,
               cache : false,
               type : 'POST',
               url : '/ajax/add_purchase_sale',
               dataType : "json",
               data : postData,

               success : function(data) {

               }

           });
           alert("Товар добавлен в корзину");


}



