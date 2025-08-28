const { SimpleConsumer, Producer } = require('rocketmq-client-nodejs');

async function startConsumer () {
  const simpleConsumer = new SimpleConsumer({
    consumerGroup: 'rocketmq-node-demo-group',
    endpoints: '192.168.2.226:8081;192.168.2.220:8081',
    subscriptions: new Map().set('node_script_parsing_request_2', '*'),
  });
  await simpleConsumer.startup();

  const messages = await simpleConsumer.receive(20);
  console.log(messages)
  console.log('got %d messages', messages.length);
  for (const message of messages) {
    console.log(message);
    console.log('body=%o', message.body.toString());
    await simpleConsumer.ack(message);
  }
}

async function startPush(params) {
  const producer = new Producer({
    endpoints: '192.168.2.226:8081',
  });

  await producer.startup();

  for (let i = 0; i < 3; i++) {  
    const receipt = await producer.send({
      topic: 'node_script_parsing_request_2',
      tag: 'script-handler',
      // body: Buffer.from(JSON.stringify({
      //   hello: 'rocketmq-client-nodejs world 😄',
      //   now: Date(),
      // })),
      body: Buffer.from(JSON.stringify({
        functionName: 'rawDataToProtocol',
        productKey: "test-node",
        args: {
          hello: 'rocketmq-client-nodejs world 😄',
          now: Date(),
        }
      })),
    });
    console.log(receipt);
  }
}

// startConsumer()
// startPush()
const args = process.argv.slice(2)
if (args.length > 0 && args[0] === 'push') {
  startPush()
}

if (args.length > 0 && args[0] === 'consume') {
  startConsumer()
}