package com.example.congresotfg.common.utils

import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import com.example.congresotfg.R
import com.example.congresotfg.myListModule.MyListFragment
import com.example.congresotfg.partnersFragmentModule.PartnersFragment

object Shortcut {

    const val shortcut_socios_id = "id_socios"
    const val shortcut_my_list_id = "id_my_list"

    @RequiresApi(Build.VERSION_CODES.N_MR1)
    fun setUp(context: Context) {

        val shortcutManager = getSystemService<ShortcutManager>(context, ShortcutManager::class.java)

        val shortcutSocios = ShortcutInfo.Builder(context, shortcut_socios_id)
            .setShortLabel("Socios")
            .setLongLabel("Abre los Socios!!")
            .setIcon(Icon.createWithResource(context, R.drawable.ic_people_outline))
            .setIntent(Intent(context, PartnersFragment::class.java))
            .build()

        val shortcutMyList = ShortcutInfo.Builder(context, shortcut_my_list_id)
            .setShortLabel("My List")
            .setLongLabel("Open My List!!")
            .setIcon(Icon.createWithResource(context, R.drawable.ic_like))
            .setIntent(Intent(context, MyListFragment::class.java))
            .build()

        shortcutManager!!.dynamicShortcuts = listOf(shortcutSocios, shortcutMyList)

    }

}