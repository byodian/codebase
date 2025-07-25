function forkEventHandler(body) {
  const {
    repository: {
      name: repo,
      html_url: repoUrl,
    },
    sender: {
      login: sender,
      html_url: senderUrl
    },
  } = body

  const messages = [
    `The repo [${repo}](${repoUrl}) has been forked by [${sender}](${senderUrl})!`,
    'Your project is spreading!',
    `*${repo}* is reaching more people! 🌍`
  ];
  return messages;
}

function render() {
  const body = {
    repository: {
      name: "oneimg",
      html_url: "https://byodian/oneimg"
    },
    sender: {
     login: "byodian",
     html_url: "https://byodian/oneimg"
    }
  }

  const app = document.getElementById("app");
  const messages = forkEventHandler(body)
  for (const message of messages) {
    const p = document.createElement('p')
    p.textContent = message
    app.appendChild(p)
  }
}
const btn = document.querySelector('.btn');
btn.onclick = handleClick;
function handleClick(e) {
  console.log(e)
  console.log('Button clicked!');
}

render()