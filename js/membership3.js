function agreeAll() {
  let check = document.querySelector("#agreeAll");
  let check1 = document.querySelector("#check1");
  let check2 = document.querySelector("#check2");

  if (check.checked) {
    check1.checked = true;
    check2.checked = true;
  } else {
    check1.checked = false;
    check2.checked = false;
  }
}
