package com.example.prog7313ui.`data`.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.example.prog7313ui.`data`.Converters
import com.example.prog7313ui.`data`.entity.Expense
import java.util.Date
import javax.`annotation`.processing.Generated
import kotlin.Double
import kotlin.Int
import kotlin.Long
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
public class ExpenseDao_Impl(
  __db: RoomDatabase,
) : ExpenseDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfExpense: EntityInsertAdapter<Expense>

  private val __converters: Converters = Converters()

  private val __deleteAdapterOfExpense: EntityDeleteOrUpdateAdapter<Expense>

  private val __updateAdapterOfExpense: EntityDeleteOrUpdateAdapter<Expense>
  init {
    this.__db = __db
    this.__insertAdapterOfExpense = object : EntityInsertAdapter<Expense>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `expenses` (`id`,`amount`,`date`,`startDate`,`endDate`,`description`,`categoryId`,`photoPath`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Expense) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindDouble(2, entity.amount)
        val _tmp: Long? = __converters.dateToTimestamp(entity.date)
        if (_tmp == null) {
          statement.bindNull(3)
        } else {
          statement.bindLong(3, _tmp)
        }
        val _tmpStartDate: Date? = entity.startDate
        val _tmp_1: Long? = __converters.dateToTimestamp(_tmpStartDate)
        if (_tmp_1 == null) {
          statement.bindNull(4)
        } else {
          statement.bindLong(4, _tmp_1)
        }
        val _tmpEndDate: Date? = entity.endDate
        val _tmp_2: Long? = __converters.dateToTimestamp(_tmpEndDate)
        if (_tmp_2 == null) {
          statement.bindNull(5)
        } else {
          statement.bindLong(5, _tmp_2)
        }
        statement.bindText(6, entity.description)
        statement.bindLong(7, entity.categoryId.toLong())
        val _tmpPhotoPath: String? = entity.photoPath
        if (_tmpPhotoPath == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpPhotoPath)
        }
      }
    }
    this.__deleteAdapterOfExpense = object : EntityDeleteOrUpdateAdapter<Expense>() {
      protected override fun createQuery(): String = "DELETE FROM `expenses` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Expense) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfExpense = object : EntityDeleteOrUpdateAdapter<Expense>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `expenses` SET `id` = ?,`amount` = ?,`date` = ?,`startDate` = ?,`endDate` = ?,`description` = ?,`categoryId` = ?,`photoPath` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Expense) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindDouble(2, entity.amount)
        val _tmp: Long? = __converters.dateToTimestamp(entity.date)
        if (_tmp == null) {
          statement.bindNull(3)
        } else {
          statement.bindLong(3, _tmp)
        }
        val _tmpStartDate: Date? = entity.startDate
        val _tmp_1: Long? = __converters.dateToTimestamp(_tmpStartDate)
        if (_tmp_1 == null) {
          statement.bindNull(4)
        } else {
          statement.bindLong(4, _tmp_1)
        }
        val _tmpEndDate: Date? = entity.endDate
        val _tmp_2: Long? = __converters.dateToTimestamp(_tmpEndDate)
        if (_tmp_2 == null) {
          statement.bindNull(5)
        } else {
          statement.bindLong(5, _tmp_2)
        }
        statement.bindText(6, entity.description)
        statement.bindLong(7, entity.categoryId.toLong())
        val _tmpPhotoPath: String? = entity.photoPath
        if (_tmpPhotoPath == null) {
          statement.bindNull(8)
        } else {
          statement.bindText(8, _tmpPhotoPath)
        }
        statement.bindLong(9, entity.id.toLong())
      }
    }
  }

  public override suspend fun insertExpense(expense: Expense): Unit = performSuspending(__db, false,
      true) { _connection ->
    __insertAdapterOfExpense.insert(_connection, expense)
  }

  public override suspend fun deleteExpense(expense: Expense): Unit = performSuspending(__db, false,
      true) { _connection ->
    __deleteAdapterOfExpense.handle(_connection, expense)
  }

  public override suspend fun updateExpense(expense: Expense): Unit = performSuspending(__db, false,
      true) { _connection ->
    __updateAdapterOfExpense.handle(_connection, expense)
  }

  public override fun getAllExpenses(): Flow<List<Expense>> {
    val _sql: String = "SELECT * FROM expenses ORDER BY date DESC"
    return createFlow(__db, false, arrayOf("expenses")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAmount: Int = getColumnIndexOrThrow(_stmt, "amount")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfStartDate: Int = getColumnIndexOrThrow(_stmt, "startDate")
        val _columnIndexOfEndDate: Int = getColumnIndexOrThrow(_stmt, "endDate")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "categoryId")
        val _columnIndexOfPhotoPath: Int = getColumnIndexOrThrow(_stmt, "photoPath")
        val _result: MutableList<Expense> = mutableListOf()
        while (_stmt.step()) {
          val _item: Expense
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpAmount: Double
          _tmpAmount = _stmt.getDouble(_columnIndexOfAmount)
          val _tmpDate: Date
          val _tmp: Long?
          if (_stmt.isNull(_columnIndexOfDate)) {
            _tmp = null
          } else {
            _tmp = _stmt.getLong(_columnIndexOfDate)
          }
          val _tmp_1: Date? = __converters.fromTimestamp(_tmp)
          if (_tmp_1 == null) {
            error("Expected NON-NULL 'java.util.Date', but it was NULL.")
          } else {
            _tmpDate = _tmp_1
          }
          val _tmpStartDate: Date?
          val _tmp_2: Long?
          if (_stmt.isNull(_columnIndexOfStartDate)) {
            _tmp_2 = null
          } else {
            _tmp_2 = _stmt.getLong(_columnIndexOfStartDate)
          }
          _tmpStartDate = __converters.fromTimestamp(_tmp_2)
          val _tmpEndDate: Date?
          val _tmp_3: Long?
          if (_stmt.isNull(_columnIndexOfEndDate)) {
            _tmp_3 = null
          } else {
            _tmp_3 = _stmt.getLong(_columnIndexOfEndDate)
          }
          _tmpEndDate = __converters.fromTimestamp(_tmp_3)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpCategoryId: Int
          _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId).toInt()
          val _tmpPhotoPath: String?
          if (_stmt.isNull(_columnIndexOfPhotoPath)) {
            _tmpPhotoPath = null
          } else {
            _tmpPhotoPath = _stmt.getText(_columnIndexOfPhotoPath)
          }
          _item =
              Expense(_tmpId,_tmpAmount,_tmpDate,_tmpStartDate,_tmpEndDate,_tmpDescription,_tmpCategoryId,_tmpPhotoPath)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getExpensesByCategory(categoryId: Int): Flow<List<Expense>> {
    val _sql: String = "SELECT * FROM expenses WHERE categoryId = ?"
    return createFlow(__db, false, arrayOf("expenses")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, categoryId.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAmount: Int = getColumnIndexOrThrow(_stmt, "amount")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfStartDate: Int = getColumnIndexOrThrow(_stmt, "startDate")
        val _columnIndexOfEndDate: Int = getColumnIndexOrThrow(_stmt, "endDate")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "categoryId")
        val _columnIndexOfPhotoPath: Int = getColumnIndexOrThrow(_stmt, "photoPath")
        val _result: MutableList<Expense> = mutableListOf()
        while (_stmt.step()) {
          val _item: Expense
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpAmount: Double
          _tmpAmount = _stmt.getDouble(_columnIndexOfAmount)
          val _tmpDate: Date
          val _tmp: Long?
          if (_stmt.isNull(_columnIndexOfDate)) {
            _tmp = null
          } else {
            _tmp = _stmt.getLong(_columnIndexOfDate)
          }
          val _tmp_1: Date? = __converters.fromTimestamp(_tmp)
          if (_tmp_1 == null) {
            error("Expected NON-NULL 'java.util.Date', but it was NULL.")
          } else {
            _tmpDate = _tmp_1
          }
          val _tmpStartDate: Date?
          val _tmp_2: Long?
          if (_stmt.isNull(_columnIndexOfStartDate)) {
            _tmp_2 = null
          } else {
            _tmp_2 = _stmt.getLong(_columnIndexOfStartDate)
          }
          _tmpStartDate = __converters.fromTimestamp(_tmp_2)
          val _tmpEndDate: Date?
          val _tmp_3: Long?
          if (_stmt.isNull(_columnIndexOfEndDate)) {
            _tmp_3 = null
          } else {
            _tmp_3 = _stmt.getLong(_columnIndexOfEndDate)
          }
          _tmpEndDate = __converters.fromTimestamp(_tmp_3)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpCategoryId: Int
          _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId).toInt()
          val _tmpPhotoPath: String?
          if (_stmt.isNull(_columnIndexOfPhotoPath)) {
            _tmpPhotoPath = null
          } else {
            _tmpPhotoPath = _stmt.getText(_columnIndexOfPhotoPath)
          }
          _item =
              Expense(_tmpId,_tmpAmount,_tmpDate,_tmpStartDate,_tmpEndDate,_tmpDescription,_tmpCategoryId,_tmpPhotoPath)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getExpensesBetweenDates(startDate: Date?, endDate: Date?):
      Flow<List<Expense>> {
    val _sql: String = """
        |
        |        SELECT * FROM expenses 
        |        WHERE (? IS NULL OR date >= ?)
        |          AND (? IS NULL OR date <= ?)
        |        ORDER BY date DESC
        |    
        """.trimMargin()
    return createFlow(__db, false, arrayOf("expenses")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: Long? = __converters.dateToTimestamp(startDate)
        if (_tmp == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, _tmp)
        }
        _argIndex = 2
        val _tmp_1: Long? = __converters.dateToTimestamp(startDate)
        if (_tmp_1 == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, _tmp_1)
        }
        _argIndex = 3
        val _tmp_2: Long? = __converters.dateToTimestamp(endDate)
        if (_tmp_2 == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, _tmp_2)
        }
        _argIndex = 4
        val _tmp_3: Long? = __converters.dateToTimestamp(endDate)
        if (_tmp_3 == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, _tmp_3)
        }
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfAmount: Int = getColumnIndexOrThrow(_stmt, "amount")
        val _columnIndexOfDate: Int = getColumnIndexOrThrow(_stmt, "date")
        val _columnIndexOfStartDate: Int = getColumnIndexOrThrow(_stmt, "startDate")
        val _columnIndexOfEndDate: Int = getColumnIndexOrThrow(_stmt, "endDate")
        val _columnIndexOfDescription: Int = getColumnIndexOrThrow(_stmt, "description")
        val _columnIndexOfCategoryId: Int = getColumnIndexOrThrow(_stmt, "categoryId")
        val _columnIndexOfPhotoPath: Int = getColumnIndexOrThrow(_stmt, "photoPath")
        val _result: MutableList<Expense> = mutableListOf()
        while (_stmt.step()) {
          val _item: Expense
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpAmount: Double
          _tmpAmount = _stmt.getDouble(_columnIndexOfAmount)
          val _tmpDate: Date
          val _tmp_4: Long?
          if (_stmt.isNull(_columnIndexOfDate)) {
            _tmp_4 = null
          } else {
            _tmp_4 = _stmt.getLong(_columnIndexOfDate)
          }
          val _tmp_5: Date? = __converters.fromTimestamp(_tmp_4)
          if (_tmp_5 == null) {
            error("Expected NON-NULL 'java.util.Date', but it was NULL.")
          } else {
            _tmpDate = _tmp_5
          }
          val _tmpStartDate: Date?
          val _tmp_6: Long?
          if (_stmt.isNull(_columnIndexOfStartDate)) {
            _tmp_6 = null
          } else {
            _tmp_6 = _stmt.getLong(_columnIndexOfStartDate)
          }
          _tmpStartDate = __converters.fromTimestamp(_tmp_6)
          val _tmpEndDate: Date?
          val _tmp_7: Long?
          if (_stmt.isNull(_columnIndexOfEndDate)) {
            _tmp_7 = null
          } else {
            _tmp_7 = _stmt.getLong(_columnIndexOfEndDate)
          }
          _tmpEndDate = __converters.fromTimestamp(_tmp_7)
          val _tmpDescription: String
          _tmpDescription = _stmt.getText(_columnIndexOfDescription)
          val _tmpCategoryId: Int
          _tmpCategoryId = _stmt.getLong(_columnIndexOfCategoryId).toInt()
          val _tmpPhotoPath: String?
          if (_stmt.isNull(_columnIndexOfPhotoPath)) {
            _tmpPhotoPath = null
          } else {
            _tmpPhotoPath = _stmt.getText(_columnIndexOfPhotoPath)
          }
          _item =
              Expense(_tmpId,_tmpAmount,_tmpDate,_tmpStartDate,_tmpEndDate,_tmpDescription,_tmpCategoryId,_tmpPhotoPath)
          _result.add(_item)
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
