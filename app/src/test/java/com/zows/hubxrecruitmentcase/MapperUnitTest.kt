package com.zows.hubxrecruitmentcase

import com.zows.hubxrecruitmentcase.common.toEntity
import com.zows.hubxrecruitmentcase.data.model.Image
import com.zows.hubxrecruitmentcase.data.model.Plant
import com.zows.hubxrecruitmentcase.data.model.PlantCategoryResponse
import com.zows.hubxrecruitmentcase.data.model.QuestionResponse
import org.junit.Test

import org.junit.Assert.*

class MapperUnitTest {

    @Test
    fun `map QuestionResponse to QuestionEntity`() {
        val questionResponse = QuestionResponse(
            id = 1,
            title = "Sample Title",
            subtitle = "Sample Subtitle",
            imageUri = "image_uri",
            uri = "uri",
            order = 1
        )

        val result = questionResponse.toEntity()

        assertEquals(1, result.first().id)
        assertEquals("Sample Title", result.first().title)
        assertEquals("Sample Subtitle", result.first().subtitle)
        assertEquals("image_uri", result.first().imageUri)
        assertEquals("uri", result.first().uri)
        assertEquals(1, result.first().order)
    }

    @Test
    fun `map PlantCategoryResponse to PlantCategoryEntity list`() {
        val plantCategoryResponse = PlantCategoryResponse(
            data = listOf(
                Plant(
                    id = 1,
                    title = "Plant A",
                    image = Image(id = 101, url = "url_a")
                ),
                Plant(
                    id = 2,
                    title = "Plant B",
                    image = Image(id = 102, url = "url_b")
                )
            ),
            meta = null
        )

        val result = plantCategoryResponse.toEntity()

        assertEquals(2, result.size)
        assertEquals(1, result[0].id)
        assertEquals("Plant A", result[0].title)
        assertEquals("url_a", result[0].url)
        assertEquals(2, result[1].id)
        assertEquals("Plant B", result[1].title)
        assertEquals("url_b", result[1].url)
    }

}