package me.hib4.wejanganmadyo.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.hib4.wejanganmadyo.data.local.NewsDao
import me.hib4.wejanganmadyo.data.local.NewsDatabase
import me.hib4.wejanganmadyo.data.local.NewsTypeConverter
import me.hib4.wejanganmadyo.data.manger.LocalUserMangerImpl
import me.hib4.wejanganmadyo.data.remote.NewsApi
import me.hib4.wejanganmadyo.data.repository.NewsRepositoryImpl
import me.hib4.wejanganmadyo.domain.manger.LocalUserManger
import me.hib4.wejanganmadyo.domain.repository.NewsRepository
import me.hib4.wejanganmadyo.domain.usecases.appentry.AppEntryUseCases
import me.hib4.wejanganmadyo.domain.usecases.appentry.ReadAppEntry
import me.hib4.wejanganmadyo.domain.usecases.appentry.SaveAppEntry
import me.hib4.wejanganmadyo.domain.usecases.news.DeleteArticle
import me.hib4.wejanganmadyo.domain.usecases.news.GetArticle
import me.hib4.wejanganmadyo.domain.usecases.news.GetArticles
import me.hib4.wejanganmadyo.domain.usecases.news.GetNews
import me.hib4.wejanganmadyo.domain.usecases.news.InsertArticle
import me.hib4.wejanganmadyo.domain.usecases.news.NewsUseCases
import me.hib4.wejanganmadyo.domain.usecases.news.SearchNews
import me.hib4.wejanganmadyo.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application,
    ): LocalUserManger = LocalUserMangerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManger,
    ): AppEntryUseCases = AppEntryUseCases(
        saveAppEntry = SaveAppEntry(localUserManger),
        readAppEntry = ReadAppEntry(localUserManger)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao,
    ): NewsRepository = NewsRepositoryImpl(
        newsApi = newsApi,
        newsDao = newsDao
    )

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
    ): NewsUseCases = NewsUseCases(
        getNews = GetNews(newsRepository),
        searchNews = SearchNews(newsRepository),
        insertArticle = InsertArticle(newsRepository),
        getArticles = GetArticles(newsRepository),
        getArticle = GetArticle(newsRepository),
        deleteArticle = DeleteArticle(newsRepository)
    )

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application,
    ): NewsDatabase = Room.databaseBuilder(
        context = application,
        name = "news_db",
        klass = NewsDatabase::class.java,
    ).addTypeConverter(NewsTypeConverter())
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase,
    ): NewsDao = newsDatabase.newsDao
}