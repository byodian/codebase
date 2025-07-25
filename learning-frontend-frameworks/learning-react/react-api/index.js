import fs from 'fs';
import { renderToString } from 'react-dom/server';
import React from 'react';
import App from './App.js'; // 注意使用 .js 扩展名

const html = renderToString(<App />);

const finalHtml = `
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <title>SSG Demo</title>
</head>
<body>
  <div id="root">${html}</div>
</body>
</html>
`;

fs.writeFileSync('./out.html', finalHtml);
console.log('✅ HTML page generated: out.html');
