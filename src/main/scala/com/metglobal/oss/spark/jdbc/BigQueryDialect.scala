/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.metglobal.oss.spark.jdbc

import java.sql.{JDBCType, SQLException, Types}

import org.apache.spark.sql.jdbc.JdbcDialect
import org.apache.spark.sql.types._

case object BigQueryDialect extends JdbcDialect {
  val PREFIX = "#standardSQL\n"

  override def canHandle(url: String): Boolean = url.startsWith("jdbc:bigquery")

  override def quoteIdentifier(colName: String): String = s"`$colName`"

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

  override def getTableExistsQuery(table: String): String = s"$PREFIX SELECT 1 FROM $table LIMIT 1"

  override def getSchemaQuery(table: String): String = s"$PREFIX SELECT * FROM $table LIMIT 1"

  override def isCascadingTruncateTable(): Option[Boolean] = Some(false)
}