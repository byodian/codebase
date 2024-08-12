let componentHooks = []
let currentHookIndex = 0;

function createSetter(state) {
  return function setterWithcurrentHookIndex(newVal) {
    state = newVal;
  };
}

function useState(initVal) {
  let pair = componentHooks[currentHookIndex];
  if (pair) {
    currentHookIndex++;
    return pair;
  }

  pair = [initVal, setState];
  function setState(newVal) {
    pair[0] = newVal;
    updateDOM();
  }
  componentHooks[currentHookIndex] = pair;

  currentHookIndex++;
  return pair;
}

// Our component code that uses hooks
function RenderFunctionComponent() {
  const [firstName, setFirstName] = useState("Rudi"); // currentHookIndex: 0
  const [lastName, setLastName] = useState("Yardley"); // currentHookIndex: 1

  console.log(firstName, lastName)
}

function updateDOM() {
  currentHookIndex = 0;
  RenderFunctionComponent();
}

updateDOM();