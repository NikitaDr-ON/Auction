$(document).ready(function (){
    showLots();
    showAllLots();
    showAuctions();
    showVintage() ;
    showAntic();
    showHandmade();
    showJewelry();
    showJewelry();
    showCollectable();
    ShowUser();
});

function showLots() {
    $.get('/ajax/get_lots', function (data){
		console.log(data);
		
        let table = "<table> <tr><th>Название</th><th>Описание</th><th>Фото</th><th>Категория</th><th>Стартовая цена</th>"

        for (i = 0; i<data.length; i++){
            table = table + "<tr><td>" + data[i].product +"</td><td>" + data[i].description + "</td><td>"+ data[i].photo + "</td><td>" 
            + data[i].category+"</td><td>" + data[i].startCost
            +"</td><td><label for=\"dateStart\">Время начала аукциона:</label><input type=\"date\" id=\"dateStart"+data[i].id+"\">"
            +"</td><td><label for=\"dateEnd\">Время завершения аукциона:</label><input type=\"date\" id=\"dateEnd"+data[i].id+"\">"
            +"</td><td><button class=\"btn btn-primary\" onclick=\"addAuction(this.id);\" id=\""+data[i].id+"\">Назначить аукцион</button></td></tr>";
        }
        table = table + "</table>";
        $("#test_database").html(table);
        $("table").addClass("table");
    });
}
function showAuctions() {
    $.get('/ajax/show_auctions', function (data){
		console.log(data);
		
        let table = "<table> <tr><th>ID продукта</th><th>Цена</th><th>Начало аукциона</th><th>Конец аукциона</th>"

        for (i = 0; i<data.length; i++){
            table = table + "<tr><td>" + data[i].lot +"</td><td>" + data[i].cost + "</td><td>"+ data[i].start + "</td><td>" 
            + data[i].end+"</td></tr>";
        }
        table = table + "</table>";
        $("#list_auction").html(table);
        $("table").addClass("table");
    });
}

function showAllLots() {
    $.get('/ajax/get_all_lots', function (data){
		console.log(data);
		
        let table = "<table> <tr><th>Название</th><th>Продавец</th><th>Описание</th><th>Фото</th><th>Категория</th><th>Стартовая цена</th>"
        for (i = 0; i<data.length; i++){
            table = table + "<tr><td>" + data[i].product +"</td><td>" + data[i].seller+"</td><td>" + data[i].description + "</td><td>" + data[i].photo + "</td><td>"+ data[i].category+"</td><td>"
            table = table + "<tr><td>" + data[i].product +"</td><td>" + data[i].seller+"</td><td>" + data[i].description + "</td><td>" + data[i].category+"</td><td>"
            + data[i].startCost+"</td><td><button class=\"btn btn-primary\" onclick=\"addFavLot();\" id=\""+data[i].id+"\" >Добавить в избранное</button>"
            +"</td><td><a href='/index/id=" + data[i].id + "' class=\"btn btn-primary\" id=\""+data[i].id+"\">Сделать ставку</a></td></tr>";
            
        }
        table = table + "</table>";
        $("#test_database_all").html(table);
        $("table").addClass("table");
    });
}
function addLot() {
    $.ajax({
        url: "/ajax/add_lot",
        method: 'POST',
        cache: false,
        contentType: 'application/json',
        data: JSON.stringify({
            product: $("#product").val(),
            startCost: $("#startCost").val(),
            seller: $("#seller").val(),
            description:$("#description").val(),
            photo:$("#photo").val(),
            category:$("#categoryId").val()
        }),
        success: function () {
            showLots()
        }

    });
}
function addAuction(clicked_id) {
	let dateStartID="dateStart"+clicked_id;
	var objStart = document.getElementById(dateStartID).value;
	let dateEndID="dateEnd"+clicked_id;
	var objEnd = document.getElementById(dateEndID).value;

    $.ajax({
        url: "/ajax/add_auction",
        method: 'POST',
        cache: false,
        contentType: 'application/json',
        data: JSON.stringify({
            lot: clicked_id,
            start: objStart,
            end: objEnd           
        }),
        success: function () {
            showLots()
        }

    });
}
function takeRate() {

    $.ajax({
        url: "/ajax/take_rate",
        method: 'POST',
        cache: false,
        contentType: 'application/json',
        data: JSON.stringify({
			lot:$("#id_product").val(),
            cost:$("#rate").val(),
            start: null,
            end:null
                    
        }),
        success: function () {
      		console.log(1)
        }

    });
}

function showVintage() {
     $.get('/ajax/get_vintage', function (data){
		console.log(data);

                let table = "<table> <tr><th>Название</th><th>Продавец</th><th>Описание</th><th>Категория</th><th>Стартовая цена</th>"

                for (i = 0; i<data.length; i++){
                    table = table + "<tr><td>" + data[i].product +"</td><td>" + data[i].seller+"</td><td>" + data[i].description + "</td><td>" + "vintage"+"</td><td>"
                    + data[i].startCost+"</td><td><button class=\"btn btn-primary\" onclick=\"addFavLot();\" id=\""+data[i].id+"\" >Добавить в избранное</button>"
                    +"</td><td><button class=\"btn btn-primary\" onclick=\"takeStavka();\" id=\""+data[i].id+"\">Сделать ставку</button></td></tr>";

                }
                table = table + "</table>";
                $("#test_database_vintage").html(table);
                $("table").addClass("table");
            });
}
function showAntic() {
     $.get('/ajax/get_antic', function (data){
		console.log(data);

                let table = "<table> <tr><th>Название</th><th>Продавец</th><th>Описание</th><th>Категория</th><th>Стартовая цена</th>"

                for (i = 0; i<data.length; i++){
                    table = table + "<tr><td>" + data[i].product +"</td><td>" + data[i].seller+"</td><td>" + data[i].description + "</td><td>" + "antiques"+"</td><td>"
                    + data[i].startCost+"</td><td><button class=\"btn btn-primary\" onclick=\"addFavLot();\" id=\""+data[i].id+"\" >Добавить в избранное</button>"
                    +"</td><td><button class=\"btn btn-primary\" onclick=\"takeStavka();\" id=\""+data[i].id+"\">Сделать ставку</button></td></tr>";

                }
                table = table + "</table>";
                $("#test_database_antic").html(table);
                $("table").addClass("table");
            });
}
function showHandmade() {
     $.get('/ajax/get_handmade', function (data){
		console.log(data);

                let table = "<table> <tr><th>Название</th><th>Продавец</th><th>Описание</th><th>Категория</th><th>Стартовая цена</th>"

                for (i = 0; i<data.length; i++){
                    table = table + "<tr><td>" + data[i].product +"</td><td>" + data[i].seller+"</td><td>" + data[i].description + "</td><td>" + "handmade"+"</td><td>"
                    + data[i].startCost+"</td><td><button class=\"btn btn-primary\" onclick=\"addFavLot();\" id=\""+data[i].id+"\" >Добавить в избранное</button>"
                    +"</td><td><button class=\"btn btn-primary\" onclick=\"takeStavka();\" id=\""+data[i].id+"\">Сделать ставку</button></td></tr>";

                }
                table = table + "</table>";
                $("#test_database_handmade").html(table);
                $("table").addClass("table");
            });
}
function showJewelry() {
     $.get('/ajax/get_drag', function (data){
		console.log(data);

                let table = "<table> <tr><th>Название</th><th>Продавец</th><th>Описание</th><th>Категория</th><th>Стартовая цена</th>"

                for (i = 0; i<data.length; i++){
                    table = table + "<tr><td>" + data[i].product +"</td><td>" + data[i].seller+"</td><td>" + data[i].description + "</td><td>" + "jewelry"+"</td><td>"
                    + data[i].startCost+"</td><td><button class=\"btn btn-primary\" onclick=\"addFavLot();\" id=\""+data[i].id+"\" >Добавить в избранное</button>"
                    +"</td><td><button class=\"btn btn-primary\" onclick=\"takeStavka();\" id=\""+data[i].id+"\">Сделать ставку</button></td></tr>";

                }
                table = table + "</table>";
                $("#test_database_jewelry").html(table);
                $("table").addClass("table");
            });
}
function showCollectable() {
     $.get('/ajax/get_collectable', function (data){
		console.log(data);

                let table = "<table> <tr><th>Название</th><th>Продавец</th><th>Описание</th><th>Категория</th><th>Стартовая цена</th>"

                for (i = 0; i<data.length; i++){
                    table = table + "<tr><td>" + data[i].product +"</td><td>" + data[i].seller+"</td><td>" + data[i].description + "</td><td>" + "collectable"+"</td><td>"
                    + data[i].startCost+"</td><td><button class=\"btn btn-primary\" onclick=\"addFavLot();\" id=\""+data[i].id+"\" >Добавить в избранное</button>"
                    +"</td><td><button class=\"btn btn-primary\" onclick=\"takeStavka(qwe);\" id=\""+data[i].id+"\">Сделать ставку</button></td></tr>";

                }
                table = table + "</table>";
                $("#test_database_collectable").html(table);
                $("table").addClass("table");
            });
}
function ShowUser() {
     $.get('/ajax/get_user', function (data){
		console.log(data);
		console.log(data.lots[0].product)
		let table = "<table> <tr><th>Название</th><th>Продавец</th><th>Описание</th><th>Категория</th><th>Стартовая цена</th>"

                        for (i = 0; i<data.lots.length; i++){
                            table = table + "<tr><td>" + data.lots[i].product +"</td><td>" +  data.lots[i].seller+"</td><td>" +  data.lots[i].description + "</td><td>" + "collectable"+"</td><td>"
                            +  data.lots[i].startCost+"</td><td><button class=\"btn btn-primary\" onclick=\"takeStavka(qwe);\" id=\""+ data.lots[i].id+"\">Сделать ставку</button></td></tr>";

                        }
                        table = table + "</table>";
                        $("#show_user").html(table);
                        $("table").addClass("table");
            });
}
