# Spark JDBC Big Query Connector

Google BigQuery support for Spark SQL

## Version

| spark-bigquery | Spark | Scala |
| :-----: | ----- | ----- |
| 0.1 | 2.2 | 2.11 |

## Usage

### Scala 2.11

```scala
import com.metglobal.oss.spark.jdbc._

// Register BigQuery dialect
JdbcDialects.registerDialect(BigQueryDialect)

var projectId = "[PROJECT ID]"
var oAuthType = "[OAUTH TYPE, DEFAULT = 0]"
var serviceAccount = "[SERVICE ACCOUNT EMAIL FOR BIGQUERY]"
var localOAuth = "[LOCAL OAUTH FILE *.P12]"

val url = s"jdbc:bigquery://https://www.googleapis.com/$projectId:443;ProjectId=$projectId;OAuthType=$oAuthType;OAuthServiceAcctEmail=$serviceAccount;OAuthPvtKeyPath=$localOAuth"

val df = spark.read
    .format("jdbc")
    .option("driver", "com.simba.googlebigquery.jdbc42.Driver") \
    .option("url", url) \
    .option("dbtable", "(SELECT a, SUM(b) AS c, CAST(d AS STRING) FROM test.records GROUP BY a) AS table") \
    .load()

// Unregister dialect
JdbcDialects.unregisterDialect(BigQueryDialect)
```

### Python

```python
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