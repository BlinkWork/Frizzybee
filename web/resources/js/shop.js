let currentPage = 1;
let tag = "";
let sortOption = 1;
let searchValue = "";

document.getElementById("clearSelection").addEventListener("click", function () {
  currentPage = 1;
  tag = "";
  sortOption = 1;
  let searchInput = searchBtn.querySelector('input[name="search"]');
  searchInput.value = "";
  showListProduct();
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
      showListProduct();
    });
  }
}


let tagLinks = document.querySelectorAll('.category-tags a');
for (let i = 0; i < tagLinks.length; i++) {
  tagLinks[i].addEventListener('click', function (event) {
    event.preventDefault();
    tag = tagLinks[i].textContent;
    showListProduct();
  });
}

let selector = document.getElementById("sort-select");
selector.addEventListener('change', function (event) {
  sortOption = selector.value;
  showListProduct();
});

let searchBtn = document.getElementById("form-searching");
searchBtn.addEventListener('submit', function (event) {
  event.preventDefault();
  let searchInput = searchBtn.querySelector('input[name="search"]');
  searchValue = searchInput.value;
  showListProduct();
});



function showListProduct() {
  let paging = currentPage;
  let sorting = sortOption;
  let taging = tag;
  let searching = searchValue;
  let action = "show";
  console.log(paging)



  $.ajax({
    type: 'POST',
    url: '/FrizzyBee/shop',
    data: {page: paging, sortOption: sorting, tag: taging, search: searching, action: action},
    success: function (res) {
      document.getElementById("productList").innerHTML = res;
      scrollHTML();
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
