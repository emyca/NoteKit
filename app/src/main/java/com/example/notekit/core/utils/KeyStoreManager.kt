package com.example.notekit.core.utils

import android.content.Context

//class KeyStoreManager(context: Context) {
//    private val masterKeyAlias = MasterKey.Builder(context)
//        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
//        .build()
//
//    private val sharedPreferences = EncryptedSharedPreferences.create(
//        context,
//        "encrypted_prefs",
//        masterKeyAlias,
//        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
//    )
//
//    fun savePin(pin: String) {
//        sharedPreferences.edit().putString("pin", pin).apply()
//    }
//
//    fun getPin(): String? {
//        return sharedPreferences.getString("pin", null)
//    }
//}