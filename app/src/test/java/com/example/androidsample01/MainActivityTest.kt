package com.example.androidsample01

import android.widget.Button
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.androidsample01.api.di.ApiModule
import com.example.androidsample01.api.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import okhttp3.Response
import org.junit.Assert
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import retrofit2.Retrofit
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior
import javax.inject.Singleton

@HiltAndroidTest
@Config(application = HiltTestApplication::class, manifest = Config.NONE)
@RunWith(RobolectricTestRunner::class)
@LooperMode(LooperMode.Mode.PAUSED)
@UninstallModules(ApiModule::class)
class MainActivityTest {

    @get:Rule(order = 0)
    var hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityScenarioRule: ActivityScenarioRule<MainActivity>
        = ActivityScenarioRule(MainActivity::class.java)

    @BeforeEach
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @Test
    fun sampleTest() {
        activityScenarioRule.scenario.onActivity {
            it.findViewById<Button>(R.id.btnLogin).performClick()
            Assert.assertTrue(true)
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
object TestModule {
    @Singleton
    @Provides
    fun provideApiService(): ApiService =
        MockRetrofit.Builder(Retrofit.Builder().build())
            .networkBehavior(NetworkBehavior.create())
            .build()
            .create(ApiService::class.java)
            .returningResponse(Response.Builder().code(200).build())

}
