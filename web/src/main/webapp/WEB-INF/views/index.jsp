<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>TOApp</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">

        </div>
    </div>
    <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
            <input type="search" class="form-control light-table-filter input-sm" data-table="filtered"
                   placeholder="Search">
        </div>
    </form>
</nav>
<div class="container" id="container" style="margin-top: 70px">
    <div class="well">
        <input type="button" id="userList" value="Get All Users" onclick="showPerson()"/><br/><br/>
        <div class="table-responsive" id="personResponse">

            <table class="table table-hover filtered sortable" id="sortabletable"></table>
            <ul id="pagination" class="pagination"></ul>
        </div>
    </div>

    <div class="well">
        <h2>Новый пользователь </h2>
        <form id="newUserForm" class="form-horizontal ">
            <div class="container" style="width: 91%;">
                <input type="hidden" name="userId" id="userId"/>
                <input type="hidden" name="userDataId" id="userDataId">
                <div class="form-group col-md-6 " style="margin-right: 10px;">
                    <label for="userNameInput">Логин: </label>
                    <input type="text" name="username" id="userNameInput" class="form-control "/>
                </div>
                <div class="form-group col-md-6 " style="margin-right: 10px;">
                    <label for="passInput">Пароль: </label>
                    <input type="password" name="password" id="passInput" class="form-control"/>
                </div>
                <div class="form-group col-md-6 " style="margin-right: 10px;">
                    <label for="fNameInput">Имя: </label>
                    <input type="text" name="firstName" id="fNameInput" class="form-control"/>
                </div>
                <div class="form-group col-md-6 " style="margin-right: 10px;">
                    <label for="lNameInput">Фамилия: </label>
                    <input type="text" name="lastName" id="lNameInput" class="form-control"/>
                </div>
                <div class="form-group col-md-6 " style="margin-right: 10px;">
                    <label for="countryInput">Страна</label>
                    <select class="form-control" id="countryInput" name="country"
                            onchange="getCitiesById(this[this.selectedIndex].id)" on>
                    </select>
                </div>
                <div class="form-group col-md-6 " style="margin-right: 10px;">
                    <label for="cityInput">Город</label>
                    <select class="form-control" id="cityInput" name="city"
                            onchange="getStreetsById(this[this.selectedIndex].id)">
                    </select>
                </div>
                <div class="form-group col-md-6 " style="margin-right: 10px;">
                    <label for="streetInput">Улица</label>
                    <select class="form-control" id="streetInput" name="street">
                    </select>
                </div>

            </div>
            <div class="container" id="numbersContainer0" style="width: 91%;">
                <div class="form-group col-md-6 " style="margin-right: 10px;">
                    <label for="phoneInput">Номер телефона: </label>
                    <input id="phoneInput" type="text" class="form-control" name="phoneNum"
                           placeholder="Additional Info">
                </div>
                <div class="form-group col-md-6 " style="margin-right: 10px;">
                    <label for="tariffInput">Тариф</label>
                    <select class="form-control" id="tariffInput" name="tariff">
                    </select>
                </div>
                <div class="form-group col-md-6 " style="margin-right: 10px;">
                    <label for="serviceInput">Доп. услуги</label>
                    <select class="form-control" id="serviceInput" name="service" multiple>
                    </select>
                </div>
                <div class="form-group col-md-6 " style="margin-right: 10px;" id="duplicateButton">
                    <input style="margin-top: 10px;" class=" btn btn-default" type="button" value="dobavit eshe nomer"
                           onclick="duplicate()" /><br/>
                </div>
            </div>
            <div class="container" id="buttonSubmit" style="width: 91%;">
                <div class="form-group col-md-6 " style="margin-right: 10px;">
                    <input style="margin-top: 10px;" class=" btn btn-default" type="submit" value="Save"
                           id="saveUser"/><br/>
                </div>
            </div>
        </form>
    </div>


</div>


<script type="text/javascript">

    var nameInput = document.getElementById("userNameInput");
    var passInput = document.getElementById("passInput");
    var fNameInput = document.getElementById("fNameInput");
    var lNameInput = document.getElementById("lNameInput");
    var countrySelect = document.getElementById("countryInput");
    var citySelect = document.getElementById("cityInput");
    var streetSelect = document.getElementById("streetInput");
    var idInput = document.getElementById("userId");
    var userDataId = document.getElementById("userDataId");
    var saveButton = document.getElementById("saveUser");

    window.onload = init;

    function init() {
        getPagesCount();
        getAllCounties();
    }
    function deleteUser(elem) {

        var xhr = new XMLHttpRequest();
        xhr.open("DELETE", '/user/' + elem.id, true);
        xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
        xhr.send();
        alert("Deleted");
        showPerson();

    }

    function getByUserId(elem) {
        var xhr = new XMLHttpRequest();

        xhr.open("GET", '/user/' + elem.id, true);
        xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
        xhr.send();

        xhr.onreadystatechange = function () {
            if (this.readyState != 4) return;

            if (xhr.status != 200) {
                alert("Error!");
            } else {
                try {
                    var data = JSON.parse(xhr.responseText)
                } catch (e) {
                    alert("Incorrect answer!");
                }
                putInForm(data);
            }
        };
    }
    //new user or update exist
    newUserForm.onsubmit = function () {

        var xhr = new XMLHttpRequest();

        var street = {
            id: streetSelect[streetSelect.selectedIndex].id
        };

        var data = {
            userId: idInput.value,
            userDataId: userDataId.value,
            username: nameInput.value,
            password: passInput.value,
            firstName: fNameInput.value,
            lastName: lNameInput.value,
            street: street
        };
        alert(data);
        if ((data.userId == "") && (data.userDataId == "")) {
            var json = JSON.stringify(data);

            xhr.open("POST", '/user', true);
            xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');

            xhr.onreadystatechange = function () {
                if (this.readyState != 4) return;
            };

            xhr.send(json);
            showPerson();
        } else {
            json = JSON.stringify(data);

            xhr.open("PUT", '/user/' + data.userDataId, true);
            xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');

            xhr.onreadystatechange = function () {
                if (this.readyState != 4) return;
            };

            xhr.send(json);
            showPerson();
        }

        nameInput.value = "";
        passInput.value = "";
        idInput.value = "";
        saveButton.value = "Save";

        return false;

    };
    //get all users
    function showPerson() {
        var xhr = new XMLHttpRequest();

        xhr.open("GET", '/user', true);
        xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
        xhr.send();

        xhr.onreadystatechange = function () {
            if (this.readyState != 4) return;

            if (xhr.status != 200) {
                alert("Error!");
            } else {
                try {
                    var data = JSON.parse(xhr.responseText)
                } catch (e) {
                    alert("Incorrect answer!");
                }
                showData(data);
            }
        };
    }

    function getAllCounties() {
        var xhr = new XMLHttpRequest();

        xhr.open("GET", '/countries', true);
        xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
        xhr.send();

        xhr.onreadystatechange = function () {
            if (this.readyState != 4) return;

            if (xhr.status != 200) {
                alert("Error!");
            } else {
                try {
                    var data = JSON.parse(xhr.responseText)
                } catch (e) {
                    alert("Incorrect answer!");
                }
                fillCountries(data);
            }
        };
    }

    function fillCountries(data) {

        data.forEach(function (addressDto) {

            var option = document.createElement("option");
            option.innerHTML = addressDto.country.name;
            option.id = addressDto.country.id;
            countrySelect.appendChild(option);

        });

        getCitiesById(countrySelect[countrySelect.selectedIndex].id);
    }

    function getCitiesById(id) {
        var xhr = new XMLHttpRequest();

        xhr.open("GET", '/city-parent-' + id, true);
        xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
        xhr.send();

        xhr.onreadystatechange = function () {
            if (this.readyState != 4) return;

            if (xhr.status != 200) {
                alert("Error!");
            } else {
                try {
                    var data = JSON.parse(xhr.responseText)
                } catch (e) {
                    alert("Incorrect answer!");
                }
                fillCities(data);
            }
        };
    }

    function fillCities(data) {

        citySelect.innerHTML = "";

        data.forEach(function (addressDto) {

            var option = document.createElement("option");
            option.innerHTML = addressDto.city.name;
            option.id = addressDto.city.id;
            citySelect.appendChild(option);

        });
        getStreetsById(citySelect[citySelect.selectedIndex].id);
    }

    function getStreetsById(id) {
        var xhr = new XMLHttpRequest();

        xhr.open("GET", '/street-parent-' + id, true);
        xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
        xhr.send();

        xhr.onreadystatechange = function () {
            if (this.readyState != 4) return;

            if (xhr.status != 200) {
                alert("Error!");
            } else {
                try {
                    var data = JSON.parse(xhr.responseText)
                } catch (e) {
                    alert("Incorrect answer!");
                }
                fillStreet(data);
            }
        };
    }

    function fillStreet(data) {
        streetSelect.innerHTML = "";

        data.forEach(function (addressDto) {

            var option = document.createElement("option");
            option.innerHTML = addressDto.street.name;
            option.id = addressDto.street.id;
            streetSelect.appendChild(option);

        });
    }

    function getUsersByGap(elem) {

        var xhr = new XMLHttpRequest();

        xhr.open("GET", '/user/page-' + elem.text, true);
        xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
        xhr.send();

        xhr.onreadystatechange = function () {
            if (this.readyState != 4) return;

            if (xhr.status != 200) {
                alert("Error!");
            } else {
                try {
                    var data = JSON.parse(xhr.responseText)
                } catch (e) {
                    alert("Incorrect answer!");
                }
                showData(data);
            }
        };
    }
    function putInForm(user) {

        saveButton.value = "update";
        idInput.value = user.userId;
        userDataId.value = user.userDataId;
        nameInput.value = user.username;
        passInput.value = user.password;
        fNameInput.value = user.firstName;
        lNameInput.value = user.lastName;
        countrySelect.value = user.country.name;
        getCitiesById(user.country.id);
        citySelect.value = user.city.name;
        streetSelect.value = user.street.name;

    }
    //creating table
    function showData(data) {
        var thead = document.createElement("thead");
        var tbody = document.createElement("tbody");
        sortabletable.innerHTML = "";

        var tableHead = {
            username: "Логин",
            firstName: "Имя",
            lastName: "Фамилия",
            country: "Страна",
            city: "Город",
            street: "Улица"
        };

        var tr = document.createElement("tr");
        for (var key in tableHead) {
            var th = document.createElement("th");
            th.innerHTML = tableHead[key];
            tr.appendChild(th);
        }

        thead.appendChild(tr);
        sortabletable.appendChild(thead);

        data.forEach(function (user) {
            var tr = document.createElement("tr");
            tr.setAttribute("data-toggle", "collapse");
            tr.setAttribute("data-target", "#user" + user.userDataId);
            tr.id = user.userDataId;

            var username = document.createElement("td");
            username.innerHTML = user.username;
            tr.appendChild(username);

            var fName = document.createElement("td");
            fName.innerHTML = user.firstName;
            tr.appendChild(fName);

            var lName = document.createElement("td");
            lName.innerHTML = user.lastName;
            tr.appendChild(lName);

            var country = document.createElement("td");
            country.innerHTML = user.country.name;
            tr.appendChild(country);

            var city = document.createElement("td");
            city.innerHTML = user.city.name;
            tr.appendChild(city);

            var street = document.createElement("td");
            street.innerHTML = user.street.name;
            tr.appendChild(street);

            tr.appendChild(getTdEdit(user.userDataId));
            tr.appendChild(getTdDelete(user.userDataId));

            var trMoreInfo = document.createElement("tr");
            trMoreInfo.id = "user" + user.userDataId;
            trMoreInfo.className = "collapse";
            var tdMoreInfo = document.createElement("td");
            tdMoreInfo.colSpan = 8;
            trMoreInfo.appendChild(tdMoreInfo);
            var infoDiv = document.createElement("div");
            infoDiv.className = "panel panel-default";
            tdMoreInfo.appendChild(infoDiv);
            var infoHeader = document.createElement("div");
            infoHeader.className = "panel-heading";
            infoDiv.appendChild(infoHeader);
            var infoBody = document.createElement("div");
            infoBody.className = "panel-body";
            infoDiv.appendChild(infoBody);

            user.userTariffs.forEach(function (tariff) {
                //
                var phoneNum = document.createElement("td");
                phoneNum.innerHTML = tariff.phoneNum;
                phoneNum.style = "display:none;";
                tr.appendChild(phoneNum);
                //
                var numHeader = document.createElement("h6");
                numHeader.name = "phoneNum";
                numHeader.value = tariff.phoneNum;
                numHeader.innerHTML = tariff.phoneNum;
                numHeader.style = "margin:0;";

                infoHeader.appendChild(numHeader);

                var tariffCol = document.createElement("div");
                tariffCol.className = "col-xs-6 col-md-4";
                var serviceCol = document.createElement("div");
                serviceCol.className = "col-xs-6 col-md-4";
                var tarName = document.createElement("p");
                tarName.className = "lead text-center";
                tarName.style = "font-size: 18px;";
                tarName.innerHTML = tariff.tariff.name;

                var connCost = document.createElement("p");
                connCost.innerHTML = "Стоимость подключения: " + tariff.tariff.connCost + "p.";

                var subFee = document.createElement("p");
                subFee.innerHTML = "Ежемесячная плата: " + tariff.tariff.subFee + "p.";

                var callCost = document.createElement("p");
                callCost.innerHTML = "Стоимость звонков: " + tariff.tariff.callCost + "p.";

                var smsCost = document.createElement("p");
                smsCost.innerHTML = "Стоимость смс: " + tariff.tariff.smsCost + "p.";

                var internetCost = document.createElement("p");
                internetCost.innerHTML = "Стоимость интернет: " + tariff.tariff.internetCost + "p.";

                tariffCol.appendChild(tarName);
                tariffCol.appendChild(connCost);
                tariffCol.appendChild(subFee);
                tariffCol.appendChild(callCost);
                tariffCol.appendChild(smsCost);
                tariffCol.appendChild(internetCost);
                infoBody.appendChild(tariffCol);
                tariff.service.forEach(function (service) {

                    var servDiv = document.createElement("div");
                    servDiv.className = "media-body";
                    servDiv.style = "padding-top: 15px";

                    var servName = document.createElement("h4");
                    servName.className = "media-heading";

                    servName.innerHTML = service.name;

                    servDiv.appendChild(servName);

                    servDiv.append(service.description);

                    serviceCol.appendChild(servDiv);

                });
                infoBody.appendChild(serviceCol);
            });

            tbody.appendChild(tr);
            tbody.appendChild(trMoreInfo);
            sortabletable.appendChild(tbody);

            $('input').click(function () {
                return this.id;
            });
        });
    }

    function getTdEdit(id) {
        var tdEdit = document.createElement("td");
        var editButton = document.createElement("input");
        editButton.type = "button";
        editButton.value = "Edit";
        editButton.id = id;
        editButton.className = "editUserButton";
        editButton.onclick = function () {
            getByUserId(this)
        };
        tdEdit.appendChild(editButton);
        return tdEdit;
    }
    function getTdDelete(id) {
        var tdDelete = document.createElement("td");
        var deleteButton = document.createElement("input");
        deleteButton.type = "button";
        deleteButton.value = "Delete";
        deleteButton.id = id;
        deleteButton.className = "deleteUserButton";
        deleteButton.onclick = function () {
            deleteUser(this)
        };
        tdDelete.appendChild(deleteButton);
        return tdDelete;
    }
    function getPagesCount() {
        var xhr = new XMLHttpRequest();

        xhr.open("GET", '/user/pages', true);
        xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
        xhr.send();

        xhr.onreadystatechange = function () {
            if (this.readyState != 4) return;

            if (xhr.status != 200) {
                alert("Error!");
            } else {
                try {
                    var count = xhr.responseText;

                } catch (e) {
                    alert("Incorrect answer!");
                }
                printPagination(count);
            }
        };
    }

    function printPagination(count) {
        var pageMenu = document.getElementById("pagination");
        var i = 0;
        while (i != count) {
            var li = document.createElement("li");
            var a = document.createElement("a");
            a.onclick = function () {
                getUsersByGap(this)
            };
            a.text = i + 1;
            li.appendChild(a);
            pageMenu.appendChild(li);
            i++;
        }

    }
    var i = 0;

    function duplicate() {
        var original = document.getElementById("numbersContainer" + i);
        var clone = original.cloneNode(true);
        clone.id = "numbersContainer" + ++i;
        original.parentNode.insertBefore(clone,document.getElementById("buttonSubmit"));
        original.removeChild(document.getElementById("duplicateButton"));
        //clone.duplicateButton.onclick = duplicate;
        clearChildren(document.getElementById(clone.id));
    }

    function clearChildren(element) {
        for (var i = 0; i < element.childNodes.length; i++) {
            var e = element.childNodes[i];
            if (e.tagName) switch (e.tagName.toLowerCase()) {
                case 'input':
                    switch (e.type) {
                        case "radio":
                        case "checkbox": e.checked = false; break;
                        case "button":
                        case "submit":
                        case "image": break;
                        default: e.value = ''; break;
                    }
                    break;
                case 'select': e.selectedIndex = 0; break;
                case 'textarea': e.innerHTML = ''; break;
                default: clearChildren(e);
            }
        }
    }

</script>


<script type="text/javascript">
    (function (document) {
        'use strict';

        var LightTableFilter = (function (Arr) {

            var _input;

            function _onInputEvent(e) {
                _input = e.target;
                var tables = document.getElementsByClassName(_input.getAttribute('data-table'));
                Arr.forEach.call(tables, function (table) {
                    Arr.forEach.call(table.tBodies, function (tbody) {
                        Arr.forEach.call(tbody.rows, _filter);
                    });
                });
            }

            function _filter(row) {
                var text = row.textContent.toLowerCase(), val = _input.value.toLowerCase();
                row.style.display = text.indexOf(val) === -1 ? 'none' : 'table-row';
            }

            return {
                init: function () {

                    var inputs = document.getElementsByClassName('light-table-filter');
                    Arr.forEach.call(inputs, function (input) {

                        input.oninput = _onInputEvent;
                    });
                }
            };
        })(Array.prototype);

        document.addEventListener('readystatechange', function () {
            if (document.readyState === 'complete') {
                LightTableFilter.init();
            }
        });

    })(document);
</script>

</body>
</html>
