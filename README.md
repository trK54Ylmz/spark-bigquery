# Spark JDBC Big Query Connector


## Usage

### Scala 2.11

```
JdbcDialects.registerDialect(BigQueryDialect)



JdbcDialects.unregisterDialect(BigQueryDialect)
```

### Python

```
sc = spark.sparkContext

sc._jvm.com.metglobal.oss.spark.jdbc.BigQueryRegister.register()

df = spark.read \
    .format("jdbc") \
    .option("driver", "com.simba.googlebigquery.jdbc42.Driver") \
    .option("url", "jdbc:bigquery://https://www.googleapis.com/...") \
    .option("dbtable", "(SELECT a, SUM(b) AS c, CAST(d AS STRING) FROM test.records GROUP BY a) AS table") \
    .load()

sc._jvm.com.metglobal.oss.spark.jdbc.BigQueryRegister.unregister()
```