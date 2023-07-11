$(function () {

  $("#rateUser").rateYo({
    rating: 5.0,
    onChange: function (rating, rateYoInstance) {
      document.getElementById("rating--score").innerHTML = rating;
    }
  });

});
let paramTemp = new URLSearchParams(window.location.search);
let id = paramTemp.get('id');
showComment(id, "show");

document.getElementById("submit--review").addEventListener("click", function (event) {
  event.preventDefault();

  let comment = document.getElementById("text--comment").value;
  if (comment.trim() === "") {
    alert("INPUT SOMETHING PLEASE");
    return;
  }
  let rateScore = document.getElementById("rating--score").innerHTML;
  if(rateScore == null || rateScore == ""){
    rateScore = 5.0;
  }
  let searchParams = new URLSearchParams(window.location.search);
  let productId = searchParams.get('id');
  let method = "add";
  $.ajax({
    type: 'POST',
    url: '/FrizzyBee/review',
    data: {comment: comment, rateScore: rateScore, productId: productId, method: method},
    success: function ()
    {
      showComment(productId, "show");
      document.getElementById("text--comment").value = "";
      renderRate(productId, "rate");
    },
    error: function ()
    {
      $('#myModal').modal('show');
    }
  });



});

function showComment(productId, method) {
  $.ajax({
    type: 'POST',
    url: '/FrizzyBee/review',
    data: {productId: productId, method: method},
    success: function (response)
    {
      document.querySelector(".product-review-list ul").innerHTML = response;
//      hideComment();
    },
    error: function ()
    {
      alert('Error show request.');
    }
  });
}

function renderRate(productId, method) {
  $.ajax({
    type: 'POST',
    url: '/FrizzyBee/review',
    data: {productId: productId, method: method},
    success: function (response)
    {
      document.querySelector(".product-details-img-full .ratting").innerHTML = response;
    },
    error: function ()
    {
      alert('Error render request.');
    }
  });
}

function hideComment(){
  let comment = document.querySelector(".product-review-list ul").querySelectorAll("li");
  for (let i = 2; i < comment.length; i++) {
    comment[i].classList.add("hidden");
    comment[i].querySelectorAll("*").forEach(e => e.style.display = "none");
  }
}