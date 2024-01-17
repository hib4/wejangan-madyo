package me.hib4.wejanganmadyo.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import me.hib4.wejanganmadyo.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConverter {
    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(source: String): Source {
        return source.split(",").let { sources ->
            Source(sources[0], sources[1])
        }
    }
}