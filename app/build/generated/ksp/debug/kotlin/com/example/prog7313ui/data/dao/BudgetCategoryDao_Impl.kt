package com.example.prog7313ui.`data`.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.example.prog7313ui.`data`.entity.BudgetCategory
import javax.`annotation`.processing.Generated
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class BudgetCategoryDao_Impl(
  __db: RoomDatabase,
) : BudgetCategoryDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfBudgetCategory: EntityInsertAdapter<BudgetCategory>

  private val __deleteAdapterOfBudgetCategory: EntityDeleteOrUpdateAdapter<BudgetCategory>

  private val __updateAdapterOfBudgetCategory: EntityDeleteOrUpdateAdapter<BudgetCategory>
  init {
    this.__db = __db
    this.__insertAdapterOfBudgetCategory = object : EntityInsertAdapter<BudgetCategory>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `budget_categories` (`id`,`name`,`minLimit`,`maxLimit`,`imageUri`) VALUES (nullif(?, 0),?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: BudgetCategory) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindDouble(3, entity.minLimit)
        statement.bindDouble(4, entity.maxLimit)
        val _tmpImageUri: String? = entity.imageUri
        if (_tmpImageUri == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpImageUri)
        }
      }
    }
    this.__deleteAdapterOfBudgetCategory = object : EntityDeleteOrUpdateAdapter<BudgetCategory>() {
      protected override fun createQuery(): String =
          "DELETE FROM `budget_categories` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: BudgetCategory) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfBudgetCategory = object : EntityDeleteOrUpdateAdapter<BudgetCategory>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `budget_categories` SET `id` = ?,`name` = ?,`minLimit` = ?,`maxLimit` = ?,`imageUri` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: BudgetCategory) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindDouble(3, entity.minLimit)
        statement.bindDouble(4, entity.maxLimit)
        val _tmpImageUri: String? = entity.imageUri
        if (_tmpImageUri == null) {
          statement.bindNull(5)
        } else {
          statement.bindText(5, _tmpImageUri)
        }
        statement.bindLong(6, entity.id.toLong())
      }
    }
  }

  public override suspend fun insertCategory(category: BudgetCategory): Unit =
      performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfBudgetCategory.insert(_connection, category)
  }

  public override suspend fun deleteCategory(category: BudgetCategory): Unit =
      performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfBudgetCategory.handle(_connection, category)
  }

  public override suspend fun updateCategory(category: BudgetCategory): Unit =
      performSuspending(__db, false, true) { _connection ->
    __updateAdapterOfBudgetCategory.handle(_connection, category)
  }

  public override fun getAllCategories(): Flow<List<BudgetCategory>> {
    val _sql: String = "SELECT * FROM budget_categories"
    return createFlow(__db, false, arrayOf("budget_categories")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfMinLimit: Int = getColumnIndexOrThrow(_stmt, "minLimit")
        val _columnIndexOfMaxLimit: Int = getColumnIndexOrThrow(_stmt, "maxLimit")
        val _columnIndexOfImageUri: Int = getColumnIndexOrThrow(_stmt, "imageUri")
        val _result: MutableList<BudgetCategory> = mutableListOf()
        while (_stmt.step()) {
          val _item: BudgetCategory
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpMinLimit: Double
          _tmpMinLimit = _stmt.getDouble(_columnIndexOfMinLimit)
          val _tmpMaxLimit: Double
          _tmpMaxLimit = _stmt.getDouble(_columnIndexOfMaxLimit)
          val _tmpImageUri: String?
          if (_stmt.isNull(_columnIndexOfImageUri)) {
            _tmpImageUri = null
          } else {
            _tmpImageUri = _stmt.getText(_columnIndexOfImageUri)
          }
          _item = BudgetCategory(_tmpId,_tmpName,_tmpMinLimit,_tmpMaxLimit,_tmpImageUri)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getCategoryId(id: Int): BudgetCategory? {
    val _sql: String = "SELECT * FROM budget_categories WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfMinLimit: Int = getColumnIndexOrThrow(_stmt, "minLimit")
        val _columnIndexOfMaxLimit: Int = getColumnIndexOrThrow(_stmt, "maxLimit")
        val _columnIndexOfImageUri: Int = getColumnIndexOrThrow(_stmt, "imageUri")
        val _result: BudgetCategory?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpMinLimit: Double
          _tmpMinLimit = _stmt.getDouble(_columnIndexOfMinLimit)
          val _tmpMaxLimit: Double
          _tmpMaxLimit = _stmt.getDouble(_columnIndexOfMaxLimit)
          val _tmpImageUri: String?
          if (_stmt.isNull(_columnIndexOfImageUri)) {
            _tmpImageUri = null
          } else {
            _tmpImageUri = _stmt.getText(_columnIndexOfImageUri)
          }
          _result = BudgetCategory(_tmpId,_tmpName,_tmpMinLimit,_tmpMaxLimit,_tmpImageUri)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
