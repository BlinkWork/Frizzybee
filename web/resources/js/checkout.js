
let shipCost = 0;
let expressShipping = document.getElementById("expressShipping");

let normalShipping = document.getElementById("normalShipping");

expressShipping.addEventListener("click", function ()
{
  shipCost = 20;
  calAmount(shipCost);
});
normalShipping.addEventListener("click", function ()
{
  shipCost = 10;
  calAmount(shipCost);
});
calAmount(20);
function calAmount(shipCost)
{
  let subPriceArr = document.getElementById("subPrice").textContent.substring(1).split(",");
  let subPrice = subPriceArr[ 0 ] + "." + subPriceArr[ 1 ];
  let totalAmount = ((subPrice * 1) + shipCost) + "";
  totalAmount = totalAmount.split(".")[ 0 ] + "," + totalAmount.split(".")[ 1 ];
  document.getElementById("totalAmount").textContent = "$" + totalAmount;
  document.querySelector(".grand-total").querySelector("span").textContent = "$" + totalAmount;
}

document.querySelector(".proceedOrder").addEventListener("click", function (event)
{
  document.querySelector(".loadPlaced").style.display = "";
  setTimeout(function ()
  {
    document.querySelector(".loadPlaced").style.display = 'none';
    document.getElementById("modalTemp").style.display = "";
    document.querySelector(".overlay").removeAttribute("hidden");
    setTimeout(function ()
    {
      document.querySelector(".overlay").setAttribute("hidden");
      window.location.href = "./shop";
    }, 2000);
  }, 2000);
});

