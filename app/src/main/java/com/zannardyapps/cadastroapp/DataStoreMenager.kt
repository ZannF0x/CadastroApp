package com.zannardyapps.cadastroapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "data_user")

object DataStoreMenager {

    //SAVE
    suspend fun saveData(context: Context, key:String, value: String){
        val prefKey = stringPreferencesKey(key)

        context.dataStore.edit {
          it[prefKey] = value
        }
    }
    /*

    suspend fun saveDataEmail(context: Context, key:String, valueEmail: String){
        val prefKey = stringPreferencesKey(key)

        context.dataStore.edit {
            it[prefKey] = valueEmail
        }
    }

    suspend fun saveDataSenha(context: Context, key:String, valueSenha: String){
        val prefKey = stringPreferencesKey(key)

        context.dataStore.edit {
            it[prefKey] = valueSenha
        }
    }
     */


    //GET
    suspend fun getToValue(context: Context, key: String, default: String = ""):String{
        val prefKey = stringPreferencesKey(key)
        val valueFlow: Flow<String> = context.dataStore.data.map {
            it[prefKey] ?: default
        }
        return valueFlow.first()
    }
    /*
    suspend fun getEmailValue(context: Context, key: String, default: String = ""):String{
        val prefKey = stringPreferencesKey(key)
        val valueFlow: Flow<String> = context.dataStore.data.map {
            it[prefKey] ?: default
        }
        return valueFlow.first()
    }

    suspend fun getSenhaValue(context: Context, key: String, default: String = ""):String{
        val prefKey = stringPreferencesKey(key)
        val valueFlow: Flow<String> = context.dataStore.data.map {
            it[prefKey] ?: default
        }
        return valueFlow.first()
    }
     */

}
