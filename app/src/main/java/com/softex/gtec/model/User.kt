package com.softex.gtec.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "user", primaryKeys = ["id"])
data class User(
    @ColumnInfo(name = "id")
    val CustomerID: Int?,
    @ColumnInfo(name = "name")
    val CustomerName: String?,
    @ColumnInfo(name = "status")
    val CustomerStatus: Int?,
    @ColumnInfo(name = "type")
    val CustomerType: String?
) {
    override fun toString(): String {
        return "{CustomerID=$CustomerID, CustomerName='$CustomerName', CustomerStatus=$CustomerStatus, CustomerType='$CustomerType'}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (CustomerID != other.CustomerID) return false
        if (CustomerName != other.CustomerName) return false
        if (CustomerStatus != other.CustomerStatus) return false
        if (CustomerType != other.CustomerType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = CustomerID ?: 0
        result = 31 * result + (CustomerName?.hashCode() ?: 0)
        result = 31 * result + (CustomerStatus ?: 0)
        result = 31 * result + (CustomerType?.hashCode() ?: 0)
        return result
    }
}