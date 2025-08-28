const { Worker, isMainThread, parentPort } = require('worker_threads');

if (isMainThread) {
  // 主线程代码，创建一个 worker
  const worker = new Worker(__filename);

  worker.on('message', message => {
    console.log(`Received from worker: ${message}`);
  })

  worker.on('error', error => {
    console.error(`Worker error: ${error}`);
  });

  worker.on('exit', code => {
    if (code !== 0) {
      console.error(`Worker stopped with exit code ${code}`);
    } else {
      console.log('Worker exited successfully');
    }
  })
  // worker.postMessage('Hello, worker!');
} else {

  // Worker 线程代码
  parentPort.on('message', message => {
    console.log(`Received from main thread: ${message}`);
    parentPort.postMessage(`Hello, main thread!`);
  });
}