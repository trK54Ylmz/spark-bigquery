package com.metglobal.oss.spark.jdbc

import java.sql.{JDBCType, SQLException, Types}

import org.apache.spark.sql.jdbc.JdbcDialect
import org.apache.spark.sql.types._

case object BigQueryDialect extends JdbcDialect {
  override def canHandle(url: String): Boolean = url.startsWith("jdbc:bigquery")

  override def getCatalystType(sqlType: Int,
                               typeName: String,
                               size: Int,
                               md: MetadataBuilder): Option[DataType] = {
    val decision = sqlType match {
      case Types.ARRAY => None
    }

    if (decision.isEmpty) {
      throw new SQLException(s"Unsupported type ${JDBCType.valueOf(sqlType).getName}")
    }

    decision
  }

  override def getTableExistsQuery(table: String): String = {
    s"SELECT * FROM $table WHERE 1=0"
  }

  override def isCascadingTruncateTable(): Option[Boolean] = Some(false)
}