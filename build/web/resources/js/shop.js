let currentPage = 1;
let tag = "";
let sortOption = 1;
let searchValue = "";



document.getElementById("clearSelection").addEventListener("click", function () {

  let temp = new URLSearchParams(window.location.search);
  if (temp.length != 0) {
    history.pushState({}, null, '/FrizzyBee/shop');
  }
  currentPage = 1;
  tag = "";
  sortOption = 1;
  let searchInput = searchBtn.querySelector('input[name="search"]');
  searchInput.value = "";
  showListProduct(1);
})

addEventPageNumber();
function addEventPageNumber() {
  let pageLinks = document.querySelectorAll('.page-item a');
  for (let i = 0; i < pageLinks.length; i++) {
    pageLinks[i].addEventListener('click', function (event) {
      event.preventDefault();
      for (let j = 0; j < pageLinks.length; j++) {
        pageLinks[j].innerHTML = pageLinks[j].textContent;
      }
      this.innerHTML = "<span>" + this.textContent + "</span>";
      currentPage = this.textContent;
      showListProduct(0);
    });
  }
}


let tagLinks = document.querySelectorAll('.category-tags a');
for (let i = 0; i < tagLinks.length; i++) {
  tagLinks[i].addEventListener('click', function (event) {
    history.pushState({}, null, '/FrizzyBee/shop');
    event.preventDefault();
    tag = tagLinks[i].textContent;
    showListProduct(1);
  });
}

let selector = document.getElementById("sort-select");
selector.addEventListener('change', function (event) {
  sortOption = selector.value;
  showListProduct(1);
});

let searchBtn = document.getElementById("form-searching");
searchBtn.addEventListener('submit', function (event) {
  history.pushState({}, null, '/FrizzyBee/shop');
  event.preventDefault();
  let searchInput = searchBtn.querySelector('input[name="search"]');
  searchValue = searchInput.value;
  showListProduct(1);
});



function showListProduct(pageBack) {
  let paging = currentPage;
  if (pageBack != 0) {
    paging = 1;
  }
  let sorting = sortOption;
  let taging = tag;
  let searching = searchValue;
  let action = "show";

  let searchParams = new URLSearchParams(window.location.search);
  if (tag == "") {
    taging = searchParams.get('tag');
  }
  if (searchValue == "") {
    searching = searchParams.get('search');
  }

  $.ajax({
    type: 'POST',
    url: '/FrizzyBee/shop',
    data: {page: paging, sortOption: sorting, tag: taging, search: searching, action: action},
    success: function (res) {
      document.getElementById("productList").innerHTML = res;
      scrollHTML();
      addCartEvent();
    },
    error: function () {
      alert('Error shows request.');

    }
  });

  action = "pageNumber";
  $.ajax({
    type: 'POST',
    url: '/FrizzyBee/shop',
    data: {page: paging, sortOption: sorting, tag: taging, search: searching, action: action},
    success: function (res) {
      document.querySelector(".page-pagination ul").innerHTML = res;
      addEventPageNumber();
    },
    error: function () {
      alert('Error shows request.');
    }
  });

  function scrollHTML() {
    $('html').animate({
      'scrollTop': 550,
    }, 700);
  }
}
addCartEvent();
function addCartEvent() {
  $('.addCart').click(function (event) {
    event.preventDefault();
    let idAdd = this.id.split("_")[1];
    let add = "add";
    $.ajax({
      type: 'POST',
      url: '/FrizzyBee/cart',
      data: {id: idAdd, quantity: 1, action: add},
      success: function (response) {
        showMiniCart("add");
        $('#modal--warning--login').modal('show');


      },
      error: function () {
        alert('Error add request.');
      }
    });
  });
}

