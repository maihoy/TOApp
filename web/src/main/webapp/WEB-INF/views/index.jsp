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
</nav>
<div class="container" id="container" style="margin-top: 70px">
    <div class="well">
        <input type="button" id="userList" value="Get All Users" onclick="showPerson()"/><br/><br/>
        <div id="personResponse">
            <table class="table table-hover" id="user_list"></table>
            <ul id="pagination" class="pagination"></ul>
        </div>

        <h2>new </h2>
        <form id="newUserForm">
            <input type="hidden" name="id" id="id"/>
            <label for="userNameInput">username: </label>
            <input type="text" name="username" id="userNameInput"/>
            <br/>

            <label for="passInput">pass: </label>
            <input type="password" name="pass" id="passInput"/>
            <br/>

            <label for="userRole">role</label>
            <select id="userRole" name="role">
                <option selected value="CLIENT">Client</option>
                <option value="ADMIN">Admin</option>
            </select>
            <input type="submit" value="Save" id="saveUser"/><br/>
        </form>
    </div>


</div>


<script type="text/javascript">

    var nameInput = document.getElementById("userNameInput");
    var passInput = document.getElementById("passInput");
    var idInput = document.getElementById("id");
    var button = document.getElementById("saveUser");

    window.onload = getPagesCount;
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

        var data = {
            id: idInput.value,
            username: nameInput.value,
            password: passInput.value,
            role: document.getElementById("userRole").value
        };

        if (data.id == "") {
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

            xhr.open("PUT", '/user/' + data.id, true);
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
        button.value = "Save";

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

    function getUsersByGap(elem){
        var xhr = new XMLHttpRequest();

        xhr.open("GET", '/user/page-'+elem.text, true);
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

        button.value = "update";

        for (var key in user) {
            if (key === "id") {
                idInput.value = user[key]
            }
            if (key === "username") {
                nameInput.value = user[key]
            }
            if (key === "password") {
                passInput.value = user[key]
            }

        }

    }
    //creating table
    function showData(data) {

        user_list.innerHTML = "";

        var tableHead = {
            id: "id",
            username: "Имяяяу",
            password: "Пароль",
            role: "Роль"
        };

        var tr = document.createElement("tr");
        for (var key in tableHead) {
            var th = document.createElement("th");
            th.innerHTML = tableHead[key];
            tr.appendChild(th);
        }

        user_list.appendChild(tr);

        data.forEach(function (user) {
            var tr = document.createElement("tr");

            for (var key in user) {
                var td = document.createElement("td");
                td.innerHTML = user[key];
                tr.appendChild(td);
            }

            tr.appendChild(getTdEdit(user.id));
            tr.appendChild(getTdDelete(user.id));

            user_list.appendChild(tr);

            $('input').click(function () {
                return this.id;
            });
        });
    }

    function getTdEdit(userId) {
        var tdEdit = document.createElement("td");
        var editButton = document.createElement("input");
        editButton.type = "button";
        editButton.value = "Edit";
        editButton.id = userId;
        editButton.className = "editUserButton";
        editButton.onclick = function () {
            getByUserId(this)
        };
        tdEdit.appendChild(editButton);
        return tdEdit;
    }
    function getTdDelete(userId) {
        var tdDelete = document.createElement("td");
        var deleteButton = document.createElement("input");
        deleteButton.type = "button";
        deleteButton.value = "Delete";
        deleteButton.id = userId;
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
        alert(count);
        var i=0;
        while (i!= count) {
            var li = document.createElement("li");
            var a = document.createElement("a");
            a.onclick =function () {
                getUsersByGap(this)
            };
            a.text = i+1;
            li.appendChild(a);
            pageMenu.appendChild(li);
            i++;
        }

    }
</script>

</body>
</html>
