package me.hib4.wejanganmadyo.domain.usecases.news

data class NewsUseCases(
    val getNews: GetNews,
    val searchNews: SearchNews,
    val insertArticle: InsertArticle,
    val getArticles: GetArticles,
    val getArticle: GetArticle,
    val deleteArticle: DeleteArticle,
)
