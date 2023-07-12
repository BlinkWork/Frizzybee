let view_more = document.getElementById("view--more");
let hiddenCmt = document.getElementById("hide--comment");

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
let numberComment = 3;
let numberBefore = 0;
showComment(id, "show", numberComment);


document.getElementById("submit--review").addEventListener("click", function (event) {
  event.preventDefault();

  let comment = document.getElementById("text--comment").value;
  if (comment.trim() === "") {
    alert("INPUT SOMETHING PLEASE");
    return;
  }
  let rateScore = document.getElementById("rating--score").innerHTML;
  if (rateScore == null || rateScore == "") {
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
      showComment(productId, "show", numberComment);
      document.getElementById("text--comment").value = "";
      renderRate(productId, "rate");
    },
    error: function ()
    {
      $('#myModal').modal('show');
    }
  });
});

function showComment(productId, method, numberComment) {
  $.ajax({
    type: 'POST',
    url: '/FrizzyBee/review',
    data: {productId: productId, method: method, numberComment: numberComment},
    success: function (response)
    {

      document.querySelector(".product-review-list ul").innerHTML = response;

    },
    error: function ()
    {
      alert('Error show request.');
    }
  });

  method = "more";

  $.ajax({
    type: 'POST',
    url: '/FrizzyBee/review',
    data: {productId: productId, method: method, numberComment: numberComment},
    success: function (response)
    {
      if (response == "0") {
        view_more.style.display = "none";
      } else {
        view_more.style.display = "block";

      }

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

view_more.addEventListener("click", function () {
  numberComment += 3;
  let searchParams = new URLSearchParams(window.location.search);
  let productId = searchParams.get('id');
  showComment(productId, "show", numberComment);
  hiddenCmt.style.display = "block";
});

hiddenCmt.addEventListener("click", function () {
  numberComment = 3;
  let searchParams = new URLSearchParams(window.location.search);
  let productId = searchParams.get('id');
  showComment(productId, "show", numberComment);
  hiddenCmt.style.display = "none";
  view_more.style.display = "block";

});

