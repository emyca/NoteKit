package com.example.notekit.core.utils

import androidx.sqlite.db.SupportSQLiteOpenHelper
import net.zetetic.database.sqlcipher.SupportOpenHelperFactory

class SupportFactory(private val passwd: ByteArray) :
    SupportOpenHelperFactory(passwd) {

    override fun create(configuration: SupportSQLiteOpenHelper.Configuration): SupportSQLiteOpenHelper {
        val helper = super.create(configuration)
        passwd.fill(0)
        return helper
    }
}