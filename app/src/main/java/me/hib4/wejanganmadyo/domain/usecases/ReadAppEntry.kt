package me.hib4.wejanganmadyo.domain.usecases

import kotlinx.coroutines.flow.Flow
import me.hib4.wejanganmadyo.domain.manger.LocalUserManger

class ReadAppEntry(
    private val localUserManger: LocalUserManger
) {
    operator fun invoke(): Flow<Boolean> {
        return localUserManger.readAppEntry()
    }
}