$(document).ready(function (){
    showAllProducts();
    showFavProducts();
    showPurchaseSale();
    checkFavProducts();
    //showMessage();

    showVintage() ;
    showAntic();
    showHandmade();
    showJewelry();
    showCollectable();

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
    	    var idService=null
            var checkboxes = document.querySelectorAll('input[type=checkbox]:checked')

            idService=document.querySelector('input[name="service"]:checked').value;

            for (var i = 0; i < checkboxes.length; i++) {
              array.push(checkboxes[i].value)
            }
            if(array.length==0 || idService==null){
            alert("Товар/товары или сервис для доставки не выбраны!")}
            else{
            console.log(array);

            var postData = {
                           arraySale: array,
                           "service": idService
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

});
/*
function showMessage(){
$.get('/ajax/show_message', function (data){
		console.log(data);
		document.getElementById("messageSum").value=data;
    });
}
*/

function showAllProducts(){
$.get('/ajax/get_all_products', function (data){
		console.log(data);

let cardItem = " "

let out = document.getElementById('out')
for (i = 0; i<data.length; i++){
    cardItem = cardItem + "<div class=\"card\">"
    +"<div style=\"border:2px solid #ccc;width: 300px; color:#000000\">"
    +"<p>"+data[i].id+"</p>"
    +"<p>"+data[i].name+"</p>"
    +"<p>"+data[i].description+"</p>"
    +"<img src=\""+data[i].photo+"\" width=\"297\" height=\"297\">"
    +"<p>"+data[i].category+"</p>"
    +"<p>"+data[i].price+"</p>"
    +"<p>"+data[i].quant+"</p>"
    +"<p>"+data[i].seller+"</p>"
    +"<p><a href='/index/id="+data[i].id+"' class=\"btn btn-primary\" >Подробнее о товаре...</a></p>"
    +"</div></div>";
}
$("#out").html(cardItem);

});
}
function showPurchaseSale(){

/*Список товаров из корзины*/

$.get('/ajax/get_goods_purchase_sale', function (data){
		console.log(data);

let cardItem = " "

let out = document.getElementById('basket_purchase')
for (i = 0; i<data.length; i++){
    cardItem = cardItem + "<div class=\"card\">"
    +"<div style=\"border:2px solid #ccc;width: 300px; color:#000000\">"
    +"<p>"+data[i].id+"</p>"
    +"<p>"+data[i].name+"</p>"
    +"<p>"+data[i].description+"</p>"
    +"<img src=\""+data[i].photo+"\" width=\"297\" height=\"297\">"
    +"<p>"+data[i].category+"</p>"
    +"<p>"+data[i].price+"</p>"
    +"<p>"+data[i].quant+"</p>"
    +"<p>"+data[i].seller+"</p>"
    +"<div><label>Количество в корзине:  </label><output class=\"quantFromPS\" id=\"qps"+data[i].id+"\"></output></div>"
    +"<p><input class=\"checkPS\" name=\"goods\" id=\"chps"+data[i].id+"\" value=\"0\" type=\"checkbox\"></p>"
    +"<p><a href='/index/id="+data[i].id+"' class=\"btn btn-primary\" >Изменить количество товара</a></p>"
    +"<p><form action=\"#\" target=\"_self\"><button onclick=\"deletePurchaseSale(this.id);\" class=\"delClass\" id=\"deleteId"+data[i].id+"\" value=\"1\" >Удалить из корзины</button></form></p>"
    +"</div></div>";
}
$("#basket_purchase").html(cardItem);

});

/*Список способов доставки*/

$.get('/ajax/get_services', function (data){
		console.log(data);

let cardItem = " "

let out = document.getElementById('cont-service')
for (i = 0; i<data.length; i++){
    cardItem = cardItem + "<div><input type=\"radio\" name=\"service\" id=\"service\" value=\""+data[i].id+"\">"
                +"<label>"+data[i].name+"</label></div>";
}
$("#cont-service").html(cardItem);
});

/*Вывод количества товаров и idSale из корзины*/

$.get('/ajax/get_purchase', function (data){
		console.log(data);

var quantFromPS = document.getElementsByClassName('quantFromPS');
var idSale = document.getElementsByClassName('checkPS');
var delSale=document.getElementsByClassName('delClass');
for (var i=0; i< quantFromPS.length; i++ ) {
  const qps =quantFromPS[i].id;
  const cps=idSale[i].id;
  const dps=delSale[i].id;

  var quant=data[i].purchase_quant;
  var sale=data[i].id_sale;


  document.getElementById(dps).value=sale;
  document.getElementById(qps).value=quant;
  document.getElementById(cps).value=sale;
}

});
}





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


function showFavProducts(){
$.get('/ajax/get_fav_products', function (data){
		console.log(data);

let cardItem = " "

let out = document.getElementById('out_fav')
for (i = 0; i<data.length; i++){
    cardItem = cardItem + "<div class=\"card\">"
    +"<div style=\"border:2px solid #ccc;width: 300px; color:#000000\">"
    +"<p>"+data[i].id+"</p>"
    +"<p>"+data[i].name+"</p>"
    +"<p>"+data[i].description+"</p>"
    +"<img src=\""+data[i].photo+"\" width=\"297\" height=\"297\">"
    +"<p>"+data[i].category+"</p>"
    +"<p>"+data[i].price+"</p>"
    +"<p>"+data[i].quant+"</p>"
    +"<p>"+data[i].seller+"</p>"
    +"<p><a href='/index/id="+data[i].id+"' class=\"btn btn-primary\" >Подробнее о товаре...</a></p>"
    +"</div></div>";
}
$("#out_fav").html(cardItem);

});
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
}
function addPurchaseSale(clicked_id) {

    var product_id = clicked_id;
    var purchase_quant = $("#number-text").val();

    var postData = {
               "product_id": product_id,
               "purchase_quant": purchase_quant
               }

          /* Ключ: тип Ajax, url, dataType, атрибуты данных */
       $.ajax({
               async : false,
               cache : false,
               type : 'POST',
               url : '/ajax/add_purchase_sale',
               dataType : "json",
               data : postData,

               success : function(data) {
                   alert(data);
               }

           });

}

function showVintage() {
     $.get('/ajax/get_vintage', function (data){
		console.log(data);

        let cardItem = " "

        let out = document.getElementById('vintag')
        for (i = 0; i<data.length; i++){
            cardItem = cardItem + "<div class=\"card\">"
            +"<div style=\"border:2px solid #ccc;width: 300px; color:#000000\">"
            +"<p>"+data[i].id+"</p>"
            +"<p>"+data[i].name+"</p>"
            +"<p>"+data[i].description+"</p>"
            +"<img src=\""+data[i].photo+"\" width=\"297\" height=\"297\">"
            +"<p>"+data[i].category+"</p>"
            +"<p>"+data[i].price+"</p>"
            +"<p>"+data[i].quant+"</p>"
            +"<p>"+data[i].seller+"</p>"
            +"<p><a href='/index/id="+data[i].id+"' class=\"btn btn-primary\" >Подробнее о товаре...</a></p>"
            +"</div></div>";
        }
        $("#vintag").html(cardItem);

        });
    }

function showAntic() {
     $.get('/ajax/get_antic', function (data){
		console.log(data);

        let cardItem = " "

        let out = document.getElementById('antic')
        for (i = 0; i<data.length; i++){
            cardItem = cardItem + "<div class=\"card\">"
            +"<div style=\"border:2px solid #ccc;width: 300px; color:#000000\">"
            +"<p>"+data[i].id+"</p>"
            +"<p>"+data[i].name+"</p>"
            +"<p>"+data[i].description+"</p>"
            +"<img src=\""+data[i].photo+"\" width=\"297\" height=\"297\">"
            +"<p>"+data[i].category+"</p>"
            +"<p>"+data[i].price+"</p>"
            +"<p>"+data[i].quant+"</p>"
            +"<p>"+data[i].seller+"</p>"
            +"<p><a href='/index/id="+data[i].id+"' class=\"btn btn-primary\" >Подробнее о товаре...</a></p>"
            +"</div></div>";
        }
        $("#antic").html(cardItem);

        });
    }
function showHandmade() {
     $.get('/ajax/get_handmade', function (data){
		console.log(data);

                let cardItem = " "

                for (i = 0; i<data.length; i++){
                    cardItem = cardItem + "<div class=\"card\">"
                    +"<div style=\"border:2px solid #ccc;width: 300px; color:#000000\">"
                    +"<p>"+data[i].id+"</p>"
                    +"<p>"+data[i].name+"</p>"
                    +"<p>"+data[i].description+"</p>"
                   +"<img src=\""+data[i].photo+"\" width=\"297\" height=\"297\">"
                    +"<p>"+data[i].category+"</p>"
                    +"<p>"+data[i].price+"</p>"
                    +"<p>"+data[i].quant+"</p>"
                    +"<p>"+data[i].seller+"</p>"
                    +"<p><a href='/index/id="+data[i].id+"' class=\"btn btn-primary\" >Подробнее о товаре...</a></p>"
                    +"</div></div>";
                }
                $("#handmade").html(cardItem);

                });
}
function showJewelry() {
     $.get('/ajax/get_drag', function (data){
		console.log(data);

                        let cardItem = " "

                        for (i = 0; i<data.length; i++){
                            cardItem = cardItem + "<div class=\"card\">"
                            +"<div style=\"border:2px solid #ccc;width: 300px; color:#000000\">"
                            +"<p>"+data[i].id+"</p>"
                            +"<p>"+data[i].name+"</p>"
                            +"<p>"+data[i].description+"</p>"
                           +"<img src=\""+data[i].photo+"\" width=\"297\" height=\"297\">"
                            +"<p>"+data[i].category+"</p>"
                            +"<p>"+data[i].price+"</p>"
                            +"<p>"+data[i].quant+"</p>"
                            +"<p>"+data[i].seller+"</p>"
                            +"<p><a href='/index/id="+data[i].id+"' class=\"btn btn-primary\" >Подробнее о товаре...</a></p>"
                            +"</div></div>";
                        }
                        $("#drag").html(cardItem);

                        });
}
function showCollectable() {
     $.get('/ajax/get_collectable', function (data){
		console.log(data);

                                let cardItem = " "

                                for (i = 0; i<data.length; i++){
                                    cardItem = cardItem + "<div class=\"card\">"
                                    +"<div style=\"border:2px solid #ccc;width: 300px; color:#000000\">"
                                    +"<p>"+data[i].id+"</p>"
                                    +"<p>"+data[i].name+"</p>"
                                    +"<p>"+data[i].description+"</p>"
                                   +"<img src=\""+data[i].photo+"\" width=\"297\" height=\"297\">"
                                    +"<p>"+data[i].category+"</p>"
                                    +"<p>"+data[i].price+"</p>"
                                    +"<p>"+data[i].quant+"</p>"
                                    +"<p>"+data[i].seller+"</p>"
                                    +"<p><a href='/index/id="+data[i].id+"' class=\"btn btn-primary\" >Подробнее о товаре...</a></p>"
                                    +"</div></div>";
                                }
                                $("#collec").html(cardItem);

                                });



}

