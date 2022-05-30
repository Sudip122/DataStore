package com.example.datastore1

import android.app.Application
import com.example.datastore1.data.ItemRoomDatabase

class InventoryApplication : Application() {
    val database: ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(this) }

}