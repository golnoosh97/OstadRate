## Simple Project about rating teachers.

## Installation:

```bash
git clone https://github.com/mahdi-malv/OstadRate.git
# Run with android studio
cd Ostadrate
studio .
```

### Class `Pulp` is a simple logger utility (For java).

#### Usage

```java
Pulp.info(TAG, message);
```

```java
Pulp.info(TAG, message, throwable);
```

```java
Pulp.info(TAG, message,
    key1, message1,
    key2, message2);
```

```java
Pulp.info(TAG, message)
   .addMessage(key1, message1)
   .addMessage(key2, message2)
   .log();
```

> Different log levels are `info`, `debug`, `warn`, `error` and `wtf`.

#### Main tag

The tag to identify logs inside logcat.

```java
Pulp.setMainTag(appLogTag);
```

#### Log listener

To add a listener to get callback while Pulp was triggered:

```java
Pulp.addHandler(new Pulp.LogHandler() {
    @Override
    public void onLog(Pulp.LogLevel logLevel, String tag, String message, Throwable throwable, Map<String, String> data) {
         // Use log data             
    }
});
```
It is possible to have multiple handlers.<br>
To remove all handlers:

```java
Pulp.clearHandlers();
```

#### Enable / Disable log

```java
Pulp.setLogEnabled(true);
Pulp.setHandlerEnabled(true);
```
