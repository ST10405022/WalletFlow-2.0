package com.example.prog7313ui.`data`

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import com.example.prog7313ui.`data`.dao.BudgetCategoryDao
import com.example.prog7313ui.`data`.dao.BudgetCategoryDao_Impl
import com.example.prog7313ui.`data`.dao.ExpenseDao
import com.example.prog7313ui.`data`.dao.ExpenseDao_Impl
import com.example.prog7313ui.`data`.dao.UserDao
import com.example.prog7313ui.`data`.dao.UserDao_Impl
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AppDatabase_Impl : AppDatabase() {
  private val _userDao: Lazy<UserDao> = lazy {
    UserDao_Impl(this)
  }

  private val _expenseDao: Lazy<ExpenseDao> = lazy {
    ExpenseDao_Impl(this)
  }

  private val _budgetCategoryDao: Lazy<BudgetCategoryDao> = lazy {
    BudgetCategoryDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(2,
        "c876ee8ae38b3e9159d49aa228975faf", "a0288be75cbc9bb9a7857d3ed0eb6f3a") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `users` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `surname` TEXT NOT NULL, `username` TEXT NOT NULL, `password` TEXT NOT NULL, `email` TEXT NOT NULL, `hashedPassword` TEXT NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `expenses` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `amount` REAL NOT NULL, `date` INTEGER NOT NULL, `startDate` INTEGER, `endDate` INTEGER, `description` TEXT NOT NULL, `categoryId` INTEGER NOT NULL, `photoPath` TEXT)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `budget_categories` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `minLimit` REAL NOT NULL, `maxLimit` REAL NOT NULL, `imageUri` TEXT)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'c876ee8ae38b3e9159d49aa228975faf')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `users`")
        connection.execSQL("DROP TABLE IF EXISTS `expenses`")
        connection.execSQL("DROP TABLE IF EXISTS `budget_categories`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection):
          RoomOpenDelegate.ValidationResult {
        val _columnsUsers: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsUsers.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("surname", TableInfo.Column("surname", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("username", TableInfo.Column("username", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("password", TableInfo.Column("password", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("email", TableInfo.Column("email", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("hashedPassword", TableInfo.Column("hashedPassword", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysUsers: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesUsers: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoUsers: TableInfo = TableInfo("users", _columnsUsers, _foreignKeysUsers,
            _indicesUsers)
        val _existingUsers: TableInfo = read(connection, "users")
        if (!_infoUsers.equals(_existingUsers)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |users(com.example.prog7313ui.data.entity.User).
              | Expected:
              |""".trimMargin() + _infoUsers + """
              |
              | Found:
              |""".trimMargin() + _existingUsers)
        }
        val _columnsExpenses: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsExpenses.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("amount", TableInfo.Column("amount", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("date", TableInfo.Column("date", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("startDate", TableInfo.Column("startDate", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("endDate", TableInfo.Column("endDate", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("description", TableInfo.Column("description", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("categoryId", TableInfo.Column("categoryId", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsExpenses.put("photoPath", TableInfo.Column("photoPath", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysExpenses: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesExpenses: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoExpenses: TableInfo = TableInfo("expenses", _columnsExpenses, _foreignKeysExpenses,
            _indicesExpenses)
        val _existingExpenses: TableInfo = read(connection, "expenses")
        if (!_infoExpenses.equals(_existingExpenses)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |expenses(com.example.prog7313ui.data.entity.Expense).
              | Expected:
              |""".trimMargin() + _infoExpenses + """
              |
              | Found:
              |""".trimMargin() + _existingExpenses)
        }
        val _columnsBudgetCategories: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsBudgetCategories.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBudgetCategories.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBudgetCategories.put("minLimit", TableInfo.Column("minLimit", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBudgetCategories.put("maxLimit", TableInfo.Column("maxLimit", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBudgetCategories.put("imageUri", TableInfo.Column("imageUri", "TEXT", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysBudgetCategories: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesBudgetCategories: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoBudgetCategories: TableInfo = TableInfo("budget_categories",
            _columnsBudgetCategories, _foreignKeysBudgetCategories, _indicesBudgetCategories)
        val _existingBudgetCategories: TableInfo = read(connection, "budget_categories")
        if (!_infoBudgetCategories.equals(_existingBudgetCategories)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |budget_categories(com.example.prog7313ui.data.entity.BudgetCategory).
              | Expected:
              |""".trimMargin() + _infoBudgetCategories + """
              |
              | Found:
              |""".trimMargin() + _existingBudgetCategories)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "users", "expenses",
        "budget_categories")
  }

  public override fun clearAllTables() {
    super.performClear(false, "users", "expenses", "budget_categories")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(UserDao::class, UserDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ExpenseDao::class, ExpenseDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(BudgetCategoryDao::class, BudgetCategoryDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override
      fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>):
      List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun userDao(): UserDao = _userDao.value

  public override fun expenseDao(): ExpenseDao = _expenseDao.value

  public override fun budgetCategoryDao(): BudgetCategoryDao = _budgetCategoryDao.value
}
