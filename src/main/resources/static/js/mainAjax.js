$(document).ready(function (){
    showLots();
    showAllLots();
    showVintage() ;
    showAntic();
    showHandmade();
    showJewelry();
    showJewelry();
    showCollectable();
});

function showLots() {
    $.get('/ajax/get_lots', function (data){
        let table = "<table> <tr><th>Название</th><th>Описание</th><th>Категория</th><th>Стартовая цена</th>"

        for (i = 0; i<data.length; i++){
                   table = table + "<tr><td>" + data[i].product +"</td><td>" + data[i].description + "</td><td>" + data[i].category+"</td><td>"
                   + data[i].startCost+"</td></tr>";
               }
        table = table + "</table>";
        $("#test_database").html(table);
        $("table").addClass("table");
    });
}
function showAllLots() {
    $.get('/ajax/get_all_lots', function (data){
		console.log(data);
        let table = "<table> <tr><th>Название</th><th>Продавец</th><th>Описание</th><th>Категория</th><th>Стартовая цена</th>"

        for (i = 0; i<data.length; i++){
            table = table + "<tr><td>" + data[i].product +"</td><td>" + data[i].seller+"</td><td>" + data[i].description + "</td><td>" + data[i].category+"</td><td>"
            + data[i].startCost+"</td><td><button class=\"btn btn-primary\" onclick=\"addFavLot();\" id=\""+data[i].id+"\" >Добавить в избранное</button>"
            +"</td><td><button class=\"btn btn-primary\" onclick=\"takeStavka();\" id=\""+data[i].id+"\">Сделать ставку</button></td></tr>";
            
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
function takeStavka(name)
{
console.log(name)
}