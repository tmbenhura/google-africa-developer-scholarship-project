package zw.co.mobility.gads.api

import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class GadsServiceTest {

    @Test
    fun gadsService_returnsLearningLeaders() {
        val learners = createGadsService().learningLeaders()?.execute()?.body()

        assertNotNull(learners)
        assertNotEquals(learners?.size, 0)
    }

    @Test
    fun gadsService_returnsSkillIQLeaders() {
        val leaders = createGadsService().skillIQLeaders()?.execute()?.body()

        assertNotNull(leaders)
        assertNotEquals(leaders?.size, 0)
    }
}