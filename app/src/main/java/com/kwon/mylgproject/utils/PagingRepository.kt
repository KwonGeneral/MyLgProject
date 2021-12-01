package com.kwon.mylgproject.utils

import androidx.paging.*
import com.kwon.mylgproject.api.ScheduleService
import com.kwon.mylgproject.data.ScheduleRecord
import com.kwon.mylgproject.db.RoomDataBase
import kotlinx.coroutines.flow.Flow


class PagedRepository(
    private val database: RoomDataBase
) {

    fun getSchedules(): Flow<PagingData<ScheduleRecord>> {
        val pagingSourceFactory = {
            database.scheduleService().getTestPage()
        }

        val pagingSourceFactory2 = {
            database.scheduleService().getTestPage3("title_100")
        }

        val testPagingSource = {
            TodoPagingSource(database.scheduleService())
        }

        return Pager(
            config = PagingConfig(pageSize = 100, enablePlaceholders = true),
            pagingSourceFactory = pagingSourceFactory2
        ).flow
    }
}


// 아래의 방식보다는 위의 방식으로 하는 것이 더 안정적이다.
// 검색 쿼리 또한, 리스트에 아이템을 넣을 때, 위의 방식이 더 안정적이다.
class TodoPagingSource(
    private val dao: ScheduleService
): PagingSource<Int, ScheduleRecord>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ScheduleRecord> {
        val page = params.key ?: 1
        return try {
            val items = dao.getTestPage2()
            LoadResult.Page(
                data = items,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (items.isEmpty()) null else page + (params.loadSize / 10)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ScheduleRecord>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}