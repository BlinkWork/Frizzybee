/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function comfirm(id) {
    $.ajax({
        url: '/FrizzyBee/order-management?event=confirm', // Trang JSP x? lý yêu c?u xác nh?n
        method: 'POST', // Ho?c 'GET' tùy thu?c vào ph??ng th?c c?a trang x? lý
        data: {order_id: id}, // D? li?u g?i kèm yêu c?u, ví d? nh? ID c?a ??n hàng
        success: function () {
            // X? lý k?t qu? tr? v? t? trang x? lý
            document.getElementById(id).textContent = "Delivery";
            document.getElementById(id).removeEventListener("click", comfirm);
            document.getElementById(id).setAttribute("onclick", "delivery(" + id + ")");
            const statusElements = document.querySelectorAll('.status' + id);
            statusElements.forEach(element => {
                element.textContent = "comfirm";
            });
        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    });
}
function delivery(id) {
    $.ajax({
        url: '/FrizzyBee/order-management?event=delivery', // Trang JSP x? lý yêu c?u xác nh?n
        method: 'POST', // Ho?c 'GET' tùy thu?c vào ph??ng th?c c?a trang x? lý
        data: {order_id: id}, // D? li?u g?i kèm yêu c?u, ví d? nh? ID c?a ??n hàng
        success: function () {
            // X? lý k?t qu? tr? v? t? trang x? lý
            document.getElementById(id).textContent = "Delivered";
            document.getElementById(id).style = "color: silver";
            const statusElements = document.querySelectorAll('.status' + id);
            statusElements.forEach(element => {
                element.textContent = "delivered";
            });
            const today = new Date();
            const receivedDateElements = document.querySelectorAll('.received-date' + id);
            receivedDateElements.forEach(element => {
                element.textContent = today.toLocaleDateString('vi-VN', {year: 'numeric', month: 'numeric', day: 'numeric'});
            });
        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    });
}

// JavaScript
const navItems = document.querySelectorAll('.nav-item');


function allOrder(page) {
    // Bỏ lớp 'active' khỏi tất cả các phần tử 'nav-item'
    navItems.forEach(item => {
        item.classList.remove('active');
    });
    // Thêm lớp 'active' cho phần tử được click
    document.getElementById('allOrder').classList.add('active');

    $.ajax({
        url: '/FrizzyBee/order-management?event=all-order', // Trang JSP x? lý yêu c?u xác nh?n
        method: 'GET', // Ho?c 'GET' tùy thu?c vào ph??ng th?c c?a trang x? lý
        data: {page: page,
            status: "all"
        }, // D? li?u g?i kèm yêu c?u, ví d? nh? ID c?a ??n hàng
        success: function (response) {
            // X? lý k?t qu? tr? v? t? trang x? lý
            document.getElementById("order-list").innerHTML = response;
        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    });
}

function waitConfirm(page) {
    // Bỏ lớp 'active' khỏi tất cả các phần tử 'nav-item'
    navItems.forEach(item => {
        item.classList.remove('active');
    });

    // Thêm lớp 'active' cho phần tử được click
    document.getElementById('wait-confirm').classList.add('active');
    $.ajax({
        url: '/FrizzyBee/order-management?event=all-order', // Trang JSP x? lý yêu c?u xác nh?n
        method: 'GET', // Ho?c 'GET' tùy thu?c vào ph??ng th?c c?a trang x? lý
        data: {page: page,
            status: "unconfimred"
        }, // D? li?u g?i kèm yêu c?u, ví d? nh? ID c?a ??n hàng
        success: function (response) {
            // X? lý k?t qu? tr? v? t? trang x? lý
            document.getElementById("order-list").innerHTML = response;
        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    });
}

function waitDelivery(page) {
    // Bỏ lớp 'active' khỏi tất cả các phần tử 'nav-item'
    navItems.forEach(item => {
        item.classList.remove('active');
    });

    // Thêm lớp 'active' cho phần tử được click
    document.getElementById('wait-delivery').classList.add('active');
    $.ajax({
        url: '/FrizzyBee/order-management?event=all-order', // Trang JSP x? lý yêu c?u xác nh?n
        method: 'GET', // Ho?c 'GET' tùy thu?c vào ph??ng th?c c?a trang x? lý
        data: {page: page,
            status: "confirm"
        }, // D? li?u g?i kèm yêu c?u, ví d? nh? ID c?a ??n hàng
        success: function (response) {
            // X? lý k?t qu? tr? v? t? trang x? lý
            document.getElementById("order-list").innerHTML = response;
        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    });
}

function delivered(page) {
    // Bỏ lớp 'active' khỏi tất cả các phần tử 'nav-item'
    navItems.forEach(item => {
        item.classList.remove('active');
    });

    // Thêm lớp 'active' cho phần tử được click
    document.getElementById('delivered').classList.add('active');
    $.ajax({
        url: '/FrizzyBee/order-management?event=all-order', // Trang JSP x? lý yêu c?u xác nh?n
        method: 'GET', // Ho?c 'GET' tùy thu?c vào ph??ng th?c c?a trang x? lý
        data: {page: page,
            status: "deliveried"
        }, // D? li?u g?i kèm yêu c?u, ví d? nh? ID c?a ??n hàng
        success: function (response) {
            // X? lý k?t qu? tr? v? t? trang x? lý
            document.getElementById("order-list").innerHTML = response;
        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    });
}

function getOrders(status, pageNumber, username, sort) {
    $.ajax({
        url: "/FrizzyBee/order-management?event=all-order",
        type: "GET",
        data: {
            status: status,
            page: pageNumber,
            username: username,
            sort: sort
        },
        success: function (response) {
            document.getElementById("order-list").innerHTML = response;
        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    });
}

$(document).ready(function () {
    // Mặc định hiển thị trang đầu tiên, mỗi trang 10 order, sắp xếp theo thời gian mới nhất
    var pageNumber = 1;
    var status = "";
    var sort = "DESC";
    var username = "";

    // Gọi hàm để lấy danh sách order ban đầu
    getOrders(status, pageNumber, username, sort);

    // Xử lý sự kiện khi người dùng thay đổi trường sắp xếp
    $("#sort-by").on("change", function () {
        sort = $(this).val();
        pageNumber = 1; // Reset lại trang về 1 khi thay đổi trường sắp xếp
        getOrders(status, pageNumber, username, sort);
    });
    //  Xử lý sự kiện khi người dùng search
    $("#search-username").on("input", function () {
        username = $(this).val(); // Lấy giá trị từ trường search
        pageNumber = 1; // Reset lại trang về 1 khi thay đổi từ khóa search
        getOrders(status, pageNumber, username, sort); // Gọi hàm để lấy danh sách order tương ứng với từ khóa search
    });
    // Xử lý sự kiện khi người dùng nhấn vào nút phân trang
    $(document).on("click", ".pagination-link", function () {
        // Loại bỏ style của các nút phân trang trước đó
        $(".pagination-link").css("background", "");
        $(".pagination-link").css("color", "");

        // Thêm style vào nút phân trang được nhấn
        $(this).css("background", "#ea880d");
        $(this).css("color", "#fff");
        pageNumber = $(this).data("page-num");
        getOrders(status, pageNumber, username, sort);
    });
});