package me.hib4.wejanganmadyo.domain.usecases.appentry

import me.hib4.wejanganmadyo.domain.manger.LocalUserManger

class SaveAppEntry(
    private val localUserManger: LocalUserManger
) {
    suspend operator fun invoke() {
        localUserManger.saveAppEntry()
    }
}