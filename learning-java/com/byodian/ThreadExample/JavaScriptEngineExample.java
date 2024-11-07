import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaScriptEngineExample {
    public static void main(String[] args) {
        executeScript();
    }

    private static void executeScript() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");

        Bindings bindings = engine.createBindings();
        bindings.put("running", true);
        engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);

        String wrapperScript = "var running = true;\n" +
                "function executeWithCheck(fn, args) {\n" +
                "    var checkInterval = 100;\n" + // check inteval msk
                "    var lastCheck = Date.now();\n" +
                "    \n" +
                "    function shouldContinue() {\n" +
                "        var now = Date.now();\n" +
                "        if (now - lastCheck >= checkInterval) {\n" +
                "            if (!SCRIPT_CONTROL.running) {\n" +
                "                throw new Error('Script execution interrupted');\n" +
                "            }\n" +
                "            lastCheck = now;\n" +
                "        }\n" +
                "        return SCRIPT_CONTROL.running;\n" +
                "    }\n" +
                "    \n" +
                "    return fn.apply(this, args);\n" +
                "};\n";

        String controlScript =
                // 控制变量
                "var running = true;\n" +

                // 检查函数
                        "function shouldContinue() {\n" +
                        "    if (!running) {\n" +
                        "        throw new Error('Script execution interrupted');\n" +
                        "    }\n" +
                        "    return running;\n" +
                        "}\n" +

                        // 函数包装器
                        "function wrapFunction(fn) {\n" +
                        "    return function() {\n" +
                        "        try {\n" +
                        "            var lines = fn.toString().split('\\n');\n" +
                        "            var newLines = [];\n" +
                        "            for(var i = 0; i < lines.length; i++) {\n" +
                        "                if(lines[i].includes('while') || lines[i].includes('for')) {\n" +
                        "                    newLines.push(lines[i]);\n" +
                        "                    newLines.push('    shouldContinue();');\n" +
                        "                } else {\n" +
                        "                    newLines.push(lines[i]);\n" +
                        "                }\n" +
                        "            }\n" +
                        "            var newFn = new Function('return ' + newLines.join('\\n'))();\n" +
                        "            return newFn.apply(this, arguments);\n" +
                        "        } catch(e) {\n" +
                        "            if(e.message === 'Script execution interrupted') {\n" +
                        "                throw e;\n" +
                        "            }\n" +
                        "            throw e;\n" +
                        "        }\n" +
                        "    }\n" +
                        "};\n";

        String script = "function normalFunction(x) {\n" +
                "    return x * 2;\n" +
                "}\n" +

                // infinite loop
                "function infiniteLoopFunction() {\n" +
                "    while(true) {}\n" +
                "}\n" +

                // need long time to run
                "function longRunningFunction() {\n" +
                "    var sum = 0;\n" +
                "    for(var i = 0; i < 1000000000; i++) {\n" +
                "        sum += i;\n" +
                "    }\n" +
                "    return sum;\n" +
                "}\n";

        try {
            engine.eval(script, bindings);
            Invocable invocable = (Invocable) engine;

            System.out.println("Testing normalFunction:");
            executeWithTimeout(bindings, invocable, "normalFunction", 1, 5);

            // System.out.println("\nTesting longRunningFunction:");
            // executeWithTimeout(invocable, "longRunningFunction", 2);

            // System.out.println("\nTesting infiniteLoopFunction:");
            // executeWithTimeout(invocable, "infiniteLoopFunction", 1);

        } catch (ScriptException e) {
            System.out.println("Script error: " + e.getMessage());
        }
    }

    public static void executeWithTimeout(Bindings bindings, Invocable invocable, String functionName,
            long timeoutSeconds, Object... args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Object> future = executor.submit(() -> {
            try {
                return invocable.invokeFunction(functionName, args);
            } catch (ScriptException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });

        try {
            Object result = future.get(timeoutSeconds, TimeUnit.SECONDS);
            System.out.println("Function " + functionName + " completed. Result: " + result);
        } catch (TimeoutException e) {
            System.err.println("Function " + functionName + " timed out after " +
                    timeoutSeconds + "s!");
            bindings.put("running", false);
            future.cancel(true);
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error executing " + functionName + ": " + e.getMessage());
        } finally {
            executor.shutdownNow();
            try {
                if (!executor.awaitTermination(500, TimeUnit.MILLISECONDS)) {
                    System.err.println("Executor did not terminate in the specified time.");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void executeJavaScriptWithInterruptingProgram() {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");

        // String script = "while(true){}";
        String script = "var running = true;\n" +
                "function checkInterrupt() {\n" +
                "    if(!running) { print(124) \n throw 'Script interrupted';\n}" +
                "}\n" +
                "while(true){\n" +
                "    checkInterrupt();\n" +
                "} \n";
        // "<template></template>\n";

        Bindings bindings = engine.createBindings();
        bindings.put("running", true);
        engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Object> future = executor.submit(() -> {
            try {
                return engine.eval(script, bindings);
            } catch (ScriptException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        });

        try {
            Object result = future.get(4, TimeUnit.SECONDS);
            System.out.println("Script output: " + result);
        } catch (TimeoutException | ExecutionException | InterruptedException e) {
            System.err.println("Script execution timed out!");
            bindings.put("running", false);
            future.cancel(true);
            // executor.shutdownNow();
            throw new RuntimeException(e);
        } finally {
            executor.shutdownNow();
        }
    }

    public static class JavaScriptRunning extends Thread {
        public static volatile boolean running = true;
        private String scripting;

        public JavaScriptRunning(String script) {
            this.scripting = script;
        }

        @Override
        public void run() {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            while (running) {
                try {
                    engine.eval(scripting);

                    Invocable invocable2 = (Invocable) engine;
                    invocable2.invokeFunction("transformPayload", "123");
                } catch (ScriptException | NoSuchMethodException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

}