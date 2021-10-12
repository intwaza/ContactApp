package com.example.contactapp.di

import android.content.Context
import com.example.contactapp.database.ContactsDao
import com.example.contactapp.database.ContactsDatabase
import com.example.contactapp.models.Contact
import com.example.contactapp.repository.ContactsRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): ContactsDatabase{
        return ContactsDatabase.getDatabase(appContext)

    }
    @Singleton
    @Provides
    fun provideContactDao(database: ContactsDatabase):ContactsDao{
        return database.getContactsDao()
    }

    @Singleton
    @Provides
    fun provideContactRepository(contactsDao: ContactsDao):ContactsRepository{
        return ContactsRepository(contactsDao)
    }

//    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit{
//        var retrofit= Retrofit.Builder()
//            .baseUrl("http://13.244.243.129")
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//        return retrofit
//    }
//    @Provides
//    fun provideOkHttpClient(): OkHttpClient{
//        val client= OkHttpClient.Builder()
//            .addInterceptor(ChuckerInterceptor())
//    }
}