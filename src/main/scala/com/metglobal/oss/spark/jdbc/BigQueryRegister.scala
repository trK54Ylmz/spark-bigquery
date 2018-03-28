package com.metglobal.oss.spark.jdbc

import org.apache.spark.sql.jdbc.JdbcDialects

object BigQueryRegister {
  def register(): Unit = {
    JdbcDialects.registerDialect(BigQueryDialect)
  }

  def unregister(): Unit = {
    JdbcDialects.unregisterDialect(BigQueryDialect)
  }
}