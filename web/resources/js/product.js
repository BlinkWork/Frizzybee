/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */



const urlParams = new URLSearchParams(window.location.search);
const currentPage = urlParams.get('page');
if (currentPage === null) {
  const pagination = document.querySelector('.pagination');
  const pages = pagination.querySelectorAll('.page-item a');
  pages[0].parentNode.classList.add('active');

}
if (currentPage) {
  console.log(currentPage);
  const pagination = document.querySelector('.pagination');
  const pages = pagination.querySelectorAll('.page-item a');
  for (let i = 0; i < pages.length; i++) {
    if (pages[i].textContent === currentPage) {
      pages[i].parentNode.classList.add('active');
      break;
    }
  }
}

window.onload = function () {
  slideOne();
  slideTwo();
}

let sliderOne = document.getElementById("slider-1");
let sliderTwo = document.getElementById("slider-2");
let displayValOne = document.getElementById("range1");
let displayValTwo = document.getElementById("range2");
let minGap = 0;
let sliderTrack = document.querySelector(".slider-track");
let sliderMaxValue = document.getElementById("slider-1").max;

function slideOne() {
  if (parseInt(sliderTwo.value) - parseInt(sliderOne.value) <= minGap) {
    sliderOne.value = parseInt(sliderTwo.value) - minGap;
  }
  displayValOne.textContent = sliderOne.value;
  fillColor();
}
function slideTwo() {
  if (parseInt(sliderTwo.value) - parseInt(sliderOne.value) <= minGap) {
    sliderTwo.value = parseInt(sliderOne.value) + minGap;
  }
  displayValTwo.textContent = sliderTwo.value;
  fillColor();
}
function fillColor() {
  percent1 = (sliderOne.value / sliderMaxValue) * 100;
  percent2 = (sliderTwo.value / sliderMaxValue) * 100;
  sliderTrack.style.background = `linear-gradient(to right, #dadae5 ${percent1}% , #3264fe ${percent1}% , #3264fe ${percent2}%, #dadae5 ${percent2}%)`;
}