package com.example.uno.data.settings

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import androidx.core.content.edit
import com.example.uno.domain.mainmenu.entities.Account
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class SharedPreferencesSettingsDataSource @Inject constructor(
    @ApplicationContext private val context: Context
) : SettingsDataSource, OnSharedPreferenceChangeListener {

    private val preferences = context.getSharedPreferences(
        PREFERENCES_NAME, Context.MODE_PRIVATE
    )
    private val idFlow = MutableStateFlow<Int?>(null)
    private val nicknameFlow = MutableStateFlow<String?>(null)

    private val accountFlow = combine(
        idFlow, nicknameFlow, ::merge
    )

    private fun merge(
        id: Int?, nickname: String?
    ): Account {
        return Account(
            id = id ?: -1, nickname = nickname ?: "default"
        )
    }

    init {
        preferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun setId(id: Int?) {
        preferences.edit {
            if (id == null) {
                remove(PREF_ID)
            } else {
                putInt(PREF_ID, id)
            }
        }
    }

    override fun setNickname(nickname: String?) {
        preferences.edit {
            if (nickname == null) {
                remove(PREF_NICK)
            } else {
                putString(PREF_NICK, nickname)
            }
        }
    }

    override fun getId(): Int {
        return preferences.getInt(PREF_ID, -1)
    }

    override fun getNickname(): String? {
        return preferences.getString(PREF_NICK, null)
    }

    override fun listenId(): Flow<Account?> {
        return accountFlow
    }

    override fun getAccount(): Account? {
        return if (getId() == -1) null
        else Account(
            id = getId(),
            nickname = getNickname() ?: "DEFAULT_NAME"
        )
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        idFlow.value = getId()
    }

    private companion object {
        const val PREFERENCES_NAME = "preferences"
        const val PREF_ID = "id"
        const val PREF_NICK = "nickname"
        const val DEFAULT_NAME = "guest"
    }
}