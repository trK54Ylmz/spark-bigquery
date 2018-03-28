package com.metglobal.oss.spark.jdbc

import org.apache.spark.sql.jdbc.JdbcDialect
import org.apache.spark.sql.types._
import java.sql.Types

case object BigQueryDialect extends JdbcDialect {
  override def canHandle(url: String): Boolean = url.startsWith("jdbc:bigquery")

  override def getCatalystType(sqlType: Int,
                               typeName: String,
                               size: Int,
                               md: MetadataBuilder): Option[DataType] = {
    sqlType match {
      case Types.VARCHAR =>
    }
  }

  override def getTableExistsQuery(table: String): String = {
    s"SELECT * FROM $table WHERE 1=0"
  }

  override def isCascadingTruncateTable(): Option[Boolean] = Some(false)
}