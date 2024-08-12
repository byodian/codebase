let isEditing = false;
let firstName = 'Jane';
let lastName = 'Jacobs';

function handleFormSubmit(e) {
  e.preventDefault();
  setIsEditing(!isEditing);
}

function handleFirstNameChange(e) {
  setFirstName(e.target.value)
}

function handleLastNameChange(e) {
  setFirstName(e.target.value)
}

function setFirstName(value) {
  firstName = value
  updateDOM();
}

function setLastName(value) {
  lastName = value
  updateDOM();
}

function setIsEditing(value) {
  isEditing = value
  updateDOM();
}

function hide(e) {
  e.style.display = 'none';
}

function show(e) {
  e.style.display = '';
}

function updateDOM() {
  helloText.textContent = 'Hello, ' + firstName + ' ' + lastName;
  firstNameText.textContent = firstName
  lastNameText.textContent = lastName

  if (isEditing) {
    editButton.textContent = 'Save profile';
    hide(firstNameText)
    hide(lastNameText)
    show(firstNameInput)
    show(lastNameInput)
  } else {
    editButton.textContent = 'Edit profile'; 
    hide(firstNameInput)
    hide(lastNameInput)
    show(firstNameText)
    show(lastNameText)
  }
}

let form = document.getElementById('form');
let editButton = document.getElementById('editButton');
let firstNameInput = document.getElementById('firstNameInput');
let firstNameText = document.getElementById('firstNameText');
let lastNameInput = document.getElementById('lastNameInput');
let lastNameText = document.getElementById('lastNameText');
let helloText = document.getElementById('helloText');
form.onsubmit = handleFormSubmit;
firstNameInput.oninput = handleFirstNameChange;
lastNameInput.oninput = handleLastNameChange;

updateDOM();
