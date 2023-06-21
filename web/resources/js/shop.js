const urlParams = new URLSearchParams(window.location.search);
let currentPage = urlParams.get('page');

if (currentPage == null) {
  currentPage = 1;
}


if (currentPage) {

  const pagination = document.querySelector('.page-pagination');
  const pages = pagination.querySelectorAll('.page-item a');
  for (let i = 0; i < pages.length; i++) {
    if (pages[i].textContent == currentPage) {

      let paginationItems = document.querySelectorAll('.page-pagination ul li');
      if (currentPage > 1) {

        let newListItem = document.createElement('li');
        newListItem.innerHTML = '<span>' + currentPage + '</span>';
        let before = parseInt(pages[i].textContent) - 1;
        paginationItems.forEach(function (item) {
          if (item.querySelector('a') && item.querySelector('a').textContent == before) {
            item.after(newListItem);
          }
        });
      } else {
        let paginationList = document.querySelector('.page-pagination ul');
        paginationList.insertAdjacentHTML('afterbegin', '<li><span>1</span></li>');

      }

      paginationItems.forEach(function (item) {
        if (item.querySelector('a') && item.querySelector('a').textContent == currentPage) {
          item.remove();
        }
      });

      break;
    }
  }
}
