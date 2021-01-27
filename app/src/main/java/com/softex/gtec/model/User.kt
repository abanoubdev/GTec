package com.softex.gtec.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user",primaryKeys = ["id"])
data class User(
    @ColumnInfo(name = "id")
    val CustomerID: Int,
    @ColumnInfo(name = "name")
    val CustomerName: String,
    @ColumnInfo(name = "status")
    val CustomerStatus: Int,
    @ColumnInfo(name = "type")
    val CustomerType: String
) {
    override fun toString(): String {
        return "{CustomerID=$CustomerID, CustomerName='$CustomerName', CustomerStatus=$CustomerStatus, CustomerType='$CustomerType'}"
    }
}